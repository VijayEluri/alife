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

/**
 * <p>Title: RealMatrix</p>
 * <p>Description: A matrix of real numbers.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class RealMatrix {
  /**
   * The number of rows
   */
  public int n = 0;
  /**
   * The number of columns
   */
  public int m = 0;
  /**
   * The real numbers in the matrix
   */
  public double[][] data = null;
  /**
   * Constructor: Receives the dimensions of the matrix and creates the zero matrix
   * @param numr the number of rows
   * @param numc the number of columns
   */
  public RealMatrix(int numr, int numc) {
    if (numr > 0 && numc > 0) {
      n = numr;
      m = numc;
      data = new double[n][m];
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          data[i][j] = 0.0;
        }
      }
    }
  }

  /**
   * Constructor: Create a new matrix with the same dimensions and components as the given matrix
   * @param source The source matrix
   */
  public RealMatrix(RealMatrix source) {
    if (source != null) {
      n = source.n;
      m = source.m;
      data = new double[n][m];
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          data[i][j] = source.data[i][j];
        }
      }
    }
  }


  /**
   * Constructor: Creates the zero square matrix
   * @param num The number of rows and columns (dimension)
   */
  public RealMatrix(int num) {
    if (num > 0) {
      n = num;
      m = num;
      data = new double[n][n];
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          data[i][j] = 0.0;
        }
      }
    }
  }

  /**
   * Creates a matrix from the given vector. The matrix will have dimension n*1
   * where element [i][0] will correspond with element x[i] of the vector
   * @param x Vector used for creating a matrix from it
   */
  public RealMatrix(double[] x) {
    n = x.length;
    m = 1;
    data = new double[n][m];
    for (int i = 0; i < n; i++) {
      data[i][0] = x[i];
    }
  }

  /**
   * Returns the number of rows of the matrix
   * @return The number of rows of the matrix
   */
  public int rows() { return n; }

  /**
   * Returns the number of columns of the matrix
   * @return The number of columns of the matrix
   */
  public int columns() { return m; }

  /**
   * Returns the column i of the matrix
   * @param i The column of the matrix to be return
   * @return The column i of the matrix
   */
  public double[] getColumn(int i) {
    double[] x = new double[n];
    for (int j = 0; j < n; j++) {
      x[j] = data[j][i];
    }
    return x;
  }
  /**
   * Returns the rows i of the matrix
   * @param i The row of the matrix to be return
   * @return The row i of the matrix
   */
  public double[] getRow(int i) {
    double[] x = new double[m];
    for (int j = 0; j < m; j++) {
      x[j] = data[i][j];
    }
    return x;
  }

  /**
   * Returns the inverse of the matrix
   * @return The inverse (null for now)
   */
  public RealMatrix inverse() {
    return null;
  }

  /**
   * Adds to the matrix the given matrix. The addition process is component by component
   * x[i,j] = x[i,j] + other[i,j] for all i=1..n and j=1..m
   * @param other The matrix to be added
   */
  public void sum(RealMatrix other) {
    int n1 = Math.min(n, other.n);
    int m1 = Math.min(m, other.m);
    for (int i = 0; i < n1; i++) {
      for (int j = 0; j < m1; j++) {
        data[i][j] += other.data[i][j];
      }
    }
  }

  /**
   * Substracts from the matrix the given matrix. The substraction process is component by component
   * x[i,j] = x[i,j] - other[i,j] for all i=1..n and j=1..m
   * @param other The matrix to be substracted
   */
  public void substract(RealMatrix other) {
    int n1 = Math.min(n, other.n);
    int m1 = Math.min(m, other.m);
    for (int i = 0; i < n1; i++) {
      for (int j = 0; j < m1; j++) {
        data[i][j] -= other.data[i][j];
      }
    }
  }
