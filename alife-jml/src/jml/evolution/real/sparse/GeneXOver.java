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
 * <p>Title: LinearXOverPerDimension</p>
 * <p>Description:Applies a linear crossover per dimension. In this case each alpha
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
  public GeneXOver(Environment _environment, Selection _selection){
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

    double a;
    double a_1;
    double tx, ty;
    Enumeration iter1 = one.elements();
    Enumeration iter2 = two.elements();
    Vector vx = new Vector();
    Vector vy = new Vector();
    SparseReal ax = null;
    SparseReal by = null;
    while(iter1.hasMoreElements() && iter2.hasMoreElements()) {
      if (ax == null) { ax = (SparseReal) iter1.nextElement(); }
      if (by == null) { by = (SparseReal) iter2.nextElement(); }
      a = NumberGenerator.random();
      if (a < 0.5) { a = 0.0; } else { a = 1.0; }
      a_1 = 1.0 - a;
      if (ax.getIndex() == by.getIndex()) {
        tx = ax.value;
        ty = by.value;
        ax.value = a * tx + a_1 * ty;
        by.value = a_1 * tx + a * ty;
        vx.add(ax);
        vy.add(by);
        ax = null;
        by = null;
      } else {
        if (ax.getIndex()< by.getIndex()) {
          tx = ax.value;
          ax.value = a * tx;
          vx.add(ax);
          vy.add( new SparseReal(ax.getIndex(), a_1 * tx));
          ax = null;
        } else {
          ty = by.value;
          by.value = a * ty;
          vx.add(new SparseReal(by.getIndex(), a_1 * ty));
          vy.add(by);
          by = null;
        }
      }
    }

    if (ax != null) {
      a = NumberGenerator.random();
      if (a < 0.5) { a = 0.0; } else { a = 1.0; }
      a_1 = 1.0 - a;
      tx = ax.value;
      ax.value = a * tx;
      vx.add(ax);
      vy.add(new SparseReal(ax.getIndex(), a_1 * tx));
    }

    if (by != null) {
      a = NumberGenerator.random();
      if (a < 0.5) { a = 0.0; } else { a = 1.0; }
      a_1 = 1.0 - a;
      ty = by.value;
      by.value = a*ty;
      vx.add(new SparseReal(by.getIndex(), a_1 * ty));
      vy.add(by);
    }

    while (iter1.hasMoreElements()) {
      a = NumberGenerator.random();
      if (a < 0.5){ a = 0.0; } else { a = 1.0; }
      a_1 = 1.0 - a;
      ax = (SparseReal)iter1.nextElement();
      tx = ax.value;
      ax.value = a * tx;
      by = new SparseReal(ax.getIndex(), a_1 * tx);
      vx.add(ax);
      vy.add(by);
    }

    while (iter2.hasMoreElements()) {
      a = NumberGenerator.random();
      if (a < 0.5) { a = 0.0; } else { a = 1.0; }
      a_1 = 1.0 - a;
      by = (SparseReal) iter2.nextElement();
      tx = 0;
      ty = by.value;
      ax = new SparseReal(by.getIndex(), a_1 * ty);
      by.value = a * ty;
      vx.add(ax);
      vy.add(by);
    }

    one.init(one.dimension(), vx);
    two.init(two.dimension(), vy);
    return null;
  }
}
