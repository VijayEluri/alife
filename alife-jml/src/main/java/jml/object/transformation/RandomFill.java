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
package jml.object.transformation;

import jml.object.Attribute;
import jml.object.LabeledObject;
import jml.object.sources.ObjectSource;
import jml.random.UniformNumberGenerator;


/**
 * <p>Title: RandomFill</p>
 * <p>Description: Completes missed values using a sample randomly selected from the
 * ObjectSource</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class RandomFill extends MissedValuesFill {
  /**
   * ObjectSource used for filling the missed values
   */
  protected ObjectSource source;

  /**
   * Data sample used for filling the missed values of another data sample
   */
  protected double[] copy;

  /**
   * A random number generator for selecting the data sample used for filling
   * missed values of other data sample
   */
  protected UniformNumberGenerator g;

  /**
   * Creates a filling strategy for missed  values using a sample randomly selected from the
   * ObjectSource
   * @param source ObjectSource used for filling the missed values
   */
  public RandomFill(ObjectSource source) {
    this.source = source;
    g = new UniformNumberGenerator(source.size());
  }

  /**
   * Determines the value that will be used for filling the missed value
   * @param i Index of the missed feature
   * @return Value that will be used for filling the missed value
   */
  public double fill(int i) {
    return copy[i];
  }

  /**
   * Determines if the data sample has some missed attribute
   * @return true if the data sample has some missed attribute. false otherwise
   */
  public boolean hasMissedValues() {
    int i = 0;
    int n = copy.length;
    while (i < n && copy[i] != Attribute.MISSING_VALUE) { i++; }
    return (i < n);
  }


  /**
   * Applies the transformation on the Data Sample and creates a new DataSample
   * with the transformed version
   * @param data Data sample to be transformed
   * @return Transformed Data Sample (new object)
   */
  public Object apply(Object data) {
    LabeledObject d;
    do {
      d = (LabeledObject) source.get(g.newInt());
      copy = (double[]) d.get();
    } while (hasMissedValues());
    return super.apply(data);
  }
}
