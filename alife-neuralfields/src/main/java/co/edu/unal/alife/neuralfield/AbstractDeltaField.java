/**
 * 
 */
package co.edu.unal.alife.neuralfield;

import java.util.List;
import java.util.Observable;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.dynamics.Solver;

/**
 * @author Juan Figueredo
 */
public abstract class AbstractDeltaField extends Observable {
	
	protected List<DeltaPopulation>					populations;
	protected List<DeltaEquation>					equations;
	protected List<List<AbstractKernelFunction>>	kernelMatrix;
	protected Solver								solver;
	
	/**
	 * @param equations
	 * @param kernelMatrix
	 * @param populations
	 */
	public AbstractDeltaField(List<DeltaEquation> equations,
			List<List<AbstractKernelFunction>> kernelMatrix,
			List<DeltaPopulation> populations, Solver solver) {
		super();
		this.equations = equations;
		this.kernelMatrix = kernelMatrix;
		this.populations = populations;
		this.solver = solver;
	}
	
	/**
	 * @return the populations
	 */
	public List<DeltaPopulation> getPopulations() {
		return populations;
	}
	
	/**
	 * @return the equations
	 */
	public List<DeltaEquation> getEquations() {
		return equations;
	}
	
	/**
	 * @return the kernelMatrix
	 */
	public List<List<AbstractKernelFunction>> getKernelMatrix() {
		return kernelMatrix;
	}
	
	/**
	 * Evaluates an iteration of the entire field in a population-by-population basis. Returns true if this step is
	 * terminal (a terminal event was found), otherwise it returns false.
	 * 
	 * @param h
	 *            the step size.
	 * @param nextStepCount
	 *            the step count for next step
	 * @return isTerminal
	 */
	public boolean evaluateStep(double h, int nextStepCount) {
		boolean isTerminal = false;
		double t = nextStepCount * h;
		for (int i = 0; i < populations.size(); i++) {
			DeltaPopulation deltaPopulation = null;
			DeltaEquation equation = equations.get(i);
			if (equation.requiresApplyInput()) {
				deltaPopulation = equation.applyInput(populations.get(i));
				//System.out.println("Apply - Pop "+t);
			}
			if (equation.isDifferential()) {
				try {
					deltaPopulation = solver.step(this, i, h);
					// System.out.println("Step - Pop "+i);
				} catch (UnsupportedOperationException e) {
					throw new RuntimeException(e.getMessage());
				}
			}
			isTerminal = deltaPopulation.isTerminal() ? true : isTerminal;
			populations.set(i, deltaPopulation);
			this.setChanged();
			this.notifyObservers(new Object[] { i, t, deltaPopulation });
		}
		return isTerminal;
	}
	
	public Solver getSolver() {
		return solver;
	}
	
	public void setSolver(Solver solver) {
		this.solver = solver;
	}
	
	/**
	 * Prepares a population in the field for the update rule applied by a ODE solver.
	 * Updates the state of the delta of all elements contained in the
	 * population.
	 * 
	 * @param localIndex
	 *            The index of the local Population, local Equation and local KernelList.
	 */
	public void updatePopulationDelta(DeltaPopulation population, int localIndex)
			throws UnsupportedOperationException {
		DeltaEquation equation = this.getEquations().get(localIndex);
		List<AbstractKernelFunction> kernelList = this.getKernelMatrix().get(localIndex);
		equation.evalEquation(population, this.getPopulations(), kernelList); //may throw UnsupportedOperationException
	}
	
	public void init() {
		for (int i = 0; i < populations.size(); i++) {
			DeltaPopulation deltaPopulation = populations.get(i);
			this.setChanged();
			this.notifyObservers(new Object[] { i, 0d, deltaPopulation });
		}
	}
	
}
