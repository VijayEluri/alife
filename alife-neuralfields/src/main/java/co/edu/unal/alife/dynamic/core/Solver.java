package co.edu.unal.alife.dynamic.core;

import co.edu.unal.alife.dynamics.Simulable;

public interface Solver {
	
	/**
	 * @param simulable
	 */
	public abstract void evaluateStep(Simulable simulable);
}
