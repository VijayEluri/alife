package co.edu.unal.alife.neuralfield.impl;

import java.util.Set;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.neuralfield.AbstractKernelFunction;
import co.edu.unal.alife.neuralfield.InputEquation;
import co.edu.unal.alife.pendulum.PendulumEquation;

public class InputEquationVectorCoding extends InputEquation {
	
	private double					inputIndex;
	private AbstractKernelFunction	kernelFunction;
	
	/**
	 * @param halfSize
	 * @param pendulum
	 */
	public InputEquationVectorCoding(double halfSize, DeltaPopulation inputPopulation, double inputIndex,
			AbstractKernelFunction kernelFunction) {
		super(halfSize, inputPopulation);
		this.inputIndex = inputIndex;
		this.kernelFunction = kernelFunction;
	}
	
	@Override
	public DeltaPopulation applyInput(DeltaPopulation localPopulation) throws UnsupportedOperationException {
		while (inputPopulation.hasNextPopulation()) {
			inputPopulation = inputPopulation.getNextPopulation();
		}
		double value = inputPopulation.getElementValue(inputIndex);
		double boundedValue = bipolarSigmoid(value) * halfSize;
		double eqPosition = Math.round(boundedValue);
		
		Set<Double> positions = localPopulation.getPositions();
		DeltaPopulation newPopulation = localPopulation.cloneEmpty();
		for (Double position : positions) {
			newPopulation.setElementValue(position, 1.0*kernelFunction.evaluateTransformation(position, eqPosition));
		}
		localPopulation.setNextPopulation(newPopulation);
		return newPopulation;
	}
	
}
