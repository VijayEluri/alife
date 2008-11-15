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
package jml.evolution.real.sparse;

import java.util.Enumeration;
import java.util.Vector;

import jml.evolution.Environment;
import jml.evolution.Selection;
import jml.evolution.operators.ArityTwo;
import jml.math.sparse.SparseReal;
import jml.math.sparse.SparseRealVector;
import jml.random.NumberGenerator;


/**
 * <p>Title: LinearXOver</p>
 * <p>Description:Applies a linear crossover. In this case the alpha is unique
 * for each component, it use two alpha, one for each SparseRealVector</p>
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
    SparseRealVector one = (SparseRealVector) c1;
    SparseRealVector two = (SparseRealVector) c2;

    double tx, ty;
    Enumeration iter1 = one.elements();
    Enumeration iter2 = two.elements();
    Vector vx = new Vector();
    Vector vy = new Vector();
    SparseReal ax = null;
    SparseReal by = null;

    double alpha = NumberGenerator.random();
    double alpha_1 = NumberGenerator.random();
    double neg_alpha = 1.0-alpha;
    double neg_alpha_1 = 1.0-alpha_1;

    while (iter1.hasMoreElements() && iter2.hasMoreElements()) {
      if (ax == null) { ax = (SparseReal) iter1.nextElement(); }
      if (by == null) { by = (SparseReal) iter2.nextElement(); }
      if (ax.getIndex() == by.getIndex()) {
        tx = ax.value;
        ty = by.value;
        ax.value = alpha * tx + neg_alpha * ty;
        by.value = alpha_1 * tx + neg_alpha_1 * ty;
        vx.add(ax);
        vy.add(by);
        ax = null;
        by = null;
      } else {
        if (ax.getIndex() < by.getIndex()) {
          tx = ax.value;
          ax.value = alpha * tx;
          vx.add(ax);
          vy.add(new SparseReal(ax.getIndex(), alpha_1 * tx));
          ax = null;
        } else {
          ty = by.value;
          by.value = neg_alpha_1 * ty;
          vx.add(new SparseReal(by.getIndex(), neg_alpha * ty));
          vy.add(by);
          by = null;
        }
      }
    }

    if (ax != null) {
      tx = ax.value;
      ax.value = alpha * tx;
      vx.add(ax);
      vy.add(new SparseReal(ax.getIndex(), alpha_1 * tx));
    }

    if( by != null ){
      ty = by.value;
      by.value = neg_alpha_1 * ty;
      vx.add(new SparseReal(by.getIndex(), neg_alpha * ty));
      vy.add(by);
    }

    while (iter1.hasMoreElements()) {
      ax = (SparseReal) iter1.nextElement();
      tx = ax.value;
      ax.value = alpha * tx;
      by = new SparseReal(ax.getIndex(), alpha_1 * tx);
      vx.add(ax);
      vy.add(by);
    }

    while (iter2.hasMoreElements()) {
      by = (SparseReal)iter2.nextElement();
      ty = by.value;
      ax = new SparseReal(by.getIndex(), neg_alpha * ty);
      by.value = neg_alpha_1 * ty;
      vx.add(ax);
      vy.add(by);
    }

    one.init(one.dimension(), vx);
    two.init(two.dimension(), vy);

    return new Double(alpha);
  }
}
