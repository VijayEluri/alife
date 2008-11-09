package co.edu.unal.alife.neuralfield;

import co.edu.unal.alife.dynamic.Function;

public abstract class KernelFunction implements Function {

	/**
	 * 
	 */
	public Object evaluateKernel(Object obj1, Object obj2) {
		return eval(obj1,obj2);
	}
}
