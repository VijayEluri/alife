/**
 * 
 */
package co.edu.unal.alife.neuralfield;

import co.edu.unal.alife.dynamic.Simulable;

/**
 * @author jjfigueredou
 *
 */
public abstract class NeuralField implements Simulable {
	
	private NeuralPopulation neuralPopulation;
	private NeuralFieldEquation fieldEquation;
	
	/* (non-Javadoc)
	 * @see co.edu.unal.alife.dynamic.Simulable#evaluateSimulable()
	 */
	@Override
	public void evaluateSimulable() {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the neuralPopulation
	 */
	public NeuralPopulation getNeuralPopulation() {
		return neuralPopulation;
	}

	/**
	 * @param neuralPopulation the neuralPopulation to set
	 */
	public void setNeuralPopulation(NeuralPopulation neuralPopulation) {
		this.neuralPopulation = neuralPopulation;
	}

	/**
	 * @return the fieldEquation
	 */
	public NeuralFieldEquation getFieldEquation() {
		return fieldEquation;
	}

	/**
	 * @param fieldEquation the fieldEquation to set
	 */
	public void setFieldEquation(NeuralFieldEquation fieldEquation) {
		this.fieldEquation = fieldEquation;
	}

}
