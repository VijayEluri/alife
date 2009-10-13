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
	private double H0 = 0.05;
	private double delta = 4;
	private double k = 0.05;
	
	/**
	 * 
	 */
	public MexicanHatKernel() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param h0
	 * @param delta
	 * @param k
	 */
	public MexicanHatKernel(double h0, double delta, double k) {
		this();
		H0 = h0;
		this.delta = delta;
		this.k = k;
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
		double d2 = squareDistance(x, y);
//		double s2 = sigma * sigma;
//		double psi = 1 / (Math.sqrt(2 * Math.PI) * s2 * sigma)
//				* (1 - d2 / s2) * Math.exp(-s2 / (2 * s2));
//		return psi;		
		double delta2 = delta * delta;
		double w = k*Math.exp(-d2/delta2)-H0;
		return w;
	}

	public double squareDistance(Double x, Double y) {
		return (x - y) * (x - y);
	}
	
	public static void main(String[] args) {
		MexicanHatKernel kernel = new MexicanHatKernel(0.05,2,2.00);
		Double double1 = kernel.evaluateKernel(0d, 1d);
		System.out.println(double1);
	}

}
