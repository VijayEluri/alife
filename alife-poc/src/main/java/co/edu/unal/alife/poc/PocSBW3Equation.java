package co.edu.unal.alife.poc;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

import java.util.Arrays;

import org.apache.commons.math.ode.DerivativeException;
import org.apache.commons.math.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math.ode.FirstOrderIntegrator;
import org.apache.commons.math.ode.IntegratorException;
import org.apache.commons.math.ode.nonstiff.DormandPrince853Integrator;

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
	 * Slope angle (fixed on construction)
	 */
	private double gamma;
	/**
	 * Feed-back loop gain (fixed on construction): k[0] = k_{\theta}, k[1] =
	 * k_{\phi}
	 */
	private double[] k;
	/**
	 * Feed-back control signal (evaluated on each step)
	 */
	private double[] tau;

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
	public PocSBW3Equation(double gamma, double[] k) {
		this.gamma = gamma;
		this.k = Arrays.copyOf(k, TAU_DIMENSION);
		this.tau = new double[TAU_DIMENSION];
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

	/**
	 * qp(1) = q(3); qp(2) = q(4); qp(3) = tau(1) + (g/l)*sin(q(1)-gamma); qp(4)
	 * = -tau(2) + (1-cos(q(2)))*qp(3) + q(3)^2*sin(q(2)) ... +
	 * (g/l)*sin(q(1)-q(2)-gamma); %fprintf('%g\t%g\n',q(1),qp(3));
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.math.ode.FirstOrderDifferentialEquations#
	 * computeDerivatives(double, double[], double[])
	 */
	public void computeDerivatives(double t, double[] q, double[] qDot) {
		// damping constant (calculated for critical damping)
		double c = 2 * sqrt(k[1]);
		// reference position error for \phi
		double e_phi = q[1] - phi_sp;
		// torque for \phi
		double tau_phi = k[1] * e_phi + c * q[3];
		// update of control signal vector
		tau[0] = 0;
		tau[1] = tau_phi;
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

	public static void main(String[] args) {
		double[] qi = new double[] { 0, 0 };
		double[] k = new double[] { 0, 81.1293 };
		double gamma = 0.004;
		double theta0 = qi[0];
		double thetap0 = qi[1];

		double[] qinit = new double[] { theta0, 2 * theta0, thetap0,
				(1 - cos(2 * theta0)) * thetap0 };

		FirstOrderIntegrator dp853 = new DormandPrince853Integrator(1.0e-8,
				100.0, 1.0e-10, 1.0e-10);
		FirstOrderDifferentialEquations ode = new PocSBW3Equation(gamma, k);
		try {
			dp853.integrate(ode, 0.0, qinit, 16.0, qinit); // now y contains final
														// state at
														// time t=16.0
			System.out.println("DONE");
		} catch (DerivativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IntegratorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}