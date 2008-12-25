/**
 * 
 */
package co.edu.unal.alife.neuralfield.impl;

import co.edu.unal.alife.neuralfield.KernelFunction;

/**
 * @author Juan Figueredo
 * 
 */
public class MexicanHatKernel extends KernelFunction {

//	public static final double sigma = 1;
	public static final double H0 = 0.02;
	public static final double delta = 1.2;
	public static final double k = 0.20;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.unal.alife.neuralfield.core.KernelFunction#evaluateKernel(java
	 * .lang.Double, java.lang.Double)
	 */
	@Override
	public Double evaluateKernel(Double x, Double y) {
		double d2 = (x - y) * (x - y);
//		double s2 = sigma * sigma;
//		double psi = 1 / (Math.sqrt(2 * Math.PI) * s2 * sigma)
//				* (1 - d2 / s2) * Math.exp(-s2 / (2 * s2));
//		return psi;		
		double delta2 = delta * delta;
		double w = k*Math.exp(-d2/delta2)-H0;
		return w;
	}

}
