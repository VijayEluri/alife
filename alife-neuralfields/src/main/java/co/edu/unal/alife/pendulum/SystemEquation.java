package co.edu.unal.alife.pendulum;

import java.util.List;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.neuralfield.DifferentialEquation;
import co.edu.unal.alife.neuralfield.KernelFunction;

public abstract class SystemEquation extends DifferentialEquation {

	public SystemEquation() {
		super();
	}

	protected abstract void getDx(DeltaPopulation<Double> deltaPopulation, double u, double tao);

	public abstract void evalEquation(DeltaPopulation<Double> localPopulation, List<DeltaPopulation<Double>> populations, List<KernelFunction> kernelList);
	
	public abstract void setActionPopulation(DeltaPopulation<Double> actionPopulation);
}