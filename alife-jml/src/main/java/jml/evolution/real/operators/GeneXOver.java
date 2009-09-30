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
import jml.random.NumberGenerator;

/**
 * <p>Title: LinearXOverPerDimension</p>
 * <p>Description:Applies a linear crossover per dimension. in this case each alpha
 * is different for each component.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class GeneXOver extends ArityTwo {
  /**
   * Defalut constructor
   * @param _environment The environment
   */
  public GeneXOver(Environment _environment) {
    super(_environment);
  }

  /**
   * Creates a linear crossover per dimension operation with the given selection strategy for
   * choosing the parents used by the crossover
   * @param _selection Selection mechanism for parent selection
   * @param _environment The environment
   */
  public GeneXOver(Environment _environment, Selection _selection) {
    super(_environment, _selection);
  }

  /**
   * Apply the 2-ary genetic operator over the individual genomes
   * @param c1 First Individuals genome to be modified by the genetic operator
   * @param c2 Second Individuals genome to be modified by the genetic operator
   * @return extra information of the genetic operator
   */
  public Object apply(Object c1, Object c2) {
    double[] x = (double[]) c1;
    double[] y = (double[]) c2;
    double a;
    double a_1;
    double tx, ty;
    int min = Math.min(x.length, y.length);
    for (int i = 0; i < min; i++) {
      a = NumberGenerator.random();
      if (a < 0.5) { a = 0.0; } else { a = 1.0; }
      a_1 = 1.0 - a;
      tx = x[i];
      ty = y[i];
      x[i] = a * tx + a_1 * ty;
      y[i] = a_1 * tx + a * ty;
    }
    return null;
  }
}
