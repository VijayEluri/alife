package co.edu.unal.alife.dynamics;

import java.util.List;

import co.edu.unal.alife.neuralfield.DeltaField;

/**
 * @author Juan Figueredo
 *
 * @param <V>
 */
public interface Solver<K,V> {
	
	List<V> simulate(double tf);
	
	DeltaPopulation<K> step(DeltaField<K> field, int localIndex);
	
	List<V> step();
}
