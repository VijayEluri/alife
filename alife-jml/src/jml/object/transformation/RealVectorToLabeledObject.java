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
import jml.object.LabeledObject;

/**
 * <p>Title: RealVectorToLabeledObject</p>
 * <p>Description: Transformation operation of a real vector to a LabeledObject,
 * the label is the value stored in one position of the real vector,
 * the object is the real vector without that position.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class RealVectorToLabeledObject extends DataTransformation {
  /**
   * Position of the real vector where is stored the label.
   * If labelPos is negative the label is the last position.
   */
  protected int labelPos;
  /**
   * Constructor: Created a new RealVectorToLabeledObject
   * @param labelPos
   */
  public RealVectorToLabeledObject(int labelPos) {
    this.labelPos = labelPos;
  }

  /**
   * Applies the transformation on the Data Sample and creates a new DataSample
   * with the transformed version
   * @param data Data sample to be transformed
   * @return Transformed Data Sample (new object)
   */
  public Object apply(Object data) {
    if (data != null) {
      double[] rec = (double[]) data;
      int n = rec.length;
      double[] x = new double[n - 1];
      int lp = labelPos;
      if (labelPos < 0) {
        lp = n - 1;
      }
      int k = 0;
      for (int i = 0; i < n; i++) {
        if (i != lp) {
          x[k] = rec[i];
          k++;
        }
      }
      return new LabeledObject(x, (int) rec[lp]);
    } else {
      return null;
    }
  }

  /**
   * Applies the inverse operation of the transformation on
   * the Data sample and creates a new Data with the inverse transformed version
   * @param data Data to be inverse-transformed
   * @return Inverse-Transformed Data sample (new object)
   */
  public Object inverse(Object data) {
    if (data != null) {
      LabeledObject obj = (LabeledObject) data;
      double[] x = (double[]) obj.get();
      int n = x.length + 1;
      int lp = labelPos;
      if (labelPos < 0) {
        lp = n - 1;
      }
      double[] rec = new double[n];
      int k = 0;
      for (int i = 0; i < n; i++) {
        if (i != lp) {
          rec[i] = x[k];
          k++;
        }
      }
      rec[lp] = obj.getLabel();
      return rec;
    } else { return null; }
  }

  /**
   * Determines if the operation is reversible or not
   * @return True if the transformation is reversible, false otherwise
   */
  public boolean isReversible() { return true; }
}
