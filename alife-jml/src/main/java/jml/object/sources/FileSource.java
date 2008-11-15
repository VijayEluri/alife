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

import java.io.FileReader;
import java.util.Enumeration;
import java.util.Vector;

import jml.object.ObjectGenerator;
import jml.parser.Tokenizer;


/**
 * <p>Title: FileObjectSource</p>
 * <p>Description: A class that represents a data source of labeled data records from a file.
 * All the data records belong to the same class (the class label of the data source).
 * In this way the records in the file cannot be labeled. The format of the file is the following:
 * <p>datarecord 1</p>
 * <p>datarecord 2</p>
 * <p>...</p>
 * <p>datarecord m</p>
 * where,
 *  n is the dimension of the data records
 *  m is the number of data records in the file</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class FileSource extends ObjectSource {

  /**
   * File name
   */	
  protected String fileName;
  
  /**
   * Object Tokenizer
   */
  protected Tokenizer tokenizer;
  
  /**
   * Object that builds a tokenizer
   */
  protected ObjectGenerator generator;

  /**
   * Maximum size of the file 
   */
  protected int MAX_SIZE = 100000;

  /**
   * Index of the last record read by the tokenizer
   */
  protected int start = 0;

  /**
   * Inner buffer (with the data objects read by the tokenizer)
   */
  protected Vector buffer = new Vector();

  /**
   * Informs if the file is full
   */
  protected boolean full;


  /**
   * This read the data source size from the first line of the file
   * @param fileName The file name
   * @param generator Object built with a tokenizer
   * @param tokenizer Object tokenizer
   * @param maxSize Maximum size of the file 
   */
  public FileSource(String fileName, ObjectGenerator generator,
                               Tokenizer tokenizer, int maxSize) {
    this.fileName = fileName;
    this.tokenizer = tokenizer;
    this.generator = generator;
    this.MAX_SIZE = maxSize;
    reset();
  }


  /**
   * Read a set of data records from the file, starting from the current position of the stream tokenizer
   * @param buffer Number of data records to be read
   * @param tokenizer Object tokenizer
   * @param generator Object built with a tokenizer
   * @param maxSize Maximum size of the file 
   * @return  true or false
   */
  protected static boolean read_buffer(Vector buffer, Tokenizer tokenizer,
                                        ObjectGenerator generator, int maxSize) {
    buffer.clear();
    int n = 0;
    Object d;
    d = generator.get(tokenizer);
    while (d != null) {
      buffer.add(d);
      n++;
      d = generator.get(tokenizer);
    } while (d != null && n < maxSize);
    return (d == null);
  }
  
  /**
    *Reset the configuration
    */
  protected void reset() {
    try {
      tokenizer.init(new FileReader(fileName));
      start = 0;
      full = read_buffer(buffer, tokenizer, generator, MAX_SIZE);
      if (full) { m = buffer.size(); }
    } catch (Exception e) { e.printStackTrace(); }
  }

  /**
   * Read the data record at an especific position. If the data record is already stored in the
   * inner buffer return it, iin other case uses the stream tokenizer for updating the inner buffer
   * @param index The index of the data object to be read
   * @return The data object at the given position, null if there is not object at that position
   */
  public Object get(int index) {
    if (full) {
      return buffer.get(index);
    } else {
      if (index < start || index >= start + buffer.size()) {
        if (index < start) {
          reset();
        } else {
          start += buffer.size();
        }
        read_buffer(buffer, tokenizer, generator, MAX_SIZE);
        while (buffer.size() > 0 && index >= start + buffer.size()) {
          start += buffer.size();
          read_buffer(buffer, tokenizer, generator, MAX_SIZE);
        }
      }
      if (buffer.size() <= index - start) {
        return null;
      }
      return buffer.get(index - start);
    }
  }

  /**
   * Close the data source
   */
  public void close() {
    tokenizer.close();
  }

  /**
   * Returns an Enumeration object for traversing the data source sequentially
   * @return an Enumeration object for traversing the data source sequentially. In this case
   * the enumeration object is an enumeration object of the inner vector object
   */
  public Enumeration elements() {
    if (full) {
      return buffer.elements();
    } else {
      try {
        Tokenizer tok = new Tokenizer(tokenizer);
        tok.init(new FileReader(fileName));
        return new ObjectTextFileSourceEnumeration(generator, tok, MAX_SIZE / 10);
      } catch (Exception e) { e.printStackTrace(); }
      return null;
    }
  }

}

/**
 * A file data source enumerator: For traversing the FileObjectSource sequentially
 * @author Jonathan Gomez
 */
class ObjectTextFileSourceEnumeration implements Enumeration {
  
  /**
   * Object tokenizer used for traversing to vector
   */
  protected Tokenizer tokenizer;
  
  /**
   * Object ObjectGenerator used for traversing the vector
   */
  protected ObjectGenerator generator;

  /**
   * Maximum size
   */
  protected int MAX_SIZE = 10000;

  /**
   * Inner buffer (with the data objects read by the tokenizer)
   */
  protected Vector buffer = new Vector();
  
  /**
   * Enumerator of the file data source inner buffer
   */
  protected Enumeration buffer_iter = null;

 /**
  * Creates an enumerator over the given file data source
  * @param generator Object built with a object tokenizer
  * @param tokenizer Object tokenizer 
  * @param MAX_SIZE Maximum size
  */
 public ObjectTextFileSourceEnumeration(ObjectGenerator generator,
                                         Tokenizer tokenizer,
                                         int MAX_SIZE) {
   this.tokenizer = tokenizer;
   this.generator = generator;
   this.MAX_SIZE = MAX_SIZE;
   FileSource.read_buffer(buffer, tokenizer, generator, this.MAX_SIZE);
   this.buffer_iter = buffer.elements();
 }


 /**
  * Determines if the file data source has more elements to be read
  * @return true if the file data source has more elements to be enumerated, false in other case
  */
  public boolean hasMoreElements() {
    if (!buffer_iter.hasMoreElements()) {
      FileSource.read_buffer(buffer, tokenizer, generator, MAX_SIZE);
      buffer_iter = buffer.elements();
    }
    return buffer_iter.hasMoreElements();
  }

  /**
   * Returns the next element in the enumeration
   * @return Next element in the enumeration
   */
  public Object nextElement() {
    return buffer_iter.nextElement();
  }
}
