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
package jml.evolution.real.fitness;

import jml.evolution.real.RealVectorFitness;

/**
 * <p>Title:  Ackley</p>
 * <p>Description: The Ackley function</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Ackley extends RealVectorFitness {

/**
 * Constructor: Creates a Ackley function
 */
  public Ackley(){}

  /**
   * Evaluate the fitness function over the real vector given
   * @param x Real vector to be evaluated
   * @return the fitness function over the real vector
   */
  public double eval( double[] x ){
    int n = x.length;
    double sum1 = 0.0;
    double sum2 = 0.0;
    for( int i=0; i<n; i++ ){
      sum1 += x[i]*x[i];
      sum2 += Math.cos(2.0*Math.PI*x[i]);
    }
    sum1 /= n;
    sum2 /= n;

    return -(20.0 + Math.exp(1.0) - 20.0*Math.exp(-0.2*Math.sqrt(sum1)) - Math.exp(sum2));
  }
}
