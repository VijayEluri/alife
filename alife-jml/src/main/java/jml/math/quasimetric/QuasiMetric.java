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
 * <p>Title: QuasiMetric</p>
 * <p>Description: Represents a quasimetric. A quasimetric satisfies the following conditions:</p>
 * <p>d(x,y) >= 0</p>
 * <p>d(x,x) = 0</p>
 * <p>d(x,y)=0 -> x = y</p>
 * <p>d(x,z) <= d(x,y) + d(y,z)</p>
 * <p>if it satisfies d(x,y) = d(y,x) then it is called metric</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public abstract class QuasiMetric {
  /**
   * Calculates the distance from one object to other. Remember in quasimetrics
   * the property d(x,y) == d(y,x) is not always true.
   * @param x The first object
   * @param y The second object
   * @return Distance from object x to object y
   */
  public abstract double distance(Object x, Object y);

  /**
   * Determines the maximum distance in the hypercube [0,1]în
   * @param n dimension of the hypercube
   * @return maximum distance in the hypercube [0,1]în
   */
  public double max01(int n) { return Double.NaN; }
}
