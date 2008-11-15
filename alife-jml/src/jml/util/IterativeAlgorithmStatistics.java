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

import java.util.Enumeration;
import java.util.Vector;

import jml.basics.Cloner;
import jml.basics.Result;


/**
 * <p>Title: IterativeAlgorithmStatistics</p>
 * <p>Description: Statistical information of an iterative algorithm.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
 public class IterativeAlgorithmStatistics extends Result implements Cloneable {
  /**
   * The statistical information of each iteration
   */
  protected Vector statistics = new Vector();

  /**
   * Constructor: default
   */
  public IterativeAlgorithmStatistics() { }

  /**
   * Assigns the statistic
   * @param statistics Object vector
   */
  public IterativeAlgorithmStatistics(Vector statistics) {
    this.statistics = statistics;
  }

  /**
   * Constructor: Creates a clone from the given object
   * @param source The statistical information to be cloned
   */
  public IterativeAlgorithmStatistics(IterativeAlgorithmStatistics source) {
    if (source != null && source.statistics != null) {
      statistics = new Vector();
      Enumeration iter = source.statistics.elements();
      while (iter.hasMoreElements()) {
        Result r = (Result) iter.nextElement();
        statistics.add((Result) Cloner.clone(r));
      }
    }
  }

  /**
   * Creates an empty statistical object
   * @return An empty statistical object
   */
  public Result create() { return new IterativeAlgorithmStatistics();  }

  /**
   * Creates a clone of the statistical object
   * @return A clone of the statistical object
   */
  public Object clone() {  return new IterativeAlgorithmStatistics(this);  }

  /**
   * Adds the statistical information of the current population
   * @param r The statistical information of the current population
   */
  public void add(Result r) {
    statistics.add(r);
  }

  /**
   * Adds the statistical information of the current population
   * @param r The statistical information of the current population
   */
  public void addFirst(Result r) {
    statistics.add(0, r);
  }

 /**
  * Return object that belongs to the statistics
  * @param i position that we need
  * @return object that belongs to the statistics
  */
  public Result getIterationResult(int i) {
    return (Result) statistics.get(i);
  }

  /**
   * Convert to string the statistical information of the evolutionary algorithm
   * @return A string with the statistical information of the evolutionary algorithm
   */
  public String toString() {
    StringBuffer sb = new StringBuffer();
    if (statistics != null) {
      int i = 0;
      Enumeration iter = statistics.elements();
      while (iter.hasMoreElements()) {
        Result r = (Result) iter.nextElement();
        sb.append(i);
        sb.append(r.toString());
        sb.append('\n');
        i++;
      }
    }
    return sb.toString();
  }


  /**
   * Adds the statistics object with the given statistic object.
   * @param otherResult The statistic object that will be added to the Statistic object
   */
  public void sum(Result otherResult) {
    if (otherResult instanceof IterativeAlgorithmStatistics) {
      IterativeAlgorithmStatistics other = (IterativeAlgorithmStatistics) otherResult;
      if (statistics != null) {
        int s = statistics.size();
        int s2 = other.statistics.size();
        Enumeration iter = statistics.elements();
        Enumeration iter2 = other.statistics.elements();
        Result r = null;
        Result r2 = null;
        while (iter.hasMoreElements() && iter2.hasMoreElements()) {
          r = (Result) iter.nextElement();
          r2 = (Result) iter2.nextElement();
          r.sum(r2);
        }
        if (s < s2) {
          while (iter2.hasMoreElements()) {
            r2 = (Result) iter2.nextElement();
            Result r3 = (Result) Cloner.clone(r);
            r3.sum(r2);
            statistics.add(r3);
          }
        } else {
          while (iter.hasMoreElements()) {
            r = (Result) iter.nextElement();
            r.sum(r2);
          }
        }
      }
    }
  }

  /**
   * Substracts the statistics object with the given statistic object.
   * @param otherResult The statistic object that will be substracted from the Statistic object
   */
  public void substract(Result otherResult) {
    if (otherResult instanceof IterativeAlgorithmStatistics) {
      IterativeAlgorithmStatistics other = (IterativeAlgorithmStatistics) otherResult;
      if (statistics != null) {
        int s = statistics.size();
        int s2 = other.statistics.size();
        Enumeration iter = statistics.elements();
        Enumeration iter2 = other.statistics.elements();
        Result r = null;
        Result r2 = null;
        while (iter.hasMoreElements() && iter2.hasMoreElements()) {
          r = (Result) iter.nextElement();
          r2 = (Result) iter2.nextElement();
          r.substract(r2);
        }
        if (s < s2) {
          while (iter2.hasMoreElements()) {
            r2 = (Result) iter2.nextElement();
            Result r3 = (Result) Cloner.clone(r);
            r3.substract(r2);
            statistics.add(r3);
          }
        } else {
          while (iter.hasMoreElements()) {
            r = (Result) iter.nextElement();
            r.substract(r2);
          }
        }
      }
    }
  }

  /**
   * Divides the statistical object by the number given.
   * @param n The number used to divide the object
   */
  public void divide(double n) {
    if (statistics != null) {
      Enumeration iter = statistics.elements();
      while (iter.hasMoreElements()) {
        Result r = (Result) iter.nextElement();
        r.divide(n);
      }
    }
  }

  /**
   * Multiplies the statistical object by itself.
   */
  public void square() {
    if (statistics != null) {
      Enumeration iter = statistics.elements();
      while (iter.hasMoreElements()) {
        Result r = (Result) iter.nextElement();
        r.square();
      }
    }
  }

  /**
   * Get the square-root of the statistical object.
   */
  public void sqrt() {
    if (statistics != null) {
      Enumeration iter = statistics.elements();
      while (iter.hasMoreElements()) {
        Result r = (Result) iter.nextElement();
        r.sqrt();
      }
    }
  };


  /**
   * Return the number of iterations executed by the evolutionary algorithm
   * @return An integer that represents the number of iterations executed by the evolutionary algorithm
   */
  public int size() {  return statistics.size();  }
}
