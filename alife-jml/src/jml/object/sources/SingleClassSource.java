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
package jml.object.sources;

import java.util.Enumeration;
import java.util.Vector;

import jml.object.LabeledObject;

/**
 * <p>Title: SamplingObjectSource</p>
 * <p>Description: A class representing a sampling process from a data source</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class SingleClassSource extends SamplingSource {

/**
 * Constructor: Creates a sampling object with marked data records
 * @param source Data source to be sampled
 * @param classId Data records that are marked (The records that are used for generating the sample set)
 */
  public SingleClassSource(ObjectSource source, int classId) {
    super(source);
    init(classId);
  }
  
  /**
   * Initializes a given position
   * @param classId Search position
   */
  public void init(int classId) {
    Vector v = new Vector();
    int n = 0;
    int k = 0;
    Enumeration iter = source.elements();
    while (iter.hasMoreElements()) {
      LabeledObject d = (LabeledObject) iter.nextElement();
      if (d.getLabel() == classId) {
        v.add(new Integer(k));
        n++;
      }
      k++;
    }
    index = new int[n];
    for (int i = 0; i < n; i++) {
      index[i] = ((Integer) v.get(i)).intValue();
    }
    m = index.length;
  }
}
