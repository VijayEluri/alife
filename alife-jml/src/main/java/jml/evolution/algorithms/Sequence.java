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
package jml.evolution.algorithms;

import java.util.Vector;

import jml.evolution.Environment;
import jml.evolution.Population;
import jml.evolution.Selection;
import jml.random.UniformNumberGenerator;


/**
 * <p>Title:Sequence</p>
 * <p>Description:A selection operator that select the indivduals in sequential order</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class Sequence extends Selection {

  /**
   * Constructor: Create a Sequental selection strategy.
   * @param _n Number of individuals to be choosen
   * @param _includeX If the individual given in the apply method is going to be selected always or not
   *                  If it is false the sequential selection will choose n consecutive individuals
   *                  starting at a random position
   */
  public Sequence( Environment _environment, int _n, boolean _includeX ){
    super( _environment, _n, _includeX );
  }

  /**
   * Choose a set of individuals from the population including the individual x
   * @param population Population source of the selection process
   * @param x Individual to be included in the selection
   */
  public Vector choose(Population population, int x) {
    Vector sel = null;
    if(population != null) {
      sel = new Vector();
      if (x < 0 || x >= population.size()) {
        UniformNumberGenerator g = new UniformNumberGenerator(population.size());
        x = g.newInt();
      }
      for (int i = 0; i < n; i++) {
        sel.add(population.get(x));
        x++;
        if (x == population.size()) { x = 0; }
      }
    }
    return sel;
  }
}
