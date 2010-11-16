package co.edu.unal.alife.neuralfield;

import co.edu.unal.alife.dynamics.DeltaPopulation;

public abstract class InputEquation extends AbstractNonDifferentialEquation {
	
	protected DeltaPopulation	inputPopulation;
	protected double			halfSize;
	
	public InputEquation(double halfSize, DeltaPopulation inputPopulation) {
		this.halfSize = halfSize;
		this.inputPopulation = inputPopulation;
	}
	
}