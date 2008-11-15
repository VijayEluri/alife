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
package jml.math.realvector;
import java.util.Vector;

import jml.object.ObjectGenerator;
import jml.parser.Token;
import jml.parser.Tokenizer;
/**
 * <p>Title: RealVectorGenerator</p>
 * <p>Description: Creates  vector of doubles with a string tokenizer.</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class RealVectorGenerator extends ObjectGenerator {
	/**
	 * Creates a RealVectorGenerator object.
	 */
	public RealVectorGenerator() { }
	/**
	 * Creates a vector of doubles with a string tokenizer
	 * @param tokenizer A string tokenizer
	 * @return A vector of doubles  
	 */
  public Object get(Tokenizer tokenizer) {
    Vector args = new Vector();
    Token token = tokenizer.nextToken();
    while (token.type != Token.ttEOF 
    		&& (token.type != Token.ttSYMBOL || token.cval != '\n')) {
      if (token.type != Token.ttWHITESPACE 
    		  && (token.type != Token.ttSYMBOL || token.cval != ',')) {
        args.add(token);
      }
      token = tokenizer.nextToken();
    }
    int n = args.size();
    if (n > 0) {
      double[] d = new double[n];
      for (int i = 0; i < n; i++) {
        d[i] = ((Token) args.get(i)).nval;
      }
      return d;
    } else { return null; }
  }

}
