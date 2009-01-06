package co.edu.unal.alife.dynamics;

import java.util.List;

public interface Simulable<V> {

	/**
	 * Evaluates an iteration of the entire field in a population-by-population basis
	 * @param h the step size.
	 */
	public abstract void evaluateStep(double h);
}
