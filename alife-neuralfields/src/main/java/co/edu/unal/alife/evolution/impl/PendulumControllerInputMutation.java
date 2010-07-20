/**
 * 
 */
package co.edu.unal.alife.evolution.impl;

import java.util.List;

import jml.evolution.Environment;
import jml.evolution.real.RealVectorLimits;

/**
 * @author Juan Figueredo
 */
public class PendulumControllerInputMutation extends PendulumControllerMutation {

	/**
	 * @param _environment
	 * @param _limits
	 * @param _sigma
	 */
	public PendulumControllerInputMutation(Environment _environment, RealVectorLimits _limits,
			double _sigma) {
		super(_environment, _limits, _sigma);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * @see jml.evolution.real.operators.GaussianMutation#apply(java.lang.Object)
	 */
	@Override
	public Object apply(Object gen) {
		PendulumControllerParameters genome = (PendulumControllerParameters) gen;
		List<Double> kernel = genome.getInputKernel();
		int size = genome.getSize();
		double[] min = limits.min;
		double[] max = limits.max;
		mutateKernel(kernel, size, min, max);
		return null;
	}

}