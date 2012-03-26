package co.edu.unal.alife.poc.neuralfield;

import static co.edu.unal.alife.poc.PocUtils.heavisideFunction;

public class PocNeuralfieldsHomogeneousEquation {

	APocNeuralfieldSpec spec;
	private PocMexicanHatKernelFunction kernelFunction;

	public PocNeuralfieldsHomogeneousEquation(APocNeuralfieldSpec spec) {
		super();
		this.spec = spec;
		// TODO: Fix kernelFunction constructor values
		this.kernelFunction = new PocMexicanHatKernelFunction(
				0.05, 4, 0.05);
	}

	protected double evalFeedbackDelta(double[] q, int localIndex) {
		double sum = 0;
		double localPosition = spec.getPosition(localIndex,0);
		for (int i = spec.getStartIndex(); i <= spec.getEndIndex(); i++) {
			double remotePosition = spec.getPosition(i,0);
			double firingRate = heavisideFunction(q[i]);
			sum += kernelFunction
					.evalKernelValue(localPosition, remotePosition)
					* firingRate;
		}
		double feedbackDelta = sum * spec.getH();
		return feedbackDelta;
	}
	
	public double evalHomogeneousDelta(double[] q, int localIndex) {
		double localValue = q[localIndex];
		double feedbackDelta = evalFeedbackDelta(q, localIndex);
		return -localValue + feedbackDelta;
	}
}
