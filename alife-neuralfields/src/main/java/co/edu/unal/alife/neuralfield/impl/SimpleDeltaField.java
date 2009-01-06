/**
 * 
 */
package co.edu.unal.alife.neuralfield.impl;

import java.util.List;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.dynamics.Solver;
import co.edu.unal.alife.neuralfield.DeltaEquation;
import co.edu.unal.alife.neuralfield.DeltaField;
import co.edu.unal.alife.neuralfield.KernelFunction;

/**
 * @author Juan Figueredo
 *
 */
public class SimpleDeltaField extends DeltaField<Double> {

	/**
	 * @param equations
	 * @param kernelMatrix
	 * @param populations
	 * @param solver
	 */
	public SimpleDeltaField(List<DeltaEquation<Double>> equations,
			List<List<KernelFunction>> kernelMatrix, List<DeltaPopulation<Double>> populations,
			Solver<Double, Double> solver) {
		super(equations, kernelMatrix, populations, solver);
		// TODO Auto-generated constructor stub
	}
}
