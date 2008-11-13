package co.edu.unal.alife.neuralfield.core;

import java.util.Collection;

/**
 * @author jjfigueredou
 * Interface that represents a population of a neural field.
 */
public interface NeuralPopulation<E> {

	/**
	 * Gets the value of an element at the specified position.
	 * @param position
	 * @return element value
	 */
	public abstract E getElementValue(Object position);
	
	/**
	 * Sets the value of an element at the specified position.
	 * @param position
	 * @param value
	 */
	public abstract void setElementValue(Object position, E value);
	
	/**
	 * Gets the element positions.
	 * @return the set of element positions.
	 */
	public abstract Collection<E> getElements();
	
}
