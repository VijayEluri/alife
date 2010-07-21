package co.edu.unal.alife.pendulum;

import java.util.List;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.neuralfield.AbstractDifferentialEquation;
import co.edu.unal.alife.neuralfield.AbstractKernelFunction;

public abstract class SystemEquation extends AbstractDifferentialEquation {

	public SystemEquation() {
		super();
	}

	protected abstract void getDx(DeltaPopulation deltaPopulation, double u, double tao);

	public abstract void evalEquation(DeltaPopulation localPopulation, List<DeltaPopulation> populations, List<AbstractKernelFunction> kernelList);
	
	public abstract void setActionPopulation(DeltaPopulation actionPopulation);
}