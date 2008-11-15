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

import jml.basics.Cloner;
import jml.math.abs.Order;
import jml.util.sort.MergeSort;

/**
 * <p>Title: Eigen</p>
 * <p>Description: Calculates the eigenvectors or the eigenvalues.</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class Eigen {
	/**
	 * Eigen values
	 */
  protected double[] values;
  /**
   * Eigen vectors
   */
  protected RealMatrix vectors;
  /**
   * The matrix
   */
  protected RealMatrix a;
  /**
   * Epsilon
   */
  private double EPSILON = 1e-6;
  /**
   * Creates an Eigen object with a matrix
   * @param a The Matrix
   */
  public Eigen(RealMatrix a) {
    this.a = new RealMatrix(a);
    evaluate();
  }
  /**
   * Creates an Eigen object with a matrix with a different epsilon
   * @param a The Matrix
   * @param epsilon The new epsilon
   */
  public Eigen(RealMatrix a, double epsilon) {
    EPSILON = epsilon;
	this.a = new RealMatrix(a);
    evaluate();
  }
  /**
   * Calculates the eigenvalues
   * @return The eigenvalues
   */
  public double[] getEigenValues() {
    return values;
  }

  /**
   * Calculates the eigenvectors
   * @return RealMatrix The eigenvectors
   */
  public RealMatrix getEigenVectors() {
    return vectors;
  }

  /**
   * Calculates the jacobi matrix
   * @param p p
   * @param q q
   * @return The jacobi matrix
   */
  protected RealMatrix Jacobi(int p, int q) {
    RealMatrix J = RealMatrix.identity(a.rows());
    double tau = (a.data[q][q] - a.data[p][p]) / (2.0 * a.data[p][q]);
    double tauRoot = Math.sqrt(1.0 + tau * tau);
//    double t1 = -tau + tau_root;
//    double t2 = -tau - tau_root;
//    double t = t2;
//    if( Math.abs( t1 ) < Math.abs( t2 ) ){ t = t1; }
    double t = 1.0 / (Math.abs(tau) + tauRoot);
    if (tau < 0.0) { t = -t; }
    double c = 1.0 / Math.sqrt(1.0 + t * t);
    double s = t * c;
    J.data[p][p] = c;
    J.data[q][q] = c;
    J.data[p][q] = s;
    J.data[q][p] = -s;
    return J;
  }

  /**
   * If the x is less than EPSILON return zero 
   * @param x Number
   * @return true if x is less than EPSILON
   */
  public double zeroCheck(double x) {
    if (Math.abs(x) < EPSILON) {
      return 0.0;
    } else {
      return x;
    }
  }

  /**
   * Calculates the Eigenvalues-vectors of a matrix  if that is symmetric
   */
  public void evaluateSymmetric() {
    int n = a.rows();
    vectors = RealMatrix.identity(n);
    double s;
    double c;
    double cc;
    double ss;
    double sc;
    double App;
    double Aqq;
    double Apq;
    boolean flag = false;
    do {
      for (int p = 0; p < n - 1; p++) {
        for (int q = p + 1; q < n; q++) {
          if (a.data[p][q] != 0.0) {
            RealMatrix J = Jacobi(p, q);

            s = J.data[p][q];
            c = J.data[p][p];
            ss = s * s;
            sc = s * c;
            cc = c * c;
            App = a.data[p][p];
            Aqq = a.data[q][q];
            Apq = a.data[p][q];
            for (int r = 0; r < n; r++) {
              if (r != p && r != q) {
                double Arp = a.data[r][p];
                double Arq = a.data[r][q];
                a.data[r][p] = zeroCheck(c * Arp - s * Arq);
                a.data[r][q] = zeroCheck(s * Arp + c * Arq);
                a.data[p][r] = a.data[r][p];
                a.data[q][r] = a.data[r][q];
              }
            }
            a.data[p][p] = zeroCheck(cc * App - 2.0 * sc * Apq + ss * Aqq);
            a.data[p][q] = zeroCheck(sc * (App - Aqq) + (cc - ss) * Apq);
            a.data[q][q] = zeroCheck(ss * App + 2.0 * sc * Apq + cc * Aqq);
            a.data[q][p] = a.data[p][q];
            vectors.multiply(J);
          }
        }
      }
      flag = a.isDiagonal();
    } while (!flag);

    EigenPair[] v = new EigenPair[n];
    for (int i = 0; i < n; i++) {
      v[i] = new EigenPair(a.data[i][i], vectors.getColumn(i));
    }
    MergeSort sort = new MergeSort();
    sort.high2low(v, new EigenPairOrder());

    double[] x = new double[n];
    for (int i = 0; i < n; i++) {
      EigenPair ep = (EigenPair) v[i];
      a.data[i][i] = x[i] = ep.value;
      for (int j = 0; j < n; j++) {
        vectors.data[j][i] = ep.vector[j];
      }
    }
    values = x;
  }

  /**
   * Calculates the Eigenvalues-vectors
   */
  public void evaluate() {
    if (a.isSymmetric()) {
      evaluateSymmetric();
    } else {
      System.out.println("EigenValues-Vectors implemented for symmetric matrices only");
    }
  }
}
/**
 * <p>Title: EigenPair</p>
 * <p>Description: Class for Eigen.</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
class EigenPair implements Cloneable {
	/**
	 * value
	 */
  protected double value = 0.0;
  /**
   * vector
   */
  protected double[] vector = null;

  /**
   * Creates a EigenPair object
   * @param value1 value
   * @param vector1 vector
   */
  public EigenPair(double value1, double[] vector1) {
    value = value1;
    vector = vector1;
  }
/**
 * Clone an EigenPair object 
 * @return The clone
 */
  public Object clone() {
    return new EigenPair(value, (double[]) Cloner.clone(vector));
  }
}
/**
 * <p>Title: EigenOrder</p>
 * <p>Description: Determines the order of EigenPair objects.</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
class EigenPairOrder extends Order {
  /**
   * Determines if the object is less than (in some order) the given object
   * @param x The first object 
   * @param y The second object
   * @return true if the object is less than the given object x. false in other case
   */
  public boolean lessThan(Object x, Object y) {
    return (((EigenPair) x).value < ((EigenPair) y).value);
  }
  /**
   * Determines if the object is equal to the given object
   * @param x The first object
   * @param y The second object
   * @return true if the object is equal to the given object x. false in other case
   */
  public boolean equalTo(Object x, Object y) {
    return (((EigenPair) x).value == ((EigenPair) y).value);
  }
}
