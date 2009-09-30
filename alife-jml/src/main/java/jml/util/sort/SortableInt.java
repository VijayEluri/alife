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

/**
 * <p>Title: SortableInt </p>
 * <p>Description: To compare a int (Stored in this class) with other int.</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class SortableInt extends SortableObject implements Cloneable {
  
  /**
   * Object int for verify
   */
  private int value;

  /**
   * Constructor: Creates a SortableInt set a value object. 
   * @param value Value object
   */
  public SortableInt(int value) {
    this.value = value;
  }

  /**
   * Verify if a value is equals a SortableObject
   * @param parm1 The SortableObject to compare
   * @return true if the object is equal than the given object parm1. false in other case
   */
  public boolean equalTo(SortableObject parm1) {
    return (value == ((SortableInt) parm1).value);
  }
  
  /**
   * To clone a SortableInt
   * @return The new SortableInt
   */
  public Object clone() {
    return new SortableInt(value);
  }
  
  /**
   * Verify if a value is less a SortableObject
   * @param parm1 Object compare to value
   * @return true if the object is less than the given object parm1. false in other case
   */
  public boolean lessThan(SortableObject parm1) {
    return (value < ((SortableInt) parm1).value);
  }
  
  /**
   * Get object value
   * @return Object value
   */
  public int getValue() {
	 return value;
  }
  
  /**
   * Set a object in value
   * @param value Object value
   */
  public void setValue(int value) {
  this.value = value;	  
  }
}
