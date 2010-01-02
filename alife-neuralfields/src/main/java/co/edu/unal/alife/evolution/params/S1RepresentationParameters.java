package co.edu.unal.alife.evolution.params;

import java.util.ArrayList;
import java.util.List;

public class S1RepresentationParameters {

	private static final double _Delta = 1d;
	private static final double _K = 2d;
	private static final double _H = 0.1;
	private List<Double> Cholesky1s;
	private List<Double> Cholesky2s;
	private List<Double> ks;
	private List<Double> Hs;
	private List<Double> deltas;
	private int prCardinality;
	private double minChoVal;
	private double maxChoVal;

	public S1RepresentationParameters(int prCardinality, double minChoVal, double maxChoVal) {
		super();
		this.prCardinality = prCardinality;
		this.Cholesky1s = new ArrayList<Double>(prCardinality);
		this.Cholesky2s = new ArrayList<Double>(prCardinality);
		for (int i = 0; i < prCardinality; i++) {
			double val = Math.random() * (maxChoVal - minChoVal) + minChoVal;
			Cholesky1s.set(i, val);
			val = Math.random() * (maxChoVal - minChoVal) + minChoVal;
			Cholesky2s.set(i, val);
			Hs.set(i, _H);
			ks.set(i, _K);
			deltas.set(i, _Delta);
		}
	}

	public List<Double> getCholesky1s() {
		return Cholesky1s;
	}

	public List<Double> getCholesky2s() {
		return Cholesky2s;
	}

	public int getPrCardinality() {
		return prCardinality;
	}

	public double getMinValue() {
		return minChoVal;
	}

	public double getMaxValue() {
		return maxChoVal;
	}

	public List<Double> getKs() {
		return ks;
	}

	public List<Double> getHs() {
		return Hs;
	}

	public List<Double> getDeltas() {
		return deltas;
	}
	

}
