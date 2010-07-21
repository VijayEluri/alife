/**
 * 
 */
package co.edu.unal.alife.pendulum;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.dynamics.impl.RungeKutta4thSolver;
import co.edu.unal.alife.evolution.impl.ParameterizedKernel;
import co.edu.unal.alife.evolution.impl.PendulumControllerParameters;
import co.edu.unal.alife.neuralfield.DeltaEquation;
import co.edu.unal.alife.neuralfield.AbstractDeltaField;
import co.edu.unal.alife.neuralfield.AbstractNonDifferentialEquation;
import co.edu.unal.alife.neuralfield.AbstractKernelFunction;
import co.edu.unal.alife.neuralfield.impl.InputEquationForPendulum;
import co.edu.unal.alife.neuralfield.impl.MapDeltaPopulation;
import co.edu.unal.alife.neuralfield.impl.SimpleDeltaField;
import co.edu.unal.alife.neuralfield.impl.SimpleDifferentialEquation;

/**
 * @author Juan Figueredo
 */
public class PendulumController {
	
	private AbstractDeltaField field = null;

	/**
	 * @param halfSize
	 * @param parameters
	 */
	public PendulumController(int halfSize, PendulumControllerParameters parameters) {
		super();
		assert (halfSize*2+1) == parameters.getSize();
		field = buildController(parameters, halfSize);
	}

	/**
	 * @param halfSize
	 * @param initialAngle
	 * @param initialPos
	 * @return
	 */
	private AbstractDeltaField buildController(PendulumControllerParameters parameters, int halfSize) {
		int N = 3;
		
		// Populations setup
		List<DeltaPopulation> populations = new ArrayList<DeltaPopulation>(N);
		MapDeltaPopulation inputPopulation = new MapDeltaPopulation(halfSize);
		MapDeltaPopulation processingPopulation = new MapDeltaPopulation(halfSize);
		MapDeltaPopulation pendulumPopulation = new MapDeltaPopulation(4,false);
		populations.add(inputPopulation);
		populations.add(processingPopulation);
		populations.add(pendulumPopulation);

		// Initial values setup
//		pendulumPopulation.setElementValue(PendulumEquation.STATE_THETA, initialAngle);
//		pendulumPopulation.setElementValue(PendulumEquation.STATE_X, initialPos);

		// Kernel Matrix setup
		List<List<AbstractKernelFunction>> kernelMatrix = new ArrayList<List<AbstractKernelFunction>>(N);
		List<AbstractKernelFunction> firstRow = new ArrayList<AbstractKernelFunction>(N);
		List<AbstractKernelFunction> secondRow = new ArrayList<AbstractKernelFunction>(N);
		List<AbstractKernelFunction> thirdRow = null;
		AbstractKernelFunction inputKernelFunction = new ParameterizedKernel(parameters.getInputKernel());
		AbstractKernelFunction selfKernelFunction = new ParameterizedKernel(parameters.getSelfKernel());
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
		List<DeltaEquation> equations = new ArrayList<DeltaEquation>(N);
		AbstractNonDifferentialEquation inputEquation = new InputEquationForPendulum(halfSize, pendulumPopulation);
		SimpleDifferentialEquation simpleEquation = new SimpleDifferentialEquation();
		PendulumEquation pendulumEquation = new PendulumEquation(processingPopulation);
		equations.add(inputEquation); // input field equation
		equations.add(simpleEquation); // output field equation
		equations.add(pendulumEquation); // plant equation

		// Solver setup
		RungeKutta4thSolver solver = new RungeKutta4thSolver();
		
		AbstractDeltaField field = new SimpleDeltaField(equations, kernelMatrix, populations, solver);
		
		return field;
	}

	/**
	 * @return the field
	 */
	public AbstractDeltaField getField() {
		return field;
	}

}
