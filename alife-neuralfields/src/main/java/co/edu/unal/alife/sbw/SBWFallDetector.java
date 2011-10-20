package co.edu.unal.alife.sbw;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.dynamics.EventDetector;

public class SBWFallDetector extends EventDetector {
	
	/* (non-Javadoc)
	 * @see co.edu.unal.alife.sbw.EventDetector#evaluate(double, co.edu.unal.alife.dynamics.DeltaPopulation)
	 */
	public double[] evaluate(double t, DeltaPopulation localPopulation) {
		double theta = localPopulation.getElementValue(SBWEquation.STATE_THETA);
		double value = 1;
		if (theta > Math.PI /2 || theta < -Math.PI /2) {
			value = 0;
		}
		return new double[] {value, 0d};
	}
}