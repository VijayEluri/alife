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

import java.util.Enumeration;

import jml.util.sort.MergeSort;

/**
 * <p>Title: SamplingObjectSource</p>
 * <p>Description: A class representing a sampling process from a data source</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class SamplingSource extends ObjectSource {

/**
 * The source of data records to be sampled
 */
  protected ObjectSource source = null;
/**
 * The subset of data records to be sampled (it is the marked data records).
 * The sampling only uses the marked data records for generating the sample set
 * If there is no marked records the sampling uses all the data records in the
 * data source for generating the sample set
 */
  protected int[] index = null;

/**
 * Constructor: Creates a sampling object with marked data records
 * @param source Data source to be sampled
 * @param index Data records that are marked (The records that are used for generating the sample set)
 */
  public SamplingSource(ObjectSource source, int[] index) {
    this.source = source;
    if (index != null) {
      this.index = index;
      MergeSort sort = new MergeSort();
      sort.low2high(this.index);
    } else {
      this.index = new int[0];
    }
    m = this.index.length;
  }
  
  /**
   * Sets the source of data records to be sampled
   * @param source The source of data records
   */
  protected SamplingSource(ObjectSource source) {
      this.source = source;
  }

  /**
   * Close the data source
   */
  public void close() {
    if (source != null) {  source.close();  }
  }

  /**
   * Get a new data source with the selected data records
   * @param xindex Data records that will define the new data source
   * @return A new data source with the selected objects
   */
  public ObjectSource get(int[] xindex) {
    int n = xindex.length;
    int[] v = new int[n];
    for (int i = 0; i < n; i++) {
      v[i] = index[xindex[i]];
    }
    return source.get(v);
  }

  /**
   * Gets the data record at the specific position given
   * @param i The index of the data object to be returned
   * @return The data object at the given position, null if there is not object at that position
   */
  public Object get(int i) {
    Object d = null;
    if (source != null) {
      if (index != null) {
        if (i >= 0 && i < index.length) {
          d = source.get(index[i]);
        }
      } else {  d = source.get(i);  }
    }
    return d;
  }

  /**
   * Returns an Enumeration object for traversing the data source sequentially
   * @return an Enumeration object for traversing the data source sequentially. In this case
   *   the enumeration object is an enumeration object of the inner vector object
   */
  public Enumeration elements() {  return new SamplingEnumeration();  }

  /**
   * A sampling iterator
   */
  public class SamplingEnumeration implements Enumeration {
    
	 /**
     * The inner data source enumerator
     */
    protected int k = 0;

   /**
    * Creates an enumerator over the given sampling
    */
    public SamplingEnumeration() {
    }

  /**
    * Determines if the sampling has more elements
    * @return true if the sampling has more elements to be enumerated, false in other case
    */
   public boolean hasMoreElements() {
      return (k < m);
    }

    /**
     * Returns the next element in the enumeration
     * @return Next element in the enumeration
     */
    public Object nextElement() {
      Object obj = source.get(index[k]);
      k++;
      return obj;
    }
  }
}
