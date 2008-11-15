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
 * <p>Title: ScalarProduct</p>
 * <p>Description: Abstract class, multiplies and divide one
 *  object for one scalar.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public abstract class ScalarProduct {
 /**
   * Multiplies object one and the scalar x
   * @param one The object
   * @param x The scalar
   */
  public abstract void fastMultiply(Object one, double x);
  /**
   * Divide object one by the scalar x
   * @param one The object
   * @param x The scalar
   */
  public abstract void fastDivide(Object one, double x);
  /**
   * Multiplies object one clone and the scalar x
   * @param one The object
   * @param x The scalar
   * @return The result
   */
  public Object multiply(Object one, double x) {
    Object copy = Cloner.clone(one);
    fastMultiply(copy, x);
    return copy;
  }
  /**
   * Divide object one clone by the scalar x
   * @param one The object
   * @param x The scalar
   * @return The result
   */
  public Object divide(Object one, double x) {
    Object copy = Cloner.clone(one);
    fastDivide(copy, x);
    return copy;
  }
}
