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

import jml.math.abs.Product;

/**
 * <p>Title: VectorDirectProduct</p>
 * <p>Description: Multiplies or divides vectors.</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class VectorDirectProduct extends Product {
	/**
	 * Multiplies two vectors, this process is component by component.
	 * The result of the operation is stored in the first vector.
	 * @param one The first vector
	 * @param two The second vector 
	 */
  public void fastMultiply(Object one, Object two) {
    double[] x = (double[]) one;
    double[] y = (double[]) two;
    int n = x.length;
    for (int i = 0; i < n; i++) {
      x[i] *= y[i];
    }
  }
	/**
	 * Divides two vectors, this process is component by component.
	 * The result of the operation is stored in the first vector.
	 * @param one The first vector
	 * @param two The second vector
	 */
  public void fastDivide(Object one, Object two) {
    double[] x = (double[]) one;
    double[] y = (double[]) two;
    int n = x.length;
    for (int i = 0; i < n; i++) {
      x[i] /= y[i];
    }
  }
}
