package co.edu.unal.alife.evolution;

import co.edu.unal.alife.evolution.params.S1ControllerParameters;
import jml.evolution.Genotype;

public class S1ControllerGenotype extends Genotype {

	public static double _minCholeskyVal = 0.1;
	public static double _maxCholeskyVal = 10.0;
	public static double _minKVal = 0.1;
	public static double _maxKVal = 3.0;
	public static double _minTVal = 0.0;
	public static double _maxTVal = 2 * Math.PI;
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
	private double minTVal;
	private double maxTVal;
	private double minAlphaVal;
	private double maxAlphaVal;

	public S1ControllerGenotype(int points, int noInputs, int noGoals,
			int noOutputs) {
		this(points, noInputs, noGoals, noOutputs, _minCholeskyVal,
				_maxCholeskyVal, _minKVal, _maxKVal, _minTVal, _maxTVal,
				_minAlphaVal, _maxAlphaVal);
	}

	public S1ControllerGenotype(int points, int noInputs, int noGoals,
			int noOutputs, double minCholeskyVal, double maxCholeskyVal,
			double minKVal, double maxKVal, double minTVal, double maxTVal,
			double minAlphaVal, double maxAlphaVal) {
		super();
		this.points = points;
		this.noInputs = noInputs;
		this.noGoals = noGoals;
		this.noOutputs = noOutputs;
		this.minCholeskyVal = minCholeskyVal;
		this.maxCholeskyVal = maxCholeskyVal;
		this.minKVal = minKVal;
		this.maxKVal = maxKVal;
		this.minTVal = minTVal;
		this.maxTVal = maxTVal;
		this.minAlphaVal = minAlphaVal;
		this.maxAlphaVal = maxAlphaVal;
	}

	@Override
	public Object newInstance() {
		S1ControllerParameters parameters = new S1ControllerParameters(noInputs, noGoals, noOutputs, minKVal,
				maxKVal, minCholeskyVal, maxCholeskyVal, minTVal, maxTVal,
				minAlphaVal, maxAlphaVal);
		return parameters;
	}

	@Override
	public int size(Object arg0) {
		return points;
	}

}
