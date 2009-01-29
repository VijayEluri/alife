/**
 * 
 */
package co.edu.unal.alife.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.edu.unal.alife.dynamics.AbstractSolver;
import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.dynamics.RungeKutta4thSolver;
import co.edu.unal.alife.evolution.ParameterizedKernel;
import co.edu.unal.alife.evolution.PendulumControllerParameters;
import co.edu.unal.alife.neuralfield.DeltaEquation;
import co.edu.unal.alife.neuralfield.DeltaField;
import co.edu.unal.alife.neuralfield.KernelFunction;
import co.edu.unal.alife.neuralfield.impl.InputEquation;
import co.edu.unal.alife.neuralfield.impl.MapDeltaPopulation;
import co.edu.unal.alife.neuralfield.impl.MexicanHatKernel;
import co.edu.unal.alife.neuralfield.impl.SimpleDeltaField;
import co.edu.unal.alife.neuralfield.impl.SimpleEquation;
import co.edu.unal.alife.output.PendulumFrame;
import co.edu.unal.alife.output.Tracer;
import co.edu.unal.alife.pendulum.PendulumEquation;

/**
 * @author Juan Figueredo
 */
public class FirstProblemSimulation {

	public static void main(String[] args) {
		int N = 3;
		int halfSize = 10;
		double initialAngle = Math.PI/6;
		double initialPos = -5.0;

		double hh = 1.0 / 40;
		double t0 = 0.0;
		double tf = 10 + hh;
		// Populations setup
		List<DeltaPopulation<Double>> populations = new ArrayList<DeltaPopulation<Double>>(N);
		MapDeltaPopulation inputPopulation = new MapDeltaPopulation(halfSize);
		MapDeltaPopulation outputPopulation = new MapDeltaPopulation(halfSize);
		MapDeltaPopulation pendulumPopulation = new MapDeltaPopulation(4, false);
		populations.add(inputPopulation);
		populations.add(outputPopulation);
		populations.add(pendulumPopulation);

		// Initial values setup
		pendulumPopulation.setElementValue(PendulumEquation.STATE_THETA, initialAngle);
		pendulumPopulation.setElementValue(PendulumEquation.STATE_X, initialPos);

		// Kernel Matrix setup
		List<List<KernelFunction>> kernelMatrix = new ArrayList<List<KernelFunction>>(N);
		List<KernelFunction> firstRow = new ArrayList<KernelFunction>(N);
		List<KernelFunction> secondRow = new ArrayList<KernelFunction>(N);
		List<KernelFunction> thirdRow = null;
//		KernelFunction inputKernelFunction = new MexicanHatKernel(0.05,2,0.20);
//		KernelFunction selfKernelFunction = new MexicanHatKernel(0.05,2,0.20);
//		KernelFunction inputKernelFunction = new MexicanHatKernel(0.10,2,2.00);
//		KernelFunction selfKernelFunction = new MexicanHatKernel(0.10,2,0.30);

		Double[] inputArray = new Double[] { 0.8, -0.4, 0.3821521867217581, 0.8,
				0.6627201010260433, 0.8, 0.5996071258752236, 0.4310174353169244, 0.8, 0.8,
				-0.0016068530503658973, 0.8, 0.8, -0.15603003061895004, 0.3911531992201058,
				-0.019875594601236057, 0.8, -0.22352170644144687, -0.4, -0.3057861650728686,
				0.3263381733053502 };
		Double[] processingArray = new Double[] { -0.4, -0.4, 0.7966620749205899, -0.4, -0.4,
				0.5584618779825862, 0.19059994140172853, -0.19807857514308644, 0.6213276196314829,
				0.1208663553191761, 0.4785335021631033, -0.05444274298155438, -0.289283934994388,
				0.6395960411516046, 0.3399083060933949, 0.07606263739663152, -0.2750226685836359,
				0.8, -0.1987387459605986, 0.8, 0.02725196339176175 };
		
		List<Double> inputList = Arrays.asList(inputArray);
		List<Double> processingList = Arrays.asList(processingArray);

		KernelFunction inputKernelFunction = new ParameterizedKernel(inputList);
		KernelFunction selfKernelFunction = new ParameterizedKernel(processingList);

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
		PendulumEquation pendulumEquation = new PendulumEquation(outputPopulation);
//		PendulumEquation pendulumEquation = new PendulumEquation(inputPopulation);
		equations.add(inputEquation); // input field equation
		equations.add(simpleEquation); // output field equation
		equations.add(pendulumEquation); // plant equation

		// Solver setup
		RungeKutta4thSolver solver = new RungeKutta4thSolver();

		DeltaField<Double> field = new SimpleDeltaField(equations, kernelMatrix, populations,
				solver);

		// Visualizer printer = new PendulumPrinter(pendulumStates, mainSize, mainSize);
		Tracer tracer = new Tracer(N);
		// field.addObserver(printer);
		field.addObserver(tracer);

		// Run simulation
		AbstractSolver.simulate(t0, tf, hh, field);

//		String[] filenames = {"inputPopulation","fieldPopulation","pendulum"};
//		String[] filenames = { "inputPopulation_old", "fieldPopulation_old", "pendulum_old" };
//		tracer.printToFiles(filenames);

		// Run animation
		new PendulumFrame(tracer);

	}
}
