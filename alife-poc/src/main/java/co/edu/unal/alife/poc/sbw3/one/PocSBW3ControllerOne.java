package co.edu.unal.alife.poc.sbw3.one;

import co.edu.unal.alife.poc.sbw3.APocSBW3Controller;

public class PocSBW3ControllerOne extends APocSBW3Controller {

	private double[] k = new double[] { 0, 81.1293 };
	public static final int N = 2;

	public PocSBW3ControllerOne() {
		super(N);
	}

	public PocSBW3ControllerOne(double k_phi) {
		super(N);
		this.k = new double[] { 0, k_phi };
	}

	protected double[] evaluateK(double t, double[] y, double[] r) {
		return k;
	}
	

	@Override
	protected void switchEvent(double t, double[] q) {
		// N/A
	}

	@Override
	public void computeDerivatives(double t, double[] q, double[] qDot) {
		// N/A
	}

}
