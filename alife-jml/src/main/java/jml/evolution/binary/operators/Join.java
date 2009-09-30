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
import jml.evolution.Selection;
import jml.evolution.binary.BinaryGenotype;
import jml.evolution.operators.ArityTwo;
import jml.structures.BitArray;

/**
 * <p>Title: Join</p>
 * <p>Description: Joins two chromosomes</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class Join extends ArityTwo {

  /**
   * Default constructor
   * @param _environment The environment
   */
  public Join(Environment _environment) {
    super(_environment);
  }

  /**
   * Constructor: Create a crossover operator with the given selection parent strategy
   * @param _selection Selection mechanism for choosing the parents
   */
  public Join(Environment _environment, Selection _selection) {
    super(_environment, _selection);
  }


  /**
   * Apply the simple point crossover operation over the given genomes
   * @param c1 The first parent
   * @param c2 The second parent
   */
  public Object apply(Object c1, Object c2) {
    BitArray child1 = (BitArray)c1;
    BitArray child2 = (BitArray)c2;
    child1.add(child2);
    return  new Integer(child2.size());
  }


 /**
  * Testing function
  */
  public static void main(String[] argv){
    System.out.println("*** Generating a genome of 20 genes randomly ***");
    BitArray parent1 = new BitArray(20, true);
    System.out.println(parent1.toString());

    System.out.println("*** Generating a genome of 10 genes randomly ***");
    BitArray parent2 = new BitArray(10, true);
    System.out.println(parent2.toString());

    Join xover = new Join(new Environment(new BinaryGenotype(10), null));

    System.out.println("*** Applying the croosover ***");
    Object pos = xover.apply(parent1, parent2);
    System.out.println("Position " + pos);

    System.out.println("*** Child 1 ***");
    System.out.println(parent1.toString());
    System.out.println("*** Child 2 ***");
    System.out.println(parent2.toString());

    System.out.println("*** Applying the croosover with parent2, parent1 ***");
    pos = xover.apply( parent2, parent1 );
    System.out.println("Position " + pos);

    System.out.println("*** Child 1 ***");
    System.out.println(parent1.toString());
    System.out.println("*** Child 2 ***");
    System.out.println(parent2.toString());
  }
}
