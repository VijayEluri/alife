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
import jml.basics.Cloner;

/**
 * <p>Title: Square</p>
 * <p>Description: Abstract class, calculate the square and square-root of
 * one object.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public abstract class Square {  
	/**
	   * Calculates the square of x clone
	   * @param x The object
	   * @return The square of the x
	   */
  public Object sqr(Object x) {
    Object copy = Cloner.clone(x);
    fastSqr(copy);
    return copy;
  }
  /**
   * Calculated the square-root of x clone
   * @param x The object
   * @return The square-root of x
   */
  public Object sqrt(Object x) {
    Object copy = Cloner.clone(x);
    fastSqrt(copy);
    return copy;
  }
  /**
   * Calculates the square of x 
   * @param one The object
   */
  public abstract void fastSqr(Object one);
  /**
   * Calculated the square-root of x
   * @param one The object
   */
  public abstract void fastSqrt(Object one);
}
