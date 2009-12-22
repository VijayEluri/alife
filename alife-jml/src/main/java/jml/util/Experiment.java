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
package jml.util;

import jml.basics.Algorithm;
import jml.basics.Result;


/**
 * <p>Title: Experiment</p>
 * <p>Description: A set of experiments over an algorithm.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */

public class Experiment extends Algorithm {

  /**
   * Experiment result
   */	
  private Result[] stat = null;

  /**
   * Algorithm to be tested
   */
  private Algorithm algorithm = null;

  /**
   * Number of repetitions
   */
  private int n = 1;

  /**
   * Constructor: Creates an experiment of the algorithm given.
   * The experiment is the execution of the algorithm the number of times desired
   * @param n Number of times the algorithm will be executed
   * @param algorithm Algorithm to be executed
   */
  public Experiment(int n, Algorithm algorithm) {
    this.algorithm = algorithm;
    this.n = n;
  }

  /**
   * This method provides the capability of initializing extra information in
   * Specific experiments, like training and testing sets in a folding experiment
   * @param k Experiment number
   */
  protected void initExperiment(int k) {
    algorithm.tracer = tracer;
    algorithm.init(input);
  }

  /**
   * Run the experiment
   */
  public void run() {
    boolean flag = (n > 0);
    if (flag) {
      stat = new Result[n];
      for (int i = 0; flag && i < n; i++) {
        this.initExperiment(i);
        algorithm.run();
        stat[i] = (Result) algorithm.output();
        flag = (stat[i] != null);
      }
    }
  }
  
  /**
   * Return result of the experiment
   * @return result of the experiment
   */
  public Object output() {
    return new ExperimentResults(stat);
  }
  
  /**
   * Get Repetition number
   * @return Repetition number
   */
  public int getRepetition() {
	  return n;
  }
  
  /**
   * Set Repetition number
   * @param n Repetition number
   */
  public void setRepetition(int n) {
	this.n = n;  
  }
  
  /**
   * Get Algorithm
   * @return Algorithm
   */
  public Algorithm getAlgorithm() {
	  return algorithm;
  }
  
  /**
   * Set Algorithm
   * @param algorithm Algorithm
   */
  public void setAlgorithm(Algorithm algorithm) {
	this.algorithm = algorithm;  
  }
  
  /**
   * Get Experiment result
   * @return Experiment result
   */
  public Result[] getResult() {
	  return stat;
  }
}
