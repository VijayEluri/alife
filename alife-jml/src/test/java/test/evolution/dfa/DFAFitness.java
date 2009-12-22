package test.evolution.dfa;

import jml.evolution.Fitness;
import jml.evolution.Individual;


/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2004
 * Company:
 * @author Jonatan Gomez
 * @version 1.0
 */

public class DFAFitness extends Fitness {
  protected boolean smartLabeling = true;
  public static long big_count = 0L;
  public static int PACKAGE_SIZE = 100;
  public Sequence[] sequence_set=null;
  public int[] prediction=null;
  public int[] real=null;
  public int[][] state_label = null;
  public DFAFitness( Sequence[] _sequence_set, boolean _smartLabeling ) {
    sequence_set = _sequence_set;
    prediction = new int[sequence_set.length];
    real = getReal( sequence_set );
    smartLabeling = _smartLabeling;
  }

  public static int[] getReal( Sequence[] sequence_set ){
    int[] real = new int[sequence_set.length];
    for( int i=0; i<sequence_set.length; i++ ){
      real[i] = sequence_set[i].type;
    }
    return real;
  }

  public static double evaluate( int[] real, int[] prediction ){
    int count = 0;
    for( int i=0; i<real.length; i++ ){
      if( real[i] == prediction[i] ){
        count++;
      }
    }
    return count;
  }


  public double evaluate(Individual obj) {
    big_count += PACKAGE_SIZE;
    DFA dfa = (DFA)obj.getThing();
    int n = dfa.size();

    int count = 0;
    if( smartLabeling ){
      state_label = new int[2][n];
      for (int i = 0; i < n; i++) {
        state_label[0][i] = 0;
        state_label[1][i] = 0;
      }

      for (int i = 0; i < PACKAGE_SIZE; i++) {
        dfa.simulate(sequence_set[i].data);
        state_label[sequence_set[i].type][dfa.state]++;
      }


      for (int i = 0; i < n; i++) {
        if (state_label[0][i] > state_label[1][i]) {
          dfa.output[i] = 0;
          count += state_label[0][i];
        }
        else {
          if (state_label[0][i] < state_label[1][i]) {
            dfa.output[i] = 1;
            count += state_label[1][i];
          }
          else {
            count += state_label[dfa.output[i]][i];
          }
        }
      }
    }else{
      for (int i = 0; i < PACKAGE_SIZE; i++) {
        dfa.simulate(sequence_set[i].data);
        if( sequence_set[i].type == dfa.output[dfa.state] ){
          count++;
        }
      }
    }
    return( count );
  }
}
