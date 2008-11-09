package co.edu.unal.alife.neuralfield;

import co.edu.unal.alife.dynamic.Function;
import co.edu.unal.alife.dynamic.IntegralOperator;

public abstract class NeuralFieldEquation implements Function {

	private IntegralOperator operator;

	/**
	 * @return the operator
	 */
	public IntegralOperator getOperator() {
		return operator;
	}

	/**
	 * @param operator
	 *            the operator to set
	 */
	public void setOperator(IntegralOperator operator) {
		this.operator = operator;
	}

}
