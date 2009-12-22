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
package jml.evolution;

import jml.random.UniformNumberGenerator;

/**
 * <p>Title: GenomeLimits</p>
 * <p>Description: Abstract class representing the genome information</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class GenomeLimits {
	/**
	 * Generates a random number
	 */
  protected UniformNumberGenerator g = null;

  /**
   * Minimum number of genes in the chromosome
   */
  public int min_genes = 1;

  /**
   * Maximum number of genes in the chromosome
   */
  public int max_genes = 1;

  /**
   * Gene size in the chromosome (number of "bits" that defines a gene)
   */
  public int gene_size = 1;

  /**
   * Constructor
   * @param minGenes Min number of genes in the chromosome
   * @param maxGenes Max number of genes in the chromosome
   * @param geneSize Size of each gene in the chromosome
   */
  public GenomeLimits(int minGenes, int maxGenes, int geneSize) {
    gene_size = geneSize;
    min_genes = minGenes;
    max_genes = maxGenes;
    if( min_genes < max_genes ){
      g = new UniformNumberGenerator( max_genes - min_genes + 1 );
    }
  }

  /**
   * Creates a Genome information object indicating the max and min number of
   * genes in the chromosome (the size of the gene is set to 1)
   * @param _min_genes Min number of genes in the chromosome
   * @param _max_genes Max number of genes in the chromosome
   */
  public GenomeLimits(int _min_genes, int _max_genes) {
    min_genes = _min_genes;
    max_genes = _max_genes;
    if( min_genes < max_genes ){
      g = new UniformNumberGenerator( max_genes - min_genes + 1 );
    }
  }

  /**
   * Creates a Genome information object indicating the fixed number of
   * genes in the chromosome (the size of the gene is set to 1)
   * @param n Number of genes in the chromosome
   */
  public GenomeLimits(int n) {
    min_genes = n;
    max_genes = n;
  }

  /**
   * Copy constructor
   * @param source Creates a Genome information object with the same information of
   * the object given
   */
  public GenomeLimits(GenomeLimits source) {
    gene_size = source.gene_size;
    min_genes = source.min_genes;
    max_genes = source.max_genes;
    g = source.g;
  }

  /**
   * Generates a random number of genes that a chromosome can have (between min and max)
   * @return A number of genes that a chromosome can have
   */
  public int random_genes_number() {
    if( g != null ){  return (min_genes + g.newInt());  }
    else{ return min_genes; }
  }

  /**
   * Save the Genome information to a String
   * @return String with the information of the genome
   */
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append("[genes number] " + gene_size + "\n");
    sb.append("[min] " + min_genes + "\n");
    sb.append("[max] " + max_genes + "\n");
    return sb.toString();
  }
}
