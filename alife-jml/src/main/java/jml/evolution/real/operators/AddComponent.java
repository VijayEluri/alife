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
import jml.evolution.binary.VariableLengthBinaryGenotype;
import jml.evolution.operators.ArityOne;
import jml.evolution.real.RealVectorGenotype;
import jml.evolution.real.RealVectorLimits;
import jml.random.UniformNumberGenerator;

/**
 * <p>Title: AddComponent</p>
 * <p>Description: Add a new component to the encoded double[]</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class AddComponent extends ArityOne {
  /**
   * Default Constructor
   * @param environment The environment
   */
  public AddComponent(Environment environment) {
    super(environment);
  }

    /**
     * Adds a new component to the vector encoded in the genome
     * @param gen Genome, this cant be modifiend (double[])
     * @return Genome modified
     */
    public Object apply(Object gen) {    	
    	 double[] genome = (double[]) gen;
    	 RealVectorLimits limits = ((RealVectorGenotype)environment.getGenotype()).getLimits();
    	 if (genome.length < limits.getMaxGenes()) {
	    	 double[] comp = genome;
	    	 double[] new_comp = new double[comp.length + 1];
	    	 for (int i = 0; i < comp.length; i++) { new_comp[i] = comp[i];  }
	    	 double min = limits.min[comp.length];
	         double max = limits.max[comp.length];
	         UniformNumberGenerator g = new UniformNumberGenerator(min, max);
	         new_comp[comp.length] = g.newDouble();
	         gen = new_comp;
	         return genome;
	       } 
    	 return genome; 
    }

   /**
    * Testing function
    * @param argv main
    */
    public static void main(String[] argv) {
    	System.out.println("*** Generating a genome of genes randomly ***");
    	double[] min = new double[] { 3, -10.0, 5 };
        double[] max = new double[] { 3, 10.0, -5 };
        RealVectorLimits limits = new RealVectorLimits(min, max, 3, 1);
        double[] genome = new double[] { 1 };
        RealVectorGenotype a = new RealVectorGenotype(limits);
        System.out.println(genome[0]);
        AddComponent add = new AddComponent(new Environment(a, null));
        System.out.println("*** Applying the mutation ***");
        add.apply(genome);
        System.out.println(genome.toString());
        System.out.println("*** Applying the mutation ***");
        add.apply(genome);
        System.out.println(genome.toString());
    }


}
