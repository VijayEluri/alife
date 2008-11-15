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

import java.util.Vector;

import jml.object.Attribute;
import jml.object.CategoricAttribute;
import jml.object.NumericAttribute;



/**
 * <p>Title: ObjectSourceInfo</p>
 * <p>Description: Object with descriptive information (Number of rows,
 * columns, type of columns, values, minimum and maximum, etc..) of a ObjectSource.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class ObjectSourceInfo {
  /**
   * Number of samples in the data set
   */
  protected int m=0;

  /**
   * Set of independent attributes defining the data set
   */
  Vector features = new Vector();

  /**
   * Attribute defining the class of each data sample
   */
  CategoricAttribute classFeature = null;

  /**
   * Constructor. Creates a new ObjectSourceInfo sending its parameters
   * @param size Number of data samples in the data set
   * @param features Set of independent features
   * @param classFeature Class feature
   */
  public ObjectSourceInfo(int size, Vector features, CategoricAttribute classFeature) {
    m = size;
    this.features = features;
    this.classFeature = classFeature;
  }

  /**
   * Clones the ObjectSourceInfo
   * @param source ObjectSourceInfo that will be cloned
   */
  public ObjectSourceInfo(ObjectSourceInfo source) {
    m = source.m;
    features = new Vector();
    for (int i = 0; i < source.dimension(); i++) {
      features.add(source.features.get(i));
    }
    classFeature = source.classFeature;
  }


  /**
   * Dimension of the data set
   * @return Number of attributes
   */
  public int dimension() { return features.size(); }

  /**
   * Number of samples in the data set
   * @return The number of samples in the data source
   */
  public int size() { return m; }

  /**
   * Return the number of classes in the data set
   * @return Number of different classes in the data set
   */
  public int classes() {
    if (classFeature != null) { return classFeature.size(); } else { return 0; }
  }

  /**
   * Returns the features defining the data set
   * @return Vector with the features of the data set
   */
  public Vector getFeatures() { return features; }


  /**
   * Determines the number of classes in the data set
   * @return Number of different classes in the data set
   */
  public int numberOfClasses() {
    return classFeature.size();
  }

  /**
   * Constant is used for defining the minimum value 
   */
  private static final int MIN = 0;
  
  /**
   * Constant is used for defining the maximun value
   */
  private static final int MAX = 1;
  
  /**
   * Constant is used for defining the average value
   */
  private static final int AVG = 2;
  
  /**
   * Constant is used for defining the STD deviation value
   */
  private static final int STD = 3;
  
  /**
   * Constant is used for defining the half value
   */
  private static final int MED = 4;

  /**
   * Gets the values for each component
   * @return The value for each component
   */
  private double[] getValue( int index ) {
    int n = dimension();
    double[] val = new double[n];
    for (int i = 0; i < n; i++) {
      if (features.get(i) instanceof NumericAttribute) {
        switch(index) {
          case MIN:
            val[i] = ((NumericAttribute) features.get(i)).min;
          break;
          case MAX: // max
            val[i] = ((NumericAttribute) features.get(i)).max;
          break;
          case AVG: // average
            val[i] = ((NumericAttribute) features.get(i)).average;
          break;
          case STD: // std_deviation
            val[i] = ((NumericAttribute) features.get(i)).std_deviation;
          break;
          case MED: // median
            val[i] = ((NumericAttribute) features.get(i)).median;
          break;
          default:
            val[i] = Attribute.MISSING_VALUE;
          break;
        }
      } else {
        val[i] = Attribute.MISSING_VALUE;
      }
    }
    return val;
  }

  /**
   * Gets the minimum values for each component
   * @return The minimum for each component
   */
  public double[] getMin() {
    return getValue(MIN);
  }

  /**
   * Gets the maximum values for each component
   * @return The maximum for each component
   */
  public double[] getMax() {
    return getValue(MAX);
  }

  /**
   * Gets the average values for each component
   * @return The average for each component
   */
  public double[] getAverage() {
    return getValue(AVG);
  }

  /**
   * Gets the standard deviation for each component
   * @return The standard deviation for each component
   */
  public double[] getStdDeviation() {
    return getValue(STD);
  }
  /**
   * Gets the median value for each component
   * @return The median value for each component
   */
  public double[] getMedian() {
    return getValue(MED);
  }

  /**
   * Sets the values for each component
   * @param index Calculate to realize
   * @param val Data Object 
   */
  private void setValue (int index, double[] val) {
    int n = dimension();
    for (int i = 0; i < n; i++) {
      if (features.get(i) instanceof NumericAttribute) {
        switch (index) {
          case MIN: //min 
            ((NumericAttribute) features.get(i)).min  = val[i];
          break;
          case MAX: // max
            ((NumericAttribute) features.get(i)).max  = val[i];
          break;
          case AVG: // average
            ((NumericAttribute) features.get(i)).average  = val[i];
          break;
          case STD: // std_deviation
            ((NumericAttribute) features.get(i)).std_deviation = val[i];
          break;
          case MED: // median
            ((NumericAttribute) features.get(i)).median  = val[i];
          break;
        }
      } else {
        val[i] = Attribute.MISSING_VALUE;
      }
    }
  }

  /**
   * Sets the minimum values for each component
   * @param val The minimum value for each component
   */
  public void setMin(double[] val) {
    setValue(MIN, val);
  }

  /**
   * Sets the maximum values for each component
   * @param val The maximum value for each component
   */
  public void setMax(double[] val) {
    setValue(MAX, val);
  }
  /**
   * Sets the average values for each component
   * @param val The average value for each component
   */
  public void setAverage(double[] val) {
    setValue(AVG, val);
  }
  /**
   * Sets the standard deviation for each component
   * @param val Standard deviation for each component
   */
  public void setStdDeviation(double[] val) {
    setValue(STD, val);
  }
  /**
   * Sets the median values for each component
   * @param val The median value for each component
   */
  public void setMedian(double[] val) {
    setValue(MED, val);
  }

  /**
   * Sets the size of the data set (useful for Dynamic data sets)
   * @param size New size of the data set
   */
  public void setSize(int size) {
    m = size;
  }
}
