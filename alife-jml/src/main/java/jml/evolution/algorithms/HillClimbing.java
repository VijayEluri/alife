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

import jml.basics.Cloner;
import jml.evolution.Environment;
import jml.evolution.Individual;
import jml.evolution.Population;
import jml.evolution.Selection;
import jml.evolution.Transformation;
import jml.evolution.operators.ArityOne;
import jml.evolution.selections.Elitism;

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
public class HillClimbing extends Transformation {
  /**
   * Set of genetic operators that are used by CEA for evolving the solution chromosomes
   */
  protected ArityOne mutation = null;

  /**
   * The selection scheme between parent-children
   */
   protected Selection selection = null;

  /**
   * Constructor: Creates a CEA transformation function that uses proportional
   * selection between parent-children
   * @param _mutation Genetic operators used to evolve the solution
   */
  public HillClimbing( ArityOne _mutation ){
    mutation = _mutation;
    if( mutation != null ){
      selection = new Elitism(mutation.getEnvironment(), 1, false, 1.0, 0.0);
    }else{
      selection = null;
    }
  }

  /**
   * Constructor
   * @param _mutation Genetic operators used to evolve the solution
   * @param _selection The selection scheme between parent-children
   */
  public HillClimbing( ArityOne _mutation, Selection _selection ){
    mutation = _mutation;
    selection = _selection;
  }


 /**
   * Transforms the given population to another population according to its rules.
   * @param population The population to be transformed
   * @return A new population built from the given population
   */
  public Population apply( Population population ){
    Population newPopulation = null;
    if( mutation != null ){
      Environment env = population.getEnvironment();
      Vector newInd = new Vector();
      for( int i=0; i<population.size(); i++ ){
        Vector v = mutation.apply( population, i );
        Enumeration iter = v.elements();
        while( iter.hasMoreElements() ){
          ((Individual)iter.nextElement()).evalFitness(env);
        }
        Individual parent = population.get(i);
        Individual par = (Individual)Cloner.clone(parent);
        v.add(0, par );
        Population p = new Population( env, v );
        v = selection.choose( p );
        iter = v.elements();
        while( iter.hasMoreElements() ){
          Individual child = (Individual)iter.nextElement();
          newInd.add(child);
        }
      }
      newPopulation = new Population( env, newInd );
    }
    return newPopulation;
  }
}
