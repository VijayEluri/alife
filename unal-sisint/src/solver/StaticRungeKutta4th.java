
/**
 * 
 */
package solver;

/**
 * @author Juan Figueredo
 *
 */
public class StaticRungeKutta4th extends ODESolver {

	/**
	 * @param x
	 * @param h
	 * @param t
	 * @param f
	 */
	public StaticRungeKutta4th(double[] x, double h, double t, Derivable f) {
		super(x, h, t, f);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param x
	 * @param h
	 * @param t
	 * @param f
	 * @param tracer
	 */
	public StaticRungeKutta4th(double[] x, double h, double t, Derivable f, Tracer tracer) {
		super(x, h, t, f, tracer);
		// TODO Auto-generated constructor stub
	}



	/* (non-Javadoc)
	 * @see solver.ODESolver#step()
	 */
	@Override
	public double[] step() {
		double[] xAtK2 = new double[x.length];
		double[] xAtK3 = new double[x.length];
		double[] xAtK4 = new double[x.length];
	    double[] k1dh = f.derX(x, t);
		double[] k2dh;
		double[] k3dh;
		double[] k4dh;
		double[] xnext = new double[x.length];
	    for (int i = 0; i < x.length ; i++) {
	    	xAtK2[i]=x[i]+h/2*k1dh[i];
		}
		k2dh = f.derX(xAtK2, t+h/2);
		for (int i = 0; i < x.length ; i++) {
	    	xAtK3[i]=x[i]+h/2*k2dh[i];
		}
		k3dh = f.derX(xAtK3, t+h/2);
		for (int i = 0; i < x.length ; i++) {
	    	xAtK4[i]=x[i]+h*k3dh[i];
		}
		k4dh = f.derX(xAtK4, t+h);
		for (int i = 0; i < x.length ; i++) {
			xnext[i]=x[i]+h/6*(k1dh[i]+2*k2dh[i]+2*k3dh[i]+k4dh[i]);
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