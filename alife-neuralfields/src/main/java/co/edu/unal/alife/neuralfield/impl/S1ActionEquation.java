package co.edu.unal.alife.neuralfield.impl;

import java.util.Set;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.neuralfield.KernelFunction;
import co.edu.unal.alife.neuralfield.NonDifferentialEquation;
import co.edu.unal.alife.neuralfield.S1TopologyUtility;

public class S1ActionEquation extends NonDifferentialEquation {

	DeltaPopulation<Double> representationPopulation;
	KernelFunction kernel;
	public static final double FIRST_ACTION = 0d;

	public S1ActionEquation(DeltaPopulation<Double> representationPopulation, KernelFunction kernel) {
		super();
		this.representationPopulation = representationPopulation;
		this.kernel = kernel;
	}

	@Override
	public DeltaPopulation<Double> applyInput(DeltaPopulation<Double> localPopulation) {
		while (representationPopulation.hasNextPopulation()) {
			representationPopulation = representationPopulation.getNextPopulation();
		}
		Set<Double> repPositions = representationPopulation.getPositions();
		int points = repPositions.size();
		DeltaPopulation<Double> newPopulation = new MapDeltaPopulation(localPopulation.getPositions());
		localPopulation.setNextPopulation(newPopulation);
		Set<Double> localPositions = localPopulation.getPositions();
		for (Double locPos : localPositions) {
			double numSum = 0d;
			double divSum = 0d;
			for (Double repPos : repPositions) {
				Double value = representationPopulation.getElementValue(repPos);
				numSum += repPos * value;
				divSum += repPos;
			}
			// Calculates theta as the centroid
			double theta = S1TopologyUtility.positionToAngle(numSum /= divSum, points);
			// Calculates gamma as the transformation of the centroid;
			double gamma = kernel.evaluateKernel(locPos, theta);
			double x = S1TopologyUtility.stereoProjection(gamma);
			newPopulation.setElementValue(locPos, x);
		}
		return newPopulation;
	}
}
