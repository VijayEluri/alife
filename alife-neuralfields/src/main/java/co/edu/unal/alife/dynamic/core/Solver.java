package co.edu.unal.alife.dynamic.core;

public interface Solver {
	
	/**
	 * @param simulable
	 */
	public abstract void evaluateStep(Simulable simulable);
}
