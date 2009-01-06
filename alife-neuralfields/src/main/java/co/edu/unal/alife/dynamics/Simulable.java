package co.edu.unal.alife.dynamics;


public interface Simulable<V> {

	/**
	 * Evaluates an iteration of the entire field in a population-by-population basis
	 * @param h the step size.
	 * @param nextStepCount the step count for next step
	 */
	public abstract void evaluateStep(double h, int nextStepCount);
}
