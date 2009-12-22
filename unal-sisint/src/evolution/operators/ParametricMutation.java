/**
 * 
 */
package evolution.operators;

import jml.evolution.Environment;
import jml.evolution.real.RealVectorLimits;
import jml.evolution.real.operators.GaussianMutation;

/**
 * @author Juan Figueredo
 *
 */
public abstract class ParametricMutation extends GaussianMutation {

   protected double sigma;
	
	/**
	 * @param _environment
	 * @param _limits
	 * @param _sigma
	 */
	public ParametricMutation(Environment _environment, RealVectorLimits _limits, double _sigma) {
		super(_environment, _limits, _sigma);
		this.sigma=_sigma;
		// TODO Auto-generated constructor stub
	}

}