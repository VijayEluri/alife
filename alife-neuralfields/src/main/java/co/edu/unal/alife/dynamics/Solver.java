package co.edu.unal.alife.dynamics;

import co.edu.unal.alife.neuralfield.DeltaField;

/**
 * @author Juan Figueredo
 *
 * @param <V>
 */
public interface Solver<K,V> {
	
	//List<V> simulate(double tf);
	
	DeltaPopulation<K> step(DeltaField<K> field, int localIndex, double h);
	
	//List<V> step();
}
