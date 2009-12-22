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
package jml.object.transformation;

/**
 * <p>Title: CombinedDataTransformation</p>
 * <p>Description: transformation operation of a Data sample that is defined as
 * the combination of two data transformations </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class CombinedDataTransformation extends DataTransformation {
  /**
   * First data transformation
   */
  private DataTransformation first;
  /**
   * Second data transformation
   */
  private DataTransformation second;
  /**
   * Constructor. Creates a combined data transformation with the given transformations
   * @param first First DataTransformation
   * @param second Second DataTransformation
   */
  public CombinedDataTransformation(DataTransformation first,
                              DataTransformation second) {
    this.first = first;
    this.second = second;
  }
  /**
   * Applies the transformation on the Data Sample and creates a new DataSample
   * with the transformed version
   * @param data Data sample to be transformed
   * @return Transformed Data Sample (new object)
   */
  public Object apply(Object data) {
    return second.apply(first.apply(data));
  }

  /**
   * Applies the inverse operation of the transformation on
   * the Data sample and creates a new Data with the inverse transformed version
   * @param data Data to be inverse-transformed
   * @return Inverse-Transformed Data sample (new object)
   */
  public Object inverse(Object data) {
    return first.inverse(second.inverse(data));
  }

  /**
   * Determines if the operation is reversible or not
   * @return True if the transformation is reversible, false otherwise
   */
  public boolean isReversible() { return first.isReversible() && second.isReversible(); }

}
