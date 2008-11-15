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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Vector;

import jml.object.LabeledObject;

/**
 * <p>Title: VectorObjectSource</p>
 * <p>Description: A vector of data records.</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class BufferSource extends ObjectSource implements Serializable {

  /**
   * Maximum number of values that a VectorObjectSource can store. If the data set
   * is defined using 10 attributes then the maximum number of records is 3000000
   */
  public static int MAX_SIZE = 30000000;

 /**
  * Vector with the data objects
  */
  protected Vector data = new Vector();

  /**
   * Default constructor
   */
  public BufferSource() { }

  /**
   * Constructor:  Creates a data source with the given data object
   * @param data Vector of data objects
   */
  public BufferSource(Vector data) {
    super(data.size());
    this.data = data;
  }

  /**
   * Gets the data record at the specific position given
   * @param index The index of the data object to be returned
   * @return The data object at the given position, null if there is not object at that position
   */
  public Object get(int index) {
    return data.get(index);
  }

  /**
   * Adds a new data sample to the data set if there is memory
   * @param x New data sample to be added into the data set
   * @return True if the data sample was added. false in other case
   */
  public boolean add(LabeledObject x) {
      data.add(x);
      m++;
      return true;
  }
  
  /**
   * To Clear the vector
   */
  public void clear() { data.clear(); }

  /**
   * Close the data source
   */
  public void close() { };

  /**
   * Get a new data source with the selected data records
   * @param index Data records that will define the new data source. I suggest to
   * sort the vector before calling this method in order to speed up the algorithm
   * @return A new data source with the selected objects
   */
  public ObjectSource get(Vector index) {
    Vector v = null;
    if (index != null && index.size() > 0) {
      v = new Vector();
      Enumeration iter = index.elements();
      while (iter.hasMoreElements()) {
        Object d = this.get(((Integer) iter.nextElement()).intValue());
        if (d != null) {
          v.add(d);
        }
      }
    }
    return new BufferSource(v);
  }


  /**
   * Returns an Enumeration object for traversing the data source sequentially
   * @return an Enumeration object for traversing the data source sequentially. In this case
   * the enumeration object is an enumeration object of the inner vector object
   */
  public Enumeration elements() {  return data.elements();  };

  /**
   * Load the vector data source from the given file
   * @param fileName Name of the file where the data source will be stored.
   * It is saved in binary format
   * @return The vector data source read from file if it contains a VectorObjectSource
   * null in other case
   */
  public static BufferSource loadBinary(String fileName) {
    try {
      FileInputStream os = new FileInputStream(fileName);
      ObjectInputStream obj = new ObjectInputStream(os);
      Object newObject = obj.readObject();
      if (newObject instanceof BufferSource) {
        return (BufferSource) newObject;
      }
      obj.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Save the vector data source to the given file
   * @param fileName Name of the file from the data source will be loaded. Binary format
   */
  public void saveBinary(String fileName) {
    try {
      FileOutputStream os = new FileOutputStream(fileName);
      ObjectOutputStream obj = new ObjectOutputStream(os);
      obj.writeObject(this);
      os.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
