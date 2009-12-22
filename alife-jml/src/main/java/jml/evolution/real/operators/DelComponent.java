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
package jml.evolution.real.operators;

import jml.evolution.Environment;
import jml.evolution.operators.ArityOne;
import jml.evolution.real.RealVectorGenotype;
import jml.evolution.real.RealVectorLimits;

/**
 * <p>Title: DelComponent</p>
 * <p>Description: Deletes the last conponent of the encoded RealVector</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universida Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class DelComponent extends ArityOne{
  /**
   * Default constructor
   * @param environment The environment
   */
  public DelComponent( Environment environment ){
    super(environment);
  }

    /**
     * Dels the last component of the vector encoded in the genome
     * @param gen Genome to be modified
     * @return Genome modified (double[])
     */
    public Object apply( Object gen ){
    	double[] genome = (double[])gen;
    	RealVectorLimits limits = ((RealVectorGenotype)environment.getGenotype()).getLimits();
      if (genome.length > limits.getMinGenes()) {
        double[] comp = genome;
        double[] new_comp = new double[comp.length - 1];
        for (int i = 0; i < new_comp.length; i++ ){  new_comp[i] = comp[i];  }
        genome = new_comp;
        return genome;
      }
      return genome;
    }

   /**
    * Testing function
    */
    public static void main(String[] argv){
    	System.out.println("*** Generating a genome of genes randomly ***");
    	double[] min = new double[] { 3, -10.0, 5 };
        double[] max = new double[] { 3, 10.0, -5 };
        RealVectorLimits limits = new RealVectorLimits(min, max, 3, 2);
        double[] genome = new double[] { 1, 5, 8 };
        RealVectorGenotype a = new RealVectorGenotype(limits);
        System.out.println(genome[0]);
        DelComponent del = new DelComponent(new Environment(a, null));
        System.out.println("*** Applying the mutation ***");
        genome = (double[]) del.apply(genome);
        System.out.println(genome.toString());
        System.out.println("*** Applying the mutation ***");
        genome = (double[]) del.apply(genome);
        System.out.println(genome.toString());
    }

}
