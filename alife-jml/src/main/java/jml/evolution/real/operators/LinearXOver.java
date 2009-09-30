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
 * <p>Title: LinearXOver</p>
 * <p>Description:Applies a linear crossover. In this case the alpha is unique
 * for each component, it use two alpha, one for the first vector and one
 * for the second vector</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class LinearXOver extends ArityTwo {
  /**
   * default constructor
   * @param _environment The environment
   */
  public LinearXOver(Environment _environment) {
    super(_environment);
  }

  /**
   * Creates a linear crossover operation with the given selection strategy for
   * choosing the parents used by the crossover
   * @param _selection Selection mechanism for parent selection
   * @param _environment The environment
   */
  public LinearXOver(Environment _environment, Selection _selection) {
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
    int min = Math.min(x.length, y.length);

    double alpha = NumberGenerator.random();
    double alpha_1 = NumberGenerator.random();
    double neg_alpha = 1.0 - alpha;
    double neg_alpha_1 = 1.0 - alpha_1;
    double tx;
    double ty;
    for(int i = 0; i < min; i++) {
      tx = x[i];
      ty = y[i];
      x[i] = alpha * tx + neg_alpha * ty;
      y[i] = alpha_1 * tx + neg_alpha_1 * ty;
    }
    return new Double(alpha);
  }
}
