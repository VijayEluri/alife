/**
 * 
 */
package co.edu.unal.alife.neuralfield;

import java.util.ArrayList;

import co.edu.unal.alife.dynamic.Simulable;

/**
 * @author jjfigueredou
 *
 */
public abstract class NeuralField implements Simulable {
	
	private ArrayList<NeuralPopulation> neuralPopulations;
	private ArrayList<NeuralFieldEquation> fieldEquations;
	
	/* (non-Javadoc)
	 * @see co.edu.unal.alife.dynamic.Simulable#evaluateSimulable()
	 */
	@Override
	public void evaluateSimulable() {
		// TODO Auto-generated method stub
	}

	/**
	 * @return the neuralPopulations
	 */
	public ArrayList<NeuralPopulation> getNeuralPopulations() {
		return neuralPopulations;
	}

	/**
	 * @param neuralPopulations the neuralPopulations to set
	 */
	public void setNeuralPopulations(ArrayList<NeuralPopulation> neuralPopulations) {
		this.neuralPopulations = neuralPopulations;
	}

	/**
	 * @return the fieldEquations
	 */
	public ArrayList<NeuralFieldEquation> getFieldEquations() {
		return fieldEquations;
	}

	/**
	 * @param fieldEquations the fieldEquations to set
	 */
	public void setFieldEquations(ArrayList<NeuralFieldEquation> fieldEquations) {
		this.fieldEquations = fieldEquations;
	}
	
	
}
