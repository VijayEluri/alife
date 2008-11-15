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
 * <p>Title:  Rastrigin</p>
 * <p>Description: The Rastrigin function</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Rastrigin extends RealVectorFitness {

/**
 * Constructor: Creates a Rastrigin function
 */
  public Rastrigin(){}

  /**
   * Evaluates the Rastrigin function over a real value
   * @param x the real value argument of the Rastrigin function
   * @return the Rastrigin value for the given value
   */
  public double eval( double x ){
    return ( x*x - 10.0*Math.cos(2.0*Math.PI*x) );
  }

  /**
   * Evaluate the fitness function over the real vector given
   * @param x Real vector to be evaluated
   * @return the fitness function over the real vector
   */
  public double eval( double[] x ){
    int n = x.length;
    double f = 0.0;
    for( int i=0; i<n; i++ ){
      f += eval(x[i]);
    }
    return -(10.0*n + f);
  }
}
