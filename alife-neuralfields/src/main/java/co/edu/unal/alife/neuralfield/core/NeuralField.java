/**
 * 
 */
package co.edu.unal.alife.neuralfield.core;

import java.util.List;

import co.edu.unal.alife.dynamic.core.Simulable;

/**
 * @author Juan Figueredo
 *
 */
public abstract class NeuralField<K,V> implements Simulable {
	
	protected List<NeuralPopulation<K, V>> populations;
	protected List<NeuralPopulationEquation> equations;
	protected List<List<KernelFunction>> kernelMatrix;
	
	/* (non-Javadoc)
	 * @see co.edu.unal.alife.dynamic.Simulable#evaluateSimulable()
	 */
	@Override
	public void evaluateSimulable() {
		for (int i = 0; i < populations.size(); i++) {
			populations.get(i).updatePopulationDelta(populations, equations.get(i), kernelMatrix.get(i));
		}
	}
	
		
}
