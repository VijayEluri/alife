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
package jml.object.util.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Enumeration;

import jml.object.sources.ObjectSource;
import jml.util.gui.DrawRegion;

/**
 * <p>Title: RealVectorObjectSourcePaint</p>
 * <p>Description: To draw a ObjectSource.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class RealVectorObjectSourcePaint extends DrawRegion {
  /**
   * ObjectSource to be drawn
   */
  private ObjectSource data = null;
  /**
   * Color used for drawing the data set
   */
  private Color color = Color.black;
  /**
   * Position x
   */
  private int x;
  /**
   * Position y
   */
  private int y;
  /**
   * Type of figure to draw
   */
  private int pointType;
  /**
   * Size of the figure to draw
   */
  private int pointSize;

  /**
   * Constructor: Creates a RealVectorObjectSourcePaint object
   * @param rec A rectangle object
   * @param data ObjectSource to be drawn
   * @param color Color used for drawing the data set
   * @param x Position x
   * @param y Position y
   * @param pointType Type of figure to draw
   * @param pointSize Size of the figure to draw
   */
  public RealVectorObjectSourcePaint(Rectangle rec, ObjectSource data,
                          Color color, int x, int y,
                          int pointType, int pointSize) {
    super(rec);
    this.data = data;
    this.color = color;
    this.x = x;
    this.y = y;
    this.pointType = pointType;
    this.pointSize = pointSize;
  }
  /**
   * Set the position (x, y)
   * @param x Position x
   * @param y Position y
   */
  public void setView(int x, int y) {
    this.x = x;
    this.y = y;
  }
  /**
   * Set the color
   * @param color The color using for drawing the data set
   */
  public void setColor(Color color) {
	  this.color = color;
  }
  /**
   * Set the type of figure to draw and its size
   * @param pointType The Type of figure to draw
   * @param pointSize The size of the figure
   */
  public void setType(int pointType, int pointSize) {
    this.pointType = pointType;
    this.pointSize = pointSize;
  }
  /**
   * To draw a ObjectSource
   * @param g A Graphics object
   */
  public void draw(Graphics g) {
      g.setColor(color);
      Enumeration iter = data.elements();
      while (iter.hasMoreElements()) {
        double[] rec = (double[]) iter.nextElement();
        int w = scaleX(rec[this.x]);
        int z = scaleY(rec[this.y]);
        drawPoint(g, w, z, pointType, pointSize);
      }
  }
}
