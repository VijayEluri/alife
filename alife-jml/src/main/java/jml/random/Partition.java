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
package jml.random;

import java.util.Vector;

/**
 * <p>Title: Partition</p>
 * <p>Description: A random partition of a set</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */

public class Partition {
  /**
   * Number of elements in the set being partitioned
   */
   protected int n = 0;

   /**
    * <P>Starting positions of each group.</P>
    * <P>start[k] indicates the initial index of group k</P>
    */
   protected int[] start = new int[]{0,0};

  /**
   * Random distribution of index
   */
   protected int[] index = null;

  /**
   * Constructor: Default constructor. Useful for inheritance
   */
  protected Partition() {}

  /**
   * Constructor: Creates a partition of a set of n elements in m random groups
   * If n mod m <> 0 then the last group is completed with element of the set
   * randomly selected
   * @param n1 Set size
   * @param m Number of groups
   * @param random If index will be initialized with a permutation or not
   */
  public Partition(int n1, int m, boolean random) {
    n = n1;
    if (random) { index = permutation(n); }
    create(m);
  }

  /**
   * Constructor: Creates a partition of a randomized set in m random groups
   * @param index1 Randomized Set. The data set already permuted
   * @param m Number of groups
   */
  public Partition(int[] index1, int m) {
    index = index1;
    n = index.length;
    create(m);
  }
  
  /**
   * Constructor: Creates a partition of a randomized set and the size of groups
   * @param index1 Randomized Set. The data set already permuted
   * @param groupsSize Size of groups
   */
  public Partition(int[] index1, int[] groupsSize) {
    index = index1;
    n = index.length;
    create(groupsSize);
  }
  
  /**
   * Constructor: Creates a partition of a size of groups 
   * @param groupsSize Size of groups
   * @param random If index will be initialized with a permutation or not
   */
  public Partition(int[] groupsSize, boolean random) {
    create(groupsSize);
    if (random) { index = permutation(n); }
  }
  /**
   * Constructor: Creates the starting positions of each group of its size
   * @param groupsSize Size of groups
   */
  protected void create(int[] groupsSize) {
    int m = groupsSize.length;
    start = new int[m + 1];
    start[0] = 0;
    for (int i = 0; i < m; i++) {
      start[i + 1] = start[i] + groupsSize[i];
    }
    n = start[m];
  }
  /**
   * Constructor: Creates the starting positions of m groups
   * @param m Number of groups
   */
  protected void create(int m) {
    start = new int[m + 1];
    start[0] = 0;
    start[m] = n;
    for (int k = 1; k < m; k++) {
      start[k] = n * k / m;
    }
  }

  /**
   * Change the current partition
   */
  public void permutation() {
    permutation(index);
  }

  /**
   * Creates a random permutation of the given set
   * @param set Set to be permuted
   */
  public static void permutation(int[] set) {
    int i, j, k;
    int n = set.length;
    int temp;
    UniformNumberGenerator g = new UniformNumberGenerator(n);
    for (i = 0; i < n; i++) {
      j = g.newInt();
      k = g.newInt();
      temp = set[j];
      set[j] = set[k];
      set[k] = temp;
    }
  }

  /**
   * Creates a random permutation of the given set
   * @param set Set to be permuted
   */
  public static void permutation(Vector set) {
    int i, j, k;
    int n = set.size();
    Object temp;
    UniformNumberGenerator g = new UniformNumberGenerator(n);
    for (i = 0; i < n; i++) {
      j = g.newInt();
      k = g.newInt();
      temp = set.get(j);
      set.set(j, set.get(k));
      set.set(k, temp);
    }
  }

  /**
   * Creates a random permutation of a set of n elements
   * @param n Set size
   * @return A random permutation of elements of a set of n elements
   */
  public static int[] permutation(int n) {
    int[] perm = new int[n];
    for (int i = 0; i < n; i++) { perm[i] = i; };
    permutation(perm);
    return perm;
  }

  /**
   * Returns the elements in the set that does not belongs to the given group
   * @param k The group to be skipped
   * @return The set without the elements in the pos group (Enumeration)
   */
  public int[] skipGroup(int k) {
    int length = n - groupSize(k);
    int[] an = new int[length];
    for (int i = 0; i < start[k]; i++) {
      an[i] = index[i];
    }
    int j = start[k];
    for (int i = start[k + 1]; i < n; i++) {
      an[j] = index[i];
      j++;
    }
    return an;
  }

  /**
   * Returns the elements in the set that belongs to the pos group according to the random partition
   * @param k the group to be returned
   * @return The elements in the pos group (Enumeration)
   */
  public int[] getGroup(int k) {
    int length = groupSize(k);
    int[] an = new int[length];
    int j = start[k];
    for (int i = 0; i < length; i++) {
      an[i] = index[j];
      j++;
    }
    return an;
  }

  /**
   * Calculates the size of a group in the partion
   * @param k The group
   * @return Size of the group in the partition
   */
  public int groupSize(int k) {
    return start[k + 1] - start[k];
  }

  /**
   * Sets the number of groups in the partition
   * @param m Number of groups in the partition
   */
  public void setGroupsNumber(int m) {
    create(m);
  }

  /**
   * Sets the size of groups in the partition
   * @param groupsSize Size of groups 
   */
  public void setGroups(int[] groupsSize) {
    n = 0;
    for (int i = 0; i < groupsSize.length; i++) { n += groupsSize[i]; }
    if (index == null || n == index.length) { create(groupsSize); }
  }

  /**
   * Return the number of groups in the partition
   * @return Number of groups in the partition
   */
  public int size() {
    return start.length - 1;
  }

  /**
   * Return the random distribution of index
   * @return Random distribution of index
   */
  public int[] get() { return index; }
}
