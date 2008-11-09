package co.edu.unal.alife.dynamic;

public interface Solver {
	
	/**
	 * @param simulable
	 */
	public abstract void evaluateStep(Simulable simulable);
}
