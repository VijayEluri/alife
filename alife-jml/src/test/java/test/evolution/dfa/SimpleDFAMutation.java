package test.evolution.dfa;
import jml.evolution.Environment;
import jml.evolution.operators.ArityOne;
import jml.random.UniformNumberGenerator;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2004
 * Company:
 * @author Jonatan Gomez
 * @version 1.0
 */

public class SimpleDFAMutation extends ArityOne{
  protected double mutation_rate = 0.0;
  protected boolean smartLabeling;

  public SimpleDFAMutation( Environment _environment, boolean _smartLabeling ){
    super( _environment );
    smartLabeling = _smartLabeling;
  }

  /**
   * Constructor: Default constructor
   */
  public SimpleDFAMutation( Environment _environment, boolean _smartLabeling,
                          double _mutation_rate ){
    super( _environment );
    mutation_rate = _mutation_rate;
    smartLabeling = _smartLabeling;
  }

  /**
   * Flips a bit in the given genome
   * @param gen Genome to be modified
   * @return Index of the flipped bit
   */
  public Object apply(Object gen) {

    int count = 0;

    DFA dfa = (DFA)gen;
    int n = dfa.size();
    int[][] transition_table = dfa.transition_table;

    double rate = mutation_rate;
    if( mutation_rate == 0.0 ){  rate = 1.0/n;   }

    UniformNumberGenerator g = new UniformNumberGenerator();
    UniformNumberGenerator g1 = new UniformNumberGenerator(n-1);
    int j;
    for(int i=0; i<n; i++ ){
      for( int k=0; k<2; k++ ){
        if(g.newDouble() < rate){
          j = g1.newInt();
          if( j >= transition_table[k][i] ) j++;
          transition_table[k][i] = j;
          count++;
        }
      }
    }

    if( !smartLabeling ){
      for(int i=0; i<n; i++ ){
        if(g.newDouble() < rate){
          dfa.output[i] = 1 - dfa.output[i];
          count++;
        }
      }
    }

    if( count == 0 ){
      g = new UniformNumberGenerator( 2 );
      g1 = new UniformNumberGenerator(n);
      int input = g.newInt();
      int state = g1.newInt();
      transition_table[input][state] = g1.newInt();
      count = 1;
    }

    return new Integer(count);
  }
}
