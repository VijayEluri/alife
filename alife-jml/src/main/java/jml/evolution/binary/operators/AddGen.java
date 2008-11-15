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
import jml.evolution.binary.VariableLengthBinaryGenotype;
import jml.evolution.operators.ArityOne;
import jml.random.UniformNumberGenerator;
import jml.structures.BitArray;

/**
 * <p>Title: AddGen</p>
 * <p>Description: The gene addition operator. Add a gene generated randomly</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class AddGen extends ArityOne {
  /**
   * If the added gene is added to the end of the genome or not (randomly added)
   */
  protected boolean append = true;
  /**
   * Constructor: create an addition gene operator that adds a gene to the end of a genome
   * @param environment The environment
   */
  public AddGen(Environment environment) {
    super(environment);
  }

  /**
   * Constructor: creates an addition gene operator that adds a gene according to
   * the variable _append
   * @param append If the added gene is added to the end of the genome or not (randomly added)
   */
  public AddGen(Environment environment, boolean append) {
    super(environment);
    this.append = append;
  }

  /**
   * Add to the end of the given genome a new gene
   * @param gen Genome to be modified
   * @return The added gene or a String 
   */
  public Object apply(Object gen) {
    BitArray genome = (BitArray) gen;
    VariableLengthBinaryGenotype genotype = (VariableLengthBinaryGenotype)environment.getGenotype();
    int gene_size = genotype.getDeltaLength();
    int min_length = genotype.getMinLength();
    int max_length = genotype.getMaxLength();
    if (genome.size() < max_length) {
      BitArray gene = new BitArray(gene_size, true);
      if (append) {
        genome.add(gene);
      } else {
        int size = genotype.size(genome);
        UniformNumberGenerator g = new UniformNumberGenerator(size + 1);
        int k = g.newInt();
        if (k == size) {
          genome.add(gene);
        } else {
          BitArray right = genome.subBitArray(min_length + k * gene_size);
          genome.del(( size - k ) * gene_size);
          genome.add(gene);
          genome.add(right);
        }
      }
      return gene;
    } else {  return "No gene was added";  }
  }

 /**
  * Testing function
  * */
  public static void main(String[] argv){
    System.out.println("*** Generating a genome of 21 genes randomly ***");
    BitArray genome = new BitArray(21, true);
    System.out.println(genome.toString());

    System.out.println("*** Generating a Addition Gen operation with gen length of 3 ***");
    AddGen add = new AddGen(new Environment(new VariableLengthBinaryGenotype(21, 27, 3), null));
    System.out.println("*** Applying the addition ***");
    Object gene;
    gene = add.apply(genome);

    System.out.println("*** Genome ***");
    System.out.println(genome.toString());
    System.out.println(gene.toString());

    System.out.println("*** Applying the addition ***");
    gene = add.apply(genome);
    System.out.println(genome.toString());
    System.out.println(gene.toString());

    System.out.println("*** Applying the addition ***");
    gene = add.apply(genome);
    System.out.println(genome.toString());
    System.out.println(gene.toString());
  }
}
