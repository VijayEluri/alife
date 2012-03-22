package co.edu.unal.alife.poc.sbw3;

import java.util.Arrays;

import org.apache.commons.math3.ode.events.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.events.EventException;

public class PocSBW3StepHandler implements EventHandler {

	static final Logger logger = LoggerFactory.getLogger(PocSBW3StepHandler.class);
	private PocSBW3Equation equation;
	private APocSBW3Controller controller;
	
	public PocSBW3StepHandler(PocSBW3Equation equation, APocSBW3Controller controller) {
		this.equation = equation;
		this.controller = controller;
	}

	@Override
	public void init(double t0, double[] y0, double t) {
		controller.switchEvent(t0, y0);
	}

	@Override
	public double g(double t, double[] y) throws EventException {
		double value = 1;
		// check if time is greater than 0 (because at t=0 the event condition
		// is met, but an action should not be triggered)
		if (true) {
			value = y[1] - 2 * y[0];
		}
//		logger.debug("Exiting g");
		logger.trace("Time t={} Switch test value={}",t,value);
		return value;
	}

	@Override
	public Action eventOccurred(double t, double[] y, boolean increasing)
			throws EventException {
		// We should reset state only when there is an upward crossing zero.
		// Downward crossings are false positives (essentially a virtual impact
		// of the swing leg at mid-stride due to the stiffness of the legs)
		logger.trace("Entering eventOcurred.");
		if (increasing) {
			logger.debug("Detected event for Reset State at time={}.",t);
			return EventHandler.Action.RESET_STATE;
		} else {
			logger.debug("Detected false event at time={}.",t);
			return EventHandler.Action.CONTINUE;
		}
	}

	@Override
	public void resetState(double t, double[] y) throws EventException {
		logger.trace("Before resetState");
		logger.trace("Time t="+t+" State y="+Arrays.toString(y));
		equation.switchState(t, y);
		controller.switchEvent(t, y);
		logger.trace("After resetState");
		logger.trace("Time t="+t+" State y="+Arrays.toString(y));
	}

}