package co.edu.unal.alife.neuralfield.core;

import co.edu.unal.alife.dynamic.core.Function;
import co.edu.unal.alife.dynamic.core.IntegralFunction;

public abstract class NeuralPopulationEquation implements Function {

	private IntegralFunction operator;

	/**
	 * @return the operator
	 */
	public IntegralFunction getOperator() {
		return operator;
	}

	/**
	 * @param operator
	 *            the operator to set
	 */
	public void setOperator(IntegralFunction operator) {
		this.operator = operator;
	}

}
