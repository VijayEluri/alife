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
import java.util.Enumeration;
import java.util.Vector;

import org.xml.sax.Attributes;

/**
 * <p>Title: CategoricAttribute</p>
 * <p>Description: A class that represents characteristics of a
 *  categorical feature (attribute)</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class CategoricAttribute extends Attribute implements Cloneable {
  /**
   * Contains the categorical values that the attribute can take
   */
  protected Vector values = null;

  /**
   * Creates a categorical attribute with the given label, and possible values
   * @param label The label associated with the attribute
   * @param values Possible values the attribute can take
   */
  public CategoricAttribute(String label, Vector values) {
    super(label);
    this.values = values;
  }

  /**
   * Creates a clone object of the given attribute
   * @param original The original attribute
   */
  public CategoricAttribute(CategoricAttribute original) {
     super(original.getLabel());
     values = new Vector();
     Enumeration iter = original.values.elements();
     while (iter.hasMoreElements()) {
       values.add(new String((String) iter.nextElement()));
     }
  }

  /**
   * Creates a XML document for the Attribute object
   * @return The XML representation of the Attribute
   */
  public String toXML() {
    StringBuffer sb = new StringBuffer();
    sb.append("  <Categorical id=\"" + this.getLabel() + "\">\n");
    Enumeration iter = values.elements();
    while (iter.hasMoreElements()) {
      sb.append("   <Value>" + iter.nextElement() + "</Value>\n");
    }
    sb.append("  </Categorical>\n");
    return sb.toString();
  }

  /**
    * Sets the information of the attribute from XML document
    * @param attributes the XML information of the Attribute
    */
  public CategoricAttribute(Attributes attributes) {
    super(attributes.getValue("id"));
    values = new Vector();
  }

  /**
   * Creates a clone of the attribute
   * @return A clone of the attribute
   */
  public Object clone() {
    return new CategoricAttribute(this);
  }


  /**
   * Adds a new categorical value to the attribute
   * @param category New categorical value associated to the attribute
   * @return The index associated to that categorical value
   */
  public int addCategory(String category) {
    int i = 0;
    while (i < values.size() &&  !category.equals(values.get(i))) { i++; }
    if (i == values.size()) { values.add(category); }
    return i;
  }

  /**
   * Determines the number of different categorical values for the attribute
   * @return Number of categorical values associated with the attribute
   */
  public int size() { return values.size(); }

  /**
   * Returns the label associated with the given categoric index
   * @param index Index of the category to be retrieved
   * @return String Label of the given category
   */
  public String getCategory(int index) {
    return values.get(index).toString();
  }
}
