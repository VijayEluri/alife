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
package jml.evolution.util;

import jml.object.transformation.Normalization;

/**
 * <p>Title: BinaryToRealVectorPhenotype</p>
 * <p>Description: Phenotype with binary genome and real thing</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company:Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class BinaryToRealVectorPhenotype extends BinaryToIntArrayPhenotype{
  double max;
  /**
   * Determines the space of the fitness functions. Since the individual is storing
   * values in the [0,1)^n space, This attribute allows to scale the individual to
   * the appropiated range of the fitness function
   */
  protected Normalization space = null;

  /**
   * Constructor: Creates an individual with a random genome
   */
  public BinaryToRealVectorPhenotype(int _int_size, boolean _grayCode,
                                      Normalization _space) {
    super(_int_size, _grayCode);
    max = (1 << int_size);
    space = _space;
  }

  /**
   * Generates a thing from the given genome
   * @param genome Genome of the thing to be expressed
   * @return A thing expressed from the genome
   */
  public Object get( Object genome ){
    int[] y = (int[])super.get( genome );
    int n = y.length;
    double[] x = new double[n];
    for( int i=0; i<n; i++ ){
      x[i] = y[i] / max;
    }
    if (space != null) {
      space.apply(x);
    }
    return x;
  }

  /**
   * Generates a genome from the given thing
   * @param thing A thing expressed from the genome
   * @return Genome of the thing
   */
  public Object set( Object thing ){
    double[] rv = (double[])thing;
    int n = rv.length;
    double[] x = (double[])rv.clone();
    int[] y = new int[n];
    if( space != null ){
      space.inverse(x);
    }
    for( int i=0; i<n; i++ ){
      y[i] = (int)(x[i]*max);
    }
    return super.set( y );
  }

}
