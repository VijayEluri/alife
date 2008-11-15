package test.evolution.dfa;

//import jml.coral.*;
//import jml.cml.function.*;
import jml.evolution.Individual;
import jml.evolution.Operator;
import jml.evolution.Population;
import jml.evolution.PopulationStatistics;
import jml.evolution.Selection;
import jml.evolution.Transformation;
import jml.evolution.algorithms.HillClimbing;
import jml.evolution.algorithms.haea.HAEA;
import jml.evolution.operators.ArityOne;


public class DFASmartLabeling extends DFAEvolver {
  protected Transformation transformation;
  protected long MAX_ITER_PER_DFA;

  public DFASmartLabeling( String trainFile, String testFile, double _noise,
                           Operator[] operators, Selection selection,
                           long _MAX_ITER_PER_DFA ){
    super( trainFile, testFile, true, _noise);
    selection.setEnvironment(environment);
    if( operators.length == 1 ){
      ArityOne operator = (ArityOne)operators[0];
      operator.setEnvironment(environment);
      transformation = new HillClimbing(operator, selection);
    }else{
      for( int i=0; i<operators.length; i++ ){
        operators[i].setEnvironment(environment);
      }
      transformation = new HAEA( operators, selection );
    }
    MAX_ITER_PER_DFA = _MAX_ITER_PER_DFA;
  }

  public DFA evolve(  long MAX_ITER ){
    int POP_SIZE = 1;
    double max_fitness = 1.0 - noise;
    Population p = new Population(environment, POP_SIZE);
    DFAFitness fitness = (DFAFitness)environment.getFitness();
    int train_size = fitness.sequence_set.length;
    fitness.PACKAGE_SIZE = fitness.sequence_set.length;
    p.evalFitness();
    PopulationStatistics stat = p.statistics();
    Individual best = stat.getBest();
    Individual currentBest = stat.getBest();
    boolean flag = true;
    long inner_count = 0;
    while( fitness.big_count<MAX_ITER && flag ){
      p = transformation.apply(p);
      stat = p.statistics();
      Individual newBest = stat.getBest();
      if( thread.print ){ print( newBest ); }
      if( newBest.getFitness() > currentBest.getFitness() ){
        currentBest = newBest;
        inner_count = 0;
      }else{
        inner_count++;
        if( inner_count == MAX_ITER_PER_DFA ){
          if( currentBest.getFitness() > best.getFitness() ){
            best = currentBest;
          }
          p = new Population(environment, POP_SIZE);
          stat = p.statistics();
          currentBest = stat.getBest();
          inner_count = 0;
        }
      }
      flag = (stat.best < max_fitness*train_size && thread.go);
    }
    System.out.println( fitness.big_count );
    thread.go = false;
    double x = best.evalFitness(environment)/ fitness.PACKAGE_SIZE;
    System.out.print(" "+x);
    sb.append(" " + x + "\n");
    thread.count++;
    return (DFA)best.getThing();
  }
}
