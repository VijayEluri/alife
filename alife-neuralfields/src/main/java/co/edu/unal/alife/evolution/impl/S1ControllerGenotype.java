package co.edu.unal.alife.evolution.impl;

import jml.evolution.Genotype;
import co.edu.unal.alife.evolution.param.S1ControllerParameters;

public class S1ControllerGenotype extends Genotype {

	public static double _minCholeskyVal = 0.1;
	public static double _maxCholeskyVal = 10.0;
	public static double _minKVal = 0.1;
	public static double _maxKVal = 3.0;
	// public static double _minTVal = 0.0;
	// public static double _maxTVal = 2 * Math.PI;
	public static double _minAlphaVal = 0.0;
	public static double _maxAlphaVal = 1.0;

	private int points;
	private int noInputs;
	private int noGoals;
	private int noOutputs;

	private double minCholeskyVal;
	private double maxCholeskyVal;
	private double minKVal;
	private double maxKVal;
	private double minAlphaVal;
	private double maxAlphaVal;

	private double[] Chol1;
	private double[] Chol2;
	private double[] ks;
	private double[] alphas;
	private double minKernelRepK;
	private double maxKernelRepK;
	private double minKernelDelta;
	private double maxKernelDelta;
	private double minKernelInK;
	private double maxKernelInK;

	public S1ControllerGenotype(int points, int noInputs, int noGoals,
			int noOutputs) {
		this(points, noInputs, noGoals, noOutputs, _minCholeskyVal,
				_maxCholeskyVal, _minKVal, _maxKVal, _minAlphaVal, _maxAlphaVal);
	}

	public S1ControllerGenotype(int points, int noInputs, int noGoals,
			int noOutputs, double[] Chol1, double[] Chol2, double[] ks,
			double[] alphas, double minKernelRepK,
			double maxKernelRepK, double minKernelDelta, double maxKernelDelta, double minKernelInK, double maxKernelInK) {
		super();
		this.points = points;
		this.noInputs = noInputs;
		this.noGoals = noGoals;
		this.noOutputs = noOutputs;
		this.Chol1 = Chol1;
		this.Chol2 = Chol2;
		this.ks = ks;
		this.alphas = alphas;
		this.minKernelRepK = minKernelRepK;
		this.maxKernelRepK = maxKernelRepK;
		this.minKernelDelta = minKernelDelta;
		this.maxKernelDelta = maxKernelDelta;
		this.minKernelInK = minKernelInK;
		this.maxKernelInK = maxKernelInK;
	}

	public S1ControllerGenotype(int points, int noInputs, int noGoals,
			int noOutputs, double minCholeskyVal, double maxCholeskyVal,
			double minKVal, double maxKVal, double minAlphaVal,
			double maxAlphaVal) {
		super();
		this.points = points;
		this.noInputs = noInputs;
		this.noGoals = noGoals;
		this.noOutputs = noOutputs;
		this.minCholeskyVal = minCholeskyVal;
		this.maxCholeskyVal = maxCholeskyVal;
		this.minKVal = minKVal;
		this.maxKVal = maxKVal;
		this.minAlphaVal = minAlphaVal;
		this.maxAlphaVal = maxAlphaVal;
	}

	@Override
	public Object newInstance() {
		S1ControllerParameters parameters = new S1ControllerParameters(
				noInputs, noGoals, noOutputs, Chol1, Chol2, ks, alphas,
				minKernelRepK, maxKernelRepK, minKernelDelta, maxKernelDelta, minKernelInK, maxKernelInK);
		// S1ControllerParameters parameters = new S1ControllerParameters(
		// noInputs, noGoals, noOutputs, minKVal, maxKVal, minCholeskyVal,
		// maxCholeskyVal, minAlphaVal, maxAlphaVal);
		return parameters;
	}

	@Override
	public int size(Object arg0) {
		return points;
	}

}
