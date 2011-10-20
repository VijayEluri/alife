package co.edu.unal.alife.sbw;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.dynamics.EventDetector;

public class SBWColissionDetector extends EventDetector {
	
	/*
	 * (non-Javadoc)
	 * @see co.edu.unal.alife.sbw.EventDetector#evaluate(double, co.edu.unal.alife.dynamics.DeltaPopulation)
	 */
	public double[] evaluate(double t, DeltaPopulation localPopulation) {
		double theta = localPopulation.getElementValue(SBWEquation.STATE_THETA);
		double phi = localPopulation.getElementValue(SBWEquation.STATE_PHI);
		double thetadot = localPopulation.getElementValue(SBWEquation.STATE_THETADOT);
		double phidot = localPopulation.getElementValue(SBWEquation.STATE_PHIDOT);
		double value = phi - 2 * theta;
		double dvalue = phidot - 2 * thetadot;
		return new double[] { value, dvalue };
	}
}