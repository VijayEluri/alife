package co.edu.unal.alife.poc.neuralfield;

import static co.edu.unal.alife.poc.PocUtils.squareDistance;

public class PocMexicanHatKernelFunction {
	public double H0;
	public double delta;
	public double k;

	public PocMexicanHatKernelFunction(double h0, double delta, double k) {
		H0 = h0;
		this.delta = delta;
		this.k = k;
	}

	public double evalKernelValue(double x, double y) {
		double d2 = squareDistance(x, y);
		double delta2 = delta * delta;
		double w = k * Math.exp(-d2 / delta2) - H0;
		return w;
	}
}