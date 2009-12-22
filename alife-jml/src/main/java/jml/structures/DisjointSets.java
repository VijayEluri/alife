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
 * <p>Title: DisjointSets</p>
 * <p>Description: This class represents a disjoint sets find-union optimal structure
 * as explained by Cormen et all in Introduction to algorithms. Mac Graw Hill, 1990.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class DisjointSets {
  /**
   * Canonical object that represents a set.
   * p[i] = x means that object i belongs to the set represented by object x
   */
  private int[] p = null;
  /**
   * The rank of the elements
   */
  private int[] rank = null;

  /**
   * Constructor: Creates a disjoint set structure of n objects. Each element
   * defines a set. p[x] = x
   * @param n Number of elements
   */
  public DisjointSets(int n) {
    p = new int[n];
    rank = new int[n];
    init();
  }

  /**
   * Initialize the disjoint find union structure. p[x] = x, rank[x] = 0
   * for all x
   */
  public void init() {
    for (int i = 0; i < p.length; i++) {
      p[i] = i;
      rank[i] = 0;
    }
  }

  /**
   * Returns the canonical object that represents the set that the object x belongs to
   * @param x Element used to determine the canonical object
   * @return the canonical object that represents the set that the object x belongs to
   */
  public int find(int x) {
    if (p[x] != x) {
      p[x] = find(p[x]);
    }
    return p[x];
  }

  /**
   * Performs the union of the set that contains element x with the set that contains
   * the element y
   * @param x The object that defines the first set to be joint
   * @param y The object that defines the second set to be joint
   * @return The canonical object of the union set.
   */
  public int union (int x, int y) {
    return link (find(x), find(y));
  }

  /**
   * An intermediate function for doing the union of two sets
   * @param x The canonical object of the first set to be joint
   * @param y The canonical object of the second set to be joint
   * @return The canonical object of the union set.
   */
  protected int link(int x, int y) {
    if (x != y) {
      if (rank[x] > rank[y]) {
        int t = x;
        x = y;
        y = t;
      } else {
        if (rank[x] == rank[y]) {  rank[y]++;  }
      }
      p[x] = y;
    }
    return y;
  }

  /**
   * Returns the canonical elements for each element in the full set
   * @return The canonical elements for each element in the full set
   */
  public int[] get() {
    for (int i = 0; i < p.length; i++) { find(p[i]); }
    return p;
  }

  /**
   * Converts the disjoinfind-union structure to a string
   * @return A string with the disjoinfind-union structure
   */
  public String toString() {
    StringBuffer sb = new StringBuffer();
    get();
    for (int i = 0; i < p.length; i++) {
      sb.append(p[i]);
      sb.append("\n");
    }
    return sb.toString();
  }
}
