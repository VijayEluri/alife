package co.edu.unal.alife.evolution.params;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.alife.neuralfield.S1SymmetryTransformation;
import co.edu.unal.alife.neuralfield.impl.S1ActionTransformation;

public class S1OutputParameters {
	private List<Boolean> boolTs;
	private List<Double> doubleTs;
	private List<Double> alphas;

	private List<List<S1ActionTransformation>> transformations;

	private int prCardinality;
	private int outCardinality;
	private double minValT;
	private double maxValT;
	private double minValAlpha;
	private double maxValAlpha;

	public S1OutputParameters(int prCardinality, int outCardinality,
			double minValT, double maxValT, double minValAlpha,
			double maxValAlpha) {
		super();
		this.prCardinality = prCardinality;
		this.outCardinality = outCardinality;
		this.minValT = minValT;
		this.maxValT = maxValT;
		this.minValAlpha = minValAlpha;
		this.maxValAlpha = maxValAlpha;
		int size = outCardinality * prCardinality;

		this.doubleTs = new ArrayList<Double>(size);
		this.alphas = new ArrayList<Double>(size);
		for (int i = 0; i < size; i++) {
			double valT = Math.random() * (maxValT - minValT) + minValT;
			doubleTs.set(i, valT);
			boolean bolT = (Math.random() > 0) ? true : false;
			boolTs.set(i, bolT);
			double valAlpha = Math.random() * (maxValAlpha - minValAlpha)
					+ minValAlpha;
			alphas.set(i, valAlpha);
		}
	}

	public void updateTransformations() {
		transformations = new ArrayList<List<S1ActionTransformation>>(
				outCardinality);
		for (int i = 0; i < outCardinality; i++) {
			List<S1ActionTransformation> transformationList = new ArrayList<S1ActionTransformation>(
					prCardinality);
			for (int j = 0; j < prCardinality; j++) {
				int index = i*prCardinality+j;
				Double angle = this.getDoubleTs().get(index);
				Boolean isReflection = this.getBoolTs().get(index);
				S1SymmetryTransformation symmetryTransformation = new S1SymmetryTransformation(angle, isReflection);
				Double alpha = this.getAlphas().get(index);
				S1ActionTransformation transformation = new S1ActionTransformation(symmetryTransformation, alpha);
				transformationList.add(transformation);
			}
			transformations.add(transformationList);
		}
	}
	
	public List<List<S1ActionTransformation>> getTransformations() {
		return transformations;
	}

	public List<Boolean> getBoolTs() {
		return boolTs;
	}

	public List<Double> getDoubleTs() {
		return doubleTs;
	}

	public List<Double> getAlphas() {
		return alphas;
	}

	public int getPrCardinality() {
		return prCardinality;
	}

	public int getOutCardinality() {
		return outCardinality;
	}

	public double getMinValT() {
		return minValT;
	}

	public double getMaxValT() {
		return maxValT;
	}

	public double getMinValAlpha() {
		return minValAlpha;
	}

	public double getMaxValAlpha() {
		return maxValAlpha;
	}

}
