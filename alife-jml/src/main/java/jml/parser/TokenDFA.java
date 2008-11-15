package jml.parser;

/**
 * Title: TokenDFA
 * Description: Define if two tokens are equals. Defines if could be posible 
 * to arrive to a posible 
 * state from transition matrix, when recieves vector of wanted states
 * point of entry and final point. 
 * Copyright:    Copyright (c) 2004
 * Company: Universidad Nacional de Colombia
 * @author Jonatan Gomez
 * @version 1.0
 */
public class TokenDFA extends DFA {
  /**
   * Using ASCII code for the input symbols
   */
  public static final int ASCII_CODE = 256; 
  /**
   * Constant ACEPTED
   */
  public static final int ACEPTED = 1;
  /**
   * Constant REJECTED 
   */
  public static final int REJECTED = 0;
  /**
   * Constant STOP REJECTED 
   */
  public static final int STOP_REJECTED = -2;
  /**
   * Constant STOP ACEPTED
   */
  public static final int STOP_ACEPTED = -1;

  /**
   * Protected constructor
   *
   */
  protected TokenDFA() { }

  /**
   * Public constructor, it calls father constructor, 
   * initializes the object
   * @param transitionTable Transition table
   */
  public TokenDFA(int[][] transitionTable) {
    super(transitionTable);
  }

  /**
   * Public constructor, it calls father constructor, 
   * initializes the object
   * @param source object that cotains transition table and output vector
   */
  public TokenDFA(DFA source) {
    super(source);
  }
/**
 * To clone objects of this class
 * @return The clone
 */
  public Object clone() {
    return new TokenDFA(this);
  }
/**
 * Keeps int vector with ascii code of each character of input string
 * @param str input string 
 * @return vector with ascii code of each character of input string
 */
  public static int[] toInt(String str) {
    int n = str.length();
    int[] input = new int[n];
    char[] cstr = str.toCharArray();
    for (int i = 0; i < n; i++) {
      input[i] = (int) cstr[i];
    }
    return input;
  }
/**
 * Returns part of the string
 * @param str input string
 * @param start start point 
 * @param end final point
 * @return part of the input string
 */
  public static String toString(int[] str, int start, int end) {
    StringBuffer sb = new StringBuffer();
    for (int i = start; i < end; i++) {
      sb.append((char) str[i]);
    }
    return sb.toString();
  }
/**
 * Returns new string token 
 * @param str input string 
 * @return new token with the input string
 */
  public Token getToken(String str) {
    return new Token(str);
  }
}
