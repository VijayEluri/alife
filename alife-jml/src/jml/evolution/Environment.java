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
/**
 * <p>Title: Enviroment</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Environment {
  /**
   * The genotype of the Enviroment
   */
  protected Genotype genotype;
  /**
   * The phenotype of the Enviroment
   */
  protected Phenotype phenotype;
  /**
   * The fitness of the Enviroment
   */
  protected Fitness fitness;
  /**
   * Default Constructor
   *
   */
  protected Environment() { }
  /**
   * Creates a Enviroment with the given genotype, phenotype  and fitness
   * @param genotype The genotype of the Enviroment
   * @param phenotype The phenotype of the Enviroment
   * @param fitness The fitness of the Enviroment
   */
  public Environment(Genotype genotype, Phenotype phenotype, Fitness fitness) {
	  this.genotype = genotype;
	  this.phenotype = phenotype;
	  this.fitness = fitness;
    link();
  }
  /**
   * Creates a Enviroment with the given genotype and fitness
   * @param genotype The genotype of the Enviroment
   * @param fitness The fitness of the Enviroment
   */
  public Environment( Genotype genotype, Fitness fitness ) {
    this.genotype = genotype;
    this.phenotype = new Phenotype();
    this.fitness = fitness;
    link();
  }
  /**
   * References the enviroment in the genotype, phenotype and fitness
   */
  protected void link()
  {
    if (genotype != null) { genotype.environment = this; }
    if (phenotype != null) { phenotype.environment = this; }
    if (fitness != null) { fitness.setEnvironment(this); }
  }
  /**
   * Returns the genotype
   * @return The genotype
   */
  public Genotype getGenotype() { return genotype; }
  /**
   * Returns the phenotype
   * @return The phenotype
   */
  public Phenotype getPhenotype() { return phenotype; }
  /**
   * Return the fitness
   * @return The fitness
   */
  public Fitness getFitness() { return fitness; }

  /**
   * Evaluates the given individual
   * @param ind The individual to evaluate
   * @return The fitness value of the given individual
   */
  public double evaluate(Individual ind) {
    return fitness.evaluate(ind);
  }
  /**
   * Sets the genotype of the Enviroment
   * @param genotype The genotype
   */
  public void setGenotype(Genotype genotype) {
    this.genotype = genotype;
    this.genotype.environment = this;
  }
}
