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

/**
 * <p>Title: ForLoopCondition</p>
 * <p>Description: Represents the condition of a for loop</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class ForLoopCondition extends Predicate implements Cloneable {

  /**
   * Iterator variable iter = start
   */
  protected int iter = -1;

  /**
   * The start value
   */
  protected int start = -1;
  /**
   * End value for the iteration process  iter < end
   */
  protected int end = 0;
  /**
   * Increment applied to the iterator in each iteration   iter += inc;
   */
  protected int inc = 1;

  /**
   * Constructor: Creates an iterate condition with start, end, inc values
   * @param start Start value for the iteration process
   * @param end End value for the iteration process
   * @param inc Increment applied to the iterator in each iteration
   */
  public ForLoopCondition(int start, int end, int inc) {
    this.start = start;
    this.end = end;
    this.inc = inc;
    this.iter = start - inc;
  }

  /**
   * Constructor: Creates an iterate condition with start, and end values. The inc value is fixed in one
   * @param start Start value for the iteration process
   * @param end End value for the iteration process
   */
  public ForLoopCondition(int start, int end) {
    this.start = start;
    this.iter = start - inc;
    this.end = end;
  }

  /**
   * Constructor: Creates an iterate condition with start, and end values.
   * The inc value is fixed in one, and the start value is fixed in zero
   * @param end End value for the iteration process
   */
  public ForLoopCondition(int end) {
    this.end = end;
  }

  /**
   * Creates a clone of the predicate
   * @return A copy of the predicate
   */
  public Object clone() {
    return new ForLoopCondition(start, end, inc);
  }

  /**
   * Sets the values of the predicate
   * @param start Start value for the iteration process
   * @param end End value for the iteration process
   * @param inc Increment applied to the iterator in each iteration
   */
  public void  set(int start, int end, int inc) {
    this.start = start;
    this.end = end;
    this.inc = inc;
    this.iter = start - inc;
  }


  /**
   * Evaluates the iteration condition and after that, it increments the iterator
   * @return true if the condition is satisfied by the iterator (iter < end), false in other case
   */
  public boolean evaluate() {
    iter += inc;
    return (iter < end);
  }

  /**
   * Initializes the internal state of the predicate
   */
  public void init() {
    iter = start - inc;
  }
}
