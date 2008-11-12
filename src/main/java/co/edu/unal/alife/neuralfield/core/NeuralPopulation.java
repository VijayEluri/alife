package co.edu.unal.alife.neuralfield.core;

import java.util.Set;

import co.edu.unal.alife.dynamic.core.CoordinateVector;

/**
 * @author jjfigueredou
 * Interface that represents a population of a neural field.
 */
public interface NeuralPopulation {

	/**
	 * Gets the value of an element at the specified position.
	 * @param position
	 * @return element value
	 */
	public abstract double getElementValue(CoordinateVector position);
	
	/**
	 * Sets the value of an element at the specified position.
	 * @param position
	 * @param value
	 */
	public abstract void setElementValue(CoordinateVector position, double value);
	
	/**
	 * Gets the element positions.
	 * @return the set of element positions.
	 */
	public abstract Set<CoordinateVector> getElementPositions();
	
}
