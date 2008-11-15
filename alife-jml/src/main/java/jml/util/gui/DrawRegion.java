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
package jml.util.gui;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * <p>Title: DrawRegion</p>
 * <p>Description: A draw area with the coordinates in the math (non window) orientation</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public abstract class DrawRegion {
	/**
	 * Constant (Type of object to draw)
	 */
    public static final int SIMPLE_POINT = 0;
    /**
     * Constant (Type of object to draw)
     */
    public static final int X_POINT = 1;
    /**
     * Constant (Type of object to draw)
     */
    public static final int PLUS_POINT = 2;
    /**
     * Constant (Type of object to draw)
     */
    public static final int TRIANGLE_POINT = 3;
    /**
     * Constant (Type of object to draw)
     */
    public static final int OVAL_POINT = 4;
    /**
     * Constant (Type of object to draw)
     */
    public static final int SQUARE_POINT = 5;
    /**
     * A rectangle (Type of object to draw)
     */
    private Rectangle roi;
	/**
	 * Creates a DrawRegion with a rectangle 
	 * @param rec The rectangle
	 */
    public DrawRegion(Rectangle rec) {
        roi = rec;
    }
    /**
     * Changes the wight with a factor x
     * @param x Scale factor
     * @return The position x more the new wight 
     */
    public int scaleX(double x) {
      return roi.x + (int) (roi.width * x + 0.5);
    }
	/**
	 * Changes the height with a factor y
	 * @param y Scale factor
	 * @return The position y more the new height
	 */
    public int scaleY(double y) {
      return roi.y - (int) (roi.height * y + 0.5);
    }
    /**
     * Draw a type of object in a position(x, y) and a size
     * @param g A Graphics object
     * @param x Position x
     * @param y Position y
     * @param type Type of the object
     * @param size The size
     */
    public void drawPoint(Graphics g, int x, int y, int type, int size) {
      switch(type) {
        case SIMPLE_POINT:
            g.drawLine(x, y, x, y);
        break;
        case X_POINT:
            g.drawLine(x - size, y + size, x + size, y - size);
            g.drawLine(x - size, y - size, x + size, y + size);
        break;
        case PLUS_POINT:
            g.drawLine(x, y + size, x, y - size);
            g.drawLine(x - size, y, x + size, y);
        break;
        case TRIANGLE_POINT:
            g.drawLine(x - size, y + size, x, y - size);
            g.drawLine(x, y - size, x + size, y + size);
            g.drawLine(x + size, y + size, x - size, y + size);
        break;
        case OVAL_POINT:
            g.drawOval(x - size, y - size, 2 * size, 2 * size);
        break;
        case SQUARE_POINT:
            g.drawLine(x - size, y + size, x - size, y - size);
            g.drawLine(x - size, y + size, x + size, y + size);
            g.drawLine(x + size, y + size, x + size, y - size);
            g.drawLine(x + size, y + size, x - size, y - size);
        break;
      }
    }
    /**
     * Abstract method
     * @param g A Graphics object
     */
    public abstract void draw(Graphics g);
}
