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
import jml.math.abs.Order;

/**
 * <p>Title: SparseValueOrder</p>
 * <p>Description: Compares two SparseValue.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class SparseValueOrder extends Order {

  /**
   * Determines if the object is less than (in some order) the given object
   * @param x Object to Compare
   * @param y Object to Compare
   * @return true if the object is less than the given object x. false in other case
   */
  public boolean lessThan (Object x, Object y) {
    return (((SparseValue) x).getIndex() < ((SparseValue) y).getIndex());
  }

  /**
   * Determines if the object is equal to the given object
   * @param x Object to Compare
   * @param y Object to Compare
   * @return true if the object is equal to the given object x. false in other case
   */
  public boolean equalTo (Object x, Object y) {
    return (((SparseValue) x).getIndex() == ((SparseValue) y).getIndex());
  }
  
  /**
   * Return default value 
   * @param x A SparseValue
   * @return default value 
   */
  public boolean sameValue (SparseValue x) {
    return false;
  }
}
