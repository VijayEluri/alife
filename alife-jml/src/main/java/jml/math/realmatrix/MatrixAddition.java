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

import jml.math.abs.Addition;


/**
 * <p>Title: MatrixAddition</p>
 * <p>Description: Adds and Subtracts matrices.</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class MatrixAddition extends Addition {
	/**  
	   * Adds to the second matrix the first matrix. The addition process is component by component
	   * x[i,j] = x[i,j] + other[i,j] for all i=1..n and j=1..m
	   * The result of the operation is stored in the first matrix.
	   * @param one The first matrix 
	   * @param two The matrix to be added
	   */
  public void fastSum(Object one, Object two) {
    RealMatrix x = (RealMatrix) one;
    RealMatrix y = (RealMatrix) two;
    for (int i = 0; i < x.n; i++) {
      for (int j = 0; j < x.m; j++) {
        x.data[i][j] += y.data[i][j];
      }
    }
  }
  /**
   * Substracts a matrix from the first matrix. 
   * The substraction process is component by component. The substraction process is component by component
   * x[i,j] = x[i,j] - other[i,j] for all i=1..n and j=1..m
   * The result of the operation is stored in the first matrix.
   * @param one The first matrix
   * @param two The matrix to be substracted
   */
  public void fastSubstract(Object one, Object two) {
    RealMatrix x = (RealMatrix) one;
    RealMatrix y = (RealMatrix) two;
    for (int i = 0; i < x.n; i++) {
      for (int j = 0; j < x.m; j++) {
        x.data[i][j] -= y.data[i][j];
      }
    }
  }
}
