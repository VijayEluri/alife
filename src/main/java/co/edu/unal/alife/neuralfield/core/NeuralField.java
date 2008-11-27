/**
 * 
 */
package co.edu.unal.alife.neuralfield.core;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.alife.dynamics.Simulable;

/**
 * @author Juan Figueredo
 *
 */
public abstract class NeuralField<K,V> implements Simulable<V> {
	
	protected List<SimulablePopulation<K, V>> populations;
	protected List<NeuralPopulationEquation> equations;
	protected List<List<KernelFunction>> kernelMatrix;
	
	/* (non-Javadoc)
	 * @see co.edu.unal.alife.dynamic.Simulable#evaluateSimulable()
	 */
	@Override
	public List<List<V>> evaluateSimulable(List<List<V>> newValues) {
		List<List<V>> newDeltas = new ArrayList<List<V>>();
		for (int i = 0; i < populations.size(); i++) {
			SimulablePopulation<K, V> population = populations.get(i);
			population.setElementValues(newValues.get(i));
			population.updatePopulationDelta(populations, equations.get(i), kernelMatrix.get(i));
			newDeltas.add(new ArrayList<V>(population.getElementDeltas()));
		}
		return newDeltas;
	}
		
}
