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

/**
 * <p>Title: Attribute</p>
 * <p>Description: Abstract class that represents characteristics of a
 *  data feature (attribute)</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public abstract class Attribute {
  /**
   * The attribute label
   */
  private String label = "x";

  /**
   * This constant is used for identifying missing values (-1e108)
   */
  public static final double MISSING_VALUE = -1e108;
  /**
   * Default constructor. Creates an Attribute object with the given label
   * @param label The label associated with the attribute
   */
  public Attribute(String label) {
    this.label = label;
  }

  /**
   * Creates a XML document for the Attribute object
   * @return The XML representation of the Attribute
   */
  public abstract String toXML();


  /**
   * Obtains the label of the attribute
   * @return Label of the attribute
   */
  public String getLabel() { return label; }
}
