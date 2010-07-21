package co.edu.unal.alife.dynamics;

import java.util.Observable;

import co.edu.unal.alife.neuralfield.AbstractDeltaField;

/**
 * @author Juan Figueredo
 * 
 */
public abstract class AbstractFixedStepSolver extends Observable implements Solver {
	

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.dynamics.Solver#simulate(double, double, double, co.edu.unal.alife.neuralfield.AbstractDeltaField)
	 */
	public void simulate(double t0, double tf, double h, AbstractDeltaField simulable) {
		int n = (int) Math.ceil(tf / h);
//		double t = 0;
		for (int i = 0; i < n; i++) {
//			t = t0 + h * i;
			simulable.evaluateStep(h, i);
		}
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
