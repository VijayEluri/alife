package co.edu.unal.alife.poc.neuralfield;


public abstract class APocNeuralfieldSpec {

	protected int startIndex;
	protected int length;
	protected int endIndex;
	protected IPocNeuralfieldInputEquation inputEquation;
	protected IPocNeuralfieldOutputEquation outputEquation;
	protected APocNeuralFieldHomogeneousEquation homogeneousEquation;
	private double restingPotential = -0.0;
	private double tau = 0.5;

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

	/**
	 * Returns step-size (in the neuralfield geometry) for the given dimension
	 * (starting in 0 for 1D).
	 * 
	 * @param dimension
	 * @return step-size.
	 */
	public abstract double getH(int dimension);

	/**
	 * Evaluates the neural field output.
	 * 
	 * @param t
	 *            the current time
	 * @param q
	 *            the state vector (including neuralfield state variables).
	 * @return output vector.
	 */
	public double evalOutput(double t, double[] q) {
		return outputEquation.evalOutput(t,q);
	}

	/**
	 * Evaluates the derivative of an element of the neuralfield-
	 * 
	 * @param qHomogenous
	 *            the state vector used for homogeneous delta evaluation (including neuralfield state variables).
	 * @param qInput
	 *            the state vector used for input delta evaluation (including neuralfield state variables).
	 * @param qDot
	 *            the output derivatives vector (including neuralfield state variables).
	 */
	public void computeDerivatives(double[] qHomogeneous, double[] qInput, double[] qDot) {
		for (int i = this.getStartIndex(); i <= this.getEndIndex(); i++) {
			double homogeneousDelta = this.evalHomogeneousDelta(qHomogeneous, i);
			double inputDelta = this.evalInputDelta(qInput, i);
			double delta = (homogeneousDelta + inputDelta + restingPotential) / tau;
			qDot[i] = delta;
		}
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
	protected double evalHomogeneousDelta(double[] q, int localIndex) {
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
	protected double evalInputDelta(double[] q, int localIndex) {
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

	public String toString(final String TOKEN, final String COMMENT) {
		StringBuffer sb = new StringBuffer();
		sb.append(COMMENT);
		sb.append("Neuralfield Spec:");
		sb.append("\n");
		sb.append(COMMENT);
		sb.append("startIndex: ");
		sb.append(startIndex);
		sb.append("\n");
		sb.append(COMMENT);
		sb.append("length: ");
		sb.append(length);
		sb.append("\n");
		sb.append(COMMENT);
		sb.append("inputEquation: ");
		sb.append(inputEquation.getClass().getName());
		sb.append("\n");
		sb.append(COMMENT);
		sb.append("outputEquation: ");
		sb.append(outputEquation.getClass().getName());
		sb.append("\n");
		sb.append(COMMENT);
		sb.append("homogeneousEquation: ");
		sb.append(homogeneousEquation.getClass().getName());
		sb.append("\n");
		return sb.toString();
	}

	public void init(double t0, double[] y0) {
		// Not Implemented
	}

}