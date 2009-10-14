package co.edu.unal.alife.neuralfield;

import java.util.List;

import co.edu.unal.alife.dynamics.DeltaPopulation;

public abstract class NonDifferentialEquation  implements DeltaEquation<Double> {

	public NonDifferentialEquation() {
		super();
	}

	public static double bipolarSigmoid(double t) {
		double alpha = 3;
		return bipolarSigmoid(alpha, t);
	}
	
	public static double bipolarSigmoid(double alpha, double t) {
		return (1 - Math.exp(-alpha * t)) / (1 + Math.exp(-alpha * t));
	}
	
	public static double unipolarSigmoid(double t) {
		double r = 3;
		return 1 / (1 + Math.exp(-r * t));
	}
	
	public static double unipolarSigmoid(double r, double t) {
		return 1 / (1 + Math.exp(-r * t));
	}

	@Override
	public void evalEquation(DeltaPopulation<Double> localPopulation, List<DeltaPopulation<Double>> populations, List<KernelFunction> kernelList) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isDifferential() {
		return false;
	}

	@Override
	public boolean requiresApplyInput() {
		return true;
	}

	
}