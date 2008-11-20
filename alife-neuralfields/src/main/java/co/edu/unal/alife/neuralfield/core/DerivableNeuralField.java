package co.edu.unal.alife.neuralfield.core;

import java.util.ArrayList;
import java.util.List;

public class DerivableNeuralField<K, V> extends NeuralField<K, V> {
	
	public List<V> getValuesAsDerivable() {
		List<V> valuesAsDerivable = new ArrayList<V>();
		for (int i = 0; i < populations.size(); i++) {
			valuesAsDerivable.addAll(populations.get(i).getElementValues());
		}
		return valuesAsDerivable;
	}
	
	public List<V> getDeltasAsDerivable() {
		List<V> deltasAsDerivable = new ArrayList<V>();
		for (int i = 0; i < populations.size(); i++) {
			deltasAsDerivable.addAll(populations.get(i).getElementDeltas());
		}
		return deltasAsDerivable;
	}
	
	public void setValuesAsDerivable(List<V> valuesAsDerivable) {
		int fromIndex = 0;
		for (NeuralPopulation<K, V> population : populations) {
			int toIndex = fromIndex + population.getSize();
			population.setElementValues(valuesAsDerivable.subList(fromIndex, toIndex));
			fromIndex = toIndex;
		}
	}

}
