/**
 * 
 */
package co.edu.unal.alife.neuralfield;

import java.util.List;
import java.util.Observable;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.dynamics.Simulable;
import co.edu.unal.alife.dynamics.Solver;

/**
 * @author Juan Figueredo
 */
public abstract class DeltaField<K> extends Observable implements Simulable<Double> {

	protected List<DeltaPopulation<K>> populations;
	protected List<DeltaEquation<K>> equations;
	protected List<List<KernelFunction>> kernelMatrix;
	protected Solver<K, Double> solver;

	/**
	 * @param equations
	 * @param kernelMatrix
	 * @param populations
	 */
	public DeltaField(List<DeltaEquation<K>> equations, List<List<KernelFunction>> kernelMatrix,
			List<DeltaPopulation<K>> populations, Solver<K, Double> solver) {
		super();
		this.equations = equations;
		this.kernelMatrix = kernelMatrix;
		this.populations = populations;
		this.solver = solver;
	}

	/**
	 * @return the populations
	 */
	public List<DeltaPopulation<K>> getPopulations() {
		return populations;
	}

	/**
	 * @return the equations
	 */
	public List<DeltaEquation<K>> getEquations() {
		return equations;
	}

	/**
	 * @return the kernelMatrix
	 */
	public List<List<KernelFunction>> getKernelMatrix() {
		return kernelMatrix;
	}

	@Override
	public void evaluateStep(double h, int nextStepCount) {
		double t = nextStepCount * h;
		for (int i = 0; i < populations.size(); i++) {
			DeltaPopulation<K> deltaPopulation = null;
			try {
				deltaPopulation = solver.step(this, i, h);
				// System.out.println("Step - Pop "+i);
			} catch (UnsupportedOperationException e) {
				deltaPopulation = equations.get(i).applyInput(populations.get(i));
				// System.out.println("Apply - Pop "+i);
			}
			populations.set(i, deltaPopulation);
			this.setChanged();
			this.notifyObservers(new Object[] { i, t, deltaPopulation });
		}
	}

}
