/**
 * 
 */
package co.edu.unal.alife.dynamic.core;

import co.edu.unal.alife.neuralfield.core.KernelFunction;

/**
 * @author jjfigueredou
 *
 */
public abstract class IntegralFunction implements Function {
	
	private KernelFunction kernel;
	
	/**
	 * @param function
	 * @return
	 */
	public abstract double integrate();

	/**
	 * @return the kernel
	 */
	public KernelFunction getKernel() {
		return kernel;
	}

	/**
	 * @param kernel the kernel to set
	 */
	public void setKernel(KernelFunction kernel) {
		this.kernel = kernel;
	}
	
	
}
