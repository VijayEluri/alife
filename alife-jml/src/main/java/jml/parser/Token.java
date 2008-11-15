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
 * <p>Title: Token</p>
 * <p>Description: A basic unit in a sintactic analysis</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Token {
  /**
   * A constant indicating that a number token has been read.
   */
  public static final int ttNUMBER = 1;

  /**
   * A constant indicating that a word token has been read.
   */
  public static final int ttWORD = 2;

  /**
   * A constant indicating that a single symbol token has been read.
   */
  public static final int ttSYMBOL = 3;

  /**
   * A constant indicating that a white space token has been read.
   */
  public static final int ttWHITESPACE = 4;

  /**
   * A constant indicating that the end of the stream has been read.
   */
  public static final int ttEOF = 0;

  /**
   * A constant indicating that an error has ocurred while the tokenizer was reading the stream.
   */
  public static final int ttERROR = -1;

  /**
   * Type of token
   */
  public int type = ttERROR;

  /**
   * Real value of the token if it is a number
   */
  public double nval;

  /**
   * String value of the token if it is a word
   */
  public String sval;

  /**
   * Char value of the token if it is a character
   */
  public char cval;

  /**
   * Creates a numeric token with the given value
   * @param nval Value of the numeric token
   */
  public Token(double nval) {
    type = ttNUMBER;
    this.nval = nval;
  }


  /**
   * Creates a word token with the given string
   * @param sval The word assigned to the word token
   */
  public Token(String sval) {
    type = ttWORD;
    this.sval = sval;
  }

  /**
   * Creates a word token with the given string
   * @param cval The word assigned to the word token
   */
  public Token(char cval) {
    type = ttSYMBOL;
    this.cval = cval;
  }

  /**
   * Creates a word token with the given string
   * @param ttype The type of token
   * @param sval The word assigned to the word token
   */
  public Token(int ttype, String sval) {
    type = ttype;
    this.sval = sval;
  }

  /**
   * Creates a error token
   */
  public Token() { }

  /**
   * Creates a eof token
   * @param eof dummy variable. It always creates a eof token
   */
  public Token(boolean eof) {
    type = ttEOF;
  }
  /**
   * Returns token value as String 
   * @return A String
   */
  public String toString() {
    if (type == Token.ttNUMBER) {
      return "" + nval;
    } else {
      return sval;
    }
  }

  /**
   * Returns true if token values and types are equal 
   * @param obj Token to compare
   * @return true if token values and types are equal
   */
  public boolean equals(Object obj) {
    if (obj != null && obj instanceof Token) {
      Token other = (Token) obj;
      boolean flag = (type == other.type);
      if (flag) {
        switch (type) {
          case ttEOF:
          case ttERROR:
            break;
          case ttNUMBER:
            flag = (nval == other.nval);
            break;
          case ttSYMBOL:
            flag = (cval == other.cval);
            break;
          default:
            flag = sval.equals(other.sval);
            break;
        }
      }
      return flag;
    }
    return false;
  }
}
