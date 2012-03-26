package co.edu.unal.alife.poc.sbw3;

import org.apache.commons.math3.ode.events.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.events.EventException;

public class PocSBW3FallHandler implements EventHandler {

	static final Logger logger = LoggerFactory
			.getLogger(PocSBW3FallHandler.class);

	@Override
	public void init(double t0, double[] y0, double t) {
		// N/A
	}

	@Override
	public double g(double t, double[] y) throws EventException {
		double value = Math.abs(y[0]) - Math.PI / 2;
		logger.trace("Time t={}, theta={}, Fall test value={}", new Object[] {
				t, y[0], value });
		return value;
	}

	@Override
	public Action eventOccurred(double t, double[] y, boolean increasing)
			throws EventException {
		if (increasing) {
			logger.debug("Detected STOP event at time={}.", t);
			return EventHandler.Action.STOP;
		} else {
			throw new RuntimeException("Unexpected decreasing zero crossing on g(t,y) for FallEvent");
		}
	}

	@Override
	public void resetState(double t, double[] y) throws EventException {
		// NOP
	}

}