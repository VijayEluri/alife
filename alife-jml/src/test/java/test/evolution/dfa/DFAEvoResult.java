package test.evolution.dfa;
import java.io.BufferedReader;
import java.io.FileReader;

import jml.basics.Result;

class DFAEvoResult extends Result implements Cloneable{
  protected double[] best = null;
  protected double accuracy = 0.0;

  public DFAEvoResult(){
    best = new double[0];
  }

  public DFAEvoResult( double[] _best, double _accuracy){
    best = _best;
    accuracy = _accuracy;
  }

  public DFAEvoResult( String fileName ) throws Exception{
    BufferedReader br = new BufferedReader( new FileReader( fileName ) );
    String line = br.readLine();
    accuracy = Double.parseDouble( line );
    br.readLine();
    line = br.readLine();
    while( line.charAt(0) == ' ' ){
      line = line.substring(1);
    }
    int SIZE = Integer.parseInt( line );
    best = new double[SIZE];
    for( int i=0; i<SIZE; i++ ){
      line = br.readLine();
      while( line.charAt(0) == ' ' ){
        line = line.substring(1);
      }
      try{
        best[i] = Double.parseDouble(line);
      }catch(Exception ex ){ best[i] = best[i-1]; }
    }
  }

  /**
   * Copy contructor
   * @param source The Statistical information to be cloned
   */
  public DFAEvoResult( DFAEvoResult source ) {
    accuracy = source.accuracy;
    int SIZE = source.best.length;
    best = new double[SIZE];
    for( int i=0; i<SIZE; i++ ){
      best[i] = source.best[i];
    }
  }

  /**
   * Creates an empty statistical information
   * @return An empty population statistical information
   */
  public Result create(){
    return new DFAEvoResult();
  }

  /**
   * Clones the statistical information
   * @return A cloned statistical information
   */
  public Object clone(){  return new DFAEvoResult( this );  }

  /**
   * Converts the population statistical information to a string
   * @return A string with the population statistical information
   */
  public String toString(){
    StringBuffer sb = new StringBuffer();
    sb.append(accuracy);
    sb.append('\n');
    int SIZE = best.length;
    sb.append(""+SIZE+"\n");
    for( int i=0; i<SIZE; i++ ){
      sb.append( best[i] );
      sb.append('\n');
    }
    return sb.toString();
  }

  public void resize( DFAEvoResult other ){
    if( best.length < other.best.length ){
      double[] b1 = new double[other.best.length];
      for( int i=0; i<best.length; i++ ){
        b1[i] = best[i];
      }
      for( int i=best.length; i<other.best.length; i++ ){
        b1[i] = best[best.length-1];
      }
      best = b1;
    }else{
      if( other.best.length < best.length ){
        double[] b1 = new double[best.length];
        for( int i=0; i<other.best.length; i++ ){
          b1[i] = other.best[i];
        }
        for( int i=other.best.length; i<best.length; i++ ){
          b1[i] = other.best[other.best.length-1];
        }
        other.best = b1;
      }
    }
  }

/**
 * Add the statistics object with the given statistic object.
 * @param _other The statistic object that will be added to the Statistic object
 */
  public void sum( Result _other ){
    if( _other instanceof DFAEvoResult ){
      DFAEvoResult other = (DFAEvoResult)_other;
      resize( other );
      accuracy += other.accuracy;
      int SIZE = best.length;
      for( int i=0; i<SIZE; i++ ){
        best[i] += other.best[i];
      }
    }
  }

/**
 * Substracts the statistics object with the given statistic object.
 * @param _other The statistic object that will be substracted from the Statistic object
 */
  public void substract( Result _other ){
    if( _other instanceof DFAEvoResult ){
      DFAEvoResult other = (DFAEvoResult)_other;
      resize( other );
      accuracy -= other.accuracy;
      int SIZE = best.length;
      for( int i=0; i<SIZE; i++ ){
        best[i] -= other.best[i];
      }
    }
  }

/**
 * Divide the statistical object by the number given.
 * @param n The number used to divide the object
 */
  public void divide( double n ){
    accuracy /= n;
    int SIZE = best.length;
    for( int i=0; i<SIZE; i++ ){
      best[i] /= n;
    }
  }

/**
 * multiply the statistical object by itself.
 */
  public void square(){
    accuracy *= accuracy;
    int SIZE = best.length;
    for( int i=0; i<SIZE; i++ ){
      best[i] *= best[i];
    }
  }

  /**
   * get the square root of the statistical object.
   */
  public void sqrt(){
    accuracy = Math.sqrt(accuracy);
    int SIZE = best.length;
    for( int i=0; i<SIZE; i++ ){
      best[i] = Math.sqrt(best[i]);
    }
  }
}

