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
package jml.evolution.binary.operators;

import jml.evolution.Environment;
import jml.evolution.operators.ArityOne;
import jml.random.UniformNumberGenerator;
import jml.structures.BitArray;

/**
 * <p>Title: Transposition</p>
 * <p>Description: The simple transposition operator (without flanking)</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class Transposition extends ArityOne {

  /**
   * Constructor: Default constructor
   */
  public Transposition(Environment _environment) {
    super(_environment);
  }


  /**
   * Interchange the bits between two positions randomly chosen
   * Example:      genome = 100011001110
   * Transposition 2-10:    101100110010
   * @param _genome Genome to be modified
   */
  public Object apply(Object _genome) {
    BitArray genome = (BitArray) _genome;

    UniformNumberGenerator gen = new UniformNumberGenerator(genome.size());
    int start = gen.newInt();
    int end = gen.newInt();

    if (start > end ) {
      int t = start;
      start = end;
      end = t;
    }
    boolean tr;
    
    while (start < end) {
      tr = genome.get(start);
      genome.set(start, genome.get(end));
      genome.set(end, tr);
      start++;
      end--;
    }
    return new Double(start);
  }
}
