package co.edu.unal.alife.neuralfield.core;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Interface that represents a population of a neural field.
 * 
 * @author jjfigueredou
 * 
 */
public abstract interface DeltaPopulation<K, V> {

	/**
	 * Gets the value of an element at the specified position.
	 * 
	 * @param position
	 * @return element value
	 */
	public abstract V getElementValue(K position);

	/**
	 * Sets the value of an element at the specified position.
	 * 
	 * @param position
	 * @param value
	 */
	public abstract void setElementValue(K position, V value);

	/**
	 * Gets the delta of an element at the specified position.
	 * 
	 * @param position
	 * @return element value
	 */
	public abstract V getElementDelta(K position);

	/**
	 * Sets the delta of an element at the specified position.
	 * 
	 * @param position
	 * @param value
	 */
	public abstract void setElementDelta(K position, V value);

	/**
	 * Gets an element at the specified position.
	 * 
	 * @param position
	 * @return element
	 */
	public abstract Element<V> getElement(K position);

	/**
	 * Gets the elements contained in the neural population.
	 * 
	 * @return collection of elements
	 */
	public abstract Collection<Element<V>> getElements();
	
	/**
	 * Gets the values of the elements contained in the neural population.
	 * @return the values
	 */
	public abstract Collection<V> getElementValues();
	
	public abstract void setElementValues(Collection<V> elementValues);
	
	/**
	 * Gets the deltas of the elements contained in the neural population.
	 * @return the deltas
	 */
	public abstract Collection<V> getElementDeltas();

	/**
	 * Get the tuples {Position,Element} contained in the neural population.
	 * 
	 * @return set of tuples
	 */
	public abstract Set<Map.Entry<K, Element<V>>> getTuples();

	/**
	 * Prepares the population for the update rule applied by a ODE solver.
	 * Updates the state of the delta of all elements contained in the
	 * population.
	 * 
	 * @param populations
	 * @param equation
	 * @param kernelTable
	 */
	public abstract void updatePopulationDelta(
			List<DeltaPopulation<K, V>> populations,
			NeuralPopulationEquation equation, List<KernelFunction> kernelList);
	
	
	/**
	 * Gets the size of the population, i.e the number of elements contained on it.
	 * @return the size
	 */
	public int getSize();
	
	/**
	 * Interface that represent an element of a neural population, i.e. a point
	 * with an associated value and a delta.
	 * 
	 * @author jjfigueredou
	 * 
	 * @param <K>
	 * @param <V>
	 */
	public interface Element<V> {
		/**
		 * Gets the value of the element.
		 * 
		 * @return value
		 */
		V getValue();

		/**
		 * Sets the value of the element.
		 * 
		 * @param value
		 */
		void setValue(V value);

		/**
		 * Gets the delta of the element.
		 * 
		 * @return delta
		 */
		V getDelta();

		/**
		 * Sets the delta of the element.
		 * 
		 * @param value
		 */
		void setDelta(V value);
	};

}
