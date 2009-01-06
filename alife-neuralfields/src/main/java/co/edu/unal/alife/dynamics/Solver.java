package co.edu.unal.alife.dynamics;

import co.edu.unal.alife.neuralfield.DeltaField;

/**
 * @author Juan Figueredo
 *
 * @param <V>
 */
public interface Solver<K,V> {

	/**
	 * 
	 * @param field
	 * @param localIndex
	 * @param h
	 * @return A NEW population with values evaluated after the solver step
	 */
	DeltaPopulation<K> step(DeltaField<K> field, int localIndex, double h);
}
