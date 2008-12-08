package co.edu.unal.alife.neuralfield.core;

import java.util.List;

public interface NeuralPopulationEquation<K,V> {
	
	public abstract List<V> evalEquation(
			List<DeltaPopulation<K, V>> populations, Integer localIndex,
			List<KernelFunction> kernelList);
}
