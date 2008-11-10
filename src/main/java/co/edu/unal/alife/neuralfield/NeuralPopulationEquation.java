package co.edu.unal.alife.neuralfield;

import co.edu.unal.alife.dynamic.Function;
import co.edu.unal.alife.dynamic.IntegralFunction;

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
