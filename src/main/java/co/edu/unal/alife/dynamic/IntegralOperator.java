/**
 * 
 */
package co.edu.unal.alife.dynamic;

import co.edu.unal.alife.neuralfield.KernelFunction;

/**
 * @author jjfigueredou
 *
 */
public abstract class IntegralOperator {
	
	private KernelFunction kernel;
	
	/**
	 * @param function
	 * @return
	 */
	public abstract Function applyOperator(Function function);
}
