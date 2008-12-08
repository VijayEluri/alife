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
public abstract class NeuralField<K> implements Simulable<Double> {
	
	protected List<DeltaPopulation<K>> populations;
	protected List<NeuralPopulationEquation<K>> equations;
	protected List<List<KernelFunction>> kernelMatrix;
	
	/**
	 * @param equations
	 * @param kernelMatrix
	 * @param populations
	 */
	public NeuralField(List<NeuralPopulationEquation<K>> equations,
			List<List<KernelFunction>> kernelMatrix,
			List<DeltaPopulation<K>> populations) {
		super();
		this.equations = equations;
		this.kernelMatrix = kernelMatrix;
		this.populations = populations;
	}



	/* (non-Javadoc)
	 * @see co.edu.unal.alife.dynamic.Simulable#evaluateSimulable()
	 */
	@Override
	public List<List<Double>> evaluateSimulable(List<List<Double>> newValues) {
		List<List<Double>> newDeltas = new ArrayList<List<Double>>();
		for (int i = 0; i < populations.size(); i++) {
			DeltaPopulation<K> population = populations.get(i);
			population.setElementValues(newValues.get(i));
			population.updatePopulationDelta(populations, i, equations.get(i), kernelMatrix.get(i));
			newDeltas.add(new ArrayList<Double>(population.getElementDeltas()));
		}
		return newDeltas;
	}
		
}
