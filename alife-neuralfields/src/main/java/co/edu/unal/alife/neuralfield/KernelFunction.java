package co.edu.unal.alife.neuralfield;


public abstract class KernelFunction  {

	/**
	 * Evaluates the kernel function between x and y 
	 * @param x
	 * @param y
	 * @return the kernel value
	 */
	public abstract Double evaluateTransformation(Double x, Double y);
}
