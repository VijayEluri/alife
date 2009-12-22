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

/**
 * <p>Title: SparseValue</p>
 * <p>Description: Stores a position of a vector where this position</p>
 * have a value different of the default value.
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class SparseValue implements Cloneable {
  /**
   * The position of a vector 
   */
  private int index;

  /**
   * Constructor: Creates a SparseValue with an index  
   * @param index The object stored by the index
   */
  public SparseValue (int index) {
    this.index = index;
  }

  /**
   * To clone a SparseValue
   * @return The new SparseValue
   */
  public Object clone() {
    return new SparseValue (index);
  }
  
  /**
   * Return default value 
   * @param x A SparseValue
   * @return default value 
   */
  public boolean sameValue (SparseValue x) {
    return false;
  }

  /**
   * Converts the index to a string
   * @return A string with the index
   */
  public String toString() {
    return "" + index;
  }
  
  /**
   * Returns the index  
   * @return The index
   */
  public int getIndex() {
	    return index;
	  }
   
}
