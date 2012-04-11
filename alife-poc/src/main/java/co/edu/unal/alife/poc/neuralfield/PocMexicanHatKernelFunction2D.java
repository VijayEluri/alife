package co.edu.unal.alife.poc.neuralfield;

import static co.edu.unal.alife.poc.PocUtils.squareEuclideanDistance;

public class PocMexicanHatKernelFunction2D {
	private double H0;
	private double delta2;
	private double k;

	public PocMexicanHatKernelFunction2D(double h0, double delta, double k) {
		H0 = h0;
		this.delta2 = delta*delta;
		this.k = k;
	}

	public double evalKernelValue(double x1, double y1, double x2, double y2) {
		double d2 = squareEuclideanDistance(x1, y1, x2, y2);
		double w = k * Math.exp(-d2 / delta2) - H0;
		return w;
	}
}