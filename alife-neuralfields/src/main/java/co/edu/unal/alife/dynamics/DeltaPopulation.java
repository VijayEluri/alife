package co.edu.unal.alife.dynamics;

import java.util.Set;

/**
 * Interface that represents a population of a neural field.
 * 
 * @author jjfigueredou
 */
/**
 * @author Juan Figueredo
 * 
 * @param
 */
public interface DeltaPopulation {
	
	/**
	 * Gets the value of an element at the specified position.
	 * 
	 * @param position
	 * @return element value
	 */
	double getElementValue(Double position);
	
	/**
	 * Sets the value of an element at the specified position.
	 * 
	 * @param position
	 * @param value
	 */
	void setElementValue(Double position, Double value);
	
	/**
	 * Gets the delta of an element at the specified position.
	 * 
	 * @param position
	 * @return element value
	 */
	double getElementDelta(Double position);
	
	/**
	 * Sets the delta of an element at the specified position.
	 * 
	 * @param position
	 * @param value
	 */
	void setElementDelta(Double position, Double value);
	
	/**
	 * Get the positions from elements contained in the neural population.
	 * 
	 * @return set of positions
	 */
	Set<Double> getPositions();
	
	/**
	 * Gets the size of the population, i.e the number of elements contained on
	 * it.
	 * 
	 * @return the size
	 */
	int getSize();
	
	/**
	 * Returns true if there is a population registered for the next simulation
	 * step
	 * 
	 * @return has next delta population
	 */
	boolean hasNextPopulation();
	
	/**
	 * Gets the population for the next simulation step
	 * 
	 * @return next delta population
	 */
	DeltaPopulation getNextPopulation();
	
	/**
	 * Sets the population for the next simulation step
	 * 
	 * @param nextPopulation
	 */
	void setNextPopulation(DeltaPopulation nextPopulation);
	
	/**
	 * Returns if this population is terminal (i.e. a terminal event was found)
	 * @return isTerminal
	 */
	boolean isTerminal();
	
	/**
	 * Sets if this population is terminal (signaling that a terminal event was found)
	 * @param isTerminal
	 */
	void setTerminal(boolean isTerminal);
	
	/**
	 * Interface that represent an element of a neural population, i.e. a point
	 * with an associated value and a delta.
	 * 
	 * @author jjfigueredou
	 * @param
	 * @param
	 */
	interface Element {
		/**
		 * Gets the value of the element.
		 * 
		 * @return value
		 */
		double getValue();
		
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
		double getDelta();
		
		/**
		 * Sets the delta of the element.
		 * 
		 * @param value
		 */
		void setDelta(Double value);
	}
	
	//TODO: Remove cloneEmpty(int size)
	/**
	 * A delta population of the same type, indicated size, but empty.
	 * 
	 * @param size
	 * @return
	 */
	DeltaPopulation cloneEmpty(int size);
	
	/**
	 * A delta population of the same type, and same positions, but empty.
	 * 
	 * @return
	 */
	DeltaPopulation cloneEmpty();
}
