package co.edu.unal.alife.poc.sbw3;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;

public class PocSBW3Equation implements FirstOrderDifferentialEquations {

	/**
	 * State vector dimension
	 */
	public static int Q_DIMENSION = 4;
	/**
	 * Control signal dimension
	 */
	public static int TAU_DIMENSION = 2;
	/**
	 * Gravity acceleration constant
	 */
	public static double G = 1.0;
	/**
	 * Leg length
	 */
	public static double L = 1.0;
	/**
	 * Reference swing leg angle (position error = \phi - phi_sp)
	 */
	public static double phi_sp = -0.3;
	/**
	 * Reference position (for \theta and \phi both)
	 */
	public static double[] r = new double[] { 0, phi_sp };

	/**
	 * Slope angle (fixed on construction)
	 */
	private double gamma;

	private APocSBW3Controller controller;

	/**
	 * ODE Equation constructor for the modified Simple Biped Walker model SBW3.
	 * 
	 * @param q
	 *            State vector
	 * @param gamma
	 *            Slope angle
	 * @param k
	 *            Feed-back loop gain
	 */
	public PocSBW3Equation(double gamma, APocSBW3Controller controller) {
		this.gamma = gamma;
		this.controller = controller;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.commons.math.ode.FirstOrderDifferentialEquations#getDimension
	 * ()
	 */
	public int getDimension() {
		return Q_DIMENSION;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.math.ode.FirstOrderDifferentialEquations#
	 * computeDerivatives(double, double[], double[])
	 */
	public void computeDerivatives(double t, double[] q, double[] qDot) {
		// control action
		double[] tau = controller.getControlAction(t, q, r);
		/**
		 * State vector q: q[0] := \theta, q[1] := \phi, q[2] := \theta_dot,
		 * q[3] := \phi_dot
		 * 
		 * Differential State vector qDot = dq
		 */
		qDot[0] = q[2];
		qDot[1] = q[3];
		qDot[2] = tau[0] + (G / L) * sin(q[0] - gamma);
		qDot[3] = -tau[1] + (1 - cos(q[1])) * qDot[2] + q[2] * q[2] * sin(q[1])
				+ (G / L) * sin(q[0] - q[1] - gamma);
	}

	/**
	 * Switches the swing and the pivot legs when a collision event is detected.
	 * 
	 * @param t
	 *            time
	 * @param y
	 *            state vector to be changed
	 */
	public void switchState(double t, double[] y) {
		double theta = y[0];
		double thetadot = y[2];
		y[0] = -theta;
		y[1] = -2 * theta;
		y[2] = cos(2 * theta) * thetadot;
		y[3] = (1 - cos(2 * theta)) * y[2];
	}
}