package co.edu.unal.alife.dynamics;

import java.util.List;

/**
 * @author Juan Figueredo
 *
 * @param <V>
 */
public interface Solver<V> {
	
	List<V> simulate(double tf); 
}
