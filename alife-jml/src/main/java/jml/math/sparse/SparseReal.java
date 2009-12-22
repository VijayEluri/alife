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
package jml.math.sparse;

import jml.structures.SparseValue;

/**
 * <p>Title: SparseReal</p>
 * <p>Description: Descending class of SparseValue, where the position stored in the index
 * have a value defined in the constructor.</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class SparseReal extends SparseValue {
	/**
	 *  The value of the position indicates for the index
	 */
  public double value;
	/**
	 * Creates a SparseReal with a index and a value.
	 * @param index1 The position with different value
	 * @param value1 Value for the position stored in the index
	 */
  public SparseReal(int index1, double value1) {
    super(index1);
    value = value1;
  }
	/**
	 * To clone the SparseReal structure.
	 * @return The new SparseReal. 
	 */
  public Object clone() {
    return new SparseReal(this.getIndex(), value);
  }
	/**
	 * Compares value with the value of x
	 * @param x The SparseValue to compare
	 * @return true if the value is equal to the value of x  
	 */
  public boolean sameValue(SparseValue x) { return (value == ((SparseReal) x).value); }
	/**
	 * Converts the SparseReal structure to a String 
	 * @return A string with the SparseReal structure
	 */
  public String toString() {
    return "" + this.getIndex() + " " + value;
  }
}
