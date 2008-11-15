package jml.evolution.util;

import jml.evolution.Environment;
import jml.evolution.Individual;
import jml.math.quasimetric.QuasiMetric;

/**
 * <p>Title: IndividualMetric</p>
 * <p>Description: To get the distance between to individuals</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class IndividualMetric extends QuasiMetric {
	/**
	 * The environment of IndividualMetrics
	 */
  protected Environment env;
  /**
   * A QuasiMetric
   */
  protected QuasiMetric metric;
  /**
   * Default constructor
   * @param _env The environment
   * @param _metric The Quasimetric
   */
  public IndividualMetric(Environment _env, QuasiMetric _metric) {
    metric = _metric;
    env = _env;
  }

  /**
   * Distance (Implement this jml.util.quasimetric.QuasiMetric method)
   * @param one First Object
   * @param two Second Object
   * @return double 
   */
  public double distance(Object one, Object two) {
    return metric.distance(((Individual) one).getThing(env), ((Individual) two).getThing(env));
  }
  /**
   * Generates a IndividualMetric 
   * @param environment The environment
   * @param metric The QuasiMetric
   * @return A IndividualMetrics
   */
  public static QuasiMetric generate( Environment environment, QuasiMetric metric) {
    if(metric instanceof IndividualMetric) {
      return metric;
    } else {
      return new IndividualMetric(environment, metric);
    }
  }
}
