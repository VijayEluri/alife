/**
 * 
 */
package solver;

/**
 * @author Juan Figueredo
 *
 */
public class SimpleEuler extends ODESolver {

	/**
	 * @param x
	 * @param h
	 * @param t
	 * @param f
	 */
	public SimpleEuler(double[] x, double h, double t, Derivable f) {
		super(x, h, t, f);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see solver.ODESolver#step()
	 */
	@Override
	public double[] step() {
		double[] xnext = new double[x.length];
	    double[] dx = f.derX(x, t);
	    //System.out.print((int)(t/h)+": ");
		for (int i = 0; i < x.length ; i++) {
			xnext[i]=x[i]+h*dx[i];
			//System.out.print(xnext[i]+" ");
		}
		//System.out.println("");
		return xnext;
	}

}
