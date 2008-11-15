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
package jml.object.util.statistic;
import java.util.Enumeration;

import jml.object.Attribute;
import jml.object.LabeledObject;
import jml.object.sources.ObjectSource;


/**
 * <p>Title: AverageStdDeviation</p>
 * <p>Description: Calculates the Average of the ObjectSource per feature </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class Average extends ObjectSourceStatistic {
  /**
   * Average values per dimension
   */
  public double[] avg = null;

  /**
   * Samples per feature. It does not count missed values
   */
  protected int[] m = null;

  /**
   * Generates the average (feature by feature) for the given ObjectSource
   * @param source ObjectSource to be analyzed
   */
  public void generate(ObjectSource source) {
    Enumeration iter = source.elements();
    LabeledObject d = (LabeledObject) iter.nextElement();
    double[] x = (double[]) d.get();
    int n = x.length;
    avg = new double[n];
    m = new int[n];
    double y;
    for (int i = 0; i < n; i++) {
      y = x[i];
      if (y != Attribute.MISSING_VALUE) {
        avg[i] = y;
        m[i] = 1;
      } else {
        avg[i] = 0;
        m[i] = 0;
      }
    }
    while (iter.hasMoreElements()) {
      d = (LabeledObject) iter.nextElement();
      x = (double[]) d.get();
      for (int i = 0; i < n; i++) {
        y = x[i];
        if (y != Attribute.MISSING_VALUE) {
          avg[i] += y;
          m[i]++;
        }
      }
    }
    for (int i = 0; i < n; i++) {
      if (m[i] > 0) {  avg[i] /= m[i]; }
    }
  }
}
