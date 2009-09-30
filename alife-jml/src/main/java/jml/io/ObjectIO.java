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
package jml.io;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Method;

/**
 * <p>Title: Statistics</p>
 * <p>Description: Abstract class for io.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class ObjectIO {
/**
 * This method do the write method of obj 
 * @param obj Object with write method
 * @param writer The writer object
 * @throws IOException IOException
 */
  public static void write(Object obj, Writer writer) throws IOException {
    Class cl = obj.getClass();
    try {
      Method m = cl.getMethod("write", new Class[]{writer.getClass()});
      m.invoke(obj, new Object[]{writer});
    } catch (Exception e) {
      writer.write(obj.toString());
    };
  }
  /**
   * This method do the read method of obj 
   * @param obj Object with read method
   * @param reader The reader object
   * @throws IOException IOException
   * @return Object readed
   */
  public static Object read(Object obj, Reader reader) throws IOException {
    Class cl = obj.getClass();
    try {
      Method m = cl.getMethod("read", new Class[]{reader.getClass()});
      return m.invoke(obj, new Object[]{reader});
    } catch (Exception e) {
      throw new IOException (e.getMessage());
    }
  }
}