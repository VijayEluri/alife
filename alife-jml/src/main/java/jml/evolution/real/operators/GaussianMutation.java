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
import jml.random.GaussianNumberGenerator;
import jml.random.UniformNumberGenerator;

/**
 * <p>Title: GaussianMutation</p>
 * <p>Description: Changes one component of the encoded double[] with a number
 * randomly generated following a Gaussian distribution with mean the old value of
 * the component and the given standard deviation</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class GaussianMutation extends ArityOne {
  /**
   * Limits of the real vector
   */
  protected RealVectorLimits limits;
  /**
   * Gauss number generator
   */
  protected GaussianNumberGenerator g = null;
  /**
   * sigma: standard deviation
   */
  protected double[] sigma = null;
  /**
   * Creates a Gaussian Mutation with the given standard deviation for each component
   * @param _limits Limits of the real vector
   * @param _sigma Standard deviation per component
   * @param _environment The environment
   */
  public GaussianMutation(Environment _environment, RealVectorLimits _limits,
                           double _sigma) {
    super(_environment);
    limits = _limits;
    int n = limits.min.length;
    sigma = new double[n];
    setSigma(_sigma);
    g = new GaussianNumberGenerator(0.0, 1.0);
  }
  /**
   * Creates a Gaussian Mutation with the given standard deviation per component
   * @param _sigma Standard deviation per component
   * @param _limits Limits of the real vector
   * @param _environment The environment
   */
  public GaussianMutation(Environment _environment, RealVectorLimits _limits,
                           double[] _sigma) {
    super(_environment);
    limits = _limits;
    sigma = _sigma;
    g = new GaussianNumberGenerator(0.0, 1.0);
  }
  /**
   * Sets the standard deviation 
   * @param _sigma Standard deviation for all the components
   */
  public void setSigma(double _sigma) {
    int n = sigma.length;
    for (int i = 0; i < n; i++) {
      sigma[i] = _sigma;
    }
  }
  /**
   * Sets the standard deviation 
   * @param _sigma Standard deviation per component
   */
  public void setSigma(double[] _sigma) {
    sigma = _sigma;
  }

  /**
   * Modifies the number in a random position for a guassian value with mean
   * thevalue encoded in the genome and sigma given as attribute
   * @param gen Genome to be modified
   * @return Index of the real modified
   */
  public Object apply(Object gen) {
    double[] genome = (double[]) gen;
    double[] min = limits.min;
    double[] max = limits.max;
    int pos = -1;
    try {
      UniformNumberGenerator s = new UniformNumberGenerator(genome.length);
      pos = s.newInt();
      double x = genome[pos];
      g.setSigma(sigma[pos]);
      double y = g.newDouble();
      x += y;
      if (x < min[pos]) { x = min[pos]; 
      } else { 
    	  if(x > max[pos]) { x = max[pos]; } 
      }
      genome[pos] = x;
    } catch (Exception e) { System.err.println("[Guassian Mutation]" + e.getMessage()); }
    return new Integer(pos);
  }

}
