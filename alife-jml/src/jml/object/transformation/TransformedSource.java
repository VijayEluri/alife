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

import java.util.Enumeration;

import jml.object.LabeledObject;
import jml.object.sources.ObjectSource;

/**
 * <p>Title: TransformedObjectSource</p>
 * <p>Description: A class representing a transformed data source</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class TransformedSource extends ObjectSource {

/**
 * The source of data records to be sampled
 */
  protected ObjectSource source = null;

  /**
   * Transformation applied to the ObjectSource
   */
  protected DataTransformation transformation = null;

  /**
   * If the operation is the inverse of the given transformation or the original
   */
  protected boolean inverse = false;

/**
 * Constructor: Creates a sampling object with marked data records
 * @param source Data source to be sampled
 * @param inverse If the transformation is the inverse of the given transformation or not
 * @param transformation DataTransformation
 */
  public TransformedSource(ObjectSource source, DataTransformation transformation,
                                boolean inverse) {
    this.source = source;
    this.inverse = inverse;
    this.transformation = transformation;
  }

  /**
   * Close the data source
   */
  public void close() {
    if (source != null) {  source.close();  }
  }

  /**
   * Returns the number of objects in the data source
   * @return Number of data objects in the data source
   */
  public int size() { return source.size(); }

  /**
   * Gets the data record at the specific position given
   * @param i The index of the data object to be returned
   * @return The data object at the given position, null if there is not object at that position
   */
  public Object get(int i) {
    if (inverse) { return transformation.inverse(source.get(i)); 
    } else { return transformation.apply(source.get(i)); }
  }

  /**
   * Returns an Enumeration object for traversing the data source sequentially
   * @return an Enumeration object for traversing the data source sequentially. In this case
   * the enumeration object is an enumeration object of the inner vector object
   */
  public Enumeration elements() {  return new TransformedEnumeration();  }

  /**
   * A sampling iterator
   */
  public class TransformedEnumeration implements Enumeration {
    /**
     * The inner data source enumerator
     */
    protected Enumeration iter = null;

   /**
    * Creates an enumerator over the given sampling
    */
    public TransformedEnumeration() {
      iter = source.elements();
    }

  /**
    * Determines if the sampling has more elements
    * @return true if the sampling has more elements to be enumerated, false in other case
    */
   public boolean hasMoreElements() {
      return iter.hasMoreElements();
    }

    /**
     * Returns the next element in the enumeration
     * @return Next element in the enumeration
     */
    public Object nextElement() {
      if (inverse) {
        return transformation.inverse((LabeledObject) iter.nextElement());
      } else {
        return transformation.apply((LabeledObject) iter.nextElement());
      }
    }
  }
}
