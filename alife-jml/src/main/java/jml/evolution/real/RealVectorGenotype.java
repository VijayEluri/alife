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
package jml.evolution.real;
import jml.evolution.Genotype;
import jml.math.realvector.RandomVector;

/**
 * <p>Title:  BinaryGenome</p>
 * <p>Description: Interface for getting the real vector from an Individual</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class RealVectorGenotype  extends Genotype {
  /**
   * Generates a random vector of doubles 
   */
  protected RandomVector rand = new RandomVector();
  /**
   * Limits of the real vector
   */
  protected RealVectorLimits limits;
  /**
   * Gets the limits of the real vector
   * @return The limits of the real vector
   */
  public RealVectorLimits getLimits() {
	  return limits;
  }
  /**
   * Creates a RealVectorGenotype With the given RealVectorLimits 
   * @param limits Limits of the real vector
   */
  public RealVectorGenotype(RealVectorLimits limits) {
    this.limits = limits;
  }

  /**
   * Creates a new genome of the given genotype
   * @return Object The new genome
   */
  public Object newInstance() {
    return rand.generate(limits.min, limits.max);
  }

  /**
   * Returns the number of genes in the individual's genome
   * @return Number of genes in the individual's genome
   */
  public int size(Object genome) {
    return(((double[]) genome).length);
  }

}
