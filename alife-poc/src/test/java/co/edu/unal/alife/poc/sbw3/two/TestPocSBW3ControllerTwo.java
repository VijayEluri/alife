package co.edu.unal.alife.poc.sbw3.two;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class TestPocSBW3ControllerTwo {
	static final double TOL = 1e-8;

	@Test
	public void testEvaluateK() {
		PocSBW3ControllerTwo controller = new PocSBW3ControllerTwo();

		double t = 0;
		double[] q = { 0, 0, 0, 0 };
		double[] r = {};
		double[] uAssert = { 0, 1.988275000000000 };
		controller.switchEvent(t, q);
		double[] u = controller.evaluateK(t, q, r);
		assertArrayEquals(
				"Control action equality assertion failed with dataset #1.",
				uAssert, u, TOL);

		q = new double[] { 0, 0, -0.2, 0 };
		uAssert = new double[] { 0, 20.474525000000000 };
		controller.switchEvent(t, q);
		u = controller.evaluateK(t, q, r);
		assertArrayEquals(
				"Control action equality assertion failed with dataset #2.",
				uAssert, u, TOL);

		q = new double[] { 0.2, 0, -0.2, 0 };
		uAssert = new double[] { 0, 3.885475000000000 };
		controller.switchEvent(t, q);
		u = controller.evaluateK(t, q, r);
		assertArrayEquals(
				"Control action equality assertion failed with dataset #3.",
				uAssert, u, TOL);

		q = new double[] { 0.0, 0, -0.4, 0 };
		uAssert = new double[] { 0, 70.150799999999990 };
		controller.switchEvent(t, q);
		u = controller.evaluateK(t, q, r);
		assertArrayEquals(
				"Control action equality assertion failed with dataset #4.",
				uAssert, u, TOL);

		q = new double[] { 0.2, 0, -0.4, 0 };
		uAssert = new double[] { 0, 24.921849999999999 };
		controller.switchEvent(t, q);
		u = controller.evaluateK(t, q, r);
		assertArrayEquals(
				"Control action equality assertion failed with dataset #5.",
				uAssert, u, TOL);

		q = new double[] { 0.4, 0, -0.4, 0 };
		uAssert = new double[] { 0, 5.011050000000000 };
		controller.switchEvent(t, q);
		u = controller.evaluateK(t, q, r);
		assertArrayEquals(
				"Control action equality assertion failed with dataset #6.",
				uAssert, u, TOL);
	}

}
