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

import java.util.Random;

/**
 * <p>Title: NumberGenerator</p>
 * <p>Description: Abstract random number generator.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */

public abstract class NumberGenerator {

  /**
   * The basic random generator
   */
  protected static Random g = new Random();

  /**
   * Constructor: Default constructor. Useful for inheritance
   */
  protected NumberGenerator() { }; 
  /**
   * Generates a random number uniform distributed in the interval [0.0, 1.0)
   * @return A random number uniform distributed in the interval [0.0, 1.0)
   */
  public static double random() { return g.nextDouble(); }

  /**
   * Returns a random integer number
   * @return A random integer number
   */
  public abstract int newInt();

  /**
   * Returns a random double number
   * @return A random double number
   */
  public abstract double newDouble();

  /**
   * Returns a random boolean value
   * @return A random boolean value
   */
  public abstract boolean newBoolean();

  /**
   * Returns a set of random integer numbers
   * @param m The total number of random numbers
   * @return A set of m random integer numbers
   */
  public int[] getArrayInteger(int m) {
    int[] v = null;
    if (m > 0) {
      v = new int[m];
      for (int i = 0; i < m; i++) {
        v[i] = this.newInt();
      }
    }
    return v;
  }

  /**
   * Returns a set of random double numbers
   * @param m The total number of random numbers
   * @return A set of m random double numbers
   */
  public double[] getArrayDouble(int m) {
    double[] v = null;
    if (m > 0) {
      v = new double[m];
      for (int i = 0; i < m; i++) {
        v[i] = this.newDouble();
      }
    }
    return v;
  }

  /**
   * Returns a set of random boolean values
   * @param m The total number of random values
   * @return A set of m random boolean values
   */
  public boolean[] getVectorBoolean(int m) {
    boolean[] v = null;
    if (m > 0) {
      v = new boolean[m];
      for (int i = 0; i < m; i++) {
        v[i] = this.newBoolean();
      }
    }
    return v;
  }
}
