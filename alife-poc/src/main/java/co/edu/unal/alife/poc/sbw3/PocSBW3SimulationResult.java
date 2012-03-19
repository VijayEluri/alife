package co.edu.unal.alife.poc.sbw3;

public class PocSBW3SimulationResult {
	private double t;
	private double[] q;

	public PocSBW3SimulationResult(double t, double[] q) {
		super();
		this.t = t;
		this.q = q;
	}

	public double getT() {
		return t;
	}

	public double[] getQ() {
		return q;
	}

}
