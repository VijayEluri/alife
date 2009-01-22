/**
 * 
 */
package co.edu.unal.alife.applications;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.dynamics.RungeKutta4thSolver;
import co.edu.unal.alife.evolution.ParameterizedKernel;
import co.edu.unal.alife.evolution.PendulumControllerParameters;
import co.edu.unal.alife.neuralfield.DeltaEquation;
import co.edu.unal.alife.neuralfield.DeltaField;
import co.edu.unal.alife.neuralfield.KernelFunction;
import co.edu.unal.alife.neuralfield.impl.InputEquation;
import co.edu.unal.alife.neuralfield.impl.MapDeltaPopulation;
import co.edu.unal.alife.neuralfield.impl.SimpleDeltaField;
import co.edu.unal.alife.neuralfield.impl.SimpleEquation;

/**
 * @author Juan Figueredo
 */
public class PendulumController {
	
	private DeltaField<Double> field;

	/**
	 * @param halfSize
	 * @param parameters
	 */
	public PendulumController(int halfSize, PendulumControllerParameters parameters) {
		super();
		assert (halfSize*2)+1 == parameters.getSize();
		field = buildController(parameters, halfSize);
	}

	/**
	 * @param halfSize
	 * @param initialAngle
	 * @param initialPos
	 * @return
	 */
	private DeltaField<Double> buildController(PendulumControllerParameters parameters, int halfSize) {
		int N = 3;
		
		// Populations setup
		List<DeltaPopulation<Double>> populations = new ArrayList<DeltaPopulation<Double>>(N);
		MapDeltaPopulation inputPopulation = new MapDeltaPopulation(halfSize);
		MapDeltaPopulation outputPopulation = new MapDeltaPopulation(halfSize);
		MapDeltaPopulation pendulumPopulation = new MapDeltaPopulation(4,false);
		populations.add(inputPopulation);
		populations.add(outputPopulation);
		populations.add(pendulumPopulation);

		// Initial values setup
//		pendulumPopulation.setElementValue(PendulumEquation.STATE_THETA, initialAngle);
//		pendulumPopulation.setElementValue(PendulumEquation.STATE_X, initialPos);

		// Kernel Matrix setup
		List<List<KernelFunction>> kernelMatrix = new ArrayList<List<KernelFunction>>(N);
		List<KernelFunction> firstRow = new ArrayList<KernelFunction>(N);
		List<KernelFunction> secondRow = new ArrayList<KernelFunction>(N);
		List<KernelFunction> thirdRow = null;
		KernelFunction inputKernelFunction = new ParameterizedKernel(parameters.getInputKernel());
		KernelFunction selfKernelFunction = new ParameterizedKernel(parameters.getSelfKernel());
		firstRow.add(null); // self-connectivity of input field
		firstRow.add(null); // connectivity from output to input
		firstRow.add(null); // connectivity from plant to input
		secondRow.add(inputKernelFunction); // connectivity from input to output
		secondRow.add(selfKernelFunction); // self-connectivity of output field
		secondRow.add(null); // connectivity from plant to output
		kernelMatrix.add(firstRow);
		kernelMatrix.add(secondRow);
		kernelMatrix.add(thirdRow);

		// Equations setup
		List<DeltaEquation<Double>> equations = new ArrayList<DeltaEquation<Double>>(N);
		InputEquation inputEquation = new InputEquation(halfSize, pendulumPopulation);
		SimpleEquation simpleEquation = new SimpleEquation();
		PendulumEquation pendulumEquation = new PendulumEquation(inputPopulation);
		equations.add(inputEquation); // input field equation
		equations.add(simpleEquation); // output field equation
		equations.add(pendulumEquation); // plant equation

		// Solver setup
		RungeKutta4thSolver solver = new RungeKutta4thSolver();
		
		DeltaField<Double> field = new SimpleDeltaField(equations, kernelMatrix, populations, solver);
		
		return field;
	}

	/**
	 * @return the field
	 */
	public DeltaField<Double> getField() {
		return field;
	}

}
