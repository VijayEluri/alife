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
package jml.basics;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Vector;

/**
 * <p>Title: Cloner</p>
 * <p>Description: To clone objects</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Cloner {
	/**
	   * To clone an Integer
	   * @param obj The integer to be clone
	   * @return The new integer
	   */  
  public static Integer clone(Integer obj) {
    return new Integer(obj.intValue());
  }
  /**
   * To clone a string
   * @param obj The string to be clone
   * @return The new string
   */  
  public static String clone(String obj) {
    return new String(obj);
  }
  /**
   * To clone a Vector
   * @param obj The vector to be clone
   * @return The new vector
   */  
  public static Vector clone(Vector obj) {
    Vector v = new Vector();
    Enumeration iter = obj.elements();
    while (iter.hasMoreElements()) {
      v.add(clone(iter.nextElement()));
    }
    return v;
  }
  /**
   * To clone a vector of integers
   * @param obj The vector of integers to be clone
   * @return The new vector
   */  
  public static int[] clone(int[] obj) {
    return (int[]) obj.clone();
  }
  /**
   * To clone a vector of doubles
   * @param obj The vector of doubles to be clone
   * @return The new vector
   */  
  public static double[] clone(double[] obj) {
    return (double[]) obj.clone();
  }
  /**
   * To clone an Object 
   * @param obj The object to be clone
   * @return The new object 
   */  
  public static Object clone(Object obj) {
    if (obj instanceof Vector) { return clone((Vector) obj); }
    if (obj instanceof String) { return clone((String) obj); }
    if (obj instanceof Integer) { return clone((Integer) obj); }
    if (obj instanceof int[]) { return clone((int[]) obj); }
    if (obj instanceof double[]) { return clone((double[]) obj); }
    Class cl = obj.getClass();
    try {
      Method m = cl.getMethod("clone", null);
      return m.invoke(obj, null);
    } catch (Exception e) {
      System.out.println("Helllloooooo....");
      e.printStackTrace();
    };
    return null;
  }
}
