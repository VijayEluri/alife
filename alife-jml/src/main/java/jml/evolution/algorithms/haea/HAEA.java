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

import java.util.Enumeration;
import java.util.Vector;

import jml.basics.Cloner;
import jml.basics.Result;
import jml.evolution.Environment;
import jml.evolution.Individual;
import jml.evolution.Operator;
import jml.evolution.Population;
import jml.evolution.Selection;
import jml.evolution.Transformation;
import jml.evolution.selections.Elitism;
import jml.random.NumberGenerator;
import jml.random.WeightedNumberGenerator;

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
public class HAEA extends Transformation {

  /**
   * Set of genetic operators that are used by CEA for evolving the solution chromosomes
   */
  protected Operator[] operators = null;

  /**
   * The selection scheme between parent-children
   */
   protected Selection selection = null;

  /**
   * Population of operator rates
   */
  protected Vector rates = new Vector();

  /**
   * Constructor: Creates a CEA transformation function that uses proportional
   * selection between parent-children
   * @param _operators Genetic operators used to evolve the solution
   */
  public HAEA(Operator[] _operators){
    operators = _operators;
    if( operators != null && operators.length > 0 ){
      selection = new Elitism(operators[0].getEnvironment(), 1, false, 1.0, 0.0);
    }else{
      selection = null;
    }
  }

  /**
   * Constructor
   * @param _operators Genetic operators used to evolve the solution
   * @param _selection The selection scheme between parent-children
   */
  public HAEA(Operator[] _operators, Selection _selection) {
    operators = _operators;
    selection = _selection;
  }

  /**
   * Init the internal state of transformation
   */
  public void init(){
    int n = operators.length;
    for( int i=0; i<rates.size(); i++ ){
      double[] tempRates = new double[n];
      for( int j=0; j<n; j++ ){  tempRates[j] = NumberGenerator.random();  }
      normalize( tempRates );
      rates.add( tempRates  );
    }
  }


  /**
   * Normalize the given vector of reals
   * @param r the vector to be normalized
   */
  protected void normalize( double[] r ){
    double total = 0.0;
    int n = r.length;
    for( int i=0; i<n; i++ ){  total += r[i];  }
    for( int i=0; i<n; i++ ){  r[i] /= total;  }
  }

  /**
   * Select an operator to be applied according to the rates encoded in the individual
   * @param x Rates to be taken into account
   * @return Index of the choosen operator
   */
  protected int selectOperator( double[] x ){
    WeightedNumberGenerator g = new WeightedNumberGenerator( x );
    return g.newInt();
  }

  /**
   * Decrease the rate of the given operator
   * @param x Rates vector index
   * @param oper Operator index
   */
  protected void decrease( double[] x, int oper ){
    x[oper] *= (1.0-NumberGenerator.random());
    normalize( x );
  }

  /**
   * Increase the rate of the given operator
   * @param x Rates vector index
   * @param oper Operator index
   */
  protected void increase( double[] x, int oper ){
    x[oper] *= (1.0+NumberGenerator.random());
    normalize( x );
  }

  /**
   * Increase the rate of the operator in the proportion given
   * @param x Rates vector index
   * @param oper Operator index
   * @param delta Proportion that the operator rate will be incremented
   */
  protected void increase( double[] x, int oper, double delta ){
    x[oper] *= (1.0+delta);
    normalize( x );
  }

  public void updateRatesSize( Population population ){
    int psize = population.size();
    int rsize = rates.size();
    if( psize != rsize ){
      int n = operators.length;
      if( psize > rsize ){
        for( int i=rsize; i<psize; i++ ){
          double[] tempRates = new double[n];
          for( int j=0; j<n; j++ ){  tempRates[j] = NumberGenerator.random();  }
          normalize( tempRates );
          rates.add( tempRates );
        }
      }else{
        for( int i=rsize-1; i>=psize; i-- ){
          rates.remove(i);
        }
      }
    }
  }

 /**
   * Transforms the given population to another population according to its rules.
   * @param population The population to be transformed
   * @return A new population built from the given population
   */
  public Population apply( Population population ){
    Population newPopulation = null;
    if (operators != null) {
      Environment env = population.getEnvironment();
      updateRatesSize( population );
      Vector newRates = new Vector();
      Vector newInd = new Vector();
      double avgFitness = 0.0;
      for( int i=0; i<population.size(); i++ ){
        double[] x = (double[])rates.get(i);
        int oper = selectOperator( x );
        Operator o = operators[ oper ];
        Vector v = o.apply( population, i );
        Enumeration iter = v.elements();
        while( iter.hasMoreElements() ){
          ((Individual)iter.nextElement()).evalFitness(env);
        }
//        v.add( population.get(i).copy() ); // dependency on Sort algorithm
        Individual parent = population.get(i);
        Individual par = (Individual)Cloner.clone(parent);
        par.setThing(parent.getThing());
        v.add(0, par );
//        par.energy = parent.energy;
        Population p = new Population( env, v );
//        selection.setSize( (int)(Math.random() * ( v.size() + 1) ) );
        v = selection.choose( p );
        double pf = parent.getFitness();
        iter = v.elements();
        while( iter.hasMoreElements() ){
          Individual child = (Individual)iter.nextElement();
          double[] y = (double[])x.clone();
          double f = child.getFitness();
//          if( maxFitness < f ){ maxFitness = f; }else{
//            if( minFitness
//          }
          avgFitness += f;
          if( pf < child.getFitness() ){
            increase( y, oper );
//            if( Math.random() < 0.5 ){
//            child.energy+=1;
//            }
          }else{
            decrease( y, oper );
//            if( Math.random() < 0.5 ){
//              child.energy-=9;
//            }
          }
//          if( child.energy > 0 ){
            newRates.add(y);
            newInd.add(child);
//          }
        }
      }
//      int n = newInd.size();
//      avgFitness /= n;
//      int i=0;
//      while( i<n ){
//
//      }
      newPopulation = new Population( env, newInd );
      rates = newRates;
    }
    return newPopulation;
  }

  /**
  * Return the statistical information of the population given. includes information
  * of the transformation process if any
  * @param population Population used to calculate the statistical information
  * @return Statistical information of the transformation process
  */
  public Result statistics( Population population ){
    updateRatesSize(population);
    return new HAEAStatistics( rates, population.statistics() );
  }
}
