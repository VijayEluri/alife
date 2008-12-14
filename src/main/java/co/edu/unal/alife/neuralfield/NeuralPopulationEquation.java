package co.edu.unal.alife.neuralfield;

import java.util.List;


public interface NeuralPopulationEquation<K> {
	
	public abstract List<Double> evalEquation(
			List<DeltaPopulation<K>> populations, int localIndex,
			List<KernelFunction> kernelList);
	
	public abstract List<Double> applyInput();
}
