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
package jml.evolution;

/**
 * <p>Title: Fitness</p>
 * <p>Description: An abstract class that represents a fitness function.</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public abstract class Fitness {

  protected Environment environment;

  /**
   * Determines if the fitness function is non-statoinary. It will help the ea
   * to evaluate the fitness function whenever is required
   */
  protected boolean nonStationary = false;

  /**
   * Evaluates the fitness function of the object given
   * @param obj Object used to calculate the fitness
   * @return The fitness value of the given object
   */
  public abstract double evaluate(Individual obj);

  /**
   * Determines if the fitness function is not stationary or stationary
   * @return true if the fitness function is not stationary, false if it is stationary
   */
  public boolean isNonStationary() { return nonStationary; }
  /**
   * Sets the attribute enviroment
   * @param environment The enviroment
   */
  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }
}
