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
 * <p>Title: UniformNumberGenerator</p>
 * <p>Description: This class is a simple uniform random number generator.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */

public class UniformNumberGenerator extends NumberGenerator {

  /**
   * The inferior range of the random number generator interval
   */
  protected double min = 0.0;

  /**
   * The length of the range
   */
  protected double length = 1.0;

  /**
   * Default constructor: Creates a Uniform Number Generator that can generate random real numbers in the interval [0.0,1.0)
   * With this constructor the newInt method will return 0.0 always
   */
  public UniformNumberGenerator() { }

  /**
   * Constructor: Creates a uniform random number generator that generates numbers in the interval [0.0, n)
   * The argument has to be positive in other case the positive value will be taken.
   * @param n The final limit of the random number generator range
   */
  public UniformNumberGenerator(double n) {
    min = 0.0;
    length = n;
  }

  /**
   * Constructor: Creates a uniform random number generator that generates random numbers between the interval provided
   * [min_val, max_val)
   * @param minVal The inferior limit of the range
   * @param maxVal The superior limit of the range
   */
  public UniformNumberGenerator(double minVal, double maxVal) {
    min = minVal;
    length = maxVal - minVal;
  }

  /**
   * Creates a uniform random number generator that generates random numbers between the interval provided
   * [minVal, maxVal)
   * @param minVal The inferior limit of the range
   * @param maxVal The superior limit of the range
   */
  public void set(double minVal, double maxVal) {
    min = minVal;
    length = maxVal - minVal;
  }

  /**
   * Returns a random integer number
   * @return A random integer number
   */
  public int newInt() {  return (int) newDouble();  }

  /**
   * Returns a random double number
   * @return A random double number
   */
  public double newDouble() {  return (min + length * g.nextDouble());  }

  /**
   * Returns a random boolean value
   * @return A random boolean value
   */
  public boolean newBoolean() { return g.nextBoolean(); };
}
