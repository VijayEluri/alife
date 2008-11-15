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

import jml.math.realmatrix.RealMatrix;
import jml.object.Attribute;
import jml.object.LabeledObject;
import jml.object.sources.ObjectSource;


/**
 * <p>Title: Covariance</p>
 * <p>Description: Calculates the Covariance matrix of the ObjectSource</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class Covariance extends Average{
  /**
   * Covariance Matrix
   */
  public RealMatrix covariance = null;

  /**
   * Generates the covariance matrix for the given ObjectSource
   * @param source ObjectSource to be analyzed
   */
  public void generate(ObjectSource source) {
    super.generate(source);
    LabeledObject d;
    int n = avg.length;
    covariance = new RealMatrix(n);
    double[] r;
    double x;
    double y;
    Enumeration iter = source.elements();
    while (iter.hasMoreElements()) {
      d = (LabeledObject) iter.nextElement();
      r = (double[]) d.get();
      for (int i = 0; i < n; i++) {
        x = r[i];
        if (x != Attribute.MISSING_VALUE) {
          x -= avg[i];
          for (int j = i; j < n; j++) {
            y = r[j];
            if (y != Attribute.MISSING_VALUE) {
              y -= avg[j];
              covariance.data[i][j] += x * y;
            }
          }
        }
      }
    }
    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        if (m[i] > 0 && m[j] > 0) {
          covariance.data[i][j] = covariance.data[i][j] / (Math.sqrt(((double) (m[i] - 1)) * (m[j] - 1)));
        } else {
          covariance.data[i][j] = 0.0;
        }
        covariance.data[j][i] = covariance.data[i][j];
      }
    }
  }
}
