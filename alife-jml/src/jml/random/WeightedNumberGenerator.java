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
package jml.random;


/**
 * <p>Title: WeightedNumberGenerator</p>
 * <p>Description: This class is a random number generator, that generates the numbers according to the
 * probabilities given.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class WeightedNumberGenerator extends NumberGenerator {
  /**
   * The probabilities assigned to each number that the object can generate
   */
  double[] weight = null;
  /**
   * The numbers that the object can generate
   */
  double[] object = null;

  /**
   * Constructor: Creates a weighted number generator of natural numbers in the
   * interval [0,n-1] according to the probabilities given. Here n is the dimension
   * of the probabilities array.
   * @param weight1 The probabilies of generation of each number
   */
  public WeightedNumberGenerator(double[] weight1) {
    weight = weight1;
    if (weight != null) {
      object = new double[weight.length];
      for (int i = 0; i < weight.length; i++) {
        object[i] = i;
      }
    }
  }

  /**
   * Constructor: Creates a weighted number generator of the given numbers according to the probabilities given.
   * @param weight1 The probabilies of generation of each number
   * @param object1 The numbers that the object can generate
   */
  public WeightedNumberGenerator(double[] weight1, double[] object1) {
    weight = weight1;
    object = object1;
  }

  /**
   * Constructor: Creates a weighted number generator of the given numbers according to the probabilities given.
   * @param weight1 The probabilies of generation of each number
   * @param object1 The numbers that the object can generate
   */
  public WeightedNumberGenerator(double[] weight1, int[] object1) {
    weight = weight1;
    object = new double[object1.length];
    for (int i = 0; i < object.length; i++) {
      object[i] = object1[i];
    }
  }


  /**
   * Set the numbers probabilities
   * @param weight1 The probabilies of generation of each number
   */
  public void setWeights(double[] weight1) {
    weight = weight1;
  }

  /**
   * Normalizes the weight array in that way that the sum of weights is equal to 1.0.
   * This method is not called in constructors.
   */
  public void normalize() {
    if (weight != null) {
      double sum = 0.0;
      for (int i = 0; i < weight.length; i++) { sum += weight[i]; }
      for (int i = 0; i < weight.length; i++) { weight[i] /= sum; }
    }
  }

  /**
   * Returns a random integer number. This method returns the integer value of the real returned by the newDouble method
   * @return A random integer number
   */
  public int newInt() {  return (int) newDouble();  }

  /**
   * Returns a random double number according to the probabilities associated with each number
   * @return A random double number
   */
  public double newDouble() {
    double x = g.nextDouble();
    int i = 0;
    while (i < weight.length && x >= weight[i]) {
      x -= weight[i];
      i++;
    };
    if (i == weight.length) {
      UniformNumberGenerator g = new UniformNumberGenerator(weight.length);
      i = g.newInt();
    };
    return object[i];
  }

  /**
   * Returns a random boolean value.
   * If the integer returned by the method new integer is odd this method return true. If is even return false
   * @return A random boolean value
   */
  public boolean newBoolean() { return ((newInt() % 2) == 1); }

}
