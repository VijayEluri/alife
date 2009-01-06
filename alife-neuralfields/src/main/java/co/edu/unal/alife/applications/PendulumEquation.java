/**
 * 
 */
package co.edu.unal.alife.applications;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.util.List;
import java.util.Set;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.neuralfield.DeltaEquation;
import co.edu.unal.alife.neuralfield.KernelFunction;

/**
 * @author Juan Figueredo
 */
public class PendulumEquation implements DeltaEquation<Double> {

	private static final double M = 1, m = 1, l = 1, g = 9.81, halfSize = 10;
	public static final double STATE_X = 0.0, STATE_THETA = 1.0, STATE_V = 2.0, STATE_OMEGA = 3.0;
	DeltaPopulation<Double> inputPopulation;

	/**
	 * @param inputPopulation
	 */
	public PendulumEquation(DeltaPopulation<Double> inputPopulation) {
		super();
		this.inputPopulation = inputPopulation;
	}

	/*
	 * (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.DeltaPopulationEquation#applyInput()
	 */
	@Override
	public void applyInput(DeltaPopulation<Double> deltaPopulation) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * co.edu.unal.alife.neuralfield.DeltaPopulationEquation#evalEquation(co.edu.unal.alife.dynamics
	 * .DeltaPopulation, java.util.List, java.util.List)
	 */
	@Override
	public void evalEquation(DeltaPopulation<Double> localPopulation,
			List<DeltaPopulation<Double>> populations, List<KernelFunction> kernelList) {
		double u = 0;
		if (inputPopulation != null) {
			Set<Double> positions = inputPopulation.getPositions();
			double maxSoFar = Double.MIN_VALUE;
			Double argMaxSoFar = null;
			for (Double position : positions) {
				double value = inputPopulation.getElementValue(position);
				if (value > maxSoFar) {
					argMaxSoFar = position;
					maxSoFar = value;
				}
			}
			System.out.println(argMaxSoFar);
			u = (argMaxSoFar - halfSize) / halfSize;
		}
		localPopulation
				.setElementValue(STATE_X, stdAngle(localPopulation.getElementValue(STATE_X)));
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
	private void getDx(DeltaPopulation<Double> deltaPopulation, double u, double tao) {
		double theta = deltaPopulation.getElementValue(STATE_THETA);
		double v = deltaPopulation.getElementValue(STATE_V);
		double omega = deltaPopulation.getElementValue(STATE_OMEGA);
		deltaPopulation.setElementDelta(STATE_X, v);
		deltaPopulation.setElementDelta(STATE_THETA, omega);
		double st = sin(theta);
		double ct = cos(theta);
		deltaPopulation.setElementDelta(STATE_V, (m * l * omega * omega * st - m * g * ct * st + u)
				/ (M + m * st * st));
		deltaPopulation.setElementDelta(STATE_OMEGA, ((M + m) * g * st - m * l * omega * omega * st
				* ct - u * ct)
				/ (l * (M + m * st * st)) - 0.5 * omega + m * l * l * tao);
	}

}
