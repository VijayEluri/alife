package co.edu.unal.alife.dynamics;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Juan Figueredo
 * 
 */
public abstract class AbstractSolver<V> extends Observable implements Solver<V> {

	protected List<V> x;
	protected double h;
	protected double t;
	protected Derivable<V> f;
	protected boolean isTraceEnabled;
	protected double tracePeriod;

	/**
	 * @param x
	 *            the vector of dependent variables
	 * @param h
	 *            the step size
	 * @param t
	 *            the simulation time
	 * @param f
	 *            the function dx=f(x,t)
	 * @param isTraceEnabled
	 *            the boolean indicating if observers are notified
	 * @param tracePeriod
	 *            the period of observer notification
	 */
	public AbstractSolver(List<V> x, double h, double t,
			Derivable<V> f, boolean isTraceEnabled, double tracePeriod) {
		this.x = x;
		this.h = h;
		this.t = t;
		this.f = f;
		this.isTraceEnabled = isTraceEnabled;
		this.tracePeriod = tracePeriod;
	}

	/**
	 * @param tf
	 * @return
	 */
	public List<V> simulate(double tf) {
		double evalTime = 0;
		int n = (int) Math.ceil(tf / h);
		double t0 = t;
		for (int i = 0; i < n; i++) {
			t = t0 + h * i;
			x = step();
			if (isTraceEnabled && t >= evalTime) {
				evalTime += tracePeriod;
				this.notifyObservers(x);
			}
		}
		return x;
	}

	public Derivable<V> getF() {
		return f;
	}

	public double getH() {
		return h;
	}

	public double getT() {
		return t;
	}

	public List<V> getX() {
		return x;
	}

	public abstract List<V> step();

}
