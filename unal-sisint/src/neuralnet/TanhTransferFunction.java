/**
 * 
 */
package neuralnet;

/**
 * @author Juan Figueredo
 *
 */
public class TanhTransferFunction extends TransferFunction {

	/* (non-Javadoc)
	 * @see neuralnet.TransferFunction#eval(double[])
	 */
	@Override
	public double[] eval(double[] parameters, double[] state) {
		double alpha = parameters[0];
		double[] output = new double[state.length];
		for (int i = 0; i < state.length; i++) {
			output[i] = Math.tanh(alpha*state[i]);
		}
		return output; 
	}
	
	public double[] eval(double[] state){
		double[] param = {1};
		return eval(param, state);
	}

}
