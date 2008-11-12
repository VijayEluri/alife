/**
 * 
 */
package co.edu.unal.alife.neuralfield.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import co.edu.unal.alife.dynamic.core.CoordinateVector;
import co.edu.unal.alife.neuralfield.core.NeuralPopulation;

/**
 * @author jjfigueredou
 * Class that implements a Map-based Neural Population.
 */
public class MapNeuralPopulation implements NeuralPopulation {

	private Map<CoordinateVector,Double> populationMap = new HashMap<CoordinateVector,Double>(); 
	
	/* (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#getElementPositions()
	 */
	@Override
	public Set<CoordinateVector> getElementPositions() {
		return populationMap.keySet();
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#getElementValue(co.edu.unal.alife.dynamic.core.CoordinateVector)
	 */
	@Override
	public double getElementValue(CoordinateVector position) {	
		return populationMap.get(position);
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#setElementValue(co.edu.unal.alife.dynamic.core.CoordinateVector, double)
	 */
	@Override
	public void setElementValue(CoordinateVector position, double value) {
		populationMap.put(position, value);
	}

	/**
	 * @return the populationMap
	 */
	public Map<CoordinateVector, Double> getPopulationMap() {
		return populationMap;
	}

	/**
	 * @param populationMap the populationMap to set
	 */
	public void setPopulationMap(Map<CoordinateVector, Double> populationMap) {
		this.populationMap = populationMap;
	}
	
}
