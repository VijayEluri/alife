package co.edu.unal.alife.dynamics;

import java.util.List;
import java.util.Map;
import java.util.Set;

import co.edu.unal.alife.neuralfield.AbstractDeltaField;

/**
 * Interface that represents a population of a neural field.
 * 
 * @author jjfigueredou
 */
/**
 * @author Juan Figueredo
 * 
 * @param <K>
 */
public abstract interface DeltaPopulation<K> {

	/**
	 * Gets the value of an element at the specified position.
	 * 
	 * @param position
	 * @return element value
	 */
	public abstract Double getElementValue(K position);

	/**
	 * Sets the value of an element at the specified position.
	 * 
	 * @param position
	 * @param value
	 */
	public abstract void setElementValue(K position, Double value);

	/**
	 * Gets the delta of an element at the specified position.
	 * 
	 * @param position
	 * @return element value
	 */
	public abstract Double getElementDelta(K position);

	/**
	 * Sets the delta of an element at the specified position.
	 * 
	 * @param position
	 * @param value
	 */
	public abstract void setElementDelta(K position, Double value);

	// /**
	// * Gets an element at the specified position.
	// *
	// * @param position
	// * @return element
	// */
	// public abstract Element getElement(K position);

	// /**
	// * Gets the elements contained in the neural population.
	// *
	// * @return collection of elements
	// */
	// public abstract Collection<? extends Element> getElements();

	/**
	 * Get the positions from elements contained in the neural population.
	 * 
	 * @return set of positions
	 */
	public abstract Set<K> getPositions();

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
			AbstractDeltaField<K> environment, int localIndex)
			throws UnsupportedOperationException;

	/**
	 * Gets the map that back the delta population.
	 * 
	 * @return the population map
	 */
	public Map<K, Element> getPopulationMap();

	/**
	 * Gets the size of the population, i.e the number of elements contained on
	 * it.
	 * 
	 * @return the size
	 */
	public int getSize();

	/**
	 * Returns true if there is a population registered for the next simulation
	 * step
	 * 
	 * @return has next delta population
	 */
	public boolean hasNextPopulation();

	/**
	 * Gets the population for the next simulation step
	 * 
	 * @return next delta population
	 */
	public DeltaPopulation<K> getNextPopulation();

	/**
	 * Sets the population for the next simulation step
	 * 
	 * @param nextPopulation
	 */
	public void setNextPopulation(DeltaPopulation<K> nextPopulation);

	/**
	 * Interface that represent an element of a neural population, i.e. a point
	 * with an associated value and a delta.
	 * 
	 * @author jjfigueredou
	 * @param <K>
	 * @param
	 */
	public interface Element {
		/**
		 * Gets the value of the element.
		 * 
		 * @return value
		 */
		Double getValue();

		/**
		 * Sets the value of the element.
		 * 
		 * @param value
		 */
		void setValue(Double value);

		/**
		 * Gets the delta of the element.
		 * 
		 * @return delta
		 */
		Double getDelta();

		/**
		 * Sets the delta of the element.
		 * 
		 * @param value
		 */
		void setDelta(Double value);
	}

	/**
	 * A delta population of the same type, indicated size, but empty.
	 * 
	 * @param size
	 * @return
	 */
	DeltaPopulation<K> cloneEmpty(int size);

	/**
	 * A delta population of the same type, and same positions, but empty.
	 * 
	 * @param size
	 * @return
	 */
	DeltaPopulation<K> cloneEmpty(Set<K> positions);

	
	/**
	 * To string useful for 3d printing
	 * 
	 * @param t
	 * @return
	 */
	String toString(String t);

	/**
	 * To string useful for 2d with datasources printing
	 * 
	 * @param times
	 * @param dataSource
	 * @return
	 */
	String toString(List<String> times, List<DeltaPopulation<Double>> dataSource);

}
