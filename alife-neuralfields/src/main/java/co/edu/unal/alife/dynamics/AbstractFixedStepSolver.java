package co.edu.unal.alife.dynamics;

import co.edu.unal.alife.neuralfield.AbstractDeltaField;

/**
 * @author Juan Figueredo
 * 
 */
public abstract class AbstractFixedStepSolver implements Solver {
	

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.dynamics.Solver#simulate(double, double, double, co.edu.unal.alife.neuralfield.AbstractDeltaField)
	 */
	public boolean simulate(double t0, double tf, double h, AbstractDeltaField simulable) {
		int n = (int) Math.ceil(tf / h);
//		double t = 0;
		int i = 0;
		boolean isTerminal = false;
		simulable.init();
		while(!isTerminal && i<n) {
//			t = t0 + h * i;
			isTerminal = simulable.evaluateStep(h, i+1);
			i++;
		}
		return isTerminal;
	}
}
