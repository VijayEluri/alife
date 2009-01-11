package co.edu.unal.alife.neuralfield;

import java.util.List;

import co.edu.unal.alife.dynamics.DeltaPopulation;

public interface DeltaEquation<K> {

	public abstract void evalEquation(DeltaPopulation<K> localPopulation,
			List<DeltaPopulation<K>> populations, List<KernelFunction> kernelList) throws UnsupportedOperationException;

	public abstract DeltaPopulation<K> applyInput(DeltaPopulation<K> localPopulation) throws UnsupportedOperationException;
}
