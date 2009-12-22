package test.evolution.dfa;

//import jml.coral.*;
//import jml.cml.function.*;
import java.io.FileReader;
import java.io.FileWriter;

import jml.evolution.Individual;
import jml.parser.Token;
import jml.parser.Tokenizer;


public abstract class DFAEvolver {
  protected double noise = 0.0; // percentage
  protected StringBuffer sb = new StringBuffer();

  protected DFAEnvironment environment;
  protected DFAPrintThread thread = null;

  public DFAEvolver(String trainFile, String testFile, boolean smartLabeling, double _noise) {
    environment = new DFAEnvironment( trainFile, testFile, smartLabeling );
    noise = _noise;
  }

  public void initThread( long running_time ){
    if( thread  != null ){
      thread.go = false;
    }
    thread = new DFAPrintThread( this, running_time, 100 );
    thread.start();
  }

  public double print( Individual ind ){
    DFAFitness fitness = (DFAFitness)environment.getFitness();
    int m = fitness.PACKAGE_SIZE;
    int train_size = environment.train_set.length;
    fitness.PACKAGE_SIZE = environment.train_set.length;
    double x = ind.evalFitness( environment ) / train_size;
//    System.out.println(x);
    fitness.PACKAGE_SIZE = m;
    ind.evalFitness( environment );
    sb.append(" " + x + "\n");
    thread.print = false;
    return x;
  }

  public abstract DFA evolve(  long MAX_ITER );

  public void saveStat( String fileName, String truthLabelFile, DFA a ){
    DFAFitness fitness = (DFAFitness)environment.getFitness();
    fitness.prediction = new int[environment.test_set.length];
    a.getPrediction(environment.test_set, fitness.prediction);
    try{
      FileReader reader = new FileReader( truthLabelFile );
      Tokenizer tokenizer = new Tokenizer(reader);
      Token t = tokenizer.nextToken();
      int size = (int)t.nval;
      int[] real = new int[size];
      for( int i=0; i<size; i++ ){
        t = tokenizer.nextToken();
        real[i] = (int)t.nval;
      }
      double acc = fitness.evaluate(real, fitness.prediction);
      FileWriter file = new FileWriter( fileName );
      file.write(""+acc+"\n********\n");
      file.write(" "+thread.count+"\n" );
      file.write(sb.toString());
      file.write(a.toString());
      file.close();
    }catch( Exception e ){
      e.printStackTrace();
    }
  }
}
