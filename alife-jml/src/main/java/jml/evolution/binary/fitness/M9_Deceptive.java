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
 * <p>Title: M9_Deceptive</p>
 * <p>Description: Extended deceptive binary functions</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class M9_Deceptive extends BinaryFitness {

  protected int length = 8;
  /**
   * Distance
   * @param genes Bitarray source
   * @param b Boolean array
   * @param start Position
   * @return The distance
   */
  public int distance(BitArray genes, boolean[] b, int start) {
    int d = 0;
    int j = 0;
    for (int i = length * start; i < length * (start + 1); i++) {
      if (genes.get(i) ^ b[j]) { d++; }
      j++;
    }
    return d;
  }
  /**
   * Distance0
   * @param genes Bitarray source
   * @param start Position
   * @return Distance0
   */
  public int distance0(BitArray genes, int start){
    boolean[] b = new boolean[]{false,false,false,false,false,false,false,false};
    return distance(genes,b,start);
  }
  /**
   * Distance1
   * @param genes Bitarray source
   * @param start Position
   * @return Distance1
   */
  public int distance1(BitArray genes, int start) {
    boolean[] b = new boolean[]{true,false,false,false,true,true,false,false};
    return distance(genes,b,start);
  }
  /**
   * Distance2
   * @param genes Bitarray source
   * @param start Position
   * @return Distance2
   */
  public int distance2(  BitArray genes, int start ){
    boolean[] b = new boolean[]{false,true,false,false,true,false,true,false};
    return distance(genes,b,start);
  }

  /**
   * Return the integer value codified by the bits in a section of the array
   * @param genes Bitarray source
   * @param start Index of the first bit in the section to extract the index
   * @return The integer value codified by the bits in a section of the array
   */
  public int getValue(BitArray genes, int start) {
    int d0 = distance0( genes, start );
    int d1 = distance1( genes, start );
    int d2 = distance2( genes, start );
    int d = Math.min(d0,d1);
    d = Math.min(d,d2);
    if( d == 0 ){ d = 10; };
    return d;
  }

  /**
   * Evaluate the max ones fitness function over the binary array given
   * @param x Binary Array to be evaluated
   * @return the fitness function over the binary array
   */
  public double eval( BitArray x ){
    double f=0.0;
    int n = x.size() / length;
    for( int i=0; i<n; i++ ){
      f += getValue(x, i);
    }
    return f;
  }
}
