package test.evolution.dfa;

import jml.random.UniformNumberGenerator;
import jml.util.IntUtil;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2004
 * Company:
 * @author Jonatan Gomez
 * @version 1.0
 */

public class DFA {
  public static int NUMBER_SYMBOLS = 2;
  public static int MAX_NUMBER_STATES = 30;
  public static int MIN_NUMBER_STATES = 30;
  public static int BITS_SYMBOL = 1;
  public static int BITS_STATE = 6;
//  public static int GENE_SIZE = 14;
  public static int GENE_SIZE = IntUtil.INTEGER_SIZE;

//  public static void init( int SYMBOLS, int STATES ){
//    NUMBER_SYMBOLS = SYMBOLS;
//    MAX_NUMBER_STATES = STATES;
//    BITS_SYMBOL = Math.l
//  }

  public int[] output = null;
  public int[][] transition_table = null;
  public int[][] used_transition = null;
  public int out = 0;
  public int state = 0;


  public static int getValue( int n, int m ){
    n = (n<<1) >>> 1;
    n %= m;
//    n = Math.abs(n) % m;
//    if( n < 0 ){
//      n = -n;
//      n %= m;
//    }
    return n;
  }

  public DFA( int n ){
    output = new int[n];
    transition_table = new int[NUMBER_SYMBOLS][n];
    UniformNumberGenerator g = new UniformNumberGenerator( n );
    UniformNumberGenerator g1 = new UniformNumberGenerator( 2 );
    for( int i=0; i<n; i++ ){
      transition_table[0][i] = g.newInt();
      transition_table[1][i] = g.newInt();
      output[i] = g1.newInt();
    }
  }

  public DFA( DFA source ){
    int n = source.output.length;
    output = new int[n];
    transition_table = new int[NUMBER_SYMBOLS][n];
    for( int i=0; i<n; i++ ){
      transition_table[0][i] = source.transition_table[0][i];
      transition_table[1][i] = source.transition_table[1][i];
      output[i] = source.output[i];
    }
  }


  public Object clone(){
    return new DFA( this );
  }

  public int size(){ return output.length; }


  public int simulate( int[] input ){
//    used_transition = new int[input.length][2];
    state = 0;
    for( int i=0; i<input.length; i++ ){
//      used_transition[i][0] = state;
//      used_transition[i][1] = input[i];
      state = transition_table[input[i]][state];
    }
    out = output[state];
    return out;
  }

  public void getPrediction( Sequence[] sequence_set, int[] prediction ){
    for( int i=0; i<sequence_set.length; i++ ){
      prediction[i] = simulate( sequence_set[i].data );
    }
  }

  public String toString(){
    int size = transition_table[0].length;
    StringBuffer sb = new StringBuffer();
    for( int i=0; i<size; i++ ){
      sb.append(i);
      sb.append(" ");
      sb.append(transition_table[0][i]);
      sb.append(" ");
      sb.append(transition_table[1][i]);
      sb.append(" ");
      sb.append(output[i]);
      sb.append("\n");
    }
    return sb.toString();
  }
}
