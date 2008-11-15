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
package jml.evolution.selections;

import java.util.Vector;

import jml.evolution.Environment;
import jml.evolution.Population;
import jml.evolution.Selection;
import jml.random.WeightedNumberGenerator;

/**
 * <p>Title: Elitism</p>
 * <p>Description: A elitist selection strategy. In this strategy the best individuals
 * (Elite percentage) are always selected and the worst individuals (cull percentange)
 * are never take into account. The remaining part of the individual is choosen
 * randomly, and each individual has a probability to be choosen that is proportional to
 * its fitness.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Elitism extends Selection {

  /**
   * Elite percentage: Percentage of individuals to be included in the selection
   * according to their fitness
   */
  protected double elite_percentage = 0.1;
  /**
   * Cull percentage: percentage of individuals to be excluded in the selection
   * according to their fitness
   */
  protected double cull_percentage = 0.1;

  /**
   * Constructor: Create a Elitist selection strategy.
   * @param _environment Environment of the Population
   * @param _n Number of individuals to be choosen
   * @param _includeX If the individual given in the apply method is going to be selected always or not
   * @param _elite_percentage Percentage of individuals to be included in the selection
   * @param _cull_percentage Percentage of individuals to be excluded in the selection
   */
  public Elitism( Environment _environment, int _n, boolean _includeX,
                  double _elite_percentage, double _cull_percentage ){
    super(_environment,_n, _includeX);
    elite_percentage = _elite_percentage;
    cull_percentage = _cull_percentage;
  }

  /**
   * Choose a set of individuals from the population including the individual x
   * @param population Population source of the selection process
   * @param x Individual to be included in the selection
   */
  public Vector choose( Population population, int x ){
    Vector sel = null;
    if( population != null ){
      if( n == 1 ){
        sel = new Vector();
        if( includeX ){
          sel.add(population.get(x));
        }else{
          population.sort();
//          sel.add(population.get(population.size()-1));
          sel.add(population.get(0));
        }
      }else{
        int k = (int)(population.size() * (1.0-cull_percentage));
        double[] weight = new double[k];
        double total = k*(k+1)/2.0;
        for( int i=0; i<k; i++ ){ weight[i] = (k-i)/total; }
        WeightedNumberGenerator generator = new WeightedNumberGenerator( weight );
        int[] sort_index = new int[population.size()];
        for( int i=0; i<sort_index.length; i++ ){  sort_index[i] = i;  }
        for( int i=0; i<sort_index.length-1; i++ ){
          for( int j=i+1; j<sort_index.length; j++ ){
            if( population.get(sort_index[i]).getFitness() <
                population.get(sort_index[j]).getFitness() ){
            int temp = sort_index[i];
            sort_index[i] = sort_index[j];
            sort_index[j] = temp;
          }
          }
        }
        int m = (int)(population.size() * elite_percentage);
        boolean flag = false;
        sel = new Vector();
        for( int i=0; i<n && i<m; i++ ){
          if( sort_index[i] == x ){ flag = true; }
          sel.add( population.get( sort_index[i] ) );
        }
        for( int i=m; i<n; i++ ){
          int index = sort_index[generator.newInt()];
          if( index == x ){ flag = true; }
          sel.add( population.get( index ) );
        }
        if( !flag && x >=0 && x < population.size() ){
          sel.remove(sel.size()-1);
          sel.add( population.get(x) );
        }
      }
    }
    return sel;
  }
}
