package co.edu.unal.alife.neuralfield.impl;

import java.util.List;
import java.util.Set;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.neuralfield.NonDifferentialEquation;
import co.edu.unal.alife.neuralfield.S1TopologyUtility;

public class S1ActionEquation extends NonDifferentialEquation {

	private List<DeltaPopulation<Double>> representationPops;
	private List<List<S1ActionTransformation>> transformations;
	public static final double FIRST_ACTION = 0d;

	public S1ActionEquation(List<DeltaPopulation<Double>> representationPops,
			List<List<S1ActionTransformation>> transformations) {
		super();
		this.representationPops = representationPops;
		this.transformations = transformations;
	}

	@Override
	public DeltaPopulation<Double> applyInput(
			DeltaPopulation<Double> localPopulation) {
		DeltaPopulation<Double> newPopulation = new MapDeltaPopulation(
				localPopulation.getPositions());
		localPopulation.setNextPopulation(newPopulation);
		Set<Double> localPositions = localPopulation.getPositions();
		int indexX = 0;
		int indexY = 0;
		for (Double locPos : localPositions) {
			double u = 0;
			for (DeltaPopulation<Double> population : representationPops) {
				while (population.hasNextPopulation()) {
					population = population.getNextPopulation();
				}
				Set<Double> repPositions = population.getPositions();
				int points = repPositions.size();
				double numSum = 0d;
				double divSum = 0d;
				for (Double repPos : repPositions) {
					Double value = population.getElementValue(repPos);
					numSum += repPos * value;
					divSum += repPos;
				}
				// Calculates theta as the centroid
				double theta = S1TopologyUtility.positionToAngle(
						numSum /= divSum, points);
				// Calculates gamma as the transformation of the centroid;
				S1ActionTransformation transformation = transformations.get(
						indexX).get(indexY);
				double gamma = transformation.evaluateTransformation(theta);
				u += transformation.evaluateWeight(gamma);
				indexY++;
			}
			double x = S1TopologyUtility.stereoProjection(u);
			newPopulation.setElementValue(locPos, x);
			indexX++;
		}
		return newPopulation;
	}
}
