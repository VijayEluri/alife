/**
 * 
 */
package co.edu.unal.alife.neuralfield;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.dynamics.RungeKutta4thSolver;
import co.edu.unal.alife.dynamics.Simulable;
import co.edu.unal.alife.dynamics.Solver;

/**
 * @author Juan Figueredo
 */
public abstract class DeltaField<K> implements Simulable<Double> {

	protected List<DeltaPopulation<K>> populations;
	protected List<DeltaPopulationEquation<K>> equations;
	protected List<List<KernelFunction>> kernelMatrix;
	protected Solver<Double, Double> solver;

	/**
	 * @param equations
	 * @param kernelMatrix
	 * @param populations
	 */
	public DeltaField(List<DeltaPopulationEquation<K>> equations,
			List<List<KernelFunction>> kernelMatrix, List<DeltaPopulation<K>> populations) {
		super();
		this.equations = equations;
		this.kernelMatrix = kernelMatrix;
		this.populations = populations;
		this.solver = new RungeKutta4thSolver();
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
	public List<DeltaPopulationEquation<K>> getEquations() {
		return equations;
	}

	/**
	 * @return the kernelMatrix
	 */
	public List<List<KernelFunction>> getKernelMatrix() {
		return kernelMatrix;
	}

	public List<List<Double>> evaluateStep(List<List<Double>> newValues) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see co.edu.unal.alife.dynamic.Simulable#evaluateSimulable()
	 */

	public List<List<Double>> evaluateSimulable(List<List<Double>> newValues) {
		List<List<Double>> newDeltas = new ArrayList<List<Double>>();
		// for (int i = 0; i < populations.size(); i++) {
		// DeltaPopulation<K> population = populations.get(i);
		// population.setElementValues(newValues.get(i));
		// population.updatePopulationDelta(this, i);
		// newDeltas.add(new ArrayList<Double>(population.getElementDeltas()));
		// }
		return newDeltas;
	}

}
