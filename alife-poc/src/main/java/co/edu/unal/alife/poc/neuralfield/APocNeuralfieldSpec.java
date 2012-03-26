package co.edu.unal.alife.poc.neuralfield;


public abstract class APocNeuralfieldSpec {

	protected int startIndex;
	protected int length;
	protected int endIndex;
	protected IPocNeuralfieldInputEquation inputEquation;
	protected IPocNeuralfieldOutputEquation outputEquation;
	protected PocNeuralfieldsHomogeneousEquation homogeneousEquation;

	public APocNeuralfieldSpec(int startIndex, int length) {
		this.startIndex = startIndex;
		this.length = length;
		this.endIndex = startIndex + length - 1;
	}

	/**
	 * Returns the position (in the neuralfield geometry) of the element with
	 * the given index (in the state vector), for the given dimension (starting
	 * in 0 for 1D).
	 * 
	 * @param index
	 * @param dimension
	 * @return position.
	 */
	public abstract double getPosition(int index, int dimension);

	public abstract double getH();

	/**
	 * Evaluates the neural field output.
	 * 
	 * @param q
	 *            the state vector (including neuralfield state variables).
	 * @return output vector.
	 */
	public double evalOutput(double[] q) {
		return outputEquation.evalOutput(q);
	}

	/**
	 * Evaluates the contribution of the homogeneous neuralfield dynamics to an
	 * element in that field.
	 * 
	 * @param q
	 *            the state vector (including neuralfield state variables).
	 * @param localIndex
	 *            the index in the state vector of the targeted element.
	 * @return homogeneous delta.
	 */
	public double evalHomogeneousDelta(double[] q, int localIndex) {
		return homogeneousEquation.evalHomogeneousDelta(q, localIndex);
	}

	/**
	 * Evaluates the contribution of the input equation to an element in the
	 * neuralfield.
	 * 
	 * @param q
	 *            the state vector (including neuralfield state variables).
	 * @param localIndex
	 *            the index in the state vector of the targeted element.
	 * @return input delta.
	 */
	public double evalInputDelta(double[] q, int localIndex) {
		return inputEquation.evalInputDelta(q, localIndex);
	}

	/**
	 * Returns the lower index in the state vector that belongs to the
	 * neuralfield.
	 * 
	 * @return start index.
	 */
	public int getStartIndex() {
		return startIndex;
	}

	/**
	 * Returns the higher index in the state vector that belongs to the
	 * neuralfield.
	 * 
	 * @return end index.
	 */
	public int getEndIndex() {
		return endIndex;
	}

	/**
	 * Returns the number of elements in state vector that belong to the
	 * neuralfield.
	 * 
	 * @return length.
	 */
	public int getLength() {
		return length;
	}

}