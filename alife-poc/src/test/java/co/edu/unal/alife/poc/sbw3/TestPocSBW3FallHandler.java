package co.edu.unal.alife.poc.sbw3;

import static org.junit.Assert.*;

import org.apache.commons.math3.ode.events.EventHandler.Action;
import org.junit.Before;
import org.junit.Test;

public class TestPocSBW3FallHandler {
	static final double TOL = 1e-10;
	private PocSBW3FallHandler handler;

	@Before
	public void setUp() {
		this.handler = new PocSBW3FallHandler();
	}

	@Test
	public void testG() {
		double t = 0;
		double[] q = { 0, 0, 0, 0 };
		double g = handler.g(t, q);
		assertTrue("Error on g(t,q) with dataset #1", g<0);

		q = new double[]{ Math.PI / 2 - TOL, 0, 0, 0 };
		g = handler.g(t, q);
		assertTrue("Error on g(t,q) with dataset #2", g<0);
		
		q = new double[]{ Math.PI / 2 + TOL, 0, 0, 0 };
		g = handler.g(t, q);
		assertTrue("Error on g(t,q) with dataset #3", g>0);
		
		q = new double[]{ - Math.PI / 2 + TOL, 0, 0, 0 };
		g = handler.g(t, q);
		assertTrue("Error on g(t,q) with dataset #4", g<0);
		
		q = new double[]{ - Math.PI / 2 - TOL, 0, 0, 0 };
		g = handler.g(t, q);
		assertTrue("Error on g(t,q) with dataset #5", g>0);
	}

	@Test
	public void testEventOccurred() {
		double t = 0;
		double[] q = { Math.PI / 2 + TOL, 0, 0, 0 };
		boolean increasing = true;
		Action actionAssert = Action.STOP;
		Action action = handler.eventOccurred(t, q, increasing);
		assertTrue("Error on eventOcurred(t,q,increasing) with dataset #1",
				action.equals(actionAssert));
		
	}

}
