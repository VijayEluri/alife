package co.edu.unal.alife.dynamics;

import java.util.Observable;

/**
 * @author Juan Figueredo
 * 
 */
public abstract class AbstractSolver<K,V> extends Observable implements Solver<K,V> {
	
	/**
	 * 
	 */
	public AbstractSolver() {
		super();
		// TODO Auto-generated constructor stub
	}

//	/**
//	 * @param tf
//	 * @return
//	 */
//	public List<V> simulate(double tf) {
//		double evalTime = 0;
//		int n = (int) Math.ceil(tf / h);
//		double t0 = t;
//		for (int i = 0; i < n; i++) {
//			t = t0 + h * i;
//			x = step();
//			if (isTraceEnabled && i%traceSkip==0) {
//				evalTime += traceSkip;
//				this.setChanged();
//				this.notifyObservers(new Object[]{t,x});
//			}
//		}
//		return x;
//	}

}
