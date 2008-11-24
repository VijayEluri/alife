/**
 * 
 */
package solver;

/**
 * @author Juan Figueredo
 *
 */
public class StaticRungeKutta2th extends ODESolver {

	/**
	 * @param x
	 * @param h
	 * @param t
	 * @param f
	 */
	public StaticRungeKutta2th(double[] x, double h, double t, Derivable f) {
		super(x, h, t, f);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see solver.ODESolver#step()
	 */
	@Override
	public double[] step() {
		double[] xAtK2 = new double[x.length];
	    double[] k1dh = f.derX(x, t);
		double[] k2dh;
		double[] xnext = new double[x.length];
	    //System.out.print((int)(t/h)+": ");
	    for (int i = 0; i < x.length ; i++) {
	    	xAtK2[i]=x[i]+h/2*k1dh[i];
		}
		k2dh = f.derX(xAtK2, t+h/2);
		for (int i = 0; i < x.length ; i++) {
			xnext[i]=x[i]+h*k2dh[i];
			//System.out.print(xnext[i]+" ");
		}
		//System.out.println("");
		return xnext;
	} 
	
	/*public double[] step() {
	    double[] k1 = new double[x.length];
	    double[] k2 = new double[x.length];
	    double[] xTemp = new double[x.length];
		double[] xnext = new double[x.length];
	    //System.out.print((int)(t/h)+": ");
	    for (int i = 0; i < x.length ; i++) {
	    	k1[i]=h*f.derX(i, x, t);
	    	xTemp[i]=x[i]+k1[i]/2;
		}
	    for (int i = 0; i < x.length ; i++) {
	    	k2[i]=h*f.derX(i, xTemp, t+h/2);
	    	xnext[i]=x[i]+k2[i];
		}
		//System.out.println("");
		return xnext;
	}*/
}
