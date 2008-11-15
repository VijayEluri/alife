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
package jml.evolution.real.operators;

import jml.evolution.Environment;
import jml.evolution.Selection;
import jml.evolution.operators.ArityTwo;
import jml.random.UniformNumberGenerator;

/**
 * <p>Title: SimpleXOver</p>
 * <p>Description:Exchanges the last components of the first individual with
 * the last components of the second individual</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class SimpleXOver extends ArityTwo {
  /**
   * Default constructor
   * @param environment The environment
   */
  public SimpleXOver(Environment environment) {
    super(environment);
  }

  /**
   * Creates a crossover  operation with the given selection strategy for
   * choosing the parents used by the crossover
   * @param _selection Selection mechanism for parent selection
   * @param _environment The environment
   */
  public SimpleXOver(Environment _environment, Selection _selection) {
    super(_environment, _selection);
  }


  /**
   * Apply the 2-ary genetic operator over the individual genomes
   * @param c1 First Individuals genome to be modified by the genetic operator
   * @param c2 Second Individuals genome to be modified by the genetic operator
   * @return Extra information of the genetic operator
   */
  public Object apply(Object c1, Object c2) {
    double[] x = (double[]) c1;
    double[] y = (double[]) c2;
    int min = Math.min(x.length, y.length);
    UniformNumberGenerator g = new UniformNumberGenerator(min-1);
    int pos = g.newInt() + 1;
    double t;
    for(int i = 0; i < pos; i++) {
      t = x[i];
      x[i] = y[i];
      y[i] = t;
    }
    return new Integer( pos );
  }
}
