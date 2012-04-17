package co.edu.unal.alife.poc.neuralfield;

import static co.edu.unal.alife.poc.PocUtils.heavisideFunction;

public class PocNeuralfieldsHomogeneousEquation2D extends APocNeuralFieldHomogeneousEquation{

	private PocMexicanHatKernelFunction2D kernelFunction;

	public PocNeuralfieldsHomogeneousEquation2D(APocNeuralfieldSpec spec) {
		super();
		this.spec = spec;
		// These values are balanced by trial-and-error.
		this.kernelFunction = new PocMexicanHatKernelFunction2D(0.1, 0.2, 10);
	}

	protected double evalFeedbackDelta(double[] q, int localIndex) {
		double sum = 0;
		double localXPosition = spec.getPosition(localIndex, 0);
		double localYPosition = spec.getPosition(localIndex, 1);
		for (int i = spec.getStartIndex(); i <= spec.getEndIndex(); i++) {
			double remoteXPosition = spec.getPosition(i, 0);
			double remoteYPosition = spec.getPosition(i, 1);
			double firingRate = heavisideFunction(q[i]);
			sum += kernelFunction.evalKernelValue(localXPosition,
					localYPosition, remoteXPosition, remoteYPosition)
					* firingRate;
		}
		double feedbackDelta = sum * spec.getH(0) * spec.getH(1);
		return 0*feedbackDelta;
	}
}
