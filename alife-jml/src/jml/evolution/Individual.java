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

import jml.basics.Cloner;

/**
 * <p>Title: Individual</p>
 * <p>Description: An abstract individual class representation. It is abstract because the
 * evaluate fitness method has to be overwritten for every subclass</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class Individual implements Cloneable{
  /**
   * Chromosome of the individual
   */
  protected Object genome;

  /**
   * Thing represented by the individual
   */
  protected Object thing = null;

  /**
   * Individual fitness value
   */
  protected double fitness = -1.0e108;

  /**
   * Constructor: Creates an individual with the given genome
   * @param genome Chromosome of the individual
   */
  public Individual(Object genome) {
	  this.genome = genome;
  }

  /**
   * Constructor: Creates an individual 
   * @param genome The genome of the individual
   * @param thing The thing represented by the individual
   * @param fitness The fitness of the individual
   */
  public Individual(Object genome, Object thing, double fitness) {
	  this.genome = genome;
	  this.thing = thing;
	  this.fitness = fitness;
  }

  /**
   * Constructor: Creates a clone from the given Individual
   * @param source Individual to be cloned
   */
  public Individual(Individual source) {
    if (source != null) {
      fitness = source.fitness;
      genome = Cloner.clone(source.genome);
      thing = null;
    }
  }
  /**
   * Creates a clone
   * @return a clone of the individual
   */
  public Object clone() {
    return new Individual(this);
  }

  /**
   * Gets the thing represented by the individual
   * @param env The enviroment
   * @return Thing represented by the individual
   */
  public Object getThing(Environment env) {
    if (thing == null ){
      thing = env.getPhenotype().get(genome);
    }
    return thing;
  }

  /**
   * Sets the attribute "thing"
   * @param thing The thing represented by the individual
   */
  public void setThing(Object thing) { this.thing = thing; }
  /**
   * Return the attribute "thing"
   * @return The thing represented by the individual
   */
  public Object getThing() { return thing; }
  /**
   * Returns the genome of the individual
   * @return Individual's genome
   */
  public Object getGenome() { return genome; }
  /**
   * Return an individual converted in a string 
   * @return A String
   */
  public String toString() {
    if (thing != null) { return thing.toString(); } else { return genome.toString(); }
  }

  /**
   * Returns the fitness of the individual.
   * @return Individual's fitness
   */
  public double getFitness() {
    return fitness;
  }

  /**
   * Sets the fitness of the individual.
   * @param fitness The individual's fitness
   */
  public void setFitness(double fitness){ this.fitness = fitness; }

  /**
   * Calculates the fitness of the individual.
   * @return Individual's fitness
   */
  public double evalFitness( Environment env ){
    Fitness f = env.getFitness();
    getThing(env);
    fitness = f.evaluate(this);
    return fitness;
  }
  
  /**
   * Determines if the genotype represents a valid individual
   * @return true if the genotype represents a valid individual, false in other case
   */
  public boolean isFeasible(){ return true; }

}
