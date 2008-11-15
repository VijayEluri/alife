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
import java.util.Enumeration;

import jml.math.sparse.SparseReal;
import jml.math.sparse.SparseRealVector;
/**
 * <p>Title: SparseMinkowski</p>
 * <p>Description: Calculates the Minkowski distance between two real vectors,
 * <p>without calculating the p-root</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class SparseMinkowski extends QuasiMetric {
  /**
   * The Minkowski coeficient
   */
  private double p;
  /**
   * Creates a Minkowski distance onbject with the given Minkowski coeficient, without calculating the p-root
   * @param min Minkowski coeficient
   */
  public SparseMinkowski(double min) {
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

    SparseReal a = null;
    SparseReal b = null;
    Enumeration iter1 = ((SparseRealVector) x).elements();
    Enumeration iter2 = ((SparseRealVector) y).elements();
    while (iter1.hasMoreElements() && iter2.hasMoreElements()) {
      if (a == null) { a = (SparseReal) iter1.nextElement(); }
      if (b == null) { b = (SparseReal) iter2.nextElement(); }
      if (a.getIndex() == b.getIndex()) {
        s += Math.pow(Math.abs(a.value - b.value), p);
        a = null;
        b = null;
      } else {
        if (a.getIndex() < b.getIndex()) {
          s += Math.pow(Math.abs(a.value), p);
          a = null;
        } else {
          s += Math.pow(Math.abs(b.value), p);
          b = null;
        }
      }
    }

    if (a != null) {
      s += Math.pow(Math.abs(a.value), p);
    }

    if (b != null) {
      s += Math.pow(Math.abs(b.value), p);
    }

    while (iter1.hasMoreElements()) {
      a = (SparseReal) iter1.nextElement();
      s += Math.pow(Math.abs(a.value), p);
    }
    while (iter2.hasMoreElements()) {
      b = (SparseReal) iter2.nextElement();
      s += Math.pow(Math.abs(b.value), p);
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
