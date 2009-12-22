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
package jml.evolution.algorithms;


import java.util.Enumeration;
import java.util.Vector;

import jml.evolution.Environment;
import jml.evolution.Operator;
import jml.evolution.Population;
import jml.evolution.Selection;
import jml.evolution.Transformation;
import jml.evolution.binary.operators.Mutation;
import jml.evolution.binary.operators.XOver;
import jml.evolution.operators.ArityTwo;
import jml.random.NumberGenerator;

/**
 * <p>Title: GenerationalEA</p>
 * <p>Description: The Generational Genetic Algorithm (includes the Simple Genetic Algorithm)</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */

public class GenerationalEA extends Transformation{

  /**
   * Probability that each operator has to be applied
   */
  protected double[] operator_probabilities = null;
  /**
   * Set of operators that can be applied
   */
  protected Operator[] operators = null;

  /**
   * Creates a Simple Genetic Algorithm Transformation function, with the given
   * selection scheme, and simple crossover, and simple gen mutation operators. The
   * operators have the given probability to be applied
   * @param selection Selection scheme to be applied
   * @param mProb Mutation probability
   * @param xoverProb Crossover probability
   */
  public GenerationalEA( Selection selection,
                   double mProb, double xoverProb ) {
    Environment env = selection.getEnvironment();
    operators = new Operator[3];
    operators[0] = selection;
    operators[1] = new XOver( env, new Sequence(env,2, true) );
    operators[2] = new Mutation(env, mProb);

    operator_probabilities = new double[3];
    operator_probabilities[0] = 1.0;
    operator_probabilities[1] = xoverProb;
    operator_probabilities[2] = 1.0;
  }

  /**
   * Creates a Simple Genetic Algorithm Transformation function, with the given
   * selection scheme, and simple crossover, and simple gen mutation operators. The
   * operators have the given probability to be applied
   * @param selection Selection scheme to be applied
   * @param xover XOver operator to be used
   * @param mutation Mutation operator to be used
   * @param mProb Mutation probability
   * @param xoverProb Crossover probability
   */
  public GenerationalEA( Selection selection, Operator mutation, ArityTwo xover,
                   double mProb, double xoverProb ) {
    Environment env = selection.getEnvironment();
    operators = new Operator[3];
    operators[0] = selection;
    operators[1] = xover;
    xover.setSelection(new Sequence(env,2,true));
    operators[2] = mutation;

    operator_probabilities = new double[3];
    operator_probabilities[0] = 1.0;
    operator_probabilities[1] = xoverProb;
    operator_probabilities[2] = mProb;
  }

  /**
   * Creates a Simple Genetic Algorithm Transformation function, with the given
   * operators and operator probabilities
   * @param _operator_probabilities Probability that each operator has to be applied
   * @param _operators Set of operators that can be applied
   */
  public GenerationalEA( double[] _operator_probabilities, Operator[] _operators ) {
    operators = _operators;
    operator_probabilities = _operator_probabilities;
  }

 /**
   * Transforms the given population to another population according to its rules.
   * Also updates the statistical information of the transformation process
   * @param population The population to be transformed
   * @return A new population built from the given population
   */
  public Population apply( Population population ){
    if( operators != null ){
      for( int i=0; i<operators.length; i++ ){
        int n = operators[i].getArity();
        int m = population.size() / n;
        Vector ind = new Vector();
        int k=0;
        for( int j=0; j<m; j++ ){
          if( NumberGenerator.random() < operator_probabilities[i] ){
            Vector subInd = operators[i].apply( population, k );
            Enumeration iter = subInd.elements();
            while( iter.hasMoreElements() ){
              ind.add( iter.nextElement() );
            }
          }else{
            for( int h=0; h<n; h++ ){
              ind.add( population.get(k+h) );
            }
          }
          k += n;
        }
        population = new Population( population.getEnvironment(), ind );
      }
    }
    return population;
  }
}
