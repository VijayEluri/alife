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
package jml.math.abs;
import java.util.Enumeration;

import jml.basics.Cloner;
import jml.object.sources.ObjectSource;
/**
 * <p>Title: Statistics</p>
 * <p>Description: This class provides methods for calculating 
 * statistical information of different objects.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Statistics {
	/**
	 * For addition operations 
	 */	
  private Addition sum;
  /**
   * For product operations
   */
  private ScalarProduct product;
  /**
   * For sqr operations
   */
  private Square sqr;
  /**
   * Length of the object (array)
   */
  private int n = 0;
  /**
   * Creates an Statistics object
   * @param add Addition object
   * @param prod Product object
   * @param sq Square object
   */
  public Statistics(Addition add, ScalarProduct prod, Square sq) {
    sum = add;
    product = prod;
    sqr = sq;
  }

  /**
   * Calculates the average Result object of a given collection of Result objects.
   * All the objects in the argument collection should belong to the
   * same class. In other case the average will be not calculated.
   * @param x A collection of Result objects
   * @return The average Result object of a given collection of Result objects
   */
  public Object average(Object[] x) {
    Object avg = null;
    if (x != null && x.length > 0) {
      avg = Cloner.clone(x[0]);
      n = x.length;
      for (int i = 1; i < n; i++) {
        sum.fastSum(avg, x[i]);
      }
      product.fastDivide(avg, n);
    }
    return avg;
  }

  /**
   * Calculates the average Result object of a given collection of Result objects.
   * All the objects in the argument collection should belong to the
   * same class. In other case the average will be not calculated.
   * @param x A collection of Result objects
   * @return The average Result object of a given collection of Result objects
   */
  public Object average(ObjectSource x) {
    Object avg = null;
    Enumeration iter = x.elements();
    if (iter.hasMoreElements()) {
      n = 0;
      avg = Cloner.clone(iter.nextElement());
      while (iter.hasMoreElements()) {
        sum.fastSum(avg, iter.nextElement());
        n++;
      }
      product.fastDivide(avg, n);
    }
    return avg;
  }

  /**
   * Calculates the variance Result object of a given collection of Result objects.
   * All the objects in the argument collection should belong to the
   * same class. In other case the variance will be not calculated.
   * @param x A collection of Result objects
   * @return The variance Result object of a given collection of Result objects
   */
  public Object variance(Object[] x) {
    return variance(x, average(x));
  }

  /**
   * Calculates the variance Result object of a given collection of Result objects.
   * All the objects in the argument collection should belong to the
   * same class. In other case the variance will be not calculated.
   * @param x A collection of Result objects
   * @param avg The average statistics of the collection
   * @return The variance Result object of a given collection of Result objects
   */
  public Object variance(Object[] x, Object avg) {
    Object variance = null;
    if (x != null && x.length > 0) {
      variance = Cloner.clone(x[0]);
      sum.fastSubstract(variance, avg);
      sqr.fastSqr(variance);
      n = x.length;
      for (int i = 1; i < n; i++) {
        Object c = Cloner.clone(x[i]);
        sum.fastSubstract(c, avg);
        sqr.fastSqr(c);
        sum.fastSum(variance, c);
      }
      product.fastDivide(variance, n - 1);
    }
    return variance;
  }

  /**
   * Calculates the variance Result object of a given collection of Result objects.
   * All the objects in the argument collection should belong to the
   * same class. In other case the variance will be not calculated.
   * @param x A collection of Result objects
   * @return The variance Result object of a given collection of Result objects
   */
  public Object variance(ObjectSource x) {
    return variance(x, average(x));
  }

  /**
   * Calculates the variance Result object of a given collection of Result objects.
   * All the objects in the argument collection should belong to the
   * same class. In other case the variance will be not calculated.
   * @param x A collection of Result objects
   * @param avg The average statistics of the collection
   * @return The variance Result object of a given collection of Result objects
   */
  public Object variance(ObjectSource x, Object avg) {
    Object variance = null;
    Enumeration iter = x.elements();
    if (iter.hasMoreElements()) {
      n = 0;
      variance = Cloner.clone(iter.nextElement());
      sum.fastSubstract(variance, avg);
      sqr.fastSqr(variance);
      while (iter.hasMoreElements()) {
        Object c = Cloner.clone(iter.nextElement());
        sum.fastSubstract(c, avg);
        sqr.fastSqr(c);
        sum.fastSum(variance, c);
        n++;
      }
      product.fastDivide(variance, n - 1);
    }
    return variance;
  }

  /**
   * Calculates the standar deviation Result object of a given collection of Result objects.
   * All the objects in the argument collection should belong to the
   * same class. In other case the standard deviation will be not calculated.
   * @param x A collection of Result objects
   * @return The standar deviation object of a given collection of Result objects
   */
  public Object stdDeviation(Object[] x) {
    return stdDeviation(x, average(x));
  }

  /**
   * Calculates the standar deviation Result object of a given collection of Result objects.
   * All the objects in the argument collection should belong to the
   * same class. In other case the standard deviation will be not calculated.
   * @param x A collection of Result objects
   * @param avg The average statistics of the collection
   * @return The standar deviation object of a given collection of Result objects
   */
  public Object stdDeviation(Object[] x, Object avg) {
    Object r = variance(x, avg);
    sqr.fastSqrt(r);
    return r;
  }

  /**
   * Calculates the standar deviation Result object of a given collection of Result objects.
   * All the objects in the argument collection should belong to the
   * same class. In other case the standard deviation will be not calculated.
   * @param x A collection of Result objects
   * @return The standar deviation object of a given collection of Result objects
   */
  public Object stdDeviation(ObjectSource x) {
    return stdDeviation(x, average(x));
  }

  /**
   * Calculates the standar deviation Result object of a given collection of Result objects.
   * All the objects in the argument collection should belong to the
   * same class. In other case the standard deviation will be not calculated.
   * @param x A collection of Result objects
   * @param avg The average statistics of the collection
   * @return The standar deviation object of a given collection of Result objects
   */
  public Object stdDeviation(ObjectSource x, Object avg) {
    Object r = variance(x, avg);
    sqr.fastSqrt(r);
    return r;
  }

  /**D
  public Object std_error(Object[] x) {
    return std_error(x, average(x));
  }

  /**
   * Calculates the standar error Result object of a given collection of Result objects.
   * All the objects in the argument collection should belong to the
   * same class. In other case the standard deviation will be not calculated.
   * @param x A collection of Result objects
   * @param avg The average statistics of the collection
   * @return The standar error object of a given collection of Result objects
   */
  public Object stdError(Object[] x, Object avg) {
    Object r = stdDeviation(x, avg);
    product.fastDivide(r, Math.sqrt(x.length));
    return r;
  }

  /**
   * Calculates the standar error Result object of a given collection of Result objects.
   * All the objects in the argument collection should belong to the
   * same class. In other case the standard deviation will be not calculated.
   * @param x A collection of Result objects
   * @return The standar error object of a given collection of Result objects
   */
  public Object stdError(ObjectSource x) {
    return stdError(x, average(x), n);
  }

  /**
   * Calculates the standar error Result object of a given collection of Result objects.
   * All the objects in the argument collection should belong to the
   * same class. In other case the standard deviation will be not calculated.
   * @param x A collection of Result objects
   * @param avg The average statistics of the collection
   * @param nN Lenght of the object
   * @return The standar error object of a given collection of Result objects
   */
  public Object stdError(ObjectSource x, Object avg, int nN) {
    n = nN;
    Object r = stdDeviation(x, avg);
    product.fastDivide(r, Math.sqrt(n));
    return r;
  }

}
