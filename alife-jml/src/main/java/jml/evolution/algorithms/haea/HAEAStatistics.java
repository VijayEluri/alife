/*
 * <copyright>
 *  Copyright 2004-2005 (Jonatan Gomez Solutions JG-Sol)
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the JML Open Source License as published by
 *  UN-Data Mining Group on the JML Open Source Website
 *  (http://dis.unal.edu.co/profesores/jgomez/projects/jml/index.htm).
 *
 *  THE JML SOFTWARE AND ANY DERIVATIVE SUPPLIED BY LICENSOR IS
 *  PROVIDED "AS IS" WITHOUT WARRANTIES OF ANY KIND, WHETHER EXPRESS OR
 *  IMPLIED, INCLUDING (BUT NOT LIMITED TO) ALL IMPLIED WARRANTIES OF
 *  MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE, AND WITHOUT
 *  ANY WARRANTIES AS TO NON-INFRINGEMENT.  IN NO EVENT SHALL COPYRIGHT
 *  HOLDER BE LIABLE FOR ANY DIRECT, SPECIAL, INDIRECT OR CONSEQUENTIAL
 *  DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE OF DATA OR PROFITS,
 *  TORTIOUS CONDUCT, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
 *  PERFORMANCE OF THE JML SOFTWARE.
 *
 * </copyright>
 */
package jml.evolution.algorithms.haea;

import java.util.Vector;

import jml.basics.Result;
import jml.evolution.PopulationStatistics;


/**
 * <p>Title: HAEA</p>
 * <p>Description: The Hybrid Adaptive Evolutionary Algorithm proposed by Gomez in
 * "Self Adaptation of Operator Rates in Evolutionary Algorithms", Proceedings of Gecco 2004.</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class HAEAStatistics extends PopulationStatistics implements Cloneable {
  /**
   * Operators rates for the best individual
   */
  protected double[] opers = null;
  /**
   * Average operator rates
   */
  protected double[] avg_opers = null;

  /**
   * Constructor: Creates a empty CEA statistics information
   * @param opers_number NUmber of genetic operators rates for each individual in the population
   * @param popStat The population statistical information
   */
  public HAEAStatistics( int opers_number, PopulationStatistics popStat ) {
    super(popStat);
    opers = new double[opers_number];
    avg_opers = new double[opers_number];
  }

  /**
   * Constructor: Creates a CEA statistics using the information from the population
   * and the operators rate information
   * @param rates Genetic operators rates for each individual in the population
   * @param popStat The population statistical information
   */
  public HAEAStatistics( Vector rates, PopulationStatistics popStat ) {
    super(popStat);
    if( rates != null && rates.size() > 0 ){
      int bestIndex = -1;
      if( popStat != null ){ bestIndex = popStat.getBestIndex(); }
      int opers_number = ((double[])rates.get(0)).length;
      opers = new double[opers_number];
      avg_opers = new double[opers_number];
      for( int j=0; j<opers_number; j++ ){
        if( bestIndex != -1 ){
          opers[j] = ((double[])rates.get(bestIndex))[j];
        }else{
          opers[j] = 0.0;
        }
        avg_opers[j] = 0.0;
        for( int i=0; i<rates.size(); i++ ){
          avg_opers[j] += ((double[])rates.get(i))[j];
        }
        avg_opers[j] /= rates.size();
      }
    }
  }

  /**
   * Copy contructor
   * @param source The Statistical information to be cloned
   */
  public HAEAStatistics( PopulationStatistics source ) {
    super( source );
    if( source instanceof HAEAStatistics && ((HAEAStatistics)source).opers != null ){
      HAEAStatistics hsource = (HAEAStatistics)source;
      opers = new double[ hsource.opers.length ];
      avg_opers = new double[ hsource.avg_opers.length ];
      for( int i =0; i<opers.length; i++ ){
        opers[i] = hsource.opers[i];
        avg_opers[i] = hsource.avg_opers[i];
      }
    }
  }

  /**
   * Creates an empty statistical information
   * @return An empty population statistical information
   */
    public Result create(){
      int n = 0;
      if( opers != null ){ n = opers.length; }
      return new HAEAStatistics( n, (PopulationStatistics)super.create() );
    }

  /**
   * Clones the statistical information
   * @return A cloned statistical information
   */
  public Object clone(){  return new HAEAStatistics( this );  }

  /**
   * Converts the statistical information to a string
   * @return A string with the population and transformation statistical information
   */
  public String toString(){
    StringBuffer sb = new StringBuffer();
    sb.append( super.toString() );
    if( opers != null ){
      for( int i =0; i<opers.length; i++ ){
        sb.append(' ');
        sb.append( opers[i] );
      }
      for( int i =0; i<avg_opers.length; i++ ){
        sb.append(' ');
        sb.append( avg_opers[i] );
      }
    }
    return sb.toString();
  }

/**
 * Add the statistics object with the given statistic object.
 * @param _other The statistic object that will be added to the Statistic object
 */
  public void sum( Result _other ){
    super.sum( _other );
    if( _other instanceof HAEAStatistics ){
      HAEAStatistics other = (HAEAStatistics)_other;
      if( opers != null ){
        for( int i =0; i<opers.length; i++ ){
          opers[i] += other.opers[i];
        }
        for( int i =0; i<avg_opers.length; i++ ){
          avg_opers[i] += other.avg_opers[i];
        }
      }
    }
  }

/**
 * Substracts the statistics object with the given statistic object.
 * @param _other The statistic object that will be substracted from the Statistic object
 */
  public void substract( Result _other ){
    super.substract( _other );
    if( _other instanceof HAEAStatistics ){
      HAEAStatistics other = (HAEAStatistics)_other;
      if( opers != null ){
        for( int i =0; i<opers.length; i++ ){
          opers[i] -= other.opers[i];
        }
        for( int i =0; i<avg_opers.length; i++ ){
          avg_opers[i] -= other.avg_opers[i];
        }
      }
    }
  }

/**
 * Divide the statistical object by the number given.
 * @param n The number used to divide the object
 */
  public void divide( double n ){
    super.divide(n);
    if( opers != null ){
      for( int i =0; i<opers.length; i++ ){
        opers[i] /= n;
      }
      for( int i =0; i<avg_opers.length; i++ ){
        avg_opers[i] /= n;
      }
    }
  }

/**
 * multiply the statistical object by itself.
 */
  public void square(){
    super.square();
    if( opers != null ){
      for( int i =0; i<opers.length; i++ ){
        opers[i] *= opers[i];
      }
      for( int i =0; i<avg_opers.length; i++ ){
        avg_opers[i] *= avg_opers[i];
      }
    }
  }

  /**
   * get the square root of the statistical object.
   */
  public void sqrt(){
    super.sqrt();
    if( opers != null ){
      for( int i =0; i<opers.length; i++ ){
        opers[i] = Math.sqrt( opers[i] );
      }
      for( int i =0; i<avg_opers.length; i++ ){
        avg_opers[i] = Math.sqrt( avg_opers[i] );
      }
    }
  }
}
