package co.edu.unal.alife.testbed.statecontroller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.dynamics.impl.MapDeltaPopulation;
import co.edu.unal.alife.dynamics.impl.RungeKutta4thSolver;
import co.edu.unal.alife.neuralfield.AbstractDeltaField;
import co.edu.unal.alife.neuralfield.AbstractKernelFunction;
import co.edu.unal.alife.neuralfield.AbstractNonDifferentialEquation;
import co.edu.unal.alife.neuralfield.DeltaEquation;
import co.edu.unal.alife.neuralfield.impl.InputEquationVectorCoding;
import co.edu.unal.alife.neuralfield.impl.MexicanHatKernel;
import co.edu.unal.alife.neuralfield.impl.SimpleDeltaField;
import co.edu.unal.alife.neuralfield.impl.SimpleDifferentialEquation;
import co.edu.unal.alife.output.PendulumFrame;
import co.edu.unal.alife.output.Tracer;
import co.edu.unal.alife.pendulum.PendulumEquation;

public class Main {
	private static final double	K	= 50.00;
	private static final double	K_OMEGA	= 0.50*K;
	private static final double	K_THETA	= 0.50*K;
	
	public static void main(String[] args) {
		int N = 4;
		int halfSize = 100;
		double initialAngle = Math.PI / 6;
		double initialPos = -5.0;
		
		double hh = 1.0 / 50;
		double t0 = 0.0;
		double tf = 5 + hh;
		
		// Populations setup
		List<DeltaPopulation> populations = new ArrayList<DeltaPopulation>(N);
		MapDeltaPopulation inputPopulationTheta = new MapDeltaPopulation(halfSize);
		MapDeltaPopulation inputPopulationOmega = new MapDeltaPopulation(halfSize);
		MapDeltaPopulation pendulumPopulation = buildPendulumPopulation(initialAngle, initialPos);
		MapDeltaPopulation outputPopulation = new MapDeltaPopulation(halfSize);
		populations.add(inputPopulationTheta);
		populations.add(inputPopulationOmega);
		populations.add(outputPopulation);
		populations.add(pendulumPopulation);
		
		// Kernel Matrix setup
		List<List<AbstractKernelFunction>> kernelMatrix = new ArrayList<List<AbstractKernelFunction>>(N);
		List<AbstractKernelFunction> firstRow = new ArrayList<AbstractKernelFunction>(N);
		List<AbstractKernelFunction> secondRow = new ArrayList<AbstractKernelFunction>(N);
		List<AbstractKernelFunction> thirdRow = new ArrayList<AbstractKernelFunction>(N);
		List<AbstractKernelFunction> fourthRow = null;
		
		AbstractKernelFunction vectorCodingKernelFunction = new MexicanHatKernel(0.10, 0.05*halfSize, 1.0);
		
		AbstractKernelFunction inputThetaKernelFunction = new MexicanHatKernel(0.10, 0.1*halfSize, K_THETA);
		AbstractKernelFunction inputOmegaKernelFunction = new MexicanHatKernel(0.10, 0.1*halfSize, K_OMEGA);
		AbstractKernelFunction selfKernelFunction = new MexicanHatKernel(0.10, 0.1*halfSize, 0.30);
		thirdRow.add(inputThetaKernelFunction); // connectivity from inputTheta to output
		thirdRow.add(inputOmegaKernelFunction); // connectivity from inputOmega to output
		thirdRow.add(selfKernelFunction); // self-connectivity of output field
		thirdRow.add(null); // connectivity from pendulum to output
		
		kernelMatrix.add(firstRow);
		kernelMatrix.add(secondRow);
		kernelMatrix.add(thirdRow);
		kernelMatrix.add(fourthRow);
		
		// Equations setup
		List<DeltaEquation> equations = new ArrayList<DeltaEquation>(N);
		AbstractNonDifferentialEquation inputThetaEquation = new InputEquationVectorCoding(halfSize,
				pendulumPopulation, PendulumEquation.STATE_THETA, vectorCodingKernelFunction);
		AbstractNonDifferentialEquation inputOmegaEquation = new InputEquationVectorCoding(halfSize,
			pendulumPopulation, PendulumEquation.STATE_OMEGA, vectorCodingKernelFunction);
		SimpleDifferentialEquation outputEquation = new SimpleDifferentialEquation();
		PendulumEquation pendulumEquation = new PendulumEquation(outputPopulation);
		equations.add(inputThetaEquation); // input field equation
		equations.add(inputOmegaEquation); // input field equation		
		equations.add(outputEquation); // output field equation
		equations.add(pendulumEquation); // plant equation
		
		// Solver setup
		RungeKutta4thSolver solver = new RungeKutta4thSolver();
		
		AbstractDeltaField field = new SimpleDeltaField(equations, kernelMatrix, populations, solver);
		
		// Visualizer printer = new PendulumPrinter(pendulumStates, mainSize,
		// mainSize);
		Tracer tracer = new Tracer(N);
		// field.addObserver(printer);
		field.addObserver(tracer);
		
		// Run simulation
		field.getSolver().simulate(t0, tf, hh, field);
		
		// String[] filenames =
		// {"inputPopulation","fieldPopulation","pendulum"};
		// String[] filenames = { "inputPopulation_old", "fieldPopulation_old",
		// "pendulum_old" };
		// tracer.printToFiles(filenames);
		String[] filenames = { null, null, null, "pendulum_stateController_" + new Date() }; // 2d
		// String[] filenames = {
		// "inputPopulation_ijcnn","fieldPopulation_ijcnn",null}; //3d
		tracer.printToFiles(filenames, true);
		
		// Run animation
		new PendulumFrame(0,5d,2,5d,3,tracer);
		
	}
	
	private static MapDeltaPopulation buildPendulumPopulation(double initialAngle, double initialPos) {
		MapDeltaPopulation pendulumPopulation = new MapDeltaPopulation(4, false);
		// Initial values setup
		pendulumPopulation.setElementValue(PendulumEquation.STATE_THETA, initialAngle);
		pendulumPopulation.setElementValue(PendulumEquation.STATE_X, initialPos);
		return pendulumPopulation;
	}
}
