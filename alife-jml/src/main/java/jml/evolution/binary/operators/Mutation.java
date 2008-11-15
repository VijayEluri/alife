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
 * <p>Title: Mutation</p>
 * <p>Description: The simple bit mutation operator</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class Mutation extends ArityOne {
  /**
   * Probability of mutating one single bit
   */
  protected double bit_mutation_rate = 0.0;

  /**
   * Default constructor. Creates a bit mutation with bit mutation rate equal to
   * 1 divided by the length of the chromosome
   */
  public Mutation(Environment _environment) {
    super(_environment);
  }

  /**
   * Constructor: Creates a mutation with the given mutation rate
   * @param _bit_mutation_rate Probability of mutating each single bit
   */
  public Mutation(Environment _environment, double _bit_mutation_rate) {
    super(_environment);
    bit_mutation_rate = _bit_mutation_rate;
  }

  /**
   * Flips a bit in the given genome
   * @param gen Genome to be modified
   * @return Number of mutated bits
   */
  public Object apply(Object gen) {
    BitArray genome = (BitArray) gen;
    int count = 0;
    try{
      double rate = bit_mutation_rate;
      if(bit_mutation_rate == 0.0) { rate = 1.0/genome.size(); }
      UniformNumberGenerator g = new UniformNumberGenerator();
      for (int i = 0; i < genome.size(); i++) {
        if (g.newDouble() < rate) {
          genome.not(i);
          count++;
        }
      }
    }catch( Exception e ){ System.err.println("[Mutation]"+e.getMessage()); }
    return new Integer(count);
  }

 /**
  * Testing function
  */
  public static void main(String[] argv){
    System.out.println("*** Generating a genome of 21 genes randomly ***");
    BitArray genome = new BitArray(21, true);
    System.out.println(genome.toString());

    Mutation mutation = new Mutation(new Environment(new BinaryGenotype(21), null), 0.01);

    System.out.println("*** Applying the mutation ***");
    Object pos = mutation.apply(genome);
    System.out.println("Number of mutated bits " + pos.toString());

    System.out.println("*** Genome ***");
    System.out.println(genome.toString());

    System.out.println("*** Applying the mutation ***");
    pos = mutation.apply(genome);
    System.out.println("Number of mutated bits: " + pos.toString());

    System.out.println("*** Genome ***");
    System.out.println(genome.toString());

  }

}
