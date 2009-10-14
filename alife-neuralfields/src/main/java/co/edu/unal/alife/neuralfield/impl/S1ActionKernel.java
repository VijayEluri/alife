/**
 * 
 */
package co.edu.unal.alife.neuralfield.impl;

import co.edu.unal.alife.neuralfield.KernelFunction;

/**
 * @author Juan Figueredo
 * 
 */
public class S1ActionKernel extends KernelFunction {

	private double[] a;
	private double[] b;

	/**
	 * 
	 */
	public S1ActionKernel() {
		// TODO Auto-generated constructor stub
	}

	public S1ActionKernel(double[] a, double[] b) {
		this();
		this.a = a;
		this.b = b;
	}
	
	public S1ActionKernel(double a, double b) {
		this(new double[]{a},new double[]{b});
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.unal.alife.neuralfield.core.KernelFunction#evaluateKernel(java
	 * .lang.Double, java.lang.Double)
	 */
	@Override
	public Double evaluateKernel(Double x, Double y) {
		int xIndex = x.intValue();
		double w = a[xIndex] * y + b[xIndex];
		return w;
	}

}
