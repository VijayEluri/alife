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
import java.util.Vector;

import jml.math.abs.Order;

/**
 * <p>Title: Sort</p>
 * <p>Description: Abstract Sorting algorithm for vectors</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public abstract class Sort {

  /**
   * Sorts a object low to high
   * @param a Object to be sorted
   * @param order Object to order
   */
  public abstract void low2high(Object[] a, Order order);
  
  /**
   * Sorts high to low a object
   * @param a Object to be sorted
   * @param order Object to order
   */
  public abstract void high2low(Object[] a, Order order);

  /**
   * Sorts a int[]low to high 
   * @param a Object to be sorted
   */
  public abstract void low2high(int[] a);
  
  /**
   * sorts a int[] high to low 
   * @param a Object to be sorted
   */
  public abstract void high2low(int[] a);

  /**
   * Sorts a double[] low to high 
   * @param a Object to be sorted
   */
  public abstract void low2high(double[] a);
  
  /**
   * sorts a double[] high to low 
   * @param a Object to be sorted
   */
  public abstract void high2low(double[] a);

   
  /**
   * Order in the vector
   * than the element given. The object should be sorted low to high
   * @param a Vector of elements (should be sorted) 
   * @param order verify to order  
   */
  public void low2high(Vector a, Order order) {
    Object[] obj = a.toArray();
    low2high(obj, order);
    int n = obj.length;
    for (int i = 0; i < n; i++) { a.set(i, obj[i]); }
  }
  
  
  /**
   * Orders in the vector
   * than the element given. The object should be sorted high to low
   * @param a Vector of elements (should be sorted) 
   * @param order verify to order  
   */
  public void high2low(Vector a, Order order) {
    Object[] obj = a.toArray();
    high2low(obj, order);
    int n = obj.length;
    for (int i = 0; i < n; i++) { a.set(i, obj[i]); }
  }
  
  /**
   * Search for the position of the lowest element in the vector that is bigger
   * than the element given. The object should be sorted low to high
   * @param sorted Vector of elements (should be sorted) 
   * @param x Element to be located
   * @param order verify to order  
   * @return position this object
   */
  public static int findHigh2Low(Vector sorted, Object x, Order order) {
    Object[] a = sorted.toArray();
    return findHigh2Low(a, x, order);
  }
  
  /**
   * Search for the position of the lowest element in the vector that is bigger
   * than the element given. The array should be sorted high to low
   * @param sorted Vector of elements (should be sorted) 
   * @param x Element to be located
   * @param order verify to order  
   * @return position this object
   */
  public static int findLow2High(Vector sorted, Object x, Order order) {
    Object[] a = sorted.toArray();
    return findLow2High(a, x, order);
  }

  /**
   * Search for the position of the lowest element in the object that is bigger
   * than the element given. The array should be sorted high to low
   * @param sorted Object of elements (should be sorted) 
   * @param x Element to be located
   * @param order verify to order  
   * @return position this object
   */
  public static int findHigh2Low(Object[] sorted, Object x, Order order) {
    int n = sorted.length;
    int a = 0;
    int b = n - 1;
    if (n == 0 || order.greaterThan(x, sorted[a]) || order.equalTo(x, sorted[a])) { return 0; }
    if (order.lessThan(x, sorted[b])) { return b + 1; }
    if (order.equalTo(x, sorted[b])) { return b; }
    while (a + 1 < b) {
      int m = (a + b) / 2;
      if (order.equalTo(x, sorted[m])) { return m; }
      if (order.greaterThan(x, sorted[m])) {
        b = m;
      } else {
        a = m;
      }
    }
    return b;
  }

  /**
   * Search for the position of the lowest element in the objects that is bigger
   * than the element given. The object should be sorted low to high
   * @param sorted Object of elements (should be sorted) 
   * @param x Element to be located
   * @param order verify to order  
   * @return position this object
   */
  public static int findLow2High(Object[] sorted, Object x, Order order) {
    int n = sorted.length;
    int a = 0;
    int b = n - 1;
    if (n == 0 || order.lessThan(x, sorted[a]) || order.equalTo(x, sorted[a])) { return 0; }
    if (order.greaterThan(x, sorted[b])) { return b + 1; }
    if (order.equalTo(x, sorted[b])) { return b; }
    while (a + 1 < b) {
      int m = (a + b) / 2;
      if (order.equalTo(x, sorted[m])) { return m; }
      if (order.lessThan(x, sorted[m])) {
        b = m;
      } else {
        a = m;
      }
    }
    return b;
  }

  /**
   * Search for the position of the lowest element in the objects that is bigger
   * than the element given.
   * @param sorted Object of elements (should be sorted) 
   * @param x Element to be located
   * @param order verify to order 
   * @return position this object
   */
  public static int find(Object[] sorted, Object x, Order order) {
    int n = sorted.length;
    if (n > 0 && order.lessThan(sorted[0], sorted[n - 1])) {
      return findLow2High(sorted, x, order);
    } else {
      return findHigh2Low(sorted, x, order);
    }
  }
}
