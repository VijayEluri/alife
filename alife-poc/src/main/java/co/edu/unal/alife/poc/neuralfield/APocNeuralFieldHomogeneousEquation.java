package co.edu.unal.alife.poc.neuralfield;

public abstract class APocNeuralFieldHomogeneousEquation {

	protected APocNeuralfieldSpec spec;

	protected abstract double evalFeedbackDelta(double[] q, int localIndex);

	public APocNeuralFieldHomogeneousEquation() {
		super();
	}

	public double evalHomogeneousDelta(double[] q, int localIndex) {
		double localValue = q[localIndex];
		double feedbackDelta = evalFeedbackDelta(q, localIndex);
		return -localValue + feedbackDelta;
	}

}