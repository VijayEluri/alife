/**
 * 
 */
package co.edu.unal.alife.neuralfield.impl;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Set;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.neuralfield.AbstractNonDifferentialEquation;
import co.edu.unal.alife.pendulum.PendulumEquation;
import co.edu.unal.alife.pendulum.S1PendulumEquation;

/**
 * @author Juan Figueredo
 */
public class S1InputEquationForPendulum extends AbstractNonDifferentialEquation {

	DeltaPopulation pendulum;
	List<Double> ks;

	/**
	 * @param pendulum
	 * @param halfSize
	 */
	public S1InputEquationForPendulum(DeltaPopulation pendulum,
			List<Double> ks) {
		super();
		this.pendulum = pendulum;
		this.ks = ks;
		if (ks.size() != pendulum.getSize()) {
			throw new InputMismatchException(
					"The number of Ks and of SystemPopulation elements must be equal");
		}
	}

	public DeltaPopulation applyInput(
			DeltaPopulation localPopulation) {
		int points = localPopulation.getSize();
		double kTheta = ks.get((int) S1PendulumEquation.STATE_THETA);
		double kOmega = ks.get((int) S1PendulumEquation.STATE_OMEGA);
		double kX = ks.get((int) S1PendulumEquation.STATE_X);
		double kV = ks.get((int) S1PendulumEquation.STATE_V);
		while (pendulum.hasNextPopulation()) {
			pendulum = pendulum.getNextPopulation();
		}
		double theta = pendulum.getElementValue(PendulumEquation.STATE_THETA);
		double boundedValue = S1TopologyUtility.angleToPosition(theta, points);
		double eqPosition = Math.round(boundedValue);
		// System.out.println("THETA:" + theta + "->" + boundedValue + ":" +
		// eqPosition);

		double omega = pendulum.getElementValue(PendulumEquation.STATE_OMEGA);
		double boundedValue2 = S1TopologyUtility.angleToPosition(
				bipolarSigmoid(omega) * Math.PI, points);
		double eqPosition2 = Math.round(boundedValue2);
		// System.out.println("OMEGA:" + omega + "->" + boundedValue2 + ":" +
		// eqPosition2);

		double x = pendulum.getElementValue(PendulumEquation.STATE_X);
		double boundedValue3 = S1TopologyUtility.angleToPosition(
				S1TopologyUtility.reverseStereoProjection(x), points);
		double eqPosition3 = Math.round(boundedValue3);

		double v = pendulum.getElementValue(PendulumEquation.STATE_V);
		double boundedValue4 = S1TopologyUtility.angleToPosition(
				S1TopologyUtility.reverseStereoProjection(v), points);
		double eqPosition4 = Math.round(boundedValue4);

		Set<Double> positions = localPopulation.getPositions();
		DeltaPopulation newPopulation = localPopulation
				.cloneEmpty();
		for (Double position : positions) {
			double value = 0.0;
			newPopulation.setElementValue(position, value);
			if (position.equals(eqPosition)) {
				value += kTheta;
				newPopulation.setElementValue(position, value);
			}
			if (position.equals(eqPosition2)) {
				value += kOmega;
				newPopulation.setElementValue(position, value);
			}
			if (position.equals(eqPosition3)) {
				value += kX;
				newPopulation.setElementValue(position, value);
			}
			if (position.equals(eqPosition4)) {
				value += kV;
				newPopulation.setElementValue(position, value);
			}
		}
		localPopulation.setNextPopulation(newPopulation);
		return newPopulation;
	}

	public static void main(String[] args) {
		List<Double> testVals = new ArrayList<Double>(21);
		List<Double> boundedVals = new ArrayList<Double>(21);
		double current = -Math.PI;
		double step = Math.PI / 5;
		for (int i = 0; i < 11; i++) {
			testVals.add(current);
			double bipolarSigmoid = bipolarSigmoid(current);
			boundedVals.add(bipolarSigmoid);
			System.out.println(current + ":" + bipolarSigmoid);
			current += step;
		}
		System.out.println(-3.141592653589793 / -0.15580032922161896);
	}

}
