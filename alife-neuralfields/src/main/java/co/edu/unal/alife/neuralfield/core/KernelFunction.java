package co.edu.unal.alife.neuralfield.core;

import co.edu.unal.alife.dynamic.core.Function;

public abstract class KernelFunction  {

	/**
	 * Evaluates the kernel function between x and y 
	 * @param x
	 * @param y
	 * @return the kernel value
	 */
	public abstract Double evaluateKernel(Double x, Double y);
}
