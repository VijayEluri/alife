package co.edu.unal.alife.evolution.param;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.alife.evolution.impl.CloneUtil;
import co.edu.unal.alife.neuralfield.impl.S1ActionTransformation;
import co.edu.unal.alife.neuralfield.impl.S1SymmetryTransformation;

public class S1OutputParameters {
	public final static boolean BOOL_T = false;
	public final static double VAL_T = 0d;

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

	public S1OutputParameters() {
	};

	//Used for fixed OutputParams in evolution
	public S1OutputParameters(int prCardinality, int outCardinality,
			double[] alphas) {
		this.prCardinality = prCardinality;
		this.outCardinality = outCardinality;
		int size = outCardinality * prCardinality;

		this.doubleTs = new ArrayList<Double>(size);
		this.alphas = new ArrayList<Double>(size);
		this.boolTs = new ArrayList<Boolean>(size);
		for (int i = 0; i < size; i++) {
			this.alphas.add(alphas[i]);
			this.doubleTs.add(VAL_T);
			this.boolTs.add(BOOL_T);
		}
		createTransformations();
	}

	public S1OutputParameters(int prCardinality, int outCardinality,
			double minValAlpha, double maxValAlpha) {
		super();
		this.prCardinality = prCardinality;
		this.outCardinality = outCardinality;
		this.minValAlpha = minValAlpha;
		this.maxValAlpha = maxValAlpha;
		int size = outCardinality * prCardinality;

		this.doubleTs = new ArrayList<Double>(size);
		this.alphas = new ArrayList<Double>(size);
		this.boolTs = new ArrayList<Boolean>(size);
		for (int i = 0; i < size; i++) {
			// TODO: Uncomment for evolution
			// double valT = Math.random() * (maxValT - minValT) + minValT;
			doubleTs.add(VAL_T);
			// boolean bolT = (Math.random() > 0) ? true : false;
			boolTs.add(BOOL_T);
			double valAlpha = Math.random() * (maxValAlpha - minValAlpha)
					+ minValAlpha;
			alphas.add(valAlpha);
		}
		createTransformations();
	}

	public void createTransformations() {
		transformations = new ArrayList<List<S1ActionTransformation>>(
				outCardinality);
		for (int i = 0; i < outCardinality; i++) {
			List<S1ActionTransformation> transformationList = new ArrayList<S1ActionTransformation>(
					prCardinality);
			for (int j = 0; j < prCardinality; j++) {
				int index = i * prCardinality + j;
				Double angle = this.getDoubleTs().get(index);
				Boolean isReflection = this.getBoolTs().get(index);
				S1SymmetryTransformation symmetryTransformation = new S1SymmetryTransformation(
						angle, isReflection);
				Double alpha = this.getAlphas().get(index);
				S1ActionTransformation transformation = new S1ActionTransformation(
						symmetryTransformation, alpha);
				transformationList.add(transformation);
			}
			transformations.add(transformationList);
		}
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		S1OutputParameters out = new S1OutputParameters();
		out.prCardinality = this.prCardinality;
		out.outCardinality = this.outCardinality;
		out.minValT = this.minValT;
		out.maxValT = this.maxValT;
		out.minValAlpha = this.minValAlpha;
		out.maxValAlpha = this.maxValAlpha;
		out.alphas = CloneUtil.cloneAsArrayList(this.alphas);
		out.boolTs = CloneUtil.cloneAsArrayList(this.boolTs);
		out.doubleTs = CloneUtil.cloneAsArrayList(this.doubleTs);
		out.createTransformations();
		return out;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for (double alpha : alphas) {
			sb.append(alpha);
			if (i++ < alphas.size() - 1) {
				sb.append(",");
			}
		}
		return sb.toString();
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
