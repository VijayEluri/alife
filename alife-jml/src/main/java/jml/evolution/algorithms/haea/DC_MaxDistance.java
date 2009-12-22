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
package jml.evolution.algorithms.haea;

import jml.evolution.Environment;
import jml.evolution.Individual;
import jml.math.quasimetric.QuasiMetric;


/**
 * Title:
 * Description: Deterministic Crowding for Haea including a maximum distance for
 * allowing the replacement.
 * Copyright:    Copyright (c) 2004
 * Company: Universidad Nacional de Colombia
 * @author Jonatan Gomez
 * @version 1.0
 */

public class DC_MaxDistance extends DCHaea {
  /**
   * Maximum distance for allowing the replacement
   */
  protected double max_distance;

  /**
   * Constructor
   * @param _metric QuasiMetric for calculating the distance between parent and child
   * @param _max_distance Maximum distance for allowing the replacement
   */
  public DC_MaxDistance( Environment _environment, QuasiMetric _metric, double _max_distance ) {
    super( _environment, _metric );
    max_distance = _max_distance;
  }

  /**
   * This method determines if the parent can be replaced by the child.
   * It is possible if the child is better than the parent and the distance
   * between the parent and the child is less or equal to max_distance
   * @param child The child individual
   * @param parent The parent individual
   * @return true if the parent can be replaced, false otherwise
   */
  public boolean can_replace( Individual child, Individual parent ){
    return (super.can_replace(child,parent) && metric.distance( parent, child ) <= max_distance);
  }
}
