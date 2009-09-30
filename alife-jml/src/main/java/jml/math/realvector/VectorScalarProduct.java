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

import jml.math.abs.ScalarProduct;

/**
 * <p>Title: VectorScalarProduct</p>
 * <p>Description: Multiplies or divides a vector by an scalar.</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class VectorScalarProduct extends ScalarProduct {
	/**
	 * Multiplies a vector by an scalar.
	 * @param one The vector
	 * @param x The scalar
	 */
  public void fastMultiply(Object one, double x) {
    double[] y = (double[]) one;
    int n = y.length;
    for (int i = 0; i < n; i++) {
      y[i] *= x;
    }
  }
	/**
	 * Divides a vector by an scalar.
	 * @param one The vector
	 * @param x The scalar
	 */
  public void fastDivide(Object one, double x) {
    double[] y = (double[]) one;
    int n = y.length;
    for (int i = 0; i < n; i++) {
      y[i] /= x;
    }
  }

}
