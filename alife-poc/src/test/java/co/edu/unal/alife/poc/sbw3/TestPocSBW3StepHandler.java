package co.edu.unal.alife.poc.sbw3;

import static org.junit.Assert.*;

import org.apache.commons.math3.ode.events.EventHandler.Action;
import org.junit.Before;
import org.junit.Test;

import co.edu.unal.alife.poc.sbw3.APocSBW3Controller;
import co.edu.unal.alife.poc.sbw3.PocSBW3Equation;
import co.edu.unal.alife.poc.sbw3.PocSBW3StepHandler;

public class TestPocSBW3StepHandler {
	static final double TOL = 1e-10;
	PocSBW3StepHandler handler;

	@Before
	public void setUp() {
		APocSBW3Controller controller = new APocSBW3Controller(2) {
			protected double[] evaluateK(double t, double[] q, double[] r) {
				return new double[] { 0, 0 };
			}

			public void computeDerivatives(double t, double[] q, double[] qDot) {
			}

			@Override
			protected void switchEvent(double t, double[] q) {
			};
		};
		PocSBW3Equation equation = new PocSBW3Equation(0.004, controller);

		this.handler = new PocSBW3StepHandler(equation, controller);
	}

	@Test
	public void testG() {
		double t = 0;
		double[] q = { 1, 2, 0, 0 };
		double g = handler.g(t, q);
		double gAssert = 0;
		assertEquals("Error on g(t,q) with dataset #1", g, gAssert, TOL);

		q = new double[] { 2, 2, 0, 0 };
		g = handler.g(t, q);
		gAssert = -2;
		assertEquals("Error on g(t,q) with dataset #2", g, gAssert, TOL);

	}

	@Test
	public void testEventOccurred() {
		double t = 1;
		double[] q = { 1, 2, 0, 0 };
		boolean increasing = true;
		Action actionAssert = Action.RESET_STATE;
		Action action = handler.eventOccurred(t, q, increasing);
		assertTrue("Error on eventOcurred(t,q,increasing) with dataset #1",
				action.equals(actionAssert));

		increasing = false;
		actionAssert = Action.CONTINUE;
		action = handler.eventOccurred(t, q, increasing);
		assertTrue("Error on eventOcurred(t,q,increasing) with dataset #2",
				action.equals(actionAssert));
	}

}
