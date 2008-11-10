/**
 * 
 */
package co.edu.unal.alife.neuralfield;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.alife.dynamic.Simulable;

/**
 * @author jjfigueredou
 *
 */
public abstract class NeuralField implements Simulable {
	
	private List<NeuralFieldLayer> fieldLayers;
	private List<NeuralFieldLayer> inputLayers;
	
	/* (non-Javadoc)
	 * @see co.edu.unal.alife.dynamic.Simulable#evaluateSimulable()
	 */
	@Override
	public void evaluateSimulable() {
		// TODO Auto-generated method stub
	}

	/**
	 * @return the fieldLayers
	 */
	public List<NeuralFieldLayer> getFieldLayers() {
		return fieldLayers;
	}

	/**
	 * @param fieldLayers the fieldLayers to set
	 */
	public void setFieldLayers(ArrayList<NeuralFieldLayer> fieldLayers) {
		this.fieldLayers = fieldLayers;
	}

	/**
	 * @return the inputLayers
	 */
	public List<NeuralFieldLayer> getInputLayers() {
		return inputLayers;
	}

	/**
	 * @param inputLayers the inputLayers to set
	 */
	public void setInputLayers(ArrayList<NeuralFieldLayer> inputLayers) {
		this.inputLayers = inputLayers;
	}
}
