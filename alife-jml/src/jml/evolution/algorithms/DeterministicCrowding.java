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
import jml.evolution.Individual;
import jml.evolution.Population;
import jml.evolution.Transformation;
import jml.evolution.binary.operators.Mutation;
import jml.evolution.binary.operators.XOver;
import jml.evolution.operators.ArityOne;
import jml.evolution.operators.ArityTwo;
import jml.evolution.util.IndividualMetric;
import jml.math.quasimetric.QuasiMetric;
import jml.random.NumberGenerator;
import jml.random.Partition;

/**
 * <p>Title: DeterministicCrowding</p>
 * <p>Description: The Deterministic Crowding Approach proposed by Mahfoud for niching</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */

public class DeterministicCrowding extends Transformation{
  /**
   * Distance between individuals
   */
  QuasiMetric metric = null;

  /**
   * Crossover Probability
   */
  protected double xoverProbability = 1.0;
  /**
   * Mutation Probability
   */
  protected double mutationProbability = 0.1;

  /**
   * Mutation operator
   */
  protected ArityOne mutation;

  /**
   * Crossover operator
   */
  protected ArityTwo xover;

  /**
   * Creates a Simple Genetic Algorithm Transformation function, with the given
   * selection scheme, and simple crossover, and simple gen mutation operators. The
   * operators have the given probability to be applied
   * @param _metric Distance between individuals
   * @param mProb Probability of mutating one bit of the individual
   * @param xoverProb Crossover probability
   */
  public DeterministicCrowding( Environment environment, QuasiMetric _metric,
                                double mProb, double xoverProb ) {
    metric = IndividualMetric.generate(environment, _metric);
    xover = new XOver( environment, new Sequence(null, 2, true) );
    mutation = new Mutation( environment, mProb);
    mutationProbability = 1.0;
    xoverProbability = xoverProb;
  }

  /**
   * Creates a Simple Genetic Algorithm Transformation function, with the given
   * selection scheme, and simple crossover, and simple gen mutation operators. The
   * operators have the given probability to be applied
   * @param _metric Distance between individuals
   * @param _xover XOver operator to be used
   * @param _mutation Mutation operator to be used
   * @param mProb Probability of applying the mutation operator
   * @param xoverProb Crossover probability
   */
  public DeterministicCrowding( QuasiMetric _metric, ArityOne _mutation, ArityTwo _xover,
                                double mProb, double xoverProb ) {
    metric = IndividualMetric.generate(xover.getEnvironment(), _metric);
    xover = _xover;
    mutation = _mutation;
    mutationProbability = mProb;
    xoverProbability = xoverProb;
  }

 /**
   * Transforms the given population to another population according to its rules.
   * Also updates the statistical information of the transformation process
   * @param population The population to be transformed
   * @return A new population built from the given population
   */
  public Population apply( Population population ){
    Environment env = population.getEnvironment();
    int n = population.size();
    Partition.permutation( population.individuals );
    Vector ind = new Vector();
    Individual P1, P2, C1, C2;
    for( int i=0; i<n; i+=2 ){
      P1 = population.get(i);
      P2 = population.get(i+1);
      C1 = (Individual)Cloner.clone( P1 );
      C2 = (Individual)Cloner.clone( P2 );

      if( NumberGenerator.random() < xoverProbability ){
        xover.apply(C1.getGenome(), C2.getGenome());
      }

      if( NumberGenerator.random() < mutationProbability ){
        mutation.apply(C1.getGenome());
        mutation.apply(C2.getGenome());
      }

      C1.evalFitness( env );
      C2.evalFitness( env );

      if( metric.distance(P1,C1) + metric.distance(P2,C2) <=
          metric.distance(P1,C2) + metric.distance(P2,C1) ){
        Individual temp = C1;
        C1 = C2;
        C2 = temp;
      }
      if( C1.getFitness() < P1.getFitness() ){  C1 = P1;  }
      if( C2.getFitness() < P2.getFitness() ){  C2 = P2;  }
      ind.add(C1);
      ind.add(C2);
    }
    return  new Population( env, ind );
  }
}
