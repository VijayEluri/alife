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

import java.util.Vector;

import jml.basics.Cloner;
import jml.evolution.Environment;
import jml.evolution.IndividualOrder;
import jml.evolution.Operator;
import jml.evolution.Population;
import jml.evolution.Selection;
import jml.evolution.binary.operators.XOver;
import jml.evolution.operators.ArityTwo;
import jml.evolution.selections.Tournament;
import jml.random.NumberGenerator;
import jml.util.sort.Sort;
import jml.util.sort.SortableObject;


/**
 * <p>Title: SteadyStateEA</p>
 * <p>Description: An Steady State Genetic Algorithm</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */

public class SteadyStateEA extends GenerationalEA {
  /**
   * Indicates if the population requires to be sorted or not. This is useful for
   * sorting the population only once.
   */
  boolean need_sort = true;

  /**
   * Creates a Steady State Genetic Algorithm Transformation function, with the given
   * selection scheme, simple crossover, and simple gen mutation operators. The
   * operators have the given probability to be applied
   * @param selection Selection scheme to be applied between the offsprings
   * @param mProb Mutation probability
   * @param xoverProb Crossover probability
   */
  public SteadyStateEA(Selection selection, double mProb, double xoverProb) {
    super(selection, mProb, xoverProb);
    Environment env = selection.getEnvironment();
    operators[1] = new XOver(env, new Tournament(env, 2, true, 2));
  }

  /**
   * Creates a Steady State Genetic Algorithm Transformation function, with the given
   * selection scheme, and simple crossover, and simple gen mutation operators. The
   * operators have the given probability to be applied
   * @param selection Selection scheme to be applied
   * @param xover XOver operator to be used
   * @param mutation Mutation operator to be used
   * @param mProb Mutation probability
   * @param xoverProb Crossover probability
   */
  public SteadyStateEA( Selection selection, Operator mutation, ArityTwo xover,
                   double mProb, double xoverProb ) {
    super( selection, mutation, xover, mProb, xoverProb );
  }

  /**
   * Creates a Steady State Genetic Algorithm Transformation function, with the given
   * operators and operator probabilities
   * @param _operator_probabilities Probability that each operator has to be applied
   * @param _operators Set of operators that can be applied
   */
  public SteadyStateEA( double[] _operator_probabilities, Operator[] _operators ) {
    super( _operator_probabilities, _operators );
  }

  /**
   * Init the internal state of transformation
   */
  public void init(){
    need_sort = true;
  }

 /**
   * Transforms the given population to another population according to its rules.
   * Also updates the statistical information of the transformation process
   * @param population The population to be transformed
   * @return A new population built from the given population
   */
  public Population apply( Population population ){
    Vector v = new Vector();
    for( int i=0; i<population.size(); i++ ){
      v.add( Cloner.clone( population.get(i) ) );
    }
    Environment env = population.getEnvironment();
    population = new Population( env, v );
    if( need_sort ){
      population.sort();
      need_sort = false;
    }
    for( int i=0; i<population.size(); i++ ){
      Vector children = population.individuals;
      for( int j=0; j<operators.length; j++ ){
        if( NumberGenerator.random() < operator_probabilities[j] ){
          children = operators[j].apply( new Population(env,children), 0 );
        }
      }
      population.individuals.remove(population.individuals.size() - 1);
      int index = Sort.findHigh2Low(population.individuals,
                                    (SortableObject) children.get(0),
                                    new IndividualOrder());
      population.individuals.add(index,children.get(0));
    }
    return population;
  }
}
