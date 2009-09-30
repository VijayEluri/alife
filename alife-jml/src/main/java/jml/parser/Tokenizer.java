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

import java.io.Reader;
import java.io.StringReader;
import java.util.Vector;
/**
 * <p>Title: SimpleStreamTokenizer</p>
 * <p>Description: A simple stream tokenizer, where the token separators are
 * the blank characters, (space, enter, tab). The posible tokens are number or word</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Tokenizer {
  /**
   * TokenDFA Object
   */
  protected TokenDFA[] dfa;
  /**
   * Star point of input
   */
  public int start = 0;
  /**
   * input vector (transitions we want to make)
   */
  protected int[] input = null;

  /**
   * Input stream
   */
  protected Reader is = null;
  /**
   * Default constructor
   */
  protected Tokenizer() { }

  /**
   * Constructor: creates a simple stream tokenizer over the given stream
   * @param dfa The input stream to be tokenized
   */
  public Tokenizer(TokenDFA[] dfa) {
    this.dfa = dfa;
  }

  /**
   * Constructor: creates a simple stream tokenizer over the given stream
   * @param dfa Matrix of tokens
   * @param is The input stream to be tokenized
   */
  public Tokenizer(TokenDFA[] dfa,  Reader is) {
    this.is = is;
    this.dfa = dfa;
  }

  /**
   * Constructor: creates a simple stream tokenizer over the given stream
   * @param is The input stream to be tokenized
   */
  public Tokenizer(Reader is) {
    this.is = is;
  }
/**
 * Public constructor, assigns TokenDFA object to this object
 * @param source Tokenizer input object
 */
  public Tokenizer(Tokenizer source) {
    dfa = source.dfa;
  }

  /**
   * Constructor: creates a simple stream tokenizer over a string.
   * @param buffer The string used to create the tokenizer
   */
  public Tokenizer(String buffer) {
    is = new StringReader(buffer);
  }
/**
 * initializes Reader over the input stream
 * @param is The input stream 
 */
  public void init(Reader is) {
    this.is = is;
    input = null;
  }

  /**
   * Creates the original command line from the given arguments
   * This constructor is useful to tokenize the command arguments in a program
   * @param argv The array of string used to create the tokenizer
   * @return A String with the original command line
   */
  public static String commandLine(String[] argv) {
    StringBuffer sb = new StringBuffer();
    if (argv != null) {
      for (int i = 0; i < argv.length; i++) {
        sb.append(" ");
        sb.append(argv[i]);
      }
    }
    return sb.toString();
  }
/**
 * Return complete is reader string line 
 * @return string cotains in is reader, with the enter character if line has one
 */
  protected String getFullLine() {
    StringBuffer sb = new StringBuffer();
    try {
      int c = is.read();
      while (c != -1 && (char) c != '\n') {
        sb.append((char) c);
        c = (int) is.read();
      }
      if (c == '\n') { sb.append((char) c); }
    } catch (Exception e) {
      System.err.println("[SimpleStreamTokenizer]" + e.getMessage());
    }
    return sb.toString();
  }
/**
 * Returns line, even if line has an enter
 * @return return line without the end of line character
 */
  public String getLine() {
    String line = getFullLine();
    if (line != null && line.length() > 0 && line.charAt(line.length() - 1) == '\n') {
      line = line.substring(0, line.length() - 1);
    }
    return line;
  }

  /**
   * Obtains a list of the tokens readed by the tokenizer. Does not include the eof
   * token
   * @return Vector with the tokens readed by the tokenizer
   */
public Vector getTokens() {
    Vector tokens = new Vector();
    Token token = nextToken();
    while (token != null && token.type != Token.ttEOF) {
      tokens.add(token);
      token = nextToken();
    }
    return tokens;
  }
/**
 * Removes all the token of the given type that are into a vector
 * @param ttype type of tokens to remove
 * @param tokens input vector of tokens
 */
  public static void removeToken(int ttype, Vector tokens) {
    int k = 0;
    while (k < tokens.size()) {
      Token t = (Token) tokens.get(k);
      if (t.type == ttype) {
        tokens.remove(k);
      } else {
        k++;
      }
    }
  }

  /**
   * remove one o more tokens of a given vector depending of its type and value
   * @param token token object to remove from the vector
   * @param tokens input vector of tokens
   */
  public static void removeToken(Token token, Vector tokens) {
    int k = 0;
    while (k < tokens.size()) {
      Token t = (Token) tokens.get(k);
      if (t.equals(token)) {
        tokens.remove(k);
      } else {
        k++;
      }
    }
  }

  /**
   * Closes the input stream
   */
  public void close() {
    try {
      if (is != null) {
        is.close();
        is = null;
      }
    } catch (Exception e) {
      System.err.println("[SimpleStreamTokenizer]" + e.getMessage());
    }
  }

  /**
   * Reads the available token in the input stream. If there are not more tokens
   * returns ttEOF indicating that the end of the input stream has been reached.
   * If the token is a number then the value of the number is stored in the nval attribute.
   * If the token is not a number the token is stored in the sval attribute.
   * @return Type of the token read. If the parse process fails then ttERROR is returned.
   */
  public Token nextToken() {
    if (input == null) {
      String line = getFullLine();
      if (line != null) {
        input = TokenDFA.toInt(line);
        start = 0;
      }
    }
    if (input != null && input.length > 0) {
      // try each automaton
      int k = 0;
      boolean go = true;
      Token t = new Token((char) input[start]);
      while (k < dfa.length && go) {
        int end = dfa[k].simulate(start, input);
        if (end > start && (dfa[k].state == TokenDFA.STOP_ACEPTED 
        		|| (dfa[k].state != TokenDFA.STOP_REJECTED 
        				&& dfa[k].output[dfa[k].state] == TokenDFA.ACEPTED))) {
          go = false;
          String str = TokenDFA.toString(input, start, end);
          t = dfa[k].getToken(str); // k
          start = end;
        } else {
          k++;
        }
      }
      if (go) {
        start++;
      }
      if (start == input.length) {
        input = null;
      }
      return t;
    } else {
      return new Token(true);
    }
  }

}
