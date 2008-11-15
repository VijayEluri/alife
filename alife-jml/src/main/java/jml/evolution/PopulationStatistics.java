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

import jml.basics.Result;

/**
 * Title: PopulationStatistics</p>
 * Description: Statistical information of a population</p>
 * Copyright:    Copyright (c) 2004</p>
 * Company: Universidad Nacional de Colombia
 * @author Jonatan Gomez
 * @version 1.0
 */

public class PopulationStatistics extends Result implements Cloneable {
	/**
	 * The population
	 */
  protected Population population = null;
  /**
   * Population size
   */
  protected double pop_size;
  /**
   * Best individual index
   */
  protected int bestIndex;
  /**
   * Best individual
   */
  protected Individual bestIndividual;
  /**
   * Fitness of the best individual
   */
  public double best;
  /**
   * Fitness of the worst individual
   */
  public double worst;
  /**
   * Average Fitness
   */
  public double avg;
  /**
   * Average genome length
   */
  public double avg_length;
  /**
   * Length of the best individual
   */
  public double best_length;
  /**
   * Number of feasible individuals
   */
  public double feasible_chroms;

  /**
   * Constructor: Default
   */
  public PopulationStatistics() {}

  /**
   * Constructor: Creastes a PopulationStatistics
   * @param _population The population 
   */
  public PopulationStatistics(Population _population) {
    population = _population;
    Genotype genotype = population.environment.getGenotype();
    Vector individuals = population.individuals;

    Enumeration iter = individuals.elements();

    Individual ind = (Individual) iter.nextElement();

    best = ind.getFitness();
    worst = ind.getFitness();
    avg = ind.getFitness();
    avg_length = (double) genotype.size(ind.getGenome());

    feasible_chroms = 0.0;
    if (ind.isFeasible()) { feasible_chroms++; }

    int best_index = 0;
    int i=0;
    while( iter.hasMoreElements() ){
      i++;
      ind = (Individual)iter.nextElement();
      if (best < ind.getFitness()) {
        best = ind.getFitness();
        best_index = i;
      } else {
        if( worst > ind.getFitness()) {
          worst = ind.getFitness();
        }
      }
      avg += ind.getFitness();
      avg_length += (double)genotype.size(ind.getGenome());
      if (ind.isFeasible()) { feasible_chroms++; }
    }

    bestIndividual = (Individual) individuals.elementAt(best_index);
    bestIndex = best_index;
    pop_size = individuals.size();
    avg /= pop_size;
    avg_length /= pop_size;
    feasible_chroms /= pop_size;
    best_length = (double) genotype.size(bestIndividual.getGenome());
  }

  /**
   * Copy contructor
   * @param source The Statistical information to be cloned
   */
  public PopulationStatistics(PopulationStatistics source) {
    population = source.population;
    bestIndividual = source.bestIndividual;
    bestIndex = source.bestIndex;
    best = source.best;
    avg = source.avg;
    worst = source.worst;
    feasible_chroms = source.feasible_chroms;
    best_length = source.best_length;
    avg_length = source.avg_length;
    pop_size = source.pop_size;
  }

  /**
   * Creates an empty statistical information
   * @return An empty population statistical information
   */
  public Result create() {
    return new PopulationStatistics();
  }

  /**
   * Clones the statistical information
   * @return A cloned statistical information
   */
  public Object clone() { return new PopulationStatistics(this); }

  /**
   * Converts the population statistical information to a string
   * @return A string with the population statistical information
   */
  public String toString(){
    StringBuffer sb = new StringBuffer();
    sb.append(' ');
    sb.append(bestIndividual);
    sb.append(' ');
    sb.append(best);
    sb.append(' ');
    sb.append(avg);
    sb.append(' ');
    sb.append(worst);
    sb.append(' ');
    sb.append(best_length);
    sb.append(' ');
    sb.append(avg_length);
    sb.append(' ');
    sb.append(pop_size);
    sb.append(' ');
    sb.append(feasible_chroms);
    return sb.toString();
  }

/**
 * Add the statistics object with the given statistic object.
 * @param otherp The statistic object that will be added to the Statistic object
 */
  public void sum( Result otherp ){
    if( otherp instanceof PopulationStatistics) {
      PopulationStatistics other = (PopulationStatistics) otherp;
      best += other.best;
      avg += other.avg;
      worst += other.worst;
      avg_length += other.avg_length;
      best_length += other.best_length;
      feasible_chroms += other.feasible_chroms;
      pop_size += other.pop_size;
    }
  }

/**
 * Substracts the statistics object with the given statistic object.
 * @param otherp The statistic object that will be substracted from the Statistic object
 */
  public void substract(Result otherp) {
    if (otherp instanceof PopulationStatistics) {
      PopulationStatistics other = (PopulationStatistics) otherp;
      best -= other.best;
      avg -= other.avg;
      worst -= other.worst;
      avg_length -= other.avg_length;
      best_length -= other.best_length;
      feasible_chroms -= other.feasible_chroms;
      pop_size -= other.pop_size;
    }
  }

/**
 * Divide the statistical object by the number given.
 * @param n The number used to divide the object
 */
  public void divide(double n) {
    best /= n;
    avg /= n;
    worst /= n;
    avg_length /= n;
    best_length /= n;
    feasible_chroms /= n;
    pop_size /= n;
  }

/**
 * Multiply the statistical object by itself.
 */
  public void square() {
    best *= best;
    avg *= avg;
    worst *= worst;
    avg_length *= avg_length;
    best_length *= best_length;
    feasible_chroms *= feasible_chroms;
    pop_size *= pop_size;
  }

  /**
   * get the square root of the statistical object.
   */
  public void sqrt() {
    best = Math.sqrt(best);
    avg = Math.sqrt(avg);
    worst = Math.sqrt(worst);
    avg_length = Math.sqrt(avg_length);
    best_length = Math.sqrt(best_length);
    feasible_chroms = Math.sqrt(feasible_chroms);
    pop_size = Math.sqrt(pop_size);
  }

  /**
   * Gets the best individual
   * @return The best individual in the population
   */
  public Individual getBest() {
    return bestIndividual;
  }

  /**
   * Gets the best individual index
   * @return Index of the best individual in the population
   */
  public int getBestIndex() {
    return bestIndex;
  }
  /**
   * Gets the population
   * @return The population
   */
  public Population getPopulation() { return population; }
}
