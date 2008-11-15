package test.evolution.dfa;
import java.util.Vector;

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

public class SpecialDFAMutation extends ArityOne{
  protected double mutation_rate = 0.0;
  protected boolean smartLabeling;

  public SpecialDFAMutation( Environment _environment, boolean _smartLabeling ){
    super( _environment );
    smartLabeling = _smartLabeling;
  }

  /**
   * Constructor: Default constructor
   */
  public SpecialDFAMutation( Environment _environment, boolean _smartLabeling,
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

    int[] counter = new int[n];
    for( int i=0; i<n; i++ ){ counter[i] = 0; }
    for(int i=0; i<n; i++ ){
      for( int k=0; k<2; k++ ){
        counter[transition_table[k][i]]++;
      }
    }

    int min = 2*n;
    for( int i=0; i<n; i++ ){
      if( counter[i] < min ){ min = counter[i]; }
    }
    Vector v = new Vector();
    for( int i=0; i<n; i++ ){
      if( counter[i] == min ){ v.add( new Integer(i) );  }
    }

    UniformNumberGenerator g = new UniformNumberGenerator( 2 );
    UniformNumberGenerator g1 = new UniformNumberGenerator(n);
    UniformNumberGenerator g2 = new UniformNumberGenerator(v.size());

    int input = g.newInt();
    int state = g1.newInt();
    int new_state = ((Integer)v.get(g2.newInt())).intValue();

    transition_table[input][state] = new_state;

    if( !smartLabeling ){
      for(int i=0; i<n; i++ ){
        if(g.newDouble() < rate){
          dfa.output[i] = 1 - dfa.output[i];
          count++;
        }
      }
    }

    return new Integer(count);
  }
}
