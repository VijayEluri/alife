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
package jml.evolution.binary.fitness;

import jml.evolution.binary.BinaryFitness;
import jml.structures.BitArray;

/**
 * <p>Title: RoyalRoad</p>
 * <p>Description: The fitness of a binary array is the royal road function
 * as proposed by Mickalewicks</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */

public class RoyalRoad extends BinaryFitness{
  /**
   * The royal road path length
   */
  protected int pathLength = 8;

  /**
   * Constructor: Create a royal road fitness function with the path given
   * @param _pathLength The royal road path length
   */
  public RoyalRoad( int _pathLength ){
    pathLength = _pathLength;
  }

  /**
   * Evaluate the max ones fitness function over the binary array given
   * @param x Binary Array to be evaluated
   * @return the fitness function over the binary array
   */
  public double eval( BitArray x ){
    double f=0.0;
    int n = x.size() / pathLength;
    for( int i=0; i<n; i++ ){
      int start = pathLength*i;
      int end = start+pathLength;
      while( start < end && x.get(start) ){ start++; }
      if( start == end ){ f += pathLength; }
    }
    return f;
  }
}
