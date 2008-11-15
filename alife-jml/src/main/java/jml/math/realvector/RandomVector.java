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
package jml.math.realvector;

import jml.random.UniformNumberGenerator;

/**
 * <p>Title: RandomVector</p>
 * <p>Description: Generates a random vector of doubles .</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class RandomVector {
	/**
	 * Generates a vector of doubles with size n. 
	 * @param n Size of the vector
	 * @return A vector of doubles
	 */
  public double[] generate(int n) {
    double[] x = new double[n];
    UniformNumberGenerator g = new UniformNumberGenerator();
    for (int i = 0; i < n; i++) {  x[i] = g.newDouble();  }
    return x;
  }
	/**
	 * Generates a vector of doubles with two limits array.
	 * @param min The inferior limit array
	 * @param max The superior limit array
	 * @return A vector of doubles
	 */
  public double[] generate(double[] min, double[] max) {
    int n = min.length;
    double[] x = new double[n];
    UniformNumberGenerator g = new UniformNumberGenerator();
    for (int i = 0; i < n; i++) {
      x[i] = min[i] + g.newDouble() * (max[i] - min[i]);
    }
    return x;
  }
}
