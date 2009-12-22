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


import java.io.FileWriter;
import java.util.Enumeration;

import jml.io.ObjectIO;

/**
 * <p>Title: ObjectSource</p>
 * <p>Description: An abstract class that represents an indexed source of labeled data record.</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */

public abstract class ObjectSource {
  /**
   * ObjectSource size
   */
  protected int m;
  
  /**
   * Constructor: Creates a ObjectSource with size equals a 0
   */
  public ObjectSource() { m = 0; };
  
  /**
   * Constructor: Creates a ObjectSource with size m
   * @param m Size
   */
  public ObjectSource(int m) { this.m = m; }

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
  public  void close() { }

  /**
   * Gets the data record at the specific position given
   * @param index The index of the data object to be returned
   * @return The data object at the given position, null if there is not object at that position
   */
  public abstract Object get(int index);

  /**
   * Returns an Enumeration object for traversing the data source sequentially
   * @return an Enumeration object for traversing the data source sequentially.
   */
  public abstract Enumeration elements();

  /**
   * Return the number of objects in the data source
   * @return Number of data objects in the data source
   */
  public int size() {
    return m;
  }

  /**
   * Return the dimension of the data records stored in the collection
   * @return Dimension of the data records stored in the collection
   */
//  public int dimension(){
//    if( getInfo() != null ){ return info.dimension(); }else{ return 0; }
//  }

  /**
   * Writes a ObjectSource to a text file
   * @param fileName Name of the file that will contain the ObjectSource
   */
  public void save(String fileName) {
    try {
      FileWriter file = new FileWriter(fileName);
      Enumeration iter = elements();
      while (iter.hasMoreElements()) {
        ObjectIO.write(iter.nextElement(), file);
      }
      file.close();
    } catch (Exception e) {  e.printStackTrace(); }
  }
}
