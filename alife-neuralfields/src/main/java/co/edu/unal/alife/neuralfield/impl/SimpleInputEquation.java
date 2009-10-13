/**
 * 
 */
package co.edu.unal.alife.neuralfield.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.neuralfield.InputEquation;
import co.edu.unal.alife.pendulum.PendulumEquation;

/**
 * @author Juan Figueredo
 */
public class SimpleInputEquation extends InputEquation {

	DeltaPopulation<Double> pendulum;
	double halfSize;

	/**
	 * @param halfSize
	 * @param pendulum
	 */
	public SimpleInputEquation(double halfSize, DeltaPopulation<Double> pendulum) {
		super();
		this.halfSize = halfSize;
		this.pendulum = pendulum;
	}

	public DeltaPopulation<Double> applyInput(DeltaPopulation<Double> localPopulation) {
		while (pendulum.hasNextPopulation()) {
			pendulum = pendulum.getNextPopulation();
		}
		double theta = pendulum.getElementValue(PendulumEquation.STATE_THETA);
		double boundedValue = bipolarSigmoid(theta) * halfSize;
		double eqPosition = Math.round(boundedValue);
//		System.out.println("THETA:" + theta + "->" + boundedValue + ":" + eqPosition);

		double omega = pendulum.getElementValue(PendulumEquation.STATE_OMEGA);
		double boundedValue2 = bipolarSigmoid(omega) * halfSize;
		double eqPosition2 = Math.round(boundedValue2);
//		System.out.println("OMEGA:" + omega + "->" + boundedValue2 + ":" + eqPosition2);

		Set<Double> positions = localPopulation.getPositions();
		DeltaPopulation<Double> newPopulation = new MapDeltaPopulation(positions);
		for (Double position : positions) {
			if (position.equals(eqPosition)) {
				newPopulation.setElementValue(position, 1.0);
			} else if (position.equals(eqPosition2)) {
				newPopulation.setElementValue(position, 0.5);
			} else {
				newPopulation.setElementValue(position, 0.0);

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
