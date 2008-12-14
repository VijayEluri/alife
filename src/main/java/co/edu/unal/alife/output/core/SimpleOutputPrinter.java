/**
 * 
 */
package co.edu.unal.alife.output.core;

import java.util.List;
import java.util.Observable;

/**
 * @author Juan Figueredo
 *
 */
public class SimpleOutputPrinter implements Visualizer {
	
	private List<Double> values;
	private double t;
	
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		processTuple(arg);
		printTrace();
	}

	/**
	 * @param arg
	 */
	@SuppressWarnings("unchecked")
	public void processTuple(Object arg) {
		Object[] pair = (Object[])arg;
		t = (Double)pair[0];
		values = (List)pair[1];
	}
	
	public void printTrace() {
		System.out.println("Time: "+t);
		System.out.println(values);
	}

}
