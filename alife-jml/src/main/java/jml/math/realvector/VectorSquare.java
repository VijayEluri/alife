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

import jml.math.abs.Square;

/**
 * <p>Title: VectorSquare</p>
 * <p>Description: Calculate the square and square-root of one vector.</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class VectorSquare extends Square {
	/**
	 * Calculate the square of a vector.This process is component by component.
	 * @param one The vector
	 */
  public void fastSqr(Object one) {
    double[] x = (double[]) one;
    double[] y = (double[]) one;
    int n = x.length;
    for (int i = 0; i < n; i++) {
      x[i] *= y[i];
    }
  }
	/**
	 * Calculate the square-root of a vector. This process is component by component.
	 * @param one The vector
	 */
  public void fastSqrt(Object one) {
    double[] x = (double[]) one;
    int n = x.length;
    for (int i = 0; i < n; i++) {
      x[i] = Math.sqrt(i);
    }
  }
}
