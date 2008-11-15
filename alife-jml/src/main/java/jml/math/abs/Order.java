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
package jml.math.abs;
/**
 * <p>Title: Order</p>
 * <p>Description: Abstract class, determines if the object one is less, 
 * greater or equal than the object two</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public abstract class Order {
	/**
	   * Determines if the object one is less than (in some order) the object two
	   * @param one The first object to compare
	   * @param two The secont object to compare
	   * @return If the object one is less Than the object two
	   */
	public abstract boolean lessThan(Object one, Object two);
	
	/**
	   * Determines if the object one is greater than (in some order) the object two
	   * @param one The first object to compare
	   * @param two The second object to compare
	   * @return If the object one is greater Than the object two
	   */
	public boolean greaterThan(Object one, Object two) {
    return lessThan(two, one);
  }
	/**
	   * Determines if the object one is equal to the object two
	   * @param one The first object to compare
	   * @param two The second object to compare
	   * @return If the object one is equal to the object two
	   */
	public abstract boolean equalTo(Object one, Object two);
}
