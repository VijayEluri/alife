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
 * <p>Title: M7_Deceptive</p>
 * <p>Description: Extended deceptive binary functions</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class M7_Deceptive extends BinaryFitness {

  /**
   * Return the integer value codified by the bits in a section of the array
   * @param genes Bitarray source
   * @param start Index of the first bit in the section to extract the index
   * @param length Size of the section from which the integer is extracted
   * @return The integer value codified by the bits in a section of the array
   */
  public int getValue(BitArray genes, int start, int length) {
    int s = 0;
    length += start;
    for( int i=start; i<length; i++ ){
     if( genes.get(i) ){ s++; };
    };
    return s;
  }

  /**
   * Evaluate the max ones fitness function over the binary array given
   * @param x Binary Array to be evaluated
   * @return the fitness function over the binary array
   */
  public double eval( BitArray x ){
    int gene_size = 6;
    double f=0.0;
    int n = x.size() / gene_size;
    for( int i=0; i<n; i++ ){
      int k = getValue(x, gene_size*i, gene_size);
      switch( k ){
        case 0:
        case 6:
          f += 1;
        break;
//        case 1: f += 0; break;
        case 2:
        case 4:
          f += 0.4;
        break;
        case 3: f += 0.640576; break;
//        case 5: f += 0; break;
      }
    }
    return f;
  }
}
