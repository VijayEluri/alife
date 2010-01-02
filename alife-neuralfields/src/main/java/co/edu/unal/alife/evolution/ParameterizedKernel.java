/**
 * 
 */
package co.edu.unal.alife.evolution;

import java.util.List;

import co.edu.unal.alife.neuralfield.KernelFunction;

/**
 * @author Juan Figueredo
 *
 */
public class ParameterizedKernel extends KernelFunction {
	
	private List<Double> kernel;

	/**
	 * @param kernel
	 */
	public ParameterizedKernel(List<Double> kernel) {
		super();
		this.kernel = kernel;
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.KernelFunction#evaluateKernel(java.lang.Double, java.lang.Double)
	 */
	@Override
	public Double evaluateTransformation(Double x, Double y) {
		int pos = (int) Math.abs(x - y);
		return kernel.get(pos);
	}

}
