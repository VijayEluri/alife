/**
 * 
 */
package co.edu.unal.alife.neuralfield.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import co.edu.unal.alife.neuralfield.core.NeuralPopulation;

/**
 * @author jjfigueredou
 * Class that implements a Map-based Neural Population.
 */
public class MapNeuralPopulation<K,V> implements NeuralPopulation<K,V> {

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
	
}
