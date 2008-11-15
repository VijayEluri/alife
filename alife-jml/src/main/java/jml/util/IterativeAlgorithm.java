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

/**
 * <p>Title: IterativeAlgorithm</p>
 * <p>Description: An abstract version of an iterative algorithm.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public abstract class IterativeAlgorithm extends Algorithm {

  /**
   * The continuation condition
   */
  private Predicate condition = null;

  /**
   * Delay between each iteration (in millisecs)
   */
  private long delay = 0;

  /**
   * Constructor: Creates an iterative algorithm with the given continuation condition
   * @param condition  The algorithm stop condition (the algorithm is executed
   * until the condition is false)
   * @param delay Elapsed time between iterations (millisecs)
   */
  public IterativeAlgorithm(Predicate condition, long delay) {
    this.condition = condition;
    this.delay = delay;
  }

  /**
   * Constructor: Creates an iterative algorithm with the given continuation condition
   * @param condition  The algorithm stop condition (the algorithm is executed
   * until the condition is false)
   */
  public IterativeAlgorithm(Predicate condition) {
    this.condition = condition;
  }

  /**
   * Constructor: Creates an iterative algorithm without the continuation condition
   * useful for running the algorithm iteration by iteration.
   */
  public IterativeAlgorithm() { }

  /**
   * Inits the algorithm. Useful to initialize internal variables
   */
  public void init() {
    if (condition != null) {  condition.init(); };
  }

  /**
   * An algorithm iteration
   */
  public abstract void iteration();

  /**
   * Creates an iterative algorithm result trace object. Useful for
   * maintaining the information of the results produced by the iterative
   * algorithm iteration by iteration
   * @return IterativeAlgorithmStatistics
   */
  public IterativeAlgorithmStatistics createTraceObject() {
    return new IterativeAlgorithmStatistics();
  }


  /**
   * Executes the iterative algorithm. Keeps a vector with the results of each
   * iteration of the algorithm
   */
  public void run() {
    if (condition != null) {
      addToTrace();
      while (condition.evaluate() && continueFlag) {
        if (delay > 0) {
          try { IterativeAlgorithm.sleep(delay); } catch (Exception e) { e.printStackTrace(); }
        }
        iteration();
        addToTrace();
      }
    }
    done = true;
  }

  /**
   * Return the current iterative algorithm condition
   * @return The current iterative algorithm
   */
  public Predicate getCondition() {  return condition;  }

  /**
   * Sets the iterative algorithm condition
   * @param condition  The algorithm condition (the algorithms is executed
   * until the condition is false)
   */
  public void setCondition(Predicate condition) {  this.condition = condition;  }
}
