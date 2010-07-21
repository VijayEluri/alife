package co.edu.unal.alife.dynamics;

import co.edu.unal.alife.neuralfield.AbstractDeltaField;


/**
 * @author Juan Figueredo
 * 
 * @param <V>
 */
public interface Solver {

	/**
	 * 
	 * @param field
	 * @param localIndex
	 * @param h
	 * @return A NEW simulable with values evaluated after the solver step
	 */
	DeltaPopulation step(AbstractDeltaField field, int localIndex,
			double h) throws UnsupportedOperationException;

	/**
	 * @param t0
	 * @param tf
	 * @param h
	 * @param field
	 */
	void simulate(double t0, double tf, double h,
			AbstractDeltaField field);

}
