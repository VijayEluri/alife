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
package jml.util;
import jml.basics.Algorithm;
import jml.basics.Result;
import jml.basics.Tracer;

/**
 * <p>Title: consoleInterface</p>
 * <p>Description: User Interface based on the java console</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class SimpleConsoleTracer implements Tracer {
 
	/**
   * Creates a console interface
   */
  public SimpleConsoleTracer() {
  }

  /**
   * Draws the results produced by the algorithm to the console
   * @param algorithm Algorithm being executed
   * @param obj Result Results produced by the algorithm (for example the
   * iteration result in an iterated algorithm
   */
  public void add(Algorithm algorithm, Object obj) {
    System.out.println(obj.toString());
  }
  
  /**
   * Return default value
   * @return default value
   */
  public Result getStat() { return null; }

}
