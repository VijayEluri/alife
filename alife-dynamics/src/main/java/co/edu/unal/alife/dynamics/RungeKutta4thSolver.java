package co.edu.unal.alife.dynamics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juan Figueredo
 * 
 */
public class RungeKutta4thSolver extends AbstractSolver<Double> {	

	/**
	 * @param x
	 * @param h
	 * @param t
	 * @param f
	 * @param isTraceEnabled
	 * @param tracePeriod
	 */
	public RungeKutta4thSolver(List<Double> x, double h, double t,
			Derivable<Double> f, boolean isTraceEnabled, int traceSkip) {
		super(x, h, t, f, isTraceEnabled, traceSkip);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see solver.ODESolver#step()
	 */
	@Override
	public List<Double> step() {
//		System.out.println(x);
		List<Double> xAtK2 = new ArrayList<Double>(x.size());
		List<Double> xAtK3 = new ArrayList<Double>(x.size());
		List<Double> xAtK4 = new ArrayList<Double>(x.size());
		List<Double> k1dh = f.getDeltas(x, t);
		List<Double> k2dh;
		List<Double> k3dh;
		List<Double> k4dh;
		List<Double> xnext = new ArrayList<Double>(x.size());
		for (int i = 0; i < x.size(); i++) {
			xAtK2.add(x.get(i) + h / 2 * k1dh.get(i));
		}
		k2dh = f.getDeltas(xAtK2, t + h / 2);
		for (int i = 0; i < x.size(); i++) {
			xAtK3.add(i, x.get(i) + h / 2 * k2dh.get(i));
		}
		k3dh = f.getDeltas(xAtK3, t + h / 2);
		for (int i = 0; i < x.size(); i++) {
			xAtK4.add(i, x.get(i) + h * k3dh.get(i));
		}
		k4dh = f.getDeltas(xAtK4, t + h);
		for (int i = 0; i < x.size(); i++) {
			double value = x.get(i) + h / 6
					* (k1dh.get(i) + 2 * k2dh.get(i) + 2 * k3dh.get(i) + k4dh.get(i));
			xnext.add(i, value);
		}
		return xnext;
	}
}