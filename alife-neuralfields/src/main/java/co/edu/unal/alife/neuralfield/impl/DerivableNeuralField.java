package co.edu.unal.alife.neuralfield.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.neuralfield.DeltaField;
import co.edu.unal.alife.neuralfield.DeltaPopulationEquation;
import co.edu.unal.alife.neuralfield.KernelFunction;

public class DerivableNeuralField<K> extends DeltaField<K>{
	
	/**
	 * @param equations
	 * @param kernelMatrix
	 * @param populations
	 */
	public DerivableNeuralField(List<DeltaPopulationEquation<K>> equations,
			List<List<KernelFunction>> kernelMatrix,
			List<DeltaPopulation<K>> populations) {
		super(equations, kernelMatrix, populations);
	}

	public List<Double> getValuesAsDerivable() {
		List<Double> valuesAsDerivable = new ArrayList<Double>();
//		for (int i = 0; i < populations.size(); i++) {
//			valuesAsDerivable.addAll(populations.get(i).getElementValues());
//		}
		return valuesAsDerivable;
	}
	
	public List<Double> valuesListToDerivable(List<List<Double>> valuesList) {
		List<Double> derivable = new ArrayList<Double>();
		for (List<Double> values : valuesList) {
			derivable.addAll(values);
		}
		return derivable;
	}
	
	public List<List<Double>> derivableToValuesList(List<Double> derivable) {
		int fromIndex = 0;
		List<List<Double>> valuesList = new ArrayList<List<Double>>();
		for (DeltaPopulation<K> population : populations) {
			int toIndex = fromIndex + population.getSize();
			valuesList.add(derivable.subList(fromIndex, toIndex));
			fromIndex = toIndex;
		}
		return valuesList;
	}
	
	public List<Double> evaluateSimulableAsDerivable(List<Double> derivableValues) {
		List<List<Double>> valuesList = derivableToValuesList(derivableValues);
		List<List<Double>> deltasList = evaluateSimulable(valuesList);
		return valuesListToDerivable(deltasList);
	}

}
