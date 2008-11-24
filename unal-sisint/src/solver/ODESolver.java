/**
 * 
 */
package solver;

/**
 * @author Juan Figueredo
 *
 */
public abstract class ODESolver {

	protected double[] x;
	protected double h;
	protected double t;
	protected Derivable f;
	protected Tracer tracer;
	
	/**
	 * @param x the vector of dependent variables
	 * @param h the step size
	 * @param t the simulation time
	 * @param f the function dx=f(x,t)
	 */
	public ODESolver(double[] x, double h, double t, Derivable f, Tracer tracer) {
		this.x = x;
		this.h = h;
		this.t = t;
		this.f = f;
		this.tracer = tracer;
	}
	
	public ODESolver(double[] x, double h, double t, Derivable f) {
		this(x, h, t, f, null);
	}
	
	/**
	 * @param tf
	 * @return
	 */
	public double[] simulate(double tf, boolean trace, double traceTime) {
		double evalTime = 0;
		int n = (int)Math.ceil(tf/h);
		double t0 = t;
		for (int i = 0; i < n; i++){
			t = t0 + h*i;
			x = step();
			if(trace && t>=evalTime){
				evalTime+=traceTime;
				tracer.appendData(x);
			}
		}
		return x;
	}
	
	public double[] simulate(double tf) {
		return simulate(tf,false,0);
	}
	
	public Derivable getF() {
		return f;
	}
	public double getH() {
		return h;
	}
	public double getT() {
		return t;
	}
	public double[] getX() {
		return x;
	}
	
	public abstract double[] step();
	
}
