package co.edu.unal.alife.evolution.params;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.alife.evolution.CloneUtil;

public class S1InputParameters {
	private List<Double> ks;
	private int inCardinality;
	private int prCardinality;
	private double minValue = 0;
	private double maxValue = 0;

	public S1InputParameters() {
	};

	public S1InputParameters(int inCardinality, int prCardinality, double[] ks) {
		this.inCardinality = inCardinality;
		this.prCardinality = prCardinality;
		this.ks = new ArrayList<Double>();
		for (double d : ks) {
			this.ks.add(d);
		}
	}

	public S1InputParameters(int inCardinality, int prCardinality,
			double minValue, double maxValue) {
		super();
		this.inCardinality = inCardinality;
		this.prCardinality = prCardinality;
		int size = inCardinality * prCardinality;
		this.ks = new ArrayList<Double>(size);
		for (int i = 0; i < size; i++) {
			double val = Math.random() * (maxValue - minValue) + minValue;
			ks.add(val);
		}
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		S1InputParameters out = new S1InputParameters();
		out.inCardinality = this.inCardinality;
		out.prCardinality = this.prCardinality;
		out.minValue = this.minValue;
		out.maxValue = this.maxValue;
		out.ks = CloneUtil.cloneAsArrayList(this.ks);
		return out;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for (double k : ks) {
			sb.append(k);
			if (i++ < ks.size() - 1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

	public List<Double> getKs() {
		return ks;
	}
	
	public List<Double> getSubKs(int inputPopIndex) {
		return ks.subList(inputPopIndex*inCardinality, (inputPopIndex+1)*inCardinality);
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
