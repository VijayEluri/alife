/**
 * 
 */
package co.edu.unal.alife.neuralfield;


/**
 * @author jjfigueredou
 * 
 */
public class HiddenNeuralField extends NeuralField {

	public NeuralFieldLayer getHiddenLayer() {
		if (!this.getFieldLayers().isEmpty()) {
			return this.getFieldLayers().get(0);
		} else {
			throw new IndexOutOfBoundsException("List of (hidden) field layers empty");
		}
	}
	
	public NeuralFieldLayer getInputLayer() {
		if (!this.getInputLayers().isEmpty()) {
			return this.getInputLayers().get(0);
		} else {
			throw new IndexOutOfBoundsException("List of input layers empty");
		}
	}
}
