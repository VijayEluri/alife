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
package jml.object;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import jml.basics.Cloner;
import jml.io.ObjectIO;
import jml.parser.Token;
import jml.parser.Tokenizer;


/**
 * <p>Title: Data</p>
 * <p>Description: A data record with or without label.
 * With label is useful for classification problems.
 * Without label is useful for clustering problems.</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */

public class LabeledObject implements Cloneable {

  /**
   * If the label of the object is printed at the end of the string
   */
  public static final int END = 1;
  /**
   * If the label of the object is printed at the start of the string
   */
  public static final int START = 0;
  /**
   * If the label of the object is not printed at all
   */
  public static final int NO_LABEL = -1;
  /**
   * <p>The position of the label on the String. It can be:</p>
   * <p> END (1) : If the label of the object is printed at the end of the string</p>
   * <p> START (0) : If the label of the object is printed at the start of the string</p>
   * <p> NO_LABEL (-1) : If the label of the object is not printed at all</p>
   */
  public static int labelPos = NO_LABEL;

  /**
   * The data record class label
   */
  private int label = 0;

  /**
   * The object
   */
  protected Object data = null;

  /**
   * Constructor: Creates a LabeledObject with a object
   * @param data The object
   */
  public LabeledObject(Object data) {
    this.data = data;
  }
  
  /**
   * Constructor: Creates a LabeledObject with a object and a label
   * @param data The object
   * @param label The label
   */
  public LabeledObject(Object data, int label) {
    this.data = data;
    this.label = label;
  }

  /**
   * Creates a clone of the given data sample
   * @return A clone of the given data sample
   */
  public Object clone() {
    return new LabeledObject(Cloner.clone(data));
  }

  /**
   * Return the data record class label
   * @return Class Label
   */
  public int getLabel() { return label; }

  /**
   * Gets the object
   * @return The object
   */
  public Object get() { return data; }

  /**
   * Set the class for this data record
   * @param label Class label for the data record
   */
  public void setLabel(int label) { this.label = label; }

  /**
   * Reades the position of the label
   * @param reader Reader object  
   * @throws IOException Exception controlable
   */
  public void readLabel(Reader reader) throws IOException {
    Tokenizer tok = new Tokenizer(reader);
    Token t = tok.nextToken();
    label = (int) t.nval;
  }

  /**
   * Reader the label in the configured reader  
   * @param reader Reader object 
   * @throws IOException Exception controlable
   */ 
  public void read(Reader reader) throws IOException {
    switch(labelPos) {
      case END:
        ObjectIO.read(data, reader);
        readLabel(reader);
      break;
      case START:
        readLabel(reader);
        ObjectIO.read(data, reader);
      break;
      case NO_LABEL:
        ObjectIO.read(data, reader);
      break;
    }
  }
  
  /**
   * White the label in the configured white
   * @param writer White object
   * @throws IOException Exception controlable
   */
  public void write(Writer writer)  throws IOException {
    switch (labelPos) {
      case END:
        ObjectIO.write(data, writer);
        writer.write(" ");
        writer.write("" + label);
      break;
      case START:
        writer.write("" + label);
        writer.write(" ");
        ObjectIO.write(data, writer);
      break;
      case NO_LABEL:
        ObjectIO.write(data, writer);
      break;
    }
  }
}
