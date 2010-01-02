package co.edu.unal.alife.evolution.params;

import java.util.ArrayList;
import java.util.List;

public class S1InputParameters {
	private List<Double> ks;
	private int inCardinality;
	private int prCardinality;
	private double minValue;
	private double maxValue;

	public S1InputParameters(int inCardinality, int prCardinality,
			double minValue, double maxValue) {
		super();
		this.inCardinality = inCardinality;
		this.prCardinality = prCardinality;
		int size = inCardinality * prCardinality;
		this.ks = new ArrayList<Double>(size);
		for (int i = 0; i < size; i++) {
			double val = Math.random() * (maxValue - minValue) + minValue;
			ks.set(i, val);
		}
	}

	public List<Double> getKs() {
		return ks;
	}

	public int getInCardinality() {
		return inCardinality;
	}

	public int getPrCardinality() {
		return prCardinality;
	}

	public double getMinValue() {
		return minValue;
	}

	public double getMaxValue() {
		return maxValue;
	}
}
