package co.edu.unal.alife.neuralfield;

import java.util.List;

import co.edu.unal.alife.dynamics.DeltaPopulation;

public interface DeltaEquation {

	public abstract void evalEquation(DeltaPopulation localPopulation,
			List<DeltaPopulation> populations, List<AbstractKernelFunction> kernelList) throws UnsupportedOperationException;

	public abstract DeltaPopulation applyInput(DeltaPopulation localPopulation) throws UnsupportedOperationException;
	
	public abstract boolean isDifferential();
	
	public abstract boolean requiresApplyInput();
}
