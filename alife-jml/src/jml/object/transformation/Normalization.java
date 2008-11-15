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

/**
 * <p>Title: Normalization</p>
 * <p>Description: Scales a data sample to the given intervals (one per dimension)</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class Normalization extends DataTransformation {
  /**
   * Min value of the original data set (per feature)
   */
  private double[] min = null;

  /**
   * Max value of the original data set (per feature)
   */
  private double[] max = null;


  /**
   * Min value of the transformed data set (per feature)
   */
  private double[] newMin = null;

  /**
   * Max value of the transformed data set (per feature)
   */
  private double[] newMax = null;
  /**
   * Length of the transformed data set (per feature)
   */
  private double[] newLength = null;
  /**
   * Lenngth of the original data set (per feature)
   */
  private double[] length = null;

  /**
   * Creates a linear normalization object with the given min and max values. The
   * data is transformed to the interval [0,1]
   * @param min1 Min vaues per feature
   * @param max1 Max vaues per feature
   */
  public Normalization(double[] min1, double[] max1) {
    min = min1;
    max = max1;
    if (min != null && max != null) {
      int n = min.length;
      length = new double[n];
      for (int i = 0; i < n; i++) {
        length[i] = max[i] - min[i];
      }
    }
  }

  /**
   * Creates a linear normalization object with the given min and max values. The
   * data is transformed to the interval [new_min,new_max]
   * @param min1 vaues per feature
   * @param max1 vaues per feature
   * @param newMin1 New min value
   * @param newMax1 New max value
   */
  public Normalization(double[] min1, double[] max1, double[] newMin1, double[] newMax1) {
    min = min1;
    max = max1;
    newMin = newMin1;
    newMax = newMax1;
    if (min != null && max != null && newMin != null && newMax != null) {
      int n = min.length;
      length = new double[n];
      newLength = new double[n];
      for (int i = 0; i < n; i++) {
        length[i] = max[i] - min[i];
        newLength[i] = newMax[i] - newMin[i];
      }
    }
  }

  /**
   * Applies the transformation on the data record
   * @param x Data record to be transformed
   */
  public void apply(double[] x) {
    int n = x.length;
    if (newLength != null) {
      for (int i = 0; i < n; i++) {
        if (x[i] != Attribute.MISSING_VALUE && max[i] > min[i]) {
          x[i] = newMin[i] + newLength[i] * (x[i] - min[i]) / length[i];
        }
      }
    } else {
      for (int i = 0; i < n; i++) {
        if (x[i] != Attribute.MISSING_VALUE && max[i] > min[i]) {
          x[i] = (x[i] - min[i]) / length[i];
        }
      }
    }
  }

  /**
   * Applies the transformation on the Data Sample and creates a new DataSample
   * with the transformed version
   * @param data Data sample to be transformed
   * @return Transformed Data Sample (new object)
   */
  public Object apply(Object data) {
    double[] x = (double[]) data;
    double[] c = (double[]) x.clone();
    apply(c);
    return c;
  }

  /**
   * Applies the inverse transformation on the data record
   * @param x Data record to be transformed
   */
  public void inverse(double[] x) {
    int n = x.length;
    if (newLength != null) {
      for (int i = 0; i < n; i++) {
        if (x[i] != Attribute.MISSING_VALUE && max[i] > min[i]) {
          x[i] = min[i] + length[i] * (x[i] - newMin[i]) / newLength[i];
        }
      }
    } else {
      for (int i = 0; i < n; i++) {
        if (x[i] != Attribute.MISSING_VALUE && max[i] > min[i]) {
          x[i] = min[i] + length[i] * x[i];
        }
      }
    }
  }

  /**
   * Applies the inverse operation of the transformation on
   * the Data sample and creates a new Data with the inverse transformed version
   * @param data Data to be inverse-transformed
   * @return Inverse-Transformed Data sample (new object)
   */
  public Object inverse(Object data) {
    double[] x = (double[]) data;
    double[] c = (double[]) x.clone();
    inverse(c);
    return c;
  }

  /**
   * Determines if the operation is reversible or not
   * @return True if the transformation is reversible, false otherwise
   */
  public boolean isReversible() { return true; }
}
