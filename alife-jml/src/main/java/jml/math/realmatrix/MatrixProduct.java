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
package jml.math.realmatrix;

import jml.math.abs.Product;

/**
 * <p>Title: MatrixProduct</p>
 * <p>Description: Multiplies and Divides matrices.</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class MatrixProduct extends Product {
	/**
	 * Multiplies the first matrix by the second matrix.
	 * The result of the operation is stored in the first matrix.
	 * @param one The first matrix
	 * @param two The second matrix
	 */
  public void fastMultiply(Object one, Object two) {
    RealMatrix x = (RealMatrix) one;
    RealMatrix y = (RealMatrix) two;
    if (y.rows() == x.columns()) {
      int n = x.rows();
      int m = y.columns();
      int c = x.columns();
      double[][] newData = new double[n][m];
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          double s = 0.0;
          for (int k = 0; k < c; k++) {
            s += x.data[i][k] * y.data[k][j];
          }
          newData[i][j] = s;
        }
      }
      x.data = newData;
    }
  }
	/**
	 * Divides the first matrix by the second matrix 
	 * The result of the operation is stored in the first matrix.
	 * @param one The first matrix
	 * @param two The second matrix
	 */
  public void fastDivide(Object one, Object two) {
    RealMatrix x = (RealMatrix) one;
    RealMatrix y = (RealMatrix) two;
    y = y.inverse();
    fastMultiply(x, y);
  }
}
