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
import java.util.Vector;

import jml.util.IntUtil;

/**
 * <p>Title: FileObjectSource</p>
 * <p>Description: A multi data source. This class alows to create a data source from a set of data sources.
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class MultiSource extends ObjectSource {
  /**
   * Data sources that conform the Multi data source
   */
  protected ObjectSource[] sources = null;

 /**
  * Constructor: Creates a multi data source with the given data sources.
  * @param sources Data sources that conform the Multi data source. It has to
  * be different of null and with alength higher than 0. I am not validating
  * null pointers or size lower than 1.
  */
  public MultiSource(ObjectSource[] sources) {
    this.sources = sources;
  }

  /**
   * Close the data source
   */
  public void close() {
    for (int i = 0; i < sources.length; i++) {
      sources[i].close();
    }
  }

  /**
   * Return the number of objects in the data source
   * @return Number of data objects in the data source
   */
  public int size() {
    m = 0;
    for (int i = 0; i < sources.length; i++) {
      m += sources[i].size();
    }
    return m;
  }

  /**
   * The relative position of the data record at the position given (absolut position)
   * Index: Absolut position of the data record to analize
   * Return the relative position of the data record at the position given (absolut position).
   * It is the position of the data record in the data source that the data record belongs to
   */
  protected class Index {
    
	/**
	 * It is the position of the data record in the data source
	 */
	public int source = -1;
	
	/**
	 * The relative position of the data record
	 */
    public int pos = -1;
    
    /**
     * Locate ObjectSource in the real position
     * @param m Size Multi data source
     * @param sources A multi data source
     * @param index  Absolut position of the data record to analize
     */
    public Index (int m, ObjectSource[] sources, int index) {
      int i;
      if (index >= 0 && index < m) {
        i = 0;
        while (i < sources.length && index >= sources[i].size()) {
          index -= sources[i].size();
          i++;
        }
        source = i;
        pos = index;
      }
    }
  }

  /**
   * Gets a new data source with the selected data records
   * @param index Data records that will define the new data source
   * @return A new data source with the selected objects
   */
  public ObjectSource get(int[] index) {
    int s = size();
    int m = index.length;
    Vector[] subIndex = new Vector[numberOfObjectSources()];
    for (int i = 0; i < subIndex.length; i++) { subIndex[i] = new Vector(); }
    for (int i = 0; i < m; i++) {
      Index idx = new Index(s, sources, index[i]);
      if (idx.source != -1) {
        subIndex[idx.source].add(new Integer(index[i]));
      }
    }
    ObjectSource[] subSources = new ObjectSource[subIndex.length];
    for (int i = 0;  i < subSources.length; i++) {
      subSources[i] = sources[i].get(IntUtil.get(subIndex[i]));
    }
    return new MultiSource(subSources);
  }

  /**
   * Gets the data record at the specific position given
   * @param index The index of the data object to be returned
   * @return The data object at the given position, null if there is not object at that position
   */
  public Object get(int index) {
    Object rec = null;
    if (index >= 0 && index < size()) {
      int i = 0;
      while (i < sources.length && index >= sources[i].size()) {
        index -= sources[i].size();
        i++;
      }
      rec = sources[i].get(index);
    }
    return rec;
  }

  /**
   * Returns an Enumeration object for traversing the data source sequentially
   * @return an Enumeration object for traversing the data source sequentially.
   */
  public Enumeration elements() { return new MultiObjectSourceEnumeration(); }

  /**
   * Gets the number of different data sources
   * @return Number of different data sources
   */
  public int numberOfObjectSources() {
    int k = 0;
    if (sources != null) { k = sources.length; }
    return k;
  }

  /**
   * Gets the data source at the position given
   * @param index  Position of the data source to be obtained
   * @return Data source at the position given
   */
  public ObjectSource getSource(int index) {
    ObjectSource s = null;
    if (sources != null && 0 <= index && index < sources.length) {
      s = sources[index];
    }
    return s;
  }

  /**
   * A multi data source enumerator
   */
  class MultiObjectSourceEnumeration implements Enumeration {
    /**
     * Data source that is been read
     */
    protected int current_source = 0;
    /**
     * Inner iterator that is been used
     */
    protected Enumeration current_iter = null;

   /**
    * Creates an enumerator over the given multi data source
    */
    public MultiObjectSourceEnumeration() {
      if (size() > 0) {
        current_iter = getSource(0).elements();
      } else {
        // Dummy code to prevent null pointers for no valid iterators
        current_iter = (new Vector()).elements();
      }
    }

  /**
    * Determines if the multi data source has more elements
    * @return true if the multi data source has more elements to be enumerated, false in other case
    */
    public boolean hasMoreElements() {
      while (!current_iter.hasMoreElements() && current_source < numberOfObjectSources() - 1) {
        current_source++;
        current_iter = getSource(current_source).elements();
      }
      return current_iter.hasMoreElements();
    }

    /**
     * Returns the next element in the enumeration
     * @return Next element in the enumeration
     */
    public Object nextElement() {
      return current_iter.nextElement();
    }
  }

//  public static void main( String[] argv ){
//    if( argv.length >= 1 ){
//
//      System.out.println("*** Reading all the data sources ***");
//      ObjectSource[] sources = new ObjectSource[argv.length];
//      for( int i=0; i<sources.length; i++ ){
//        sources[i] = new FileObjectSource( argv[i], true, true );
//      }
//
//      MultiObjectSource source = new MultiObjectSource( sources );
//      int size =  source.size();
//      Data data;
//      double[] rec;
//      System.out.println("*** Reading all the data source ***");
//      System.out.println("Source size="+size);
//      Enumeration iter = source.elements();
//      while( iter.hasMoreElements() ){
//        data = (Data)iter.nextElement();
//        rec = data.get();
//        for( int j=0; j<rec.length; j++ ){
//          System.out.print(" "+rec[j]);
//        }
//        System.out.println(" Label:"+data.getLabel());
//      }
//
//      System.out.println("*** Reading the records 0, 30, 60, 90, 120 ***");
//      for( int i=0; i<5; i++ ){
//        data = source.get( 30*i );
//        if( data != null ){
//          rec = data.get();
//          for( int j=0; j<rec.length; j++ ){
//            System.out.print(" "+rec[j]);
//          }
//          System.out.println(" Label:"+data.getLabel());
//        }else{
//          System.out.println("Invalid index:"+(30*i));
//        }
//      }
//      System.out.println("*** Reading the set of records 0, 30, 60, 90, 120 ***");
//      int[] index = new int[5];
//      for( int i=0; i<5; i++ ){
//        index[i] = 30*i;
//      }
//      Vector set = source.get( index );
//      for( int i=0; i<5; i++ ){
//        data = (Data)set.elementAt(i);
//        if( data != null ){
//          rec = data.get();
//          for( int j=0; j<rec.length; j++ ){
//            System.out.print(" "+rec[j]);
//          }
//          System.out.println(" Label:"+data.getLabel());
//        }else{
//          System.out.println("Invalid index:"+(30*i));
//        }
//      }
//      source.close();
//    }else{
//      System.err.println("[MultiObjectSource] Wrong number of arguments. Use:");
//      System.err.println("[MultiObjectSource] java MultiObjectSource <fileName_1> <fileName_2> ..");
//      System.err.println("[MultiObjectSource] <fileName_i> is the name of a text file with numeric data.");
//    }
//  }
}
