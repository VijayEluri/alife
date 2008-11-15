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
package jml.parser;

/**
 * Title: DFA
 * Description: Defines if could be posible to arrive to a posible 
 * state from transition matrix, when recieves vector of wanted states
 * point of entry and final point. 
 * Copyright:  Copyright (c) 2004
 * Company: Universidad Nacional de Colombia
 * @author Jonatan Gomez
 * @version 1.0
 */

public class DFA {

  /**
   * Output array
   */
	public int[] output = null;
  /**
   * State transitions table, rows represent states, 
   * columns represent posible transitions, 
   * any number minor than 0 means that the transition 
   * is not posible 
   */
  public int[][] transitionTable = null;
  /**
   * This variable represents the actual state of the transition,
   * when we simulate it.
   */
  public int state = 0;
/**
 * Protected constructor, to avoid instance creation 
 */
  protected DFA() { }
/**
 * Public constructor, inicilizate object
 * @param transitionTable Transition table
 */
  public DFA(int[][] transitionTable) {
    this.transitionTable = transitionTable;
  }
/**
 * Public constructor, inicilizate object
 * @param source DFA object, contains transition table and output vector 
 */
  public DFA (DFA source) {
    this.output = (int[]) source.output.clone();
    this.transitionTable = (int[][]) source.transitionTable.clone();
  }

/**
 * To clone DFA object
 * @return The clone
 */
  public Object clone() {
    return new DFA(this);
  }
/**
 * This function simulates transition, for a given vector, 
 * and a start point for that vector. Posible transitions
 * are in transition table (transitionTable). 
 * @param start start point of input vector
 * @param input input vector (transitions we want to make)
 * @return index of the input vector, represents the point 
 * until transition was made 
 */
  public int simulate (int start, int[] input) {
    int n = input.length - 1;
    state = 0;
    if (n >= 0) {
      int i = start - 1;
      do {
        i++;
        state = transitionTable[state][input[i]];
      }
      while (state >= 0 && i < n);
      return i;
    } else { return 0; }
  }

/**
 * To print transition table
 * @return A String
 */
  public String toString() {
    int size = transitionTable[0].length;
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < size; i++) {
      sb.append(i);
      sb.append(" ");
      sb.append(transitionTable[0][i]);
      sb.append(" ");
      sb.append(transitionTable[1][i]);
      sb.append(" ");
      sb.append(output[i]);
      sb.append("\n");
    }
    return sb.toString();
  }
}
