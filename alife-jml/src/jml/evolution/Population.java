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
package jml.evolution;

import java.util.Enumeration;
import java.util.Vector;

import jml.util.sort.MergeSort;

/**
 * Title: Population</p>
 * Description: A collection of individuals that represents the population of an
 * evolutionary algorithm</p>
 * Copyright:    Copyright (c) 2004</p>
 * Company: Universidad Nacional de Colombia
 * @author Jonatan Gomez
 * @version 1.0
 */

public class Population {
  /**
   * Environment of the Evolutionary Algorithm
   */
  protected Environment environment = null;


  /**
   * Set of individuals in the population
   */
  public Vector individuals = new Vector();

  /**
   * Constructor: Creates a population using the given individuals
   * @param environment Environment of the Population
   * @param individuals Individuals that will conform the population
   */
  public Population(Environment environment, Vector individuals){
    this.individuals = individuals;
    this.environment = environment;
  }

  /**
   * Constructor: Creates a population with size individuals, each individual with
   * the same structure of the given individual (Useful when inheritance process
   * is used in the individual class)
   * @param environment1 Environment of the Population
   * @param size Number of individuals in the population
   */
  public Population(Environment environment1, int size) {
    environment = environment1;
    Genotype genotype = environment.getGenotype();
    for (int i = 0; i < size; i++) {
      Individual ind = new Individual(genotype.newInstance());
      ind.evalFitness(environment);
      individuals.add(ind);
    }
  }

  /**
   * Initialize the population randomly
   */
  public void init() {
    if (individuals != null && individuals.size() > 0){
      int size = individuals.size();
      Population p = new Population(environment, size);
      individuals = p.individuals;
    }
  }
  /**
   * Deletes all the individuals in the population
   */
  public void clear() { if (individuals != null) { individuals.clear(); } }

  public Environment getEnvironment(){ return environment; }

  /**
   * Returns the statistical information of the population
   * @return A statistical object with information of the population
   */
  public PopulationStatistics statistics() {
    PopulationStatistics stat = null;
    if (individuals.size() > 0) {
      stat = new PopulationStatistics(this);
    }
    return stat;
  }

  /**
   * Returns the population size
   * @return An integer that represents the population size
   */
  public int size() {
    return individuals.size();
  }

  /**
   * Returns the individual in the position given
   * @param index The index of the individual to get
   * @return An individual if the given position is valid (0<=index<population size), null in other case
   */
  public Individual get(int index) {
    Individual c = null;
    try{  c = (Individual)individuals.elementAt(index);  
    } catch(Exception e) {  System.err.println("[Population]"+e.getMessage());  }
    return c;
  }

  /**
   * Sorts the population according to the fitness value
   */
  public void sort() {
    MergeSort sort = new MergeSort();
    sort.high2low(individuals, new IndividualOrder());
  }

  /**
   * Calculates the fitness value of each individual in the population
   */
  public void evalFitness() {
    Enumeration iter = individuals.elements();
    while( iter.hasMoreElements() ){
      Individual ind = (Individual)iter.nextElement();
      ind.evalFitness( environment );
    }
  }

  /**
   * Set the collection of individuals defining the population
   * @param indiv Collection of individuals defining the population
   */
  public void setIndividuals(Vector indiv) {
    individuals.clear();
    individuals = indiv;
  }

}
