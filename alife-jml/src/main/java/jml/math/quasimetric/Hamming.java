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

import jml.structures.BitArray;

/**
 * <p>Title: Hamming</p>
 * <p>Description: Calculates the Hamming distance between two array of bits</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Hamming extends QuasiMetric {

  /**
   * Default constructor
   */
  public Hamming() {
  }

  /**
   * Calculates the Hamming distance from one bitarray to another
   * @param x The first bit array
   * @param y The second bit array
   * @return Hamming distance from object x to object y
   */
  public double distance(Object x, Object y) {
    BitArray bx = (BitArray) x;
    BitArray by = (BitArray) y;
    BitArray ans = new BitArray(bx);
    ans.xor(by);
    int n = bx.size();
    int m = by.size();
    int counter = Math.abs(n - m);
    for (int i = 0; i < ans.size(); i++) {
      if (ans.get(i)) { counter++; }
    }
    return counter;
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
