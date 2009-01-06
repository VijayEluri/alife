/**
 * 
 */
package co.edu.unal.alife.applications;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.alife.dynamics.AbstractSolver;
import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.dynamics.RungeKutta4thSolver;
import co.edu.unal.alife.neuralfield.DeltaEquation;
import co.edu.unal.alife.neuralfield.DeltaField;
import co.edu.unal.alife.neuralfield.KernelFunction;
import co.edu.unal.alife.neuralfield.impl.InputEquation;
import co.edu.unal.alife.neuralfield.impl.MapNeuralPopulation;
import co.edu.unal.alife.neuralfield.impl.MexicanHatKernel;
import co.edu.unal.alife.neuralfield.impl.SimpleDeltaField;
import co.edu.unal.alife.neuralfield.impl.SimpleEquation;
import co.edu.unal.alife.output.core.AnimationFrameGait;
import co.edu.unal.alife.output.core.Tracer;

/**
 * @author Juan Figueredo
 */
public class FirstProblemSimulation {
	
	public static void main(String[] args) {
		int N = 3;
		int halfSize = 10;
		double initialAngle = 0.1;
		
		double hh = 1.0 / 10;
		double t0 = 0.0;
		double tf = 10.0;
		// Populations setup
		List<DeltaPopulation<Double>> populations = new ArrayList<DeltaPopulation<Double>>(N);
		MapNeuralPopulation inputPopulation = new MapNeuralPopulation(halfSize);
		MapNeuralPopulation outputPopulation = new MapNeuralPopulation(halfSize);
		MapNeuralPopulation pendulumPopulation = new MapNeuralPopulation(4,false);
		populations.add(inputPopulation);
		populations.add(outputPopulation);
		populations.add(pendulumPopulation);

		// Initial values setup
		pendulumPopulation.setElementValue(PendulumEquation.STATE_THETA, initialAngle);

		// Kernel Matrix setup
		List<List<KernelFunction>> kernelMatrix = new ArrayList<List<KernelFunction>>(N);
		List<KernelFunction> firstRow = new ArrayList<KernelFunction>(N);
		List<KernelFunction> secondRow = new ArrayList<KernelFunction>(N);
		List<KernelFunction> thirdRow = null;
		KernelFunction kernelFunction = new MexicanHatKernel();
		firstRow.add(null); // self-connectivity of input field
		firstRow.add(null); // connectivity from output to input
		firstRow.add(null); // connectivity from plant to input
		secondRow.add(kernelFunction); // connectivity from input to output
		secondRow.add(kernelFunction); // self-connectivity of output field
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
		
//		Visualizer printer = new PendulumPrinter(pendulumStates, mainSize, mainSize);
		Tracer tracer = new Tracer(N);
		//field.addObserver(printer);
		field.addObserver(tracer);

		// Run simulation
		AbstractSolver.simulate(t0, tf, hh, field);

		// Run animation
		new AnimationFrameGait(tracer);

	}
}
