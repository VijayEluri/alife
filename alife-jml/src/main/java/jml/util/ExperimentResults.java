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

import jml.basics.Cloner;
import jml.basics.Result;


/**
 * <p>Title: ExperimentResults</p>
 * <p>Description: The average and std_deviation of an algorithm executed for a number of times.
 * This class support the results provided by the Experiment class</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */

public class ExperimentResults extends Result implements Cloneable {
  
  /**
   * Average result reached by the algorithm
   */
  private Result average = null;

  /**
   * Standard deviation of the algorithm tested
   */
  private Result stdDeviation = null;

  /**
   * The original set of statistics
   */
  private Result[] stat = null;

  /**
   * Constructor: default
   * @param stat Original set of statistics
   */
  public ExperimentResults(Result[] stat) {
    this.stat = stat;
    if (stat != null) {
      average = Result.average(stat);
      stdDeviation = Result.stdDeviation(stat, average);
    }
  }

  /**
   * Constructor: Creates a Experiment Result with the average and std_deviation given
   * @param average The experiment average result
   * @param stdDeviation The experiment results std_deviation
   */
  public ExperimentResults(Result average, Result stdDeviation) {
    this.average = average;
    this.stdDeviation = stdDeviation;
  }


  /**
   * Creates a clone object.
   * @return A clone object
   */
  public Object clone() {
    Result avg = null;
    if (average != null) { avg = (Result) Cloner.clone(average); }
    Result vrc = null;
    if (stdDeviation != null) { vrc = (Result) Cloner.clone(stdDeviation); }
    return new ExperimentResults(avg, vrc);
  }

  /**
   * Adds the statistics object with the given statistic object.
   * @param other The statistic object that will be added to the Statistic object
   */
  public void sum(Result other) {
    if (other instanceof ExperimentResults) {
      ExperimentResults otherthis = (ExperimentResults) other;
      if (average != null) {  average.sum(otherthis.average);  }
      if (stdDeviation != null) {  stdDeviation.sum(otherthis.stdDeviation);  }
    }
  }

  /**
   * Substracts the statistics object with the given statistic object.
   * @param other The statistic object that will be substracted from the Statistic object
   */
  public void substract(Result other) {
    if (other instanceof ExperimentResults) {
      ExperimentResults otherthis = (ExperimentResults) other;
      if (average != null) {  average.substract(otherthis.average);  }
      if (stdDeviation != null) {  stdDeviation.substract(otherthis.stdDeviation); }
    }
  }

   /**
   * Divides the statistical object by the number given.
   * @param n The number used to divide the object
   */
  public void divide(double n) {
    if (average != null) {  average.divide(n);  }
    if (stdDeviation != null) {  stdDeviation.divide(n); }
  }

  /**
   * Multiplies the statistical object by itself. This method is very useful for calculating the std_deviation.
   */
  public void square() {
    if (average != null) {  average.square();  }
    if (stdDeviation != null) {  stdDeviation.square(); }
  }

  /**
   * Multiplies the statistical object by itself. This method is very useful for calculating the std_deviation.
   */
  public void sqrt() {
    if (average != null) {  average.sqrt();  }
    if (stdDeviation != null) {  stdDeviation.sqrt();  }
  }

  /**
   * Converts the experiment results to a string
   * @return The string representation of the experiment result
   */
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append(average.toString());
    sb.append(stdDeviation.toString());
    return sb.toString();
  }

  /**
   * Return the array of statistics of the performed experiment
   * @return Array of statistics of the performed experiment
   */
  public Result[] getStat() { return stat; }

  /**
   * Return the average result of the experiment
   * @return Average result of the experiment
   */
  public Result getAvg() { return average; }

}
