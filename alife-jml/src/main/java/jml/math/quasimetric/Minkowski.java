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
package jml.math.quasimetric;

/**
 * <p>Title: Minkowski</p>
 * <p>Description: Calculates the Minkowski distance between two real vectors,
 * <p>without calculating the p-root</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Minkowski extends QuasiMetric {
  /**
   * The Minkowski coeficient
   */
  private double p;
  /**
   * Creates a Minkowski distance object with the given Minkowski coeficient, without calculating the p-root
   * @param min Minkowski coeficient
   */
  public Minkowski(double min) {
    p = min;
  }

  /**
   * Calculates the Minkowski distance from one real vector to another.
   * This object does not calculate the p-root
   * @param x The first real vector
   * @param y The second real vector
   * @return Minkowski distance (without calculating the p-root from object x to object y
   */
  public double distance(Object x, Object y) {
    double s = 0.0;
    double[] rx = (double[]) x;
    double[] ry = (double[]) y;
    int n = rx.length;
    for (int i = 0; i < n; i++) {
      s += Math.pow(Math.abs(rx[i] - ry[i]), p);
    }
    return s;
  }

  /**
   * Returns the Minkowski coeficient
   * @return The Minkowski coeficient
   */
  public double coeficient() {
    return p;
  }

  /**
   * Determines the maximum distance in the hypercube [0,1]în
   * @param n dimension of the hypercube
   * @return maximum distance in the hypercube [0,1]în
   */
  public double max01(int n) {
    return n;
  }

}
