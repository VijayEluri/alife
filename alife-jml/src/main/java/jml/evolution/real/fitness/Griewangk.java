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
 * <p>Title:  Griewangk</p>
 * <p>Description: The Griewangk function</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Griewangk extends RealVectorFitness {

/**
 * Constructor: Creates a Griewangk function
 */
  public Griewangk(){}

  /**
   * Evaluate the fitness function over the real vector given
   * @param x Real vector to be evaluated
   * @return the fitness function over the real vector
   */
  public double eval( double[] x ){
    int n = x.length;
    double sum = 0.0;
    double prod = 1.0;
    for( int i=0; i<n; i++ ){
      sum += x[i]*x[i]/4000.0;
      prod *= Math.cos(x[i]/Math.sqrt(i+1.0));
    }
    return -(1.0 + sum - prod);
  }
}
