package co.edu.unal.alife.dynamics;


/**
 * @author Juan Figueredo
 * 
 * @param <V>
 */
public interface Solver<K, V> {

	/**
	 * 
	 * @param simulable
	 * @param localIndex
	 * @param h
	 * @return A NEW simulable with values evaluated after the solver step
	 */
	DeltaPopulation<K> step(Simulable<K> simulable, int localIndex,
			double h) throws UnsupportedOperationException;

	/**
	 * @param t0
	 * @param tf
	 * @param h
	 * @param simulable
	 */
	void simulate(double t0, double tf, double h,
			Simulable<Double> simulable);

}
