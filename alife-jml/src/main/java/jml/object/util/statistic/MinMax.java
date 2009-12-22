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

import jml.object.LabeledObject;
import jml.object.sources.ObjectSource;


/**
 * <p>Title: MinMax</p>
 * <p>Description: Calculates the Min and Max values of the ObjectSource per feature </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class MinMax extends ObjectSourceStatistic {
  /**
   * Min values per dimension
   */
  public double[] min = null;
  /**
   * Max values per dimension
   */
  public double[] max = null;

  /**
   * Generates the statistical information for the given ObjectSource
   * @param source ObjectSource to be analyzed
   */
  public void generate(ObjectSource source) {
    Enumeration iter = source.elements();
    if (iter.hasMoreElements()) {
      LabeledObject d = (LabeledObject) iter.nextElement();
      double[] x = (double[]) d.get();
      int n = x.length;
      min = new double[n];
      max = new double[n];
      for (int i = 0; i < n; i++) {
        min[i] = max[i] = x[i];
      }
      double y;
      while (iter.hasMoreElements()) {
        d = (LabeledObject) iter.nextElement();
        x = (double[]) d.get();
        for (int i = 0; i < n; i++) {
          y = x[i];
          if (min[i] > y) {
            min[i] = y;
          } else {
            if (max[i] < y) {
              max[i] = y;
            }
          }
        }
      }
    }
  }
}
