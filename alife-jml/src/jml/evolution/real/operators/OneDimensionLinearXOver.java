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
import jml.random.UniformNumberGenerator;
;

/**
 * <p>Title: LinearXOver</p>
 * <p>Description:Applies a linear crossover to a single component</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class OneDimensionLinearXOver extends ArityTwo {
  /**
   * Defalut constructor
   * @param _environment The environment
   */
  public OneDimensionLinearXOver(Environment _environment) {
    super(_environment);
  }

  /**
   * Creates a one dimension linear crossover operation with the given selection strategy for
   * choosing the parents used by the crossover
   * @param _selection Selection mechanism for parent selection
   * @param _environment The environment
   */
  public OneDimensionLinearXOver(Environment _environment, Selection _selection) {
    super(_environment, _selection);
  }


  /**
   * Apply the 2-ary genetic operator over the individual genomes
   * @param c1 First Individuals genome to be modified by the genetic operator
   * @param c2 Second Individuals genome to be modified by the genetic operator
   * @return extra information of the genetic operator
   */
  public Object apply(Object c1, Object c2){
    double[] x = (double[]) c1;
    double[] y = (double[]) c2;
    int min = Math.min(x.length, y.length);

    UniformNumberGenerator g = new UniformNumberGenerator(min);
    int pos = g.newInt();

    double alpha = NumberGenerator.random();
    double alpha_1 = 1.0 - alpha;
    double tx, ty;
    tx = x[pos];
    ty = y[pos];
    x[pos] = alpha * tx + alpha_1 * ty;
    y[pos] = alpha_1 * tx + alpha * ty;
    return new Double(alpha);
  }
}
