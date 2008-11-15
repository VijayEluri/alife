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
package jml.object.util;

import java.util.Enumeration;
import java.util.Vector;

import jml.basics.Cloner;
import jml.object.LabeledObject;
import jml.object.sources.BufferSource;
import jml.object.sources.ObjectSource;
import jml.object.sources.SamplingSource;

/**
 * <p>Title: BinaryShellObjectSource</p>
 * <p>Description: A ObjectSource for binarization of another ObjectSource</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Universidad Nacional de Colombia - The University of Memphis</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class BinaryShellObjectSource extends ObjectSource {

  /**
   * Data source to be binarized
   */
  private ObjectSource realDataSource;

  /**
   * Determines which classes are negative, positive or discarded
   * A positive value in position i indicates that class i is positive.
   * A negative value in position i indicates that class is negative.
   * A zero value in position i indicates that class i is not take into account.
   * (samples will be discarded)
   */
  private int[] classType;

  /**
   * Creates a Binarization Data Source with the given data source and positive
   * classes
   * @param realDataSource The data source to be binarized
   * @param classType Determines which classes are negative, positive or discarded
   * A positive value in position i indicates that class i is positive.
   * A negative value in position i indicates that class is negative.
   * A zero value in position i indicates that class i is not take into account.
   * (samples will be discarded)
   */
  public BinaryShellObjectSource(ObjectSource realDataSource,
                                int[] classType) {
	this.realDataSource = realDataSource;
	this.classType = classType;
    Enumeration iter = this.realDataSource.elements();
    m = 0;
    while (iter.hasMoreElements()) {
      LabeledObject d = (LabeledObject) iter.nextElement();
      if (classType[d.getLabel()] != 0) {
        m++;
      }
    }
    if (m < BufferSource.MAX_SIZE) {
      Vector v = new Vector();
      iter = realDataSource.elements();
      while (iter.hasMoreElements()) {
        LabeledObject d = (LabeledObject) iter.nextElement();
        if (classType[d.getLabel()] != 0) {
          v.add(d);
        }
      }
      realDataSource = new BufferSource(v);
    }
  }

  /**
   * Returns an Enumeration object for traversing the data source sequentially
   * @return an Enumeration object for traversing the data source sequentially.
   */
  public Enumeration elements() {
    return new BinaryShellObjectSourceEnumeration(this);
  }

  /**
   * Creates a copy of the data sample with the binarized label
   * @param dl Data sample to be binarized
   * @return Data sample with the binarized label
   */
  public Object get(Object dl) {
    LabeledObject d = (LabeledObject) Cloner.clone(dl);
    int type = classType[d.getLabel()];
    if (type < 0) {
      d.setLabel(0);
    } else {
      if (type > 0) { d.setLabel(1);  
      } else { d.setLabel(-1); }
    }
    return d;
  }

  /**
   * Gets the data record at the specific position given
   * @param index The index of the data object to be returned
   * @return The data object at the given position, null if there is not object at that position
   */
  public Object get(int index) {
    return get(realDataSource.get(index));
  }

  /**
   * Get a new data source with the selected data records
   * @param index Data records that will define the new data source. I suggest to
   * sort the vector before calling this method in order to speed up the algorithm
   * @return A new data source with the selected objects
   */
  public ObjectSource get(int[] index) {
    return new SamplingSource(this, index);
  }

  /**
   * Close the data source
   */
  public void close() {  realDataSource.close();  }

  /**
   * A binary shell data source enumerator: For traversing the ObjectSource sequentially
   */
  class BinaryShellObjectSourceEnumeration implements Enumeration {
    /**
     * Binary shell data source to be enumerated
     */
    private BinaryShellObjectSource source = null;

    /**
     * Enumerator of the real data source
     */
    private Enumeration innerIter = null;

    /**
     * Sample that will be returned when next method is called
     */
    private Object next = null;

   /**
    * Creates an enumerator over the given binary shell data source
    * @param source File data source to be enumerated
    */
    public BinaryShellObjectSourceEnumeration(BinaryShellObjectSource source) {
      this.source = source;
      innerIter = source.realDataSource.elements();
    }

    /**
     * Determines if there is a new object in the iteration and store it in the
     * attribute _next in order to be return in the next call. There is a new object
     * to be returned if it has an index different of 0
     */
    public void lookNext() {
      next = null;
      while (innerIter.hasMoreElements() && next == null) {
        LabeledObject d = (LabeledObject) innerIter.nextElement();
        d = (LabeledObject) Cloner.clone(d);
        int type = d.getLabel();
        if (source.classType[type] != 0) {
          if (source.classType[type] < 0) {
            d.setLabel(0);
          } else {
            d.setLabel(1);
          }
          next = d;
        }
      }
    }

   /**
    * Determines if the data source has more elements to be read
    * @return true if the data source has more elements to be enumerated,
    * false in other case
    */
    public boolean hasMoreElements() {
      lookNext();
      return (next != null);
    }

    /**
     * Returns the next element in the enumeration
     * @return Next element in the enumeration
     */
    public Object nextElement() {
      return next;
    }
  }
}
