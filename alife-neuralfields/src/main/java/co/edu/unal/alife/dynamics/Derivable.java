/**
 * 
 */
package co.edu.unal.alife.dynamics;

import java.util.List;

/**
 * @author jjfigueredou
 *
 */
public interface Derivable<V> {
	
	/**
	 * @param x the vector of dependent variables
	 * @param t the independent variable
	 * @return
	 */
	public List<V> getDeltas(List<V> x, double t);
	
	public V getDeltas(int i, List<V> x, double t);
}
