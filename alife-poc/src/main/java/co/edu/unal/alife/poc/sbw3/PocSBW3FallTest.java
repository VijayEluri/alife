package co.edu.unal.alife.poc.sbw3;

import org.apache.commons.math3.ode.events.EventHandler;
import org.w3c.dom.events.EventException;

public class PocSBW3FallTest implements EventHandler {
	
	@Override
	public void init(double t0, double[] y0, double t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double g(double t, double[] y) throws EventException {
		double value = 1;

		if (y[0] > Math.PI / 2 || y[0] < -Math.PI / 2) {
			value = 0;
		}

		return value;
	}

	@Override
	public Action eventOccurred(double t, double[] y, boolean increasing)
			throws EventException {
		return EventHandler.Action.STOP;
	}

	@Override
	public void resetState(double t, double[] y) throws EventException {
		// NOP
	}

}