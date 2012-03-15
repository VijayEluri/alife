package co.edu.unal.alife.poc.sbw3.one;

import co.edu.unal.alife.poc.sbw3.APocSBW3Controller;

public class PocSBW3ControllerOne extends APocSBW3Controller {

	public static int N = 2;
	
	public PocSBW3ControllerOne() {
		super(N);
	}

	protected double[] evaluateK(double t, double[] y, double[] r) {
		double[] k = new double[] { 0, 81.1293 };
		return k;
	}

	@Override
	public void computeDerivatives(double t, double[] q, double[] qDot) {
		// N/A	
	}
	
	
}
