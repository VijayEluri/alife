package co.edu.unal.alife.neuralfield;

import java.util.List;

import co.edu.unal.alife.dynamics.DeltaPopulation;

public interface DeltaPopulationEquation<K> {

	public abstract List<Double> evalEquation(DeltaPopulation<K> localPopulation,
			List<DeltaPopulation<K>> populations, List<KernelFunction> kernelList);

	public abstract List<Double> applyInput();
}
