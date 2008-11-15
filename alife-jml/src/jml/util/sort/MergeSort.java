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
 * <p>Title: MergeSort</p>
 * <p>Description: A MergeSort algorithm using the same Vector (without additional memory)</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class MergeSort extends Sort {
  
  /**
   * Object BubbleSort for order
   */	
  protected BubbleSort bubble = new BubbleSort();

  /**
   * Default constructor
   */
  public MergeSort() { }

  /**
   * Sorts a vector of objects
   * @param a vector to be sorted
   * @param order verify if the vector is sorted from low to high or from high to low
   */
  public void low2high(Object[] a, Order order) {
    int n = a.length;
    if (n >= 4) {
      int nizq = n / 2;
      int nder = n - nizq;
      Object[] aIzq = new Object[nizq];
      Object[] aDer = new Object[nder];
      for (int i = 0; i < nizq; i++) { aIzq[i] = a[i]; }
      for (int i = 0; i < nder; i++) { aDer[i] = a[nizq + i]; }
      low2high(aIzq, order);
      low2high(aDer, order);
      int k = 0;
      int izq = 0;
      int der = 0;
      while (izq < nizq && der < nder) {
        if (order.lessThan(aIzq[izq], aDer[der])) {
          a[k] = aIzq[izq];
          izq++;
        } else {
          a[k] = aDer[der];
          der++;
        }
        k++;
      }
      while (izq < nizq) {
        a[k] = aIzq[izq];
        izq++;
        k++;
      }
      while (der < nder) {
        a[k] = aDer[der];
        der++;
        k++;
      }
    } else {
      bubble.low2high(a, order);
    }
  }

  /**
   * Sorts a Object of objects
   * @param a Object to be sorted
   * @param order verify If the vector is sorted from low to high or from high to low
   */
  public void high2low(Object[] a, Order order) {
    int n = a.length;
    if (n >= 4) {
      int nizq = n / 2;
      int nder = n - nizq;
      Object[] aIzq = new Object[nizq];
      Object[] aDer = new Object[nder];
      for (int i = 0; i < nizq; i++) { aIzq[i] = a[i]; }
      for (int i = 0; i < nder; i++) { aDer[i] = a[nizq + i]; }
      high2low(aIzq, order);
      high2low(aDer, order);
      int k = 0;
      int izq = 0;
      int der = 0;
      while (izq < nizq && der < nder) {
        if (order.greaterThan(aIzq[izq], aDer[der])) {
          a[k] = aIzq[izq];
          izq++;
        } else {
          a[k] = aDer[der];
          der++;
        }
        k++;
      }
      while (izq < nizq) {
        a[k] = aIzq[izq];
        izq++;
        k++;
      }
      while (der < nder) {
        a[k] = aDer[der];
        der++;
        k++;
      }
    } else {
      bubble.high2low(a, order);
    }
  }

  /**
   * Sorts a double[] of objects
   * @param a double[] to be sorted
   */
  public void low2high(double[] a) {
    int n = a.length;
    if (n >= 4) {
      int nizq = n / 2;
      int nder = n - nizq;
      double[] aIzq = new double[nizq];
      double[] aDer = new double[nder];
      for (int i = 0; i < nizq; i++) { aIzq[i] = a[i]; }
      for (int i = 0; i < nder; i++) { aDer[i] = a[nizq + i]; }
      low2high(aIzq);
      low2high(aDer);
      int k = 0;
      int izq = 0;
      int der = 0;
      while (izq < nizq && der < nder) {
        if (aIzq[izq] < aDer[der]) {
          a[k] = aIzq[izq];
          izq++;
        } else {
          a[k] = aDer[der];
          der++;
        }
        k++;
      }
      while (izq < nizq) {
        a[k] = aIzq[izq];
        izq++;
        k++;
      }
      while (der < nder) {
        a[k] = aDer[der];
        der++;
        k++;
      }
    } else {
      bubble.low2high(a);
    }
  }

  /**
   * Sorts a double[] of objects
   * @param a double[] to be sorted
   */
  public void high2low(double[] a) {
    int n = a.length;
    if (n >= 4) {
      int nizq = n / 2;
      int nder = n - nizq;
      double[] aIzq = new double[nizq];
      double[] aDer = new double[nder];
      for (int i = 0; i < nizq; i++) { aIzq[i] = a[i]; }
      for (int i = 0; i < nder; i++) { aDer[i] = a[nizq + i]; }
      high2low(aIzq);
      high2low(aDer);
      int k = 0;
      int izq = 0;
      int der = 0;
      while (izq < nizq && der < nder) {
        if (aIzq[izq] > aDer[der]) {
          a[k] = aIzq[izq];
          izq++;
        } else {
          a[k] = aDer[der];
          der++;
        }
        k++;
      }
      while (izq < nizq) {
        a[k] = aIzq[izq];
        izq++;
        k++;
      }
      while (der < nder) {
        a[k] = aDer[der];
        der++;
        k++;
      }
    } else {
      bubble.high2low(a);
    }
  }

   
  /**
   * Sorts a int[] of objects
   * @param a int[] to be sorted
   */
  public void low2high(int[] a) {
    int n = a.length;
    if (n >= 4) {
      int nizq = n / 2;
      int nder = n - nizq;
      int[] aIzq = new int[nizq];
      int[] aDer = new int[nder];
      for (int i = 0; i < nizq; i++) { aIzq[i] = a[i]; }
      for (int i = 0; i < nder; i++) { aDer[i] = a[nizq + i]; }
      low2high(aIzq);
      low2high(aDer);
      int k = 0;
      int izq = 0;
      int der = 0;
      while (izq < nizq && der < nder) {
        if (aIzq[izq] < aDer[der]) {
          a[k] = aIzq[izq];
          izq++;
        } else {
          a[k] = aDer[der];
          der++;
        }
        k++;
      }
      while (izq < nizq) {
        a[k] = aIzq[izq];
        izq++;
        k++;
      }
      while (der < nder) {
        a[k] = aDer[der];
        der++;
        k++;
      }
    } else {
      bubble.low2high(a);
    }
  }

  /**
   * Sorts a int[] of objects
   * @param a int[] to be sorted
   */
  public void high2low(int[] a) {
    int n = a.length;
    if (n >= 4) {
      int nizq = n / 2;
      int nder = n - nizq;
      int[] aIzq = new int[nizq];
      int[] aDer = new int[nder];
      for (int i = 0; i < nizq; i++) { aIzq[i] = a[i]; }
      for (int i = 0; i < nder; i++) { aDer[i] = a[nizq + i]; }
      high2low(aIzq);
      high2low(aDer);
      int k = 0;
      int izq = 0;
      int der = 0;
      while (izq < nizq && der < nder) {
        if (aIzq[izq] > aDer[der]) {
          a[k] = aIzq[izq];
          izq++;
        } else {
          a[k] = aDer[der];
          der++;
        }
        k++;
      }
      while (izq < nizq) {
        a[k] = aIzq[izq];
        izq++;
        k++;
      }
      while (der < nder) {
        a[k] = aDer[der];
        der++;
        k++;
      }
    } else {
      bubble.high2low(a);
    }
  }
 
}
