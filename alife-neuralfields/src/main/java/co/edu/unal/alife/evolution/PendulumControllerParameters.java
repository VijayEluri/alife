/**
 * 
 */
package co.edu.unal.alife.evolution;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juan Figueredo
 */
public class PendulumControllerParameters {
	private int size;
	private List<Double> inputKernel;
	private List<Double> selfKernel;

	/**
	 * @param selfKernel
	 * @param size
	 */
	public PendulumControllerParameters(List<Double> inputKernel, List<Double> selfKernel, int size) {
		this.size = size;
		this.selfKernel = selfKernel;
		this.inputKernel = inputKernel;
		assert size > 0 && selfKernel.size() == size && inputKernel.size() == size;
	}

	/**
	 * @param size
	 */
	public PendulumControllerParameters(int size, double minValue, double maxValue) {
		List<Double> listInput = new ArrayList<Double>(size);
		List<Double> listSelf = new ArrayList<Double>(size);
		for (int i = 0; i < size; i++) {
			double valueInput = Math.random() * (maxValue - minValue) + minValue;
			double valueSelf = Math.random() * (maxValue - minValue) + minValue;
			listInput.add(valueInput);
			listSelf.add(valueSelf);
		}
		this.size = size;
		this.inputKernel = listInput;
		this.selfKernel = listSelf;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		assert size > 0 && selfKernel.size() == size && inputKernel.size() == size;
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the inputKernel
	 */
	public List<Double> getInputKernel() {
		return inputKernel;
	}

	/**
	 * @return the selfKernel
	 */
	public List<Double> getSelfKernel() {
		return selfKernel;
	}

	/**
	 * /* (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		List<Double> listInput = new ArrayList<Double>(size);
		List<Double> listSelf = new ArrayList<Double>(size);
		listInput.addAll(inputKernel);
		listSelf.addAll(selfKernel);
		return new PendulumControllerParameters(listInput, listSelf, size);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String string = "Size: " + size + "\nInputKernel: " + inputKernel + "\nProcessingKernel: "
				+ selfKernel;
		return string;
	}

}
