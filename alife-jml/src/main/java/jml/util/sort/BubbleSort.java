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
package jml.util.sort;
import jml.math.abs.Order;
/**
 * <p>Title: BubbleSort</p>
 * <p>Description: A BubbleSort algorithm for order</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class BubbleSort extends Sort {
  /**
   * Default constructor
   */
  public BubbleSort() { }

  /**
   * Sorts a vector of objects
   * @param a vector to be sorted
   * @param order Verify If the vector is sorted from low to high or from high to low
   */
  public void low2high(Object[] a, Order order) {
    int n = a.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = i + 1; j < n; j++) {
        if (order.lessThan(a[j], a[i])) {
          Object x = a[i];
          a[i] = a[j];
          a[j] = x;
        }
      }
    }
  }

  /**
   * Sorts a vector of objects
   * @param a vector to be sorted
   * @param order Verify If the vector is sorted from low to high or from high to low
   */
  public void high2low(Object[] a, Order order) {
    int n = a.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = i + 1; j < n; j++) {
        if (order.greaterThan(a[j], a[i])) {
          Object x = a[i];
          a[i] = a[j];
          a[j] = x;
        }
      }
    }
  }

  /**
   * Sorted from low to high a double[]
   * @param a double[] to be sorted
   */
  public void low2high(double[] a) {
    int n = a.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = i + 1; j < n; j++) {
        if (a[j] < a[i]) {
          double x = a[i];
          a[i] = a[j];
          a[j] = x;
        }
      }
    }
  }

  /**
   * Sorted from high to low a double[]
   * @param a double[] to be sorted
   */
  public void high2low(double[] a) {
    int n = a.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = i + 1; j < n; j++) {
        if (a[j] > a[i]) {
          double x = a[i];
          a[i] = a[j];
          a[j] = x;
        }
      }
    }
  }

  /**
   * Sorted from low to high a int[]
   * @param a int[] to be sorted
   */
  public void low2high(int[] a) {
    int n = a.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = i + 1; j < n; j++) {
        if (a[j] < a[i]) {
          int x = a[i];
          a[i] = a[j];
          a[j] = x;
        }
      }
    }
  }

  /**
   * Sorted from high to low a int[]
   * @param a int[] to be sorted
   */
  public void high2low(int[] a) {
    int n = a.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = i + 1; j < n; j++) {
        if (a[j] > a[i]) {
          int x = a[i];
          a[i] = a[j];
          a[j] = x;
        }
      }
    }
  }

}
