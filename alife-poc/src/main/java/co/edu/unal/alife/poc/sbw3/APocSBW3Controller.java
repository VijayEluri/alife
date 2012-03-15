package co.edu.unal.alife.poc.sbw3;

import static java.lang.Math.sqrt;

import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;

/**
 * Abstract controller with a template method for control action evaluation, and
 * also an abstract method for controller derivative update.
 * 
 * @author shrein
 */
public abstract class APocSBW3Controller implements
		FirstOrderDifferentialEquations {

	/**
	 * Feed-back control signal (evaluated on each step)
	 */
	private double[] tau = new double[] { 0, 0 };

	/**
	 * Controller state size (excluding controlled system)
	 */
	int N;

	/**
	 * Constructor with controller state size.
	 * 
	 * @param N
	 *            controller state size (omitting controlled system size)
	 */
	public APocSBW3Controller(int N) {
		super();
		this.N = N;
	}

	/**
	 * Evaluates the control action for the current time step.
	 * 
	 * @param t
	 *            time
	 * @param q
	 *            state vector (4D)
	 * @param r
	 *            reference vector (2D)
	 * @return control action vector (2D)
	 */
	protected abstract double[] evaluateK(double t, double[] q, double[] r);

	/**
	 * Updates the state vector derivative for the controller.
	 * 
	 * @param t
	 *            time
	 * @param q
	 *            complete state vector (including both controller and
	 *            controlled system, if executed in the context of a system)
	 * @param qDot
	 *            complete derivative vector (including both controller and
	 *            controlled system, if executed in the context of a system)
	 */
	public abstract void computeDerivatives(double t, double[] q, double[] qDot);

	/**
	 * Template Method. Evaluates the control action for the current time step.
	 * 
	 * @param t
	 *            time
	 * @param q
	 *            state vector (4D)
	 * @param r
	 *            reference vector (2D)
	 * @return control action vector (2D)
	 */
	public double[] getControlAction(double t, double[] q, double[] r) {
		double[] kk = evaluateK(t, q, r);
		return evaluateTau(q, r, kk);
	}

	/**
	 * Evaluates the control action for the current time step.
	 * 
	 * @param q
	 *            state vector
	 * @param r
	 *            reference vector
	 * @param kk
	 *            feedback loop-gain
	 * @return control action vector
	 */
	protected double[] evaluateTau(double[] q, double[] r, double[] kk) {
		// reference swing leg angle (position error = \phi - phi_sp)
		double phi_sp = r[1];
		// damping constant (calculated for critical damping)
		double c = 2 * sqrt(kk[1]);
		// reference position error for \phi
		double e_phi = q[1] - phi_sp;
		// torque for \phi
		double tau_phi = kk[1] * e_phi + c * q[3];
		// control signal vector update
		tau[0] = 0;
		tau[1] = tau_phi;
		return tau;
	}

	@Override
	public int getDimension() {
		return N;
	}

}