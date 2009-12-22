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
package jml.object;
import org.xml.sax.Attributes;

/**
 * <p>Title: NumericAttribute</p>
 * <p>Description: A class that represents characteristics of a
 *  numeric feature (attribute)</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class NumericAttribute extends Attribute implements Cloneable {
  /**
   * the minimum value for the attribute. MISSING_VALUE indicates that it has not
   * been evaluated
   */
  public double min = MISSING_VALUE;
  /**
   * the maximum value for the attribute. MISSING_VALUE indicates that it has not
   * been evaluated
   */
  public double max = MISSING_VALUE;
  /**
   * the average value for the attribute. MISSING_VALUE indicates that it has not
   * been evaluated
   */
  public double average = MISSING_VALUE;
  /**
   * the standard deviation for the attribute. MISSING_VALUE indicates that it has not
   * been evaluated
   */
  public double std_deviation = MISSING_VALUE;
  /**
   * the mediam value for the attribute. MISSING_VALUE indicates that it has not
   * been evaluated
   */
  public double median = MISSING_VALUE;

  /**
   * Default constructor. Creates an Attribute object that is numerical with no
   * statistical information
   * @param label The label associated with the attribute
   */
  public NumericAttribute(String label) {
    super(label);
  }

  /**
   * Creates a numerical attribute with the given label, min and max values
   * @param label The label associated with the attribute
   * @param min minimum value for the attribute
   * @param max maximum value for the attribute
   */
  public NumericAttribute(String label, double min, double max) {
    super(label);
    this.min = min;
    this.max = max;
  }

  /**
   * Creates a numerical attribute with the given label, and statistical information
   * @param label The label associated with the attribute
   * @param min minimum value for the attribute
   * @param max maximum value for the attribute
   * @param average double
   * @param stdDeviation double
   */
  public NumericAttribute(String label, double min, double max, double average, double stdDeviation) {
    super(label);
    this.min = min;
    this.max = max;
    this.average = average;
    this.std_deviation = stdDeviation;
  }

  /**
   * Creates a numerical attribute with the given label, and statistical information
   * @param label The label associated with the attribute
   * @param min minimum value for the attribute
   * @param max maximum value for the attribute
   * @param average double
   * @param stdDeviation double
   * @param median The median value of the attribute
   */
  public NumericAttribute(String label, double min, double max,
                           double average, double stdDeviation, double median) {
    super(label);
    this.min = min;
    this.max = max;
    this.average = average;
    this.std_deviation = stdDeviation;
    this.median = median;
  }

  /**
  * Creates a clone object of the given attribute
  * @param original The original attribute
  */
 public NumericAttribute(NumericAttribute original) {
    super(original.getLabel());
    min = original.min;
    max = original.max;
    average = original.average;
    std_deviation = original.std_deviation;
    median = original.median;
  }

  /**
   * Creates a XML document for the Attribute object
   * @return The XML representation of the Attribute
   */
  public String toXML() {
    StringBuffer sb = new StringBuffer();
    sb.append("  <Numerical id=\"" + this.getLabel() + "\"");
    if (min != MISSING_VALUE) {
      sb.append(" min=\"" + min + "\"");
      sb.append(" max=\"" + max + "\"");
    }
    if (average != MISSING_VALUE) {
      sb.append(" avg=\"" + average + "\"");
      sb.append(" std=\"" + std_deviation + "\"");
    }
    if (median != MISSING_VALUE) {
      sb.append(" med=\"" + median + "\"");
    }
    sb.append("/>\n");
    return sb.toString();
  }

  /**
    * Gets the information of the attribute from XML document
    * @param attributes the XML information of the Attribute
    */
  public NumericAttribute(Attributes attributes) {
    super(attributes.getValue("id"));
    String val;
    val = attributes.getValue("min");
    if (val != null) { min = Double.parseDouble(val); } else { min = MISSING_VALUE; }
    val = attributes.getValue("max");
    if (val != null) { max = Double.parseDouble(val); } else { max = MISSING_VALUE; }
    val = attributes.getValue("avg");
    if (val != null) { average = Double.parseDouble(val); } else { average = MISSING_VALUE; }
    val = attributes.getValue("std");
    if (val != null) { std_deviation = Double.parseDouble(val); } else { std_deviation = MISSING_VALUE; }
    val = attributes.getValue("med");
    if (val != null) { median = Double.parseDouble(val); } else { median = MISSING_VALUE; }
  }

  /**
   * Creates a clone of the attribute
   * @return A clone of the attribute
   */
  public Object clone() {
    return new NumericAttribute(this);
  }

}
