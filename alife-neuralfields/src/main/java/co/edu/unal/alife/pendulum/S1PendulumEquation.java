/**
 * 
 */
package co.edu.unal.alife.pendulum;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.util.List;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.neuralfield.KernelFunction;
import co.edu.unal.alife.neuralfield.impl.S1ActionEquation;
import co.edu.unal.alife.output.Tracer;

/**
 * @author Juan Figueredo
 */
public class S1PendulumEquation extends SystemEquation {
	private static final double PENALTY = 0.5;
	private static final double K_A = (0.5) / (5 * 5);
	private static final double K_X = (0.5) / (Math.PI/2 * Math.PI/2);
	private static final double MAX_VAL = K_X*5*5+K_A*Math.PI+0.5;
	// TODO:Revisar Clase
	private static final double M = 1, m = 1, l = 1, g = 9.81;
	private static final int N = 1 + 2 + 1 + 1;
	public static final double STATE_X = 0.0, STATE_THETA = 1.0, STATE_V = 2.0,
			STATE_OMEGA = 3.0;
	DeltaPopulation<Double> actionPopulation;

	public S1PendulumEquation() {
		super();
		this.actionPopulation = null;
	}

	/**
	 * @param actionPopulation
	 */
	public S1PendulumEquation(DeltaPopulation<Double> actionPopulation) {
		super();
		this.actionPopulation = actionPopulation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.unal.alife.neuralfield.DeltaPopulationEquation#evalEquation(co
	 * .edu.unal.alife.dynamics .DeltaPopulation, java.util.List,
	 * java.util.List)
	 */
	@Override
	public void evalEquation(DeltaPopulation<Double> localPopulation,
			List<DeltaPopulation<Double>> populations,
			List<KernelFunction> kernelList) {
		double u = 0;
		while (actionPopulation.hasNextPopulation()) {
			actionPopulation = actionPopulation.getNextPopulation();
		}
		u = actionPopulation.getElementValue(S1ActionEquation.FIRST_ACTION);
		// localPopulation
		// .setElementValue(STATE_X,
		// stdAngle(localPopulation.getElementValue(STATE_X)));
		int maxPos = 5;
		if (localPopulation.getElementValue(STATE_X) > maxPos) {
			localPopulation.setElementValue(STATE_X, (localPopulation
					.getElementValue(STATE_X) % maxPos)
					- maxPos);
		}
		if (localPopulation.getElementValue(STATE_X) < -maxPos) {
			localPopulation.setElementValue(STATE_X, maxPos
					- (localPopulation.getElementValue(STATE_X) % maxPos));
		}
		localPopulation.setElementValue(STATE_THETA, stdAngle(localPopulation
				.getElementValue(STATE_THETA)));
		double tao = 0;
		getDx(localPopulation, u, tao);
	}

	public static double stdAngle(double angle) {
		return ((angle + PI) % (2 * PI) + 2 * PI) % (2 * PI) - PI;
	}

	/**
	 * @param u
	 * @param tao
	 * @return
	 */
	@Override
	protected void getDx(DeltaPopulation<Double> deltaPopulation, double u,
			double tao) {
		double theta = deltaPopulation.getElementValue(STATE_THETA);
		double v = deltaPopulation.getElementValue(STATE_V);
		// System.out.println("v: "+v);
		double omega = deltaPopulation.getElementValue(STATE_OMEGA);
		deltaPopulation.setElementDelta(STATE_X, v);
		deltaPopulation.setElementDelta(STATE_THETA, omega);
		double st = sin(theta);
		double ct = cos(theta);
		// System.out.println(u);
		deltaPopulation.setElementDelta(STATE_V, (m * l * omega * omega * st
				- m * g * ct * st + u)
				/ (M + m * st * st));
		deltaPopulation.setElementDelta(STATE_OMEGA, ((M + m) * g * st - m * l
				* omega * omega * st * ct - u * ct)
				/ (l * (M + m * st * st)) - 0.5 * omega + m * l * l * tao);
		// / (l * (M + m * st * st)) + tao/(m * l * l));
	}

	@Override
	public void setActionPopulation(DeltaPopulation<Double> actionPopulation) {
		this.actionPopulation = actionPopulation;
	}

	public static double getFitness(Tracer tracer) {
		DeltaPopulation<Double> pendulumData = tracer.getData().get(N - 1).get(
				0);
		double val = 0;
		while (pendulumData.hasNextPopulation()) {
			double localValue = 0;
			Double xValue = pendulumData
					.getElementValue(S1PendulumEquation.STATE_X);
			Double thetaValue = pendulumData
					.getElementValue(S1PendulumEquation.STATE_THETA);
			double ang = stdAngle(thetaValue);
			if (Math.abs(ang) >= Math.PI / 3) {
				localValue = K_X * (xValue * xValue) + K_A * Math.abs(ang) + PENALTY;
			} else {
				localValue = K_X * (xValue * xValue) + K_A * (ang * ang);
			}
			val += localValue / MAX_VAL;

			pendulumData = pendulumData.getNextPopulation();
		}

		val /= tracer.getData().get(2).size();

		double fitness = (1 - val)*100;

		return fitness;
	}
}
