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
 * <p>Title:  RosenbrockSaddle</p>
 * <p>Description: The RosenbrockSaddle function</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class RosenbrockSaddle extends RealVectorFitness {

/**
 * Constructor: Creates a RosenbrockSaddle function
 * The limits are [-2.048,2.048] per component
 */
  public RosenbrockSaddle(){}

  /**
   * Evaluates the RosenbrockSaddle function over two real values
   * @param x1 the first real value argument of the RosenbrockSaddle function
   * @param x2 the second real value argument of the RosenbrockSaddle function
   * @return the RosenbrockSaddle value for the given values
   */
  public double eval( double x1, double x2 ){
    double y = x1*x1 - x2;
    return (100.0*y*y + (1.0-x1)*(1.0-x1));
  }

  /**
   * Evaluate the fitness function over the real vector given
   * @param x Real vector to be evaluated
   * @return the fitness function over the real vector
   */
  public double eval( double[] x ){
    double f = 0.0;
    int n = x.length - 1;
    for( int i=0; i<n; i++ ){
      f += eval( x[i], x[i+1] );
    }
    // minimization
    return -f;
  }
}
