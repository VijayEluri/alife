package co.edu.unal.alife.dynamic.core;

import java.util.Observable;

import co.edu.unal.alife.dynamics.Simulable;

/**
 * @author jjfigueredou
 *
 */
public abstract class Simulation extends Observable implements Runnable {
	
	private Solver solver;
	private Simulable simulable;
	
	/**
	 * @return
	 */
	public abstract Object runSimulation();
		
	/**
	 * @return
	 */
	public Solver getSolver() {
		return solver;
	}
	
	/**
	 * @param solver
	 */
	public void setSolver(Solver solver) {
		this.solver = solver;
	}
	
	/**
	 * @return
	 */
	public Simulable getSimulable() {
		return simulable;
	}
	
	/**
	 * @param simulable
	 */
	public void setSimulable(Simulable simulable) {
		this.simulable = simulable;
	}
}
