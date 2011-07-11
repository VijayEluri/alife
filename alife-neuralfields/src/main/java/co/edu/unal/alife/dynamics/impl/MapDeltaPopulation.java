/**
 * 
 */
package co.edu.unal.alife.dynamics.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import co.edu.unal.alife.dynamics.AbstractDeltaPopulation;
import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.neuralfield.impl.SimpleElement;

/**
 * @author jjfigueredou
 *         Class that implements a Map-based Neural Population.
 * 
 */
public class MapDeltaPopulation extends AbstractDeltaPopulation {
	
	private Map<Double, DeltaPopulation.Element>	populationMap	= new LinkedHashMap<Double, DeltaPopulation.Element>();
	
	//TODO: Delete MapDeltaPopulation() 
	public MapDeltaPopulation() {
		super();
	}
	
	/**
	 * Creates a MapDeltaPopulation of providedSize. If isHalfSize, then the positions are created from -providedSize to
	 * +providedSize. Otherwise they are created from 0 to providedSize.
	 * 
	 * @param providedSize
	 * @param isHalfSize
	 */
	public MapDeltaPopulation(int providedSize, boolean isHalfSize) {
		super();
		if (isHalfSize) {
			for (int i = -providedSize; i <= providedSize; i++) {
				Element element = new SimpleElement();
				populationMap.put((double) i, element);
			}
		} else {
			for (int i = 0; i < providedSize; i++) {
				Element element = new SimpleElement();
				populationMap.put((double) i, element);
			}
		}
	}
	
	/**
	 * Creates a MapDeltaPopulation of providedSize as half size. The positions are created from -providedSize to
	 * +providedSize. The total size is 2 * providedSize + 1.
	 * 
	 * @param providedSize
	 * @param isHalfSize
	 */
	public MapDeltaPopulation(int halfSize) {
		this(halfSize, true);
	}
	
	/*
	 * (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.DeltaPopulation#getPositions()
	 */
	@Override
	public Set<Double> getPositions() {
		return populationMap.keySet();
	}
	
	/*
	 * (non-Javadoc)
	 * @see
	 * co.edu.unal.alife.neuralfield.core.NeuralPopulation#getElementDelta(java
	 * .lang.Object)
	 */
	@Override
	public double getElementDelta(Double position) {
		return populationMap.get(position).getDelta();
	}
	
	/*
	 * (non-Javadoc)
	 * @see
	 * co.edu.unal.alife.neuralfield.core.NeuralPopulation#getElementValue(java
	 * .lang.Object)
	 */
	@Override
	public double getElementValue(Double position) {
		return populationMap.get(position).getValue();
	}
	
	/*
	 * (non-Javadoc)
	 * @see
	 * co.edu.unal.alife.neuralfield.core.NeuralPopulation#setElementDelta(java
	 * .lang.Object, java.lang.Object)
	 */
	@Override
	public void setElementDelta(Double position, Double value) {
		populationMap.get(position).setDelta(value);
	}
	
	/*
	 * (non-Javadoc)
	 * @see
	 * co.edu.unal.alife.neuralfield.core.NeuralPopulation#setElementValue(java
	 * .lang.Object, java.lang.Object)
	 */
	@Override
	public void setElementValue(Double position, Double value) {
		populationMap.get(position).setValue(value);
	}
	
	/**
	 * Gets the map that backs the delta population.
	 * 
	 * @return the population map
	 */
	public Map<Double, DeltaPopulation.Element> getPopulationMap() {
		return populationMap;
	}
	
	@Override
	public int getSize() {
		return populationMap.size();
	}
	
	@Override
	public DeltaPopulation cloneEmpty(int size) {
		return new MapDeltaPopulation(size, false);
	}
	
	//	
	//	@Override
	//	public DeltaPopulation cloneEmpty(Set<Double> positions) {
	//		return new MapDeltaPopulation(positions);
	//	}
	
	@Override
	public DeltaPopulation cloneEmpty() {
		MapDeltaPopulation population = new MapDeltaPopulation();
		for (Double position : this.getPositions()) {
			Element element = new SimpleElement();
			population.populationMap.put(position, element);
		}
		return population;
	}
	
}
