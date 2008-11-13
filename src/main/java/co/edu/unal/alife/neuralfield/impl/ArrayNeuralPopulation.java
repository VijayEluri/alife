/**
 * 
 */
package co.edu.unal.alife.neuralfield.impl;

import java.util.Arrays;
import java.util.Collection;

import co.edu.unal.alife.neuralfield.core.NeuralPopulation;

/**
 * @author jjfigueredou
 *
 */
public class ArrayNeuralPopulation implements NeuralPopulation<Double> {

	private int halfSize;
	private Double[] populationArray;
	
	public ArrayNeuralPopulation(int halfSize) {
		this.halfSize = halfSize;
		populationArray = new Double[halfSize*2+1];
	}
	
	public int calculatePosition(int zeroCenteredPosition) {
		int position = zeroCenteredPosition + halfSize;
		return position;
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#getElements()
	 */
	@Override
	public Collection<Double> getElements() {
		return Arrays.asList(populationArray);
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#getElementValue(java.lang.Object)
	 */
	@Override
	public Double getElementValue(Object position) {
		return populationArray[calculatePosition((Integer)position)];
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#setElementValue(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void setElementValue(Object position, Double value) {
		populationArray[calculatePosition((Integer)position)] = value;
	}
}
