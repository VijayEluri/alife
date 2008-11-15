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
import jml.evolution.binary.BinaryGenotype;
import jml.evolution.operators.ArityOne;
import jml.random.UniformNumberGenerator;
import jml.structures.BitArray;

/**
 * <p>Title: SingleBitMutation</p>
 * <p>Description: Flips one bit in the chromosome. The flipped bit is randomly selected
 * with uniform probability distribution</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class SingleBitMutation extends ArityOne {

  /**
   * Constructor: Default constructor
   */
  public SingleBitMutation(Environment _environment) {
    super(_environment);
  }

  /**
   * Flips a bit in the given genome
   * @param gen Genome to be modified
   * @return Index of the flipped bit
   */
  public Object apply(Object gen) {
    BitArray genome = (BitArray) gen;
    int pos = -1;
    try {
      UniformNumberGenerator g = new UniformNumberGenerator( genome.size() );
      pos = g.newInt();
      genome.not(pos);
    } catch ( Exception e ){ System.err.println("[Mutation]"+e.getMessage()); }
    return new Integer(pos);
  }

 /**
  * Testing function
  */
  public static void main(String[] argv){
    System.out.println("*** Generating a genome of 20 genes randomly ***");
    BitArray genome = new BitArray( 21, true );
    System.out.println(genome.toString());

    SingleBitMutation mutation = new SingleBitMutation(new Environment( new BinaryGenotype(21), null ));

    System.out.println("*** Applying the mutation ***");
    Object pos = mutation.apply( genome );
    System.out.println("Position "+pos.toString());

    System.out.println("*** Genome ***");
    System.out.println(genome.toString());

    System.out.println("*** Applying the mutation ***");
    pos = mutation.apply( genome );
    System.out.println("Position "+pos.toString());

    System.out.println("*** Genome ***");
    System.out.println(genome.toString());

  }

}
