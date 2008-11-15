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

import jml.math.abs.Square;

/**
 * <p>Title: MatrixSquare</p>
 * <p>Description: Calculate the square and square-root of a matrix.</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class MatrixSquare extends Square {
	/**
	 * Calculate the square of a matrix. This process is component by component.
	 * @param one The matrix
	 */
  public void fastSqr(Object one) {
    RealMatrix y = (RealMatrix) one;
    for (int i = 0; i < y.n; i++) {
      for (int j = 0; j < y.m; j++) {
        y.data[i][j] *= y.data[i][j];
      }
    }
  }
	/**
	 * Calculate the square-root of a matrix. This process is component by component.
	 * @param one The matrix
	 */
  public void fastSqrt(Object one) {
    RealMatrix y = (RealMatrix) one;
    for (int i = 0; i < y.n; i++) {
      for (int j = 0; j < y.m; j++) {
        y.data[i][j] = Math.sqrt(y.data[i][j]);
      }
    }
  }
}
