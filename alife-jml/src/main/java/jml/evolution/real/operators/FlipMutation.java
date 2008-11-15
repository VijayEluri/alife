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
 * <p>Title: FlipMutation</p>
 * <p>Description: Changes one component of the encoded double[] with the complement
 * in the interval defined for the component (x = min + max - x)</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class FlipMutation extends ArityOne {
	/**
	 * Limits of the real vector
	 */
  RealVectorLimits limits;
  /**
   * Default constructor
   * @param environment The environment
   */
  public FlipMutation(Environment environment, RealVectorLimits limits) {
    super(environment);
    this.limits = limits;
  }

  /**
   * Modifies one component of the encoded double[] with the complement
   * in the interval defined for the component (x=min+max-x)
   * @param gen Genome to be modified
   * @return Index of the real modified
   */
  public Object apply(Object gen){
    double[] genome = (double[]) gen;
    int pos = -1;
    try {
      UniformNumberGenerator s = new UniformNumberGenerator(genome.length);
      pos = s.newInt();
      double x = genome[pos];
      double min = limits.min[pos];
      double max = limits.max[pos];
      genome[pos] = min + max - x;
    } catch (Exception e) { System.err.println("[Flip Mutation]" + e.getMessage()); }
    return new Integer(pos);
  }

}
