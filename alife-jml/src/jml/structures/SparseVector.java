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
package jml.structures;
import java.util.Enumeration;
import java.util.Vector;

import jml.basics.Cloner;
import jml.util.sort.Sort;


/**
 * <p>Title: SparseVector</p>
 * <p>Description: Stores a vector with the positions of a vector where that positions</p>
 * have a value different of the default value. The values of every position are equal.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class SparseVector implements Cloneable {
  
  /**
   * Object used to order an array SparseValue
   */
  private SparseValueOrder order = new SparseValueOrder();
  
  /**
   * The default value
   */
  private SparseValue defaultValue;
  
  /**
   * SparseVector Dimension
   */
  private int n = 0;
  
  /**
   * The positions with value different to defaultValue
   */
  private Vector values = new Vector ();

  /**
   * Get Enumeration for go throught to vector
   * @return Object Enumeration 
   */
  public Enumeration elements() { return values.elements(); }
  
  /**
   * Constructor: Creates a SparseVector giving as parameter its dimension, the vector and default value 
   * @param n Dimension
   * @param val The vector with the positions 
   * @param defaultValue default value
   */
  public SparseVector(int n, Vector val, SparseValue defaultValue) {
    this.n = n;
    this.values = val;
    this.defaultValue = defaultValue;
  }
  
  /**
   * Removes a SparseValue vector
   * @param index Object to be Removed
   * @return True if removed, and False if not removed 
   */ 
  public boolean remove(SparseValue index) {
    int i = Sort.findLow2High(values, index, order);
    boolean flag = false;
    if (i < values.size()) {
      SparseValue x = (SparseValue) values.get(i);
      flag = order.equalTo(x, index);
      if (flag) { values.remove(i); }
    }
    return flag;
  }
  
  /**
   * Locate SparseValue into Vector
   * @param y Object SparseValue for find it position
   * @return SparseValue position
   */
  public int locate(SparseValue y) {
    return Sort.findLow2High(values, y, order);
  }
  
  /**
   * Gets a SparseValue of the Vector
   * @param index A object SparseValue 
   * @return A object SparseValue or the default value
   */
  public SparseValue get(SparseValue index) {
    int i = Sort.findLow2High(values, index, order);
    if (i >= values.size()) { return defaultValue; 
    } else {
      SparseValue x = (SparseValue) values.get(i);
      if (order.equalTo(x, index)) { return x; 
      } else { return defaultValue; }
    }
  }

  /**
   * Adds a new SparseValue into Vector 
   * @param y A SparseValue
   */ 
  public void set(SparseValue y) {
    int i = Sort.findLow2High(values, y, order);
    if (i >= values.size()) {
      if (!y.sameValue(defaultValue)) {
        values.add(y);
      }
    } else {
      SparseValue z = (SparseValue) values.get(i);
      if (order.equalTo(z, y)) {
        if (y.sameValue(defaultValue)) {
          values.remove(i);
        } else {
          values.set(i, y);
        }
      } else {
        if (!y.sameValue(defaultValue)) {
          values.add(i, y);
        }
      }
    }
  }
  
  /**
   * To clone a SparseVector
   * @return The new SparseVector
   */
  public Object clone() {
    Vector xval = Cloner.clone(values);
    return new SparseVector(n, xval, (SparseValue) defaultValue.clone());
  }
  
  /**
   * Sets the dimension
   * @return The dimension
   */
  public int dimension() { return n; }
  

  /**
   * Converts the SparseVector structure to a string
   *  @return A string with the SparseVector structure
   */
  public String toString() {
    StringBuffer sb = new StringBuffer();
    Enumeration iter = values.elements();
    while (iter.hasMoreElements()) {
      sb.append(iter.nextElement().toString());
      sb.append(" ");
    }
    return sb.toString();
  }
  
  /**
   * Gets the object order
   * @return SparseValueOrder for order
   */
  public SparseValueOrder getOrder() {
	  return order;
  }
  
  /**
   * Gets the default value  
   * @return default value, an SparseValue
   */
  public SparseValue getDefaultValue() {
	  return defaultValue;
  }
  
  /**
   * Gets the vector values
   * @return Object Vector
   */
  public Vector getValues() {
	  return values;
  }
  
  /**
   * Sets the vector values
   * @param values Object Vector
   */
  public void setValues(Vector values) {
	  this.values = values;
  }
  
  /**
   * Sets the Dimension  
   * @param n Dimension
   */
  public void setDimension(int n) {
	  this.n = n;
  }  
}
