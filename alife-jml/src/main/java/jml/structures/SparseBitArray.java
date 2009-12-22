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

import java.util.Vector;

import jml.util.sort.Sort;
import jml.util.sort.SortableInt;

/**
 * <p>Title: SparceBitArray </p>
 * <p>Description: Array of Bits.</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class SparseBitArray extends SparseVector {
  
  /**
   * Constructor: Creates a SparseBitArray using True as default value 
   * @param n SparseVector Dimension
   * @param val Vector
   * @param useTrueAsDefault Default value
   */
  public SparseBitArray(int n, Vector val, boolean useTrueAsDefault) {
    super (n, val, new SparseValue(-1));
  }

  /**
   * Constructor: Creates a SparseBitArray 
   * @param n parseVector Dimension
   * @param val Object Vector
   */
  public SparseBitArray(int n, Vector val) {
    super (n, val, null);
  }
  
  /**
   * Gets a boolean if object is found 
   * @param i object searched
   * @return Return true if finds this object or false if not finds this object
   */
  public boolean get(int i) {
    SparseValue x = get(new SparseValue(i));
    if (x == this.getDefaultValue()) { return (this.getDefaultValue() != null); 
    } else { return (this.getDefaultValue() == null); }
  }

  /**
   * Sets a new bit
   * @param i object position 
   * @param value value
   */
  public void set(int i, boolean value) {
    if (value == (this.getDefaultValue() != null)) {
      remove(new SparseValue(i));
    } else {
      set (new SparseValue(i));
    }
  }
  
  /**
   * No idea
   * @param i Object position
   */
  public void flip(int i) {
    SortableInt si = new SortableInt(i);
    int index = Sort.findLow2High(this.getValues(), si, this.getOrder());
    if (index < this.getValues().size() && ((SparseValue) this.getValues ().get(index)).getIndex() == i) {
      this.getValues().remove(index);
    } else {
      this.getValues().add(index, si);
    }
  }
  
  /**
   * Gets vector values
   * @return Object values
   */
  public Vector get() {
    return this.getValues();
  }
  
  /**
   * Sets vector values
   * @param indices Object values
   */
  public void set(Vector indices) { this.setValues(indices); }

  /**
   * Gets the dimension
   * @return Dimension
   */
  public int size() {
    return dimension();
  }

}
