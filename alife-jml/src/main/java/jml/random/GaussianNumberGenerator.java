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
 * <p>Title: GaussianNumberGenerator</p>
 * <p>Description: A Gaussian random number generator.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class GaussianNumberGenerator extends NumberGenerator {
  /**
   * mean
   */
  double miu = 0.0;
  /**
   * standard deviation
   */
  double sigma = 1.0;
  /**
   * If the gaussian numbers are generated only in the right part of the
   * distribution or in the full distribution
   */
  public boolean onlyRight = false;

  /**
   * Default constructor: Creates a Gaussian Number Generator G~(0,1)
   */
  public GaussianNumberGenerator() { }

  /**
   * Constructor: Creates a Gaussian Number Generator G~(miu,sigma)
   * @param miu1 Mean
   * @param sigma1 standard deviation
   */
  public GaussianNumberGenerator(double miu1, double sigma1) {
    miu = miu1;
    sigma = sigma1;
  }

  /**
   * Constructor: Creates a Gaussian Number Generator G~(miu,sigma)
   * @param miu1 Mean
   * @param sigma1 standard deviation
   * @param onlyRight1 If the distribution takes only the right part or not
   */
  public GaussianNumberGenerator(double miu1, double sigma1, boolean onlyRight1) {
    onlyRight = onlyRight1;
    miu = miu1;
    sigma = sigma1;
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
  public double newDouble() {
    double v = sigma * g.nextGaussian();
    if (onlyRight) { v = Math.abs(v); }
    return (miu + v);
  }

  /**
   * Returns a random boolean value
   * @return A random boolean value
   */
  public boolean newBoolean() { return g.nextBoolean(); };

  /**
   * Sets the standard deviation of the number generator
   * @param sigma1 The standard deviation
   */
  public void setSigma(double sigma1) { sigma = sigma1; }
}
