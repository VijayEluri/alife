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
 * <p>Title: Addition</p>
 * <p>Description: Abstract class, adds and substracts objects.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public abstract class Addition {
  /**
   * Adds the object one and the object two
   * @param one The first Object
   * @param two The second Object
   */
  public abstract void fastSum(Object one, Object two);
  /**
   * Substract the object two from the object one
   * @param one The first Object
   * @param two The second Object
   */
  public abstract void fastSubstract(Object one, Object two);
  
  /**
   * Adds object the one clone and the two clone
   * @param one The first Object
   * @param two The second Object
   * @return The result
   */
  public Object sum(Object one, Object two) {
    Object copy = Cloner.clone(one);
    fastSum(copy, two);
    return copy;
  }

  /**
   * Substract the two clone from the one clone
   * @param one The First Object
   * @param two The Second Object
   * @return The result
   */
  public Object substract(Object one, Object two) {
    Object copy = Cloner.clone(one);
    fastSubstract(copy, two);
    return copy;
  }
}
