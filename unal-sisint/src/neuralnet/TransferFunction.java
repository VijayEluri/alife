/**
 * 
 */
package neuralnet;

/**
 * @author Juan Figueredo
 *
 */
public abstract class TransferFunction {
	public abstract double[] eval(double[] parameters, double[] state);
	public abstract double[] eval(double[] state);
}

