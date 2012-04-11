package co.edu.unal.alife.poc.sbw3;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;

import co.edu.unal.alife.poc.sbw3.APocSBW3Controller;
import co.edu.unal.alife.poc.sbw3.PocSBW3Equation;

public class TestPocSBW3Equation {
	static final double TOL = 1e-10;
	private PocSBW3Equation equation;

	@Before
	public void setUp() {
		equation = new PocSBW3Equation(0.004, new APocSBW3Controller(2) {
			protected double[] evaluateK(double t, double[] q, double[] r) {
				return new double[] { 0, 0 };
			}

			public void computeDerivatives(double t, double[] q, double[] qDot) {
			}

			@Override
			protected void switchEvent(double t, double[] q) {
			}

			@Override
			public String toString(String TOKEN, String COMMENT) {
				return null;
			};
		});
	};

	@Test
	public void testComputeDerivatives() {
		double[] q = { 0.1, 0.2, -0.5, -0.5 };
		double[] qAssert = { -0.1, -0.2, -0.490033288920621, -0.009768040419900 };
		equation.switchState(0, q);
		assertArrayEquals("switchState(t,q) equality assertion failed.", q,
				qAssert, TOL);
	}

	@Test
	public void testSwitchState() {
		double[] q = { -0.1, -0.2, -0.490033288920621, -0.009768040419900 };
		double[] qDotAssert = { -0.490033288920621, -0.009768040419900,
				-0.103812624028303, 0.046076283310771 };

		double[] qDot = new double[4];
		equation.computeDerivatives(0, q, qDot);
		assertArrayEquals(
				"computeDerivatives(t,q,qDot) equality assertion failed.",
				qDot, qDotAssert, TOL);
	}
}
