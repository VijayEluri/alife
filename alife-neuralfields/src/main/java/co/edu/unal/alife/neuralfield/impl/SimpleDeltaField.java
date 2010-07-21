/**
 * 
 */
package co.edu.unal.alife.neuralfield.impl;

import java.util.List;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.dynamics.Solver;
import co.edu.unal.alife.neuralfield.DeltaEquation;
import co.edu.unal.alife.neuralfield.AbstractDeltaField;
import co.edu.unal.alife.neuralfield.AbstractKernelFunction;

/**
 * @author Juan Figueredo
 *
 */
public class SimpleDeltaField extends AbstractDeltaField {

	/**
	 * @param equations
	 * @param kernelMatrix
	 * @param populations
	 * @param solver
	 */
	public SimpleDeltaField(List<DeltaEquation> equations,
			List<List<AbstractKernelFunction>> kernelMatrix, List<DeltaPopulation> populations,
			Solver solver) {
		super(equations, kernelMatrix, populations, solver);
		// TODO Auto-generated constructor stub
	}
}
