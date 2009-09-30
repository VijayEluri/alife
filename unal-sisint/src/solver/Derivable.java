package solver;

//"Java Tech"
//Code provided with book for educational purposes only.
//No warranty or guarantee implied.
//This code freely available. No copyright claimed.
//2003

/**
 *  This interface provides the derivative for ODE calculations.
 **/
public interface Derivable
{
	/**
	 * @param x the vector dependent variables
	 * @param t the indepented variable
	 * @return
	 */
	public double[] derX (double[] x, double t);
	public double derX (int i, double[] x, double t);
} // Derivable
