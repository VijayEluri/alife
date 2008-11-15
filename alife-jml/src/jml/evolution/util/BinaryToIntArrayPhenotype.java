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

import jml.evolution.Phenotype;
import jml.structures.BitArray;
import jml.structures.BitArrayConverter;
/**
 * <p>Title:BinaryToIntArrayPhenotype</p>
 * <p>Description: Phenotype with binary genome and int thing</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company:Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class BinaryToIntArrayPhenotype extends Phenotype {
	/**
	 * If grayCode is used
	 */
  protected boolean grayCode;
  /**
   * int size
   */
  protected int int_size;

  /**
   * Constructor: Creates an individual with a random genome
   */
  public BinaryToIntArrayPhenotype(int _int_size, boolean _grayCode) {
    grayCode = _grayCode;
    int_size = _int_size;
  }

  /**
   * Generates a thing from the given genome
   * @param genome Genome of the thing to be expressed
   * @return A thing expressed from the genome
   */
  public Object get(Object genome) {
    return BitArrayConverter.getIntArray((BitArray) genome, int_size, grayCode);
  }

  /**
   * Generates a genome from the given thing
   * @param thing A thing expressed from the genome
   * @return Genome of the thing
   */
  public Object set(Object thing) {
    BitArray A = new BitArray(1, false);
    BitArrayConverter.setIntArray(A, (int[])thing, int_size, grayCode);
    return A;
  }
}
