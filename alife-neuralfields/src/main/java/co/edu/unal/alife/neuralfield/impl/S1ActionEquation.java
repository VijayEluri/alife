package co.edu.unal.alife.neuralfield.impl;

import java.util.Set;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.neuralfield.KernelFunction;
import co.edu.unal.alife.neuralfield.NonDifferentialEquation;
import co.edu.unal.alife.neuralfield.S1TopologyUtility;

public class S1ActionEquation extends NonDifferentialEquation {

	public static final double actionPos = 0.0;
	DeltaPopulation<Double> representationPopulation;
	KernelFunction kernel;

	public S1ActionEquation(DeltaPopulation<Double> representationPopulation, KernelFunction kernel) {
		super();
		this.representationPopulation = representationPopulation;
		this.kernel = kernel;
	}

	@Override
	public DeltaPopulation<Double> applyInput(DeltaPopulation<Double> localPopulation) {
		while (representationPopulation.hasNextPopulation()){
			representationPopulation = representationPopulation.getNextPopulation();
		}
		Set<Double> repPositions = representationPopulation.getPositions();
		DeltaPopulation<Double> newPopulation = new MapDeltaPopulation(localPopulation.getPositions());
		localPopulation.setNextPopulation(newPopulation);
		double numSum = 0d;
		double divSum = 0d;
		for (Double pos : repPositions) {
			Double value = representationPopulation.getElementValue(pos);
			numSum += pos * value;
			divSum += pos;
		}
		//Calculates theta as the centroid
		double theta = S1TopologyUtility.positionToAngle(numSum /= divSum);
		//Calculates gamma as the transformation of the centroid;
		double gamma = kernel.evaluateKernel(actionPos, theta); 
		double x = S1TopologyUtility.stereoProjection(gamma);
		newPopulation.setElementValue(actionPos, x);
		return newPopulation;
	}
}
