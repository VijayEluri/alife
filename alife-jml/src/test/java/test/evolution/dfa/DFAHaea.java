package test.evolution.dfa;

//import jml.coral.*;
//import jml.cml.function.*;
import jml.evolution.Individual;
import jml.evolution.Operator;
import jml.evolution.Population;
import jml.evolution.PopulationStatistics;
import jml.evolution.Selection;
import jml.evolution.Transformation;
import jml.evolution.algorithms.haea.HAEA;


public class DFAHaea extends DFAEvolver {
  protected Transformation transformation;
  protected boolean incremental;
  protected int PACKAGE_SIZE;
  protected int POP_SIZE;
//  protected DFAPrintThread incThread;

  public DFAHaea( String trainFile, String testFile, boolean _smartLabeling,
                  double _noise,
                  Operator[] operators, Selection selection, int _POP_SIZE,
                  boolean sort, boolean _incremental, int _PACKAGE_SIZE_PERCENTAGE ){
    super( trainFile, testFile, _smartLabeling, _noise);
    for( int i=0; i<operators.length; i++ ){
      operators[i].setEnvironment(environment);
    }
    selection.setEnvironment(environment);
    transformation = getTransformation(operators, selection);
    incremental = _incremental && _PACKAGE_SIZE_PERCENTAGE <= 50;
    if( sort ){ Sequence.sort(environment.train_set); }
    if( _PACKAGE_SIZE_PERCENTAGE == 0 ){
      PACKAGE_SIZE = 1;
    }else{
      PACKAGE_SIZE = environment.train_set.length * _PACKAGE_SIZE_PERCENTAGE /
          100;
    }
    POP_SIZE = _POP_SIZE;
  }

  protected Transformation getTransformation( Operator[] operators,
                                           Selection selection ){
    return new HAEA( operators, selection );
  }

  protected double f;
  protected Population incremental_iteration( Population p, DFAFitness fitness,
                                              double min_fitness,
                                              long MAX_RECORD_EVALS ){
    int n = fitness.sequence_set.length / PACKAGE_SIZE;
    int number_of_intervals = n-1;
    DFAPrintThread incThread =
        new DFAPrintThread(this, thread.running_time / (2L*number_of_intervals), 1);
    incThread.start();
    PopulationStatistics stat;
    long start_iter = fitness.big_count;
    do{
      p = transformation.apply(p);
      stat = (PopulationStatistics) transformation.statistics(p);
      f = stat.best / fitness.PACKAGE_SIZE;
      if( thread.print ){ print( stat.getBest() ); }
    }while( f < min_fitness && (fitness.big_count - start_iter) < MAX_RECORD_EVALS
            && !incThread.print );
    incThread.go = false;
    return p;
  }

  protected Population getIncremental( long MAX_ITER ){
    Population p;
    if( incremental ){
      double max_fitness = 1.0 - noise;
      DFAFitness fitness = (DFAFitness)environment.getFitness();
      int train_size = fitness.sequence_set.length;
      long MAX_ITER_2 = MAX_ITER/2;
      long RECORDS_PER_PACKAGE = MAX_ITER_2 * PACKAGE_SIZE / train_size;
      double old_fitness = 0.75;
      fitness.PACKAGE_SIZE = PACKAGE_SIZE;
      p = new Population( environment, POP_SIZE );
      int n = fitness.sequence_set.length / PACKAGE_SIZE;
      int i=0;
      while(fitness.PACKAGE_SIZE < train_size && fitness.big_count<MAX_ITER) {
        double min_fitness = max_fitness;
        if( old_fitness < max_fitness ){
          min_fitness = old_fitness +
              (max_fitness - old_fitness) / (n - i);
        }
        p = incremental_iteration(p, fitness, Math.min(min_fitness, max_fitness),
                                  RECORDS_PER_PACKAGE);
        old_fitness = f;
        fitness.PACKAGE_SIZE += PACKAGE_SIZE;
        if (fitness.PACKAGE_SIZE > train_size) {
          fitness.PACKAGE_SIZE = train_size;
        }
      }
    }else{
      p = new Population(environment, POP_SIZE);
    }
    return p;
  }

  public DFA evolve( long MAX_ITER ){
    double max_fitness = 1.0 - noise;
    Population p = getIncremental( MAX_ITER );
//    System.out.println();
    DFAFitness fitness = (DFAFitness)environment.getFitness();
    int train_size = fitness.sequence_set.length;
    fitness.PACKAGE_SIZE = fitness.sequence_set.length;
    p.evalFitness();
    boolean flag = true;
    while( fitness.big_count<MAX_ITER && flag ){
      p = transformation.apply(p);
      PopulationStatistics stat = (PopulationStatistics) transformation.statistics(p);
      if( thread.print ){
        double x = stat.best/train_size;
//        System.out.println(x);
        sb.append(" " + x + "\n");
        thread.print = false;
      }
      flag = (stat.best < max_fitness*train_size && thread.go);
    }
    thread.go = false;
    Individual ind = ((PopulationStatistics)transformation.statistics(p)).getBest();
    double x = ind.evalFitness(environment)/ fitness.PACKAGE_SIZE;
//    System.out.println(x);
    sb.append(" " + x + "\n");
    thread.count++;
    return (DFA)ind.getThing();
  }
}
