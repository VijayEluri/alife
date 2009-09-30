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
 * <p>Title: Euclidean</p>
 * <p>Description: Calculates the Euclidean distance between two real vectors,
 * <p>calculating the square-root</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Euclidean extends Minkowski {
  /**
   * Creates an Euclidean distance object, calculating the square-root
   */
  public Euclidean() {
    super(2.0);
  }

  /**
   * Calculates the Euclidean distance from one real vector to another.
   * This object calculates the square-root
   * @param x The first real vector
   * @param y The second real vector
   * @return Euclidean distance (calculating the square-root) from object x to object y
   */
  public double distance(Object x, Object y) {
    return Math.sqrt(super.distance(x, y));
  }

  /**
   * Determines the maximum distance in the hypercube [0,1]în
   * @param n dimension of the hypercube
   * @return maximum distance in the hypercube [0,1]în
   */
  public double max01(int n) {
    return Math.sqrt(n);
  }

}
