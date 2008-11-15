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
package jml.structures;

/**
 * <p>Title: CQueue</p>
 * <p>Description: A circular queue.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class CQueue {

  /**
   * Title: Node
   * Description: A circular queue node. The queue is double linked
   * Copyright: Copyright (c) 2002
   * Company: The University of Memphis - Universidad Nacional de Colombia
   * @author Jonatan Gomez
   * @version 1.0
   *
   */
  class Node {
    /**
     * The prev node in the queue
     */
    private Node prev = null;

    /**
     * The next node in the queue
     */
    private Node next = null;

    /**
     * The object stored in the node
     */
    private Object data = null;

    /**
     * Constructor: Creates a node with the given data. The next and prev attributes are set to null
     * @param d The object stored by the node
     */
    public Node(Object d) { data = d; }
  }

  /**
   * The cursor of the circular queue (current position)
   */
  private Node start = null;

  /**
   * Constructor: Creates a empty circular queue
   */
  public CQueue() { }

  /**
   * Determines if the circular queue is empty or not.
   * @return True if the queue is empty, false in other case
   */
  public boolean empty() { return (start == null); }

  /**
   * Advances in the circular one position.
   * <p>C = a -> b -> c -> d --> a</p>
   * <p>C.next() =>  C = b -> c -> d -> a --> b and returns b</p>
   * @return The object at the circular queue cursor position after advancing one position. Returns null if the queue is empty
   */
  public Object next() {
    Object val = null;
    if (!empty()) {
      start = start.next;
      val = start.data;
    }
    return val;
  }

  /**
   * Advances in the circular queue n positions.
   * <p>C = a -> b -> c -> d --> a</p>
   * <p>C.advance( 2 ) =>  C = c -> d -> a -> b --> c and returns c</p>
   * @param n The number of positions to advance
   * @return The object at the circular queue cursor position after advancing n positions. Returns null if the queue is empty
   */
  public Object advance(int n) {
    Object val = null;
    if (!empty()) {
      for (int i = 0; i < n; i++) {
        start = start.next;
      }
      val = start.data;
    }
    return val;
  }

  /**
   * Backs in the circular one position.
   * <p>C = a -> b -> c -> d --> a</p>
   * <p>C.prev( ) =>  C = d -> a -> b -> c --> d and returns d</p>
   * @return The object at the circular queue cursor position after backing one position. Returns null if the queue is empty
   */
  public Object prev() {
    Object val = null;
    if (!empty()) {
      start = start.prev;
      val = start.data;
    }
    return val;
  }

  /**
   * Adds a new object to the circular queue. The object is added after the current object in the queue. The cursor is not moved.
   * <p>C = a -> b -> c -> d --> a</p>
   * <p>C.add( x ) =>  C = a -> x -> b -> c -> d --> a</p>
   * @param data The object to be added
   */
  public void add(Object data) {
    if (empty()) {
      start = new Node(data);
      start.prev = start;
      start.next = start;
    } else {
      Node aux = new Node(data);
      aux.next = start.next;
      aux.next.prev = aux;
      start.next = aux;
      aux.prev = start;
    }
  }

  /**
   * Deletes the current object in the circular queue.
   * The new current object is the next to the old current object
   * <p>C = a -> b -> c -> d --> a</p>
   * <p>C.del() =>  C = b -> c -> d --> b</p>
   */
  public void del() {
    if (!empty()) {
      if (start.next == start) { 
    	start = null; 
      } else {
        start.prev.next = start.next;
        start.next.prev = start.prev;
        //start = start.prev;
        start = start.next;        
      }
    }
  }

  /**
   * Gets the current object in the circular queue. If the circular queue is empty return null
   * <p>C = a -> b -> c -> d --> a</p>
   * <p>C.get() = a</p>
   * @return The cursor of the circular queue  
   */
  public Object get() {
    Object data = null;
    if (!empty()) { data = start.data; }
    return data;
  }
}
