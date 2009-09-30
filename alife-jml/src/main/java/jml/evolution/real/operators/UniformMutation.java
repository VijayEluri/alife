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
import jml.evolution.operators.ArityOne;
import jml.evolution.real.RealVectorLimits;
import jml.random.UniformNumberGenerator;

/**
 * <p>Title: UniformMutation</p>
 * <p>Description: A uniform mutation of a single component. The new value is generated
* in the interval defined for the component being modified</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class UniformMutation extends ArityOne {
  /**
   * Number generator
   */
  protected UniformNumberGenerator g = null;
  /**
   * Limits of the real vector
   */
  protected RealVectorLimits limits;
  /**
   * Default constructor
   * @param _limits Limits of the real vector
   * @param _environment The environment
   */
  public UniformMutation(Environment _environment, RealVectorLimits _limits) {
    super(_environment);
    limits = _limits;
    g = new UniformNumberGenerator(0.0, 1.0);
  }

  /**
   * Modifies the number in a random position for a guassian value with mean
   * thevalue encoded in the genome and sigma given as attribute
   * @param gen Genome to be modified
   * @return Index of the real modified
   */
  public Object apply(Object gen) {
    double[] genome = (double[])gen;
    double[] min = limits.min;
    double[] max = limits.max;
    int pos = -1;
    try{
      UniformNumberGenerator s = new UniformNumberGenerator(genome.length);
      pos = s.newInt();
      g.set(min[pos], max[pos]);
      genome[pos] = g.newDouble();
    } catch (Exception e) { System.err.println("[Guassian Mutation]" + e.getMessage()); }
    return new Integer(pos);
  }

}
