/**
 * 
 */
package co.edu.unal.alife.neuralfield.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import co.edu.unal.alife.neuralfield.core.DeltaPopulation;
import co.edu.unal.alife.neuralfield.core.KernelFunction;
import co.edu.unal.alife.neuralfield.core.NeuralPopulationEquation;

/**
 * @author jjfigueredou
 * Class that implements a Map-based Neural Population.
 * 
 * @param <K>
 * @param <V>
 */
public class MapNeuralPopulation<K,V> implements DeltaPopulation<K,V> {
	
	private Map<K,Element<V>> populationMap = new HashMap<K,Element<V>>();
	
	/* (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#getElement(java.lang.Object)
	 */
	@Override
	public Element<V> getElement(K position) {
		return populationMap.get(position);
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#getElementDelta(java.lang.Object)
	 */
	@Override
	public V getElementDelta(K position) {
		return populationMap.get(position).getDelta();
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#getElements()
	 */
	@Override
	public Collection<Element<V>> getElements() {
		return populationMap.values();
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#getElementValue(java.lang.Object)
	 */
	@Override
	public V getElementValue(K position) {
		return populationMap.get(position).getValue();
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#getTuples()
	 */
	@Override
	public Set<Entry<K, Element<V>>> getTuples() {
		return populationMap.entrySet();
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#setElementDelta(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void setElementDelta(K position, V value) {
		populationMap.get(position).setDelta(value);
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#setElementValue(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void setElementValue(K position, V value) {
		populationMap.get(position).setValue(value);
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#getElementDeltas()
	 */
	@Override
	public List<V> getElementDeltas() {
		Collection<Element<V>> elements = getElements();
		List<V> deltas = new ArrayList<V>(elements.size());
		for (Element<V> element : elements) {
			deltas.add(element.getDelta());
		}
		return deltas;
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#getElementValues()
	 */
	@Override
	public List<V> getElementValues() {
		Collection<Element<V>> elements = getElements();
		List<V> values = new ArrayList<V>(elements.size());
		for (Element<V> element : elements) {
			values.add(element.getValue());
		}
		return values;
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#setElementValues(java.util.Collection)
	 */
	@Override
	public void setElementValues(Collection<V> elementValues) {
		Iterator<V> iterator = elementValues.iterator();
		Collection<Element<V>> elements = getElements();
		for (Element<V> element : elements) {
			element.setValue(iterator.next());
		}
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#updatePopulationDelta(java.util.List, co.edu.unal.alife.neuralfield.core.NeuralPopulationEquation, java.util.List)
	 */
	@Override
	public void updatePopulationDelta(List<DeltaPopulation<K, V>> populations, int populationIndex,
			NeuralPopulationEquation<K,V> equation, List<KernelFunction> kernelList) {
		List<V> deltas = equation.evalEquation(populations, populationIndex, kernelList);
		Set<Entry<K,Element<V>>> entrySet = populationMap.entrySet();
		int i = 0;
		for (Entry<K, Element<V>> entry : entrySet) {
			entry.getValue().setDelta(deltas.get(i++));
		}
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#getSize()
	 */
	@Override
	public int getSize() {
		return populationMap.size();
	}
	
}
