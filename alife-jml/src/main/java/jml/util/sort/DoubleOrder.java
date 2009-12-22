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
 * <p>Title: DoubleOrder </p>
 * <p>Description: Compares to doubles</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class DoubleOrder extends Order {
	
  /**
   * Determines if the object is less than (in some order) the given object
   * @param one Comparison object1 (double)
   * @param two Comparison object2 (double) 
   * @return true if the object one is less than the given object two. false in other case
   */	
  public boolean lessThan(Object one, Object two) {
    return (((Double) one).doubleValue() < ((Double) two).doubleValue());
  }
  
  /**
   * Determines if the object is equal to the given object
   * @param one Comparison object1 (double)
   * @param two Comparison object2 (double)  
   * @return true if the object one is equal to the given object two. false in other case
   */
  public boolean equalTo(Object one, Object two) {
    return (((Double) one).doubleValue() == ((Double) two).doubleValue());
  }
}
