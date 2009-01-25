/**
 * 
 */
package co.edu.unal.alife.evolution;

import java.util.List;

import jml.evolution.Environment;
import jml.evolution.real.RealVectorLimits;
import jml.evolution.real.operators.GaussianMutation;

/**
 * @author Juan Figueredo
 *
 */
public class PendulumControllerMutation extends GaussianMutation {

	/**
	 * @param _environment
	 * @param _limits
	 * @param _sigma
	 */
	public PendulumControllerMutation(Environment _environment, RealVectorLimits _limits,
			double _sigma) {
		super(_environment, _limits, _sigma);
	}

	/**
	 * @param _environment
	 * @param _limits
	 * @param _sigma
	 */
	public PendulumControllerMutation(Environment _environment, RealVectorLimits _limits,
			double[] _sigma) {
		super(_environment, _limits, _sigma);
	}

	/**
	 * @param kernel
	 * @param size
	 * @param min
	 * @param max
	 */
	protected void mutateKernel(List<Double> kernel, int size, double[] min, double[] max) {
		try {
			for (int pos = 0; pos < size; pos++) {
				double x = kernel.get(pos);
				g.setSigma(sigma[pos]);
				double y = g.newDouble();
				x += y;
				if (x < min[pos]) {
					x = min[pos];
				} else if (x > max[pos]) {
					x = max[pos];
				}
				kernel.set(pos, x);
			}
		} catch (Exception e) {
			System.err.println("[Gaussian Mutation]" + e.getMessage());
		}
	}

}