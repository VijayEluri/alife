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
 * <p>Title:  Bohachevsky</p>
 * <p>Description: The Bohachevsky function</p>
 * Copyright: Copyright (c) 2004</p>
 * <p>Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Bohachevsky extends RealVectorFitness {
  /**
   * True if it is the first Bohachevsky function , false if it is the second
   */
  public boolean one;
/**
 * Constructor: Creates a Bohachevsky function
 * @param _one True if it is the Bohachevsky I function, false if it is the Bohachevsky II function
 */
  public Bohachevsky( boolean _one ){}

  /**
   * Evaluates the Bohachevsky I function over two real values
   * @param x1 the first real value argument of the Bohachevsky function
   * @param x2 the second real value argument of the Bohachevsky function
   * @return the Bohachevsky value for the given values
   */
  public double evalI( double x1, double x2 ){
    return ( x1*x1 - 2*x2*x2 - 0.3*Math.cos(3.0*Math.PI*x1)
                   - 0.4*Math.cos(4.0*Math.PI*x2) + 0.7 );
  }

  /**
   * Evaluates the Bohachevsky II function over two real values
   * @param x1 the first real value argument of the Bohachevsky function
   * @param x2 the second real value argument of the Bohachevsky function
   * @return the Bohachevsky value for the given values
   */
  public double evalII( double x1, double x2 ){
    return ( x1*x1 + 2*x2*x2 - 0.12*Math.cos(3.0*Math.PI*x1)*Math.cos(4.0*Math.PI*x2) + 0.3 );
  }


  /**
   * Evaluate the fitness function over the real vector given
   * @param x Real vector to be evaluated
   * @return the fitness function over the real vector
   */
  public double eval( double[] x ){
    double f = 0.0;
    int n = x.length - 1;
    if( one ){
      for( int i=0; i<n; i++ ){
        f += evalI( x[i], x[i+1] );
      }
    }else{
      for( int i=0; i<n; i++ ){
        f += evalII( x[i], x[i+1] );
      }
    }
    // minimization
    return -f;
  }
}
