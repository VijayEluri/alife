package co.edu.unal.alife.neuralfield.core;

import java.util.ArrayList;
import java.util.List;

public class DerivableNeuralField<K> extends NeuralField<K>{
	
	/**
	 * @param equations
	 * @param kernelMatrix
	 * @param populations
	 */
	public DerivableNeuralField(List<NeuralPopulationEquation<K>> equations,
			List<List<KernelFunction>> kernelMatrix,
			List<DeltaPopulation<K>> populations) {
		super(equations, kernelMatrix, populations);
	}

	public List<Double> getValuesAsDerivable() {
		List<Double> valuesAsDerivable = new ArrayList<Double>();
		for (int i = 0; i < populations.size(); i++) {
			valuesAsDerivable.addAll(populations.get(i).getElementValues());
		}
		return valuesAsDerivable;
	}
	
	public List<Double> getDeltasAsDerivable() {
		List<Double> deltasAsDerivable = new ArrayList<Double>();
		for (int i = 0; i < populations.size(); i++) {
			deltasAsDerivable.addAll(populations.get(i).getElementDeltas());
		}
		return deltasAsDerivable;
	}
	
	public void setValuesAsDerivable(List<Double> valuesAsDerivable) {
		int fromIndex = 0;
		for (DeltaPopulation<K> population : populations) {
			int toIndex = fromIndex + population.getSize();
			population.setElementValues(valuesAsDerivable.subList(fromIndex, toIndex));
			fromIndex = toIndex;
		}
	}

}
