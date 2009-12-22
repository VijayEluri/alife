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
package jml.evolution.binary;
import jml.random.UniformNumberGenerator;
import jml.structures.BitArray;

/**
 * <p>Title:  BinaryGenome</p>
 * <p>Description: Interface for getting the bitarray from an Individual</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class VariableLengthBinaryGenotype  extends BinaryGenotype {
  /**
   * Maximum number of genes
   */
  protected int max_length;
  /**
   * Delta lenght
   */
  protected int delta_length;
  /**
   * The final limit of the random number generator range
   */
  protected int max_delta;
  /**
   * Generator of new genes
   */
  protected UniformNumberGenerator extra_genes;

  public VariableLengthBinaryGenotype(int _min, int _max_length, int _delta_length) {
    super(_min);
    delta_length = _delta_length;
    max_delta = (_max_length - length) / delta_length;
    extra_genes = new UniformNumberGenerator(max_delta);
    max_length = length + max_delta * delta_length;
  }

  /**
   * Creates a new genome of the given genotype
   * @return Object The new genome
   */
  public Object newInstance() {
    int n = extra_genes.newInt();
    return new BitArray(length + n * delta_length, true);
  }

  /**
   * Returns the number of genes in the individual's genome
   * @return Number of genes in the individual's genome
   */
  public int size(Object genome) {
    return((((BitArray) genome).size() - length) / delta_length);
  }
  /**
   * Returns lenght
   * @return The lenght
   */
  public int getMinLength() { return length; }
  /**
   * Returns Max_Lenght
   * @return The maximum number of genes
   */
  public int getMaxLength() { return max_length; }
  /**
   * Returns DeltaLength
   * @return DeltaLenght
   */
  public int getDeltaLength() { return delta_length; }
  /**
   * Returns MaxDelta
   * @return The final limit of the random number generator range
   */
  public int getMaxDelta() { return max_delta; }
}
