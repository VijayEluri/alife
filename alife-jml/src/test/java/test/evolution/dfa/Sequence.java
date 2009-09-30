package test.evolution.dfa;

import java.io.FileReader;

import jml.parser.Token;
import jml.parser.Tokenizer;
import jml.random.UniformNumberGenerator;
import jml.util.sort.MergeSort;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2004
 * Company:
 * @author Jonatan gomez
 * @version 1.0
 */

public class Sequence {
  public int type = 1;
  public int[] data = null;

  public Sequence( Tokenizer tok ) {
    Token token = tok.nextToken();
    type = (int)token.nval;
    token = tok.nextToken();
    int length = (int)token.nval;
    data = new int[length];
    for( int i=0; i<length; i++ ){
      token = tok.nextToken();
      data[i] = (int)token.nval;
    }
  }

  public int length(){ return data.length; }

//  public Data getData(){
//    double[] x = new double[data.length];
//    for( int i=0; i<x.length; i++ ){
//      x[i] = data[i];
//    }
//    return new Data( x, type );
//  }

  public String toString(){
    StringBuffer sb = new StringBuffer();
    sb.append(type);
    sb.append(" ");
    sb.append(length());
    sb.append(" ");
    for( int i=0; i<length(); i++ ){
      sb.append(data[i]);
      sb.append(" ");
    }
    return sb.toString();
  }

  public static void perm( Sequence[] sequence_set ){
    int n = sequence_set.length;
    UniformNumberGenerator g = new UniformNumberGenerator( n );
    int j, k;
    Sequence temp;
    for( int i=0; i<n; i++ ){
      j = g.newInt();
      k = g.newInt();
      temp = sequence_set[j];
      sequence_set[j] = sequence_set[k];
      sequence_set[k] = temp;
    }
  }

  public static void sort( Sequence[] sequence_set ){
    perm( sequence_set );
    MergeSort sort = new MergeSort();
    sort.low2high(sequence_set, new SequenceOrder() );
    int n = sequence_set.length;
    for( int i=0; i<n; i++ ){
      for( int j=i+1; j<n; j++ ){
        if( sequence_set[i].length() > sequence_set[j].length() ){
          Sequence t = sequence_set[i];
          sequence_set[i] = sequence_set[j];
          sequence_set[j] = t;
        }
      }
    }
  }

  public static Sequence[] readSequenceSet( String fileName, boolean train ){
    Sequence[] v = null;
    try{
      FileReader reader = new FileReader( fileName );
      Tokenizer tok = new Tokenizer( reader );
      Token token = tok.nextToken();
      int n = (int)token.nval;
      // for the number 2
      tok.nextToken();
      // read the nominal size of the target automaton
      if( train ){
        token = tok.nextToken();
        int target_size = (int)token.nval;
        DFA.MIN_NUMBER_STATES =  target_size;
        DFA.MAX_NUMBER_STATES =  target_size;
//        DFA.MAX_NUMBER_STATES = 2 * target_size;
      }
      // reading each sequence
      v = new Sequence[n];
      for( int i=0; i<n; i++ ){
       v[i] = new Sequence( tok );
      }
      reader.close();
    }catch( Exception e ){ e.printStackTrace(); }
    return v;
  }

  public static void main( String[] argv ){
    Sequence[] v = readSequenceSet( "c://Jonatan/projects/data/gecco-2004/gecco-train-10.txt", true );
    System.out.println( v.length );
    for( int i=0; i<v.length; i++ ){
      System.out.println(v[i].toString());
    }
  }
}
