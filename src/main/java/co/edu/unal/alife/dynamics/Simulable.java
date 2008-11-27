package co.edu.unal.alife.dynamics;

import java.util.List;

public interface Simulable<V> {

	/**
	 * Returns a COPY of each list of deltas of each population after the
	 * evaluation
	 * 
	 * @param newValues
	 * @return a copy of the deltas
	 */
	public abstract List<List<V>> evaluateSimulable(List<List<V>> newValues);
}
