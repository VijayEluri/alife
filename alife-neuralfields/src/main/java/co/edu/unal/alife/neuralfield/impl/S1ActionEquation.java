package co.edu.unal.alife.neuralfield.impl;

import java.util.List;
import java.util.Set;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.neuralfield.AbstractNonDifferentialEquation;

public class S1ActionEquation extends AbstractNonDifferentialEquation {

	private List<DeltaPopulation> representationPops;
	private List<List<S1ActionTransformation>> transformations;
	public static final double FIRST_ACTION = 0d;
	private static double maxAction = 10.0;
	private static double scale = 100.0;

	public S1ActionEquation(List<DeltaPopulation> representationPops,
			List<List<S1ActionTransformation>> transformations) {
		super();
		this.representationPops = representationPops;
		this.transformations = transformations;
	}

	@Override
	public DeltaPopulation applyInput(
			DeltaPopulation localPopulation) {
		DeltaPopulation newPopulation = localPopulation
				.cloneEmpty();
		localPopulation.setNextPopulation(newPopulation);
		Set<Double> localPositions = localPopulation.getPositions();
		int indexX = 0;
		int indexY = 0;
		for (Double locPos : localPositions) {
			double u = 0;
			for (DeltaPopulation population : representationPops) {
				while (population.hasNextPopulation()) {
					population = population.getNextPopulation();
				}
				Set<Double> repPositions = population.getPositions();
				int points = repPositions.size();
				double numSum = 0d;
				double divSum = 0d;
				for (Double repPos : repPositions) {
					Double value = population.getElementValue(repPos);
					if (value > 0) {
						// System.out.println("S1ActionEquation: repPos=" +
						// repPos
						// + " value=" + value);
						numSum += repPos * value;
						divSum += value;
					}
				}
				// Calculates theta as the centroid
				double theta = (divSum > 0) ? S1TopologyUtility
						.positionToAngle(numSum / divSum, points) : 0.0;
				// System.out.println("S1ActionEquation: indexY="+indexY+" numSum="
				// + numSum
				// + " divSum=" + divSum + " n/d=" + (numSum / divSum)
				// + " theta=" + theta);
				// Calculates gamma as the transformation of the centroid;
				S1ActionTransformation transformation = transformations.get(
						indexX).get(indexY);
				// double gamma = transformation.evaluateTransformation(theta);
				// u += transformation.evaluateWeight(gamma);
				u += transformation.evaluateWeight(theta);
				indexY++;
			}
			// double x = S1TopologyUtility.stereoProjection(u);
			// double x = u == 0 ? 0 : (u > 0 ? maxAction : -maxAction);

			double x = u;
			// x*=scale;
			// if (Math.abs(x) > maxAction) {
			// x = (x >= 0) ? maxAction : -maxAction;
			// }
			// System.out
			// .println("S1ActionEquation: indexX=" + indexX + " x=" + x*scale);
			newPopulation.setElementValue(locPos, x);
			indexX++;
		}
		return newPopulation;
	}
}
