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
 * <p>Title: DelGen</p>
 * <p>Description: The gene deletion operator.  Deletes the last gene in the genome or
 * one randomly selected</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class DelGen extends ArityOne {
  /**
   * If the last gene is going to be deleted or one randomly selected
   */
  protected boolean del_last_gene = true;

  /**
   * Constructor: create a deletion gene operator that deletes the last gene of a genome
   */
  public DelGen(Environment _environment) {
    super( _environment );
  }

  /**
   * Constructor: create a deletion gene operator that deletes a gene of a genome
   * @param _del_last_gene Determines if the gene to be deleted is the last in
   * the genome or not. A true value indicates that the last gene is deleted.
   * A false value indiciates that a gene is randomly selected and deleted
   */
  public DelGen(Environment _environment,boolean _del_last_gene) {
    super( _environment );
    del_last_gene = _del_last_gene;
  }

  /**
   * Delete from the given genome the last gene
   * @param gen Genome to be modified
   */
  public Object apply(Object gen) {
    BitArray genome = (BitArray)gen;
    VariableLengthBinaryGenotype genotype = (VariableLengthBinaryGenotype)environment.getGenotype();
    int gene_size = genotype.getDeltaLength();
    int min_length = genotype.getMinLength();
    if (genome.size() > min_length + gene_size) {
      if (del_last_gene) {
        genome.del(gene_size);
        return new Integer(gene_size);
      } else {
        int size = genotype.size( genome );
        UniformNumberGenerator g = new UniformNumberGenerator( size );
        int k = g.newInt();
        BitArray right = null;
        right = genome.subBitArray( min_length + (k+1) * gene_size );
        genome.del((size - k) * gene_size);
        genome.add(right);
        return new Integer(k);
      }
    } else {  return "No gene was deleted";  }
  }

 /**
  * Testing function
  */
  public static void main(String[] argv){
    System.out.println("*** Generating a genome of 27 genes randomly ***");
    BitArray genome = new BitArray(27, true);
    System.out.println(genome.toString());

    System.out.println("*** Generating a Deletion Gen operation with gen length of 3 ***");
    DelGen del = new DelGen(new Environment( new VariableLengthBinaryGenotype(21, 27, 3), null ));

    System.out.println("*** Applying the deletion ***");
    Object gene;
    gene = del.apply(genome);

    System.out.println("*** Genome ***");
    System.out.println(genome.toString());
    System.out.println(gene.toString());

    System.out.println("*** Applying the deletion ***");
    gene = del.apply(genome);

    System.out.println("*** Genome ***");
    System.out.println(genome.toString());
    System.out.println(gene.toString());

    System.out.println("*** Applying the deletion ***");
    gene = del.apply(genome);

    System.out.println("*** Genome ***");
    System.out.println(genome.toString());
    System.out.println(gene.toString());

  }
}
