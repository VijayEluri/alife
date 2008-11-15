package jml.object.util.statistic;
import java.util.Enumeration;

import jml.object.LabeledObject;
import jml.object.sources.ObjectSource;

/**
 * Title: ClassDistribution
 * Description: Gets, sets, distribution of an objectsource
 * Copyright:    Copyright (c) 2004
 * Company: Universidad Nacional de Colombia
 * @author Jonatan Gomez
 * @version 1.0
 */
public class ClassDistribution extends ObjectSourceStatistic {
  /**
   * size of source elements
   */
	protected int size = 0;
  /**
   * Integer Array represent distribution elements
   */
  protected int[] distribution = new int[0];

  /**
   * Generates the average (feature by feature) for the given ObjectSource
   * @param source ObjectSource to be analyzed
   */
  public void generate(ObjectSource source) {
    size = 0;
    int c = 0;
    Enumeration iter = source.elements();
    while (iter.hasMoreElements()) {
      int label = ((LabeledObject) iter.nextElement()).getLabel();
      if (label >= c) {
        int[] d = new int[label + 1];
        for (int i = 0; i < c; i++) { d[i] = distribution[i]; }
        for (int i = c; i < d.length; i++) { d[i] = 0; }
        d[label] = 1;
        distribution = d;
      } else { distribution[label]++; }
      size++;
      c = label + 1;
    }
  }
/**
 * get distribution of each element in distribution int array
 * @return distribution of each element
 */
  public double[] getDistribution() {
    int n = distribution.length;
    double[] d = new double[n];
    for (int i = 0; i < n; i++) {
      d[i] = ((double) distribution[i]) / size;
    }
    return d;
  }
/**
 * Histogram
 * @return The dsistribution array
 */
  public int[] getHistogram() { return distribution; }
}