/**
 * Multiplies the matrix for the given matrix.
 * @param other The matrix to multiply
 */
  public void multiply(RealMatrix other) {
    if (other != null && other.rows() == columns()) {
      n = rows();
      m = other.columns();
      int c = columns();
      double[][] newData = new double[n][m];
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          double s = 0.0;
          for (int k = 0; k < c; k++) {
            s += data[i][k] * other.data[k][j];
          }
          newData[i][j] = s;
        }
      }
      data = newData;
    }
  }

  /**
   * Transpose the matrix (rows becomes columns, columns become rows)
   */
  public void transpose() {
    double[][] newData = new double[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        newData[i][j] = data[j][i];
      }
    }
    data = newData;
    int t = n;
    n = m;
    m = t;
  }

   /**
   * Divides each component of the matrix by the given value.
   * x[i,j] = x[i,j] * x for all i=1..n and j=1..m
   * @param x The division factor
   */
  public void divide(double x) {
    if (x != 0.0) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          data[i][j] /= x;
        }
      }
    } else {
      System.err.println("[RealMatrix]. Undefined division by zero.");
    }
  }

  /**
   * Raises each component of the matrix to the power 2.0
   * x[i,j] = x[i,j] ^ 2 for all i=1..n and j=1..m
   */
  public void elementsSquare() {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        data[i][j] *= data[i][j];
      }
    }
  }

  /**
   * Sqrt-root each component of the matrix
   * x[i,j] = x[i,j] ^ 1/2 for all i=1..n and j=1..m
   */
  public void elementsSqrt() {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        data[i][j] = Math.sqrt(data[i][j]);
      }
    }
  }

  /**
   * Calculates the sum of the elements in the given row
   * @param i The row index to be added
   * @return The sum of the elements in the given row. Return 0 if the index is not valid
   */
  public double rowSum(int i) {
    double val = 0.0;
    if (0 <= i && i < n) {
      for (int j = 0; j < m; j++) { val += data[i][j]; }
    }
    return val;
  }

  /**
   * Calculates the sum of the elements in the given column
   * @param j The column index to be added
   * @return The sum of the elements in the given column. Return 0 if the index is not valid
   */
  public double columnSum(int j) {
    double val = 0.0;
    if (0 <= j && j < m) {
      for (int i = 0; i < n; i++) { val += data[i][j]; }
    }
    return val;
  }

  /**
   * Calculates the sum of the elements in the matrix
   * @return The sum of the elements in the matrix
   */
  public double sum() {
    double val = 0.0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) { val += data[i][j]; }
    }
    return val;
  }

  /**
   * Calculates the sum of the elements in the principal diagonal, i.e.
   * sum( x[i,i] ) for all i=1..n
   * @return The sum of the elements in the principal diagonal
   */
  public double diagonalSum() {
    int x = Math.min(n, m);
    double val = 0.0;
    for (int i = 0; i < x; i++) { val += data[i][i]; }
    return val;
  }

  /**
   * Returns the real at position i, j in the matrix
   * @param i The row index
   * @param j The column index
   * @return The real value at position i,j if it is valid, 0.0 in other case
   */
  public double get(int i, int j) {
    double x = 0.0;
    if (data != null && 0 <= i && i < n && 0 <= j && j < m) {
      x = data[i][j];
    } else {
      System.err.print("[RealMatrix] Error. Index out of bound");
    }
    return x;
  }

  /**
   * Determines if the matrix is squared or not
   * @return true if the matrix is squared false otherwise
   */
  public boolean isSquare() {
    return (n == m);
  }

  /**
   * Determines if the matrix is symmetric
   * @return true if the matrix is symmetric, false otherwise
   */
  public boolean isSymmetric() {
    boolean flag = isSquare();
    if (flag) {
      for (int i = 0; flag && i < n - 1; i++) {
        for (int j = i + 1; flag && j < n; j++) {
          flag = (data[i][j] == data[j][i]);
        }
      }
    }
    return flag;
  }

  /**
   * Determines if the matrix is diagonal
   * @return true if the matrix is diagonal, false otherwise
   */
  public boolean isDiagonal() {
    boolean flag = isSymmetric();
    if (flag) {
      for (int i = 0; flag && i < n - 1; i++) {
        flag = (data[i][i] != 0.0);
        for (int j = i + 1; flag && j < n; j++) {
          flag = (data[i][j] == 0.0);
        }
      }
    }
    return flag;
  }

  /**
   * Solves the linear system Mx + B = 0 (If a solution is available)
   * @param bB The vector of independent terms
   * @return double[] of the variable values that satisfy the linear system,
   * null if there is not solution
   */
  public double[] solve(double[]bB) {
    double[] y = (double[]) bB.clone();
    double[] x = null;
    int n = this.columns();
    if (bB.length == n && n == this.rows()) {
      RealMatrix m = new RealMatrix(this);
      int[] perm = new int[n];
      for (int i = 0; i < n; i++) {
        perm[i] = i;
      }
      for (int i = 0; i < n; i++) {
        int k = i;
        while (k < n && Math.abs(m.data[k][k]) <= 1e-12) { k++; }
        if (k < n) {
          if (k != i) {
            int t = perm[i];
            perm[i] = perm[k];
            perm[k] = t;
            double t2;
            t2 = y[i];
            y[i] = y[k];
            y[k] = t2;
            for (int j = 0; j < n; j++) {
              t2 = m.data[i][j];
              m.data[i][j] = m.data[k][j];
              m.data[k][j] = t2;
            }
//            for( int j=0; j<n; j++ ){
//              t2 = M.data[j][i];
//              M.data[j][i] = M.data[j][k];
//              M.data[j][k] = t2;
//            }
          }
          double pivot = m.data[i][i];
          for (int j = 0; j < n; j++) {
            m.data[i][j] /= pivot;
          }
          y[i] /= pivot;
          for (k = 0; k < n; k++) {
            if (k != i) {
              pivot = m.data[k][i];
              for (int j = 0; j < n; j++) {
                m.data[k][j] -= pivot * m.data[i][j];
              }
              y[k] -= pivot * y[i];
            }
          }
        } else {
          if (Math.abs(y[i]) > 1e-6) {
            return null;
          }
        }
      }
      x = y;
    }
    return x;
  }

  /**
   * Converts the matrix to a String
   * @return The string representation of the matrix
   */
  public String toString() {
    String text = "" + n + " " + m + "\n";
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        text += " " + data[i][j];
      }
      text += "\n";
    }
    return text;
  }

  /**
   * Creates the identity matrix off size n*n
   * @param nN Dimension of the identity matrix
   * @return The identity matrix of size n*n
   */
  public static RealMatrix identity(int nN) {
    RealMatrix id = new RealMatrix(nN);
    for (int i = 0; i < nN; i++) {
      id.data[i][i] = 1.0;
    }
    return id;
  }
}
