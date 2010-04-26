package co.edu.unal.alife.evolution.params;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.alife.evolution.CloneUtil;

public class S1InputParameters {
	private List<Double> maps;
	private int inCardinality;
	private int prCardinality;
	private double minValue = 0;
	private double maxValue = 0;

	public S1InputParameters() {
	};

	public S1InputParameters(int inCardinality, int prCardinality, double[] ks) {
		this.inCardinality = inCardinality;
		this.prCardinality = prCardinality;
		this.maps = new ArrayList<Double>();
		for (double d : ks) {
			this.maps.add(d);
		}
	}

	public S1InputParameters(int inCardinality, int prCardinality,
			double minValue, double maxValue) {
		super();
		this.inCardinality = inCardinality;
		this.prCardinality = prCardinality;
		int size = inCardinality * prCardinality;
		this.maps = new ArrayList<Double>(size);
		for (int i = 0; i < size; i++) {
			double val = Math.random() * (maxValue - minValue) + minValue;
			maps.add(val);
		}
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		S1InputParameters out = new S1InputParameters();
		out.inCardinality = this.inCardinality;
		out.prCardinality = this.prCardinality;
		out.minValue = this.minValue;
		out.maxValue = this.maxValue;
		out.maps = CloneUtil.cloneAsArrayList(this.maps);
		return out;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for (double k : maps) {
			sb.append(k);
			if (i++ < maps.size() - 1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

	public List<Double> getMaps() {
		return maps;
	}
	
	public List<Double> getSubMaps(int inputPopIndex) {
		return maps.subList(inputPopIndex*inCardinality, (inputPopIndex+1)*inCardinality);
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
