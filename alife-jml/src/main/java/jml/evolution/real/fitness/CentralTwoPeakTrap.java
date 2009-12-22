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

/**
 *
 * <p>Title: CentralTwoPeakTrap</p>
 * <p>Description: A central two peak trap function for real values</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Universidad Nacional de Colombia - The University of Memphis</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class CentralTwoPeakTrap extends TwoPeakTrap {
  /**
   * Evaluates the Central two peak trap function 1-dimensional
   * @param x Argument for the Shubert function
   * @return The Shuberrt function evaluated on the given argument
   */
  public double eval( double x ){
    if( x < 10.0 ){
      return(160.0*x/15.0);
    }else{
      return(super.eval(x));
    }
  }
}
