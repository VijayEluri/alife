package co.edu.unal.alife.neuralfield;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.dynamics.RungeKutta4thSolver;
import co.edu.unal.alife.dynamics.SolverUtility;
import co.edu.unal.alife.neuralfield.impl.MS1MKernelParameter;
import co.edu.unal.alife.neuralfield.impl.MapDeltaPopulation;
import co.edu.unal.alife.neuralfield.impl.MexicanHatS1MetricKernel;
import co.edu.unal.alife.neuralfield.impl.S1ActionEquation;
import co.edu.unal.alife.neuralfield.impl.S1ActionKernel;
import co.edu.unal.alife.neuralfield.impl.S1InputEquationForPendulum;
import co.edu.unal.alife.neuralfield.impl.SimpleDeltaField;
import co.edu.unal.alife.neuralfield.impl.SimpleDifferentialEquation;
import co.edu.unal.alife.output.PendulumFrame;
import co.edu.unal.alife.output.Tracer;
import co.edu.unal.alife.pendulum.PendulumEquationWithS1;
import co.edu.unal.alife.pendulum.SystemEquation;

public class DeltaFieldFactory {

	private final static int N = 4;

	private DeltaFieldFactory() {
	}

	/**
	 * DeltaFieldFactoryHolder is loaded on the first execution of
	 * DeltaFieldFactory.getInstance() or the first access to
	 * DeltaFieldFactoryHolder.INSTANCE, not before.
	 */
	private static class DeltaFieldFactoryHolder {
		private static final DeltaFieldFactory INSTANCE = new DeltaFieldFactory();
	}

	public static DeltaFieldFactory getInstance() {
		return DeltaFieldFactoryHolder.INSTANCE;
	}

	/**
	 * Builds a field composed of populations embedded in S1 with a metric
	 * provided. It has a non-differential Input Population that performs vector
	 * coding of inputs, a differential Representation Population receiving the
	 * inputs with differential dynamics, and a non-differential Action
	 * Population which evaluates the output as a centroid vector
	 * decodification. It includes as fourth population the System Population
	 * provided as parameter, and connects it to the input and action
	 * populations.
	 * 
	 * 
	 * Each element of each population is represented parametricaly by a
	 * function of phy (being phy in [0,1)). In this way, all populations are
	 * homeomorphic. They are not isometric because of the different metrics
	 * used for each one.
	 * 
	 * A default RK4 Solver is provided.
	 * 
	 * @param points
	 *            The number of [0,1) subdivisions
	 * @param kernel
	 *            The standard kernel used for all calculations
	 * @param systemPopulation
	 *            The population that represents the controlled system. The
	 *            action over this population will be set to take input from the
	 *            Action Layer.
	 * @param inputEquation
	 *            The input equation propagating the system state to the
	 *            inputPopulation
	 * @return A delta field composed of the populations.
	 */
	public DeltaField<Double> buildSimpleS1Field(int points, List<List<KernelFunction>> kernelMatrix,
			DeltaPopulation<Double> systemPopulation, SystemEquation systemEquation, int actions) {
		// The are 4 populations (Input, Representation, Output, System)
		// private final static int inpPopIndex = 0;
		final int repPopIndex = 1;
		final int actPopIndex = 2;
		// private final static int sysPopIndex = 3;

		List<DeltaPopulation<Double>> populations = new ArrayList<DeltaPopulation<Double>>(N);
		MapDeltaPopulation inputPopulation = new MapDeltaPopulation(points, false);
		MapDeltaPopulation representationPopulation = new MapDeltaPopulation(points, false);
		MapDeltaPopulation actionPopulation = new MapDeltaPopulation(actions, false);
		populations.add(inputPopulation);
		populations.add(representationPopulation);
		populations.add(actionPopulation);
		populations.add(systemPopulation);

		systemEquation.setActionPopulation(actionPopulation);

		List<DeltaEquation<Double>> equations = new ArrayList<DeltaEquation<Double>>(N);
		NonDifferentialEquation inputEquation = new S1InputEquationForPendulum(systemPopulation);
		DifferentialEquation neuralFieldEquation = new SimpleDifferentialEquation();
		NonDifferentialEquation actionEquation = new S1ActionEquation(representationPopulation, kernelMatrix.get(
				actPopIndex).get(repPopIndex));
		equations.add(inputEquation);
		equations.add(neuralFieldEquation);
		equations.add(actionEquation);
		equations.add(systemEquation);

		RungeKutta4thSolver solver = new RungeKutta4thSolver();
		DeltaField<Double> field = new SimpleDeltaField(equations, kernelMatrix, populations, solver);

		return field;
	}

	public List<List<KernelFunction>> buildSimpleS1KernelMatrix(MS1MKernelParameter in2repParams,
			MS1MKernelParameter rep2repParams, double[][] abActParams, int points) {
		List<List<KernelFunction>> kernelMatrix = new ArrayList<List<KernelFunction>>(N);

		// KernelFunction creation
		KernelFunction in2repKernel = new MexicanHatS1MetricKernel(in2repParams, points);
		KernelFunction rep2repKernel = new MexicanHatS1MetricKernel(rep2repParams, points);
		KernelFunction rep2actKernel = new S1ActionKernel(abActParams[0], abActParams[1]);

		// Input population doesn't depend on kernel functions
		List<KernelFunction> firstRow = null;
		// Representation population depends on kernel functions
		List<KernelFunction> secondRow = new ArrayList<KernelFunction>(N);

		secondRow.add(in2repKernel); // connectivity from input to
		// representation
		secondRow.add(rep2repKernel); // self-connectivity of representation
		// population
		secondRow.add(null); // connectivity from action to representation
		secondRow.add(null); // connectivity from system to representation
		// Action population depends on kernel functions
		List<KernelFunction> thirdRow = new ArrayList<KernelFunction>(N);
		thirdRow.add(null); // connectivity from input to action
		thirdRow.add(rep2actKernel); // connectivity from representation to
		// output
		thirdRow.add(null); // self-connectivity of action field
		thirdRow.add(null); // connectivity from system to action
		// Input population doesn't depend on kernel functions
		List<KernelFunction> fourthRow = null;

		// Add kernel lists to matrix
		kernelMatrix.add(firstRow);
		kernelMatrix.add(secondRow);
		kernelMatrix.add(thirdRow);
		kernelMatrix.add(fourthRow);

		return kernelMatrix;
	}

	public static void main(String[] args) {

		// Discrete points in fields
		int points = 20;
		// Number of actions performed
		int actions = 1;

		// Simulation parameters
		double hh = 1.0 / 40;
		double t0 = 0.0;
		double tf = 10 + hh;
		double initialAngle = Math.PI / 6;
		double initialPos = -5.0;

		DeltaFieldFactory factory = DeltaFieldFactory.getInstance();

		// KernelMatrix Parameters
		double[][] choleskyIn = { { 1, 0 }, { 0, 1 } };
		double[][] choleskyRep = { { 1, 0 }, { 0, 1 } };
		double h0 = 0.05;
		double delta = 4;
		double k = 0.05;
		double[][] abActParams = { { -1 }, { 0 } };
		// KernelParameter Construction
		MS1MKernelParameter in2repParams = new MS1MKernelParameter(choleskyIn, h0, delta, k);
		MS1MKernelParameter rep2repParams = new MS1MKernelParameter(choleskyRep, h0, delta, k);
		// KernelMatrix construction
		List<List<KernelFunction>> kernelMatrix = factory.buildSimpleS1KernelMatrix(in2repParams, rep2repParams,
				abActParams, points);

		// System (pendulum) population construction
		DeltaPopulation<Double> systemPopulation = new MapDeltaPopulation(4, false);
		systemPopulation.setElementValue(PendulumEquationWithS1.STATE_THETA, initialAngle);
		systemPopulation.setElementValue(PendulumEquationWithS1.STATE_X, initialPos);
		// PendulumEquation Construction
		SystemEquation systemEquation = new PendulumEquationWithS1();

		// Field construction
		DeltaField<Double> field = factory.buildSimpleS1Field(points, kernelMatrix, systemPopulation, systemEquation,
				actions);

		// Monitoring
		Tracer tracer = new Tracer(N);
		// field.addObserver(printer);
		field.addObserver(tracer);

		// Run simulation
		SolverUtility.simulate(t0, tf, hh, field);

		// String[] filenames =
		// {"inputPopulation","fieldPopulation","pendulum"};
		// String[] filenames = { "inputPopulation_old", "fieldPopulation_old",
		// "pendulum_old" };
		// tracer.printToFiles(filenames);
		Date dt = new Date();
		String[] filenames = { "inpPopulation_S1_" + dt, "repPopulation_S1_" + dt, "actPopulation_S1_" + dt,
				"pendPopulation_S1_" + dt }; // 2d
		// String[] filenames = {
		// "inputPopulation_ijcnn","fieldPopulation_ijcnn",null}; //3d
		tracer.printToFiles(filenames,true);

		// Run animation
		new PendulumFrame(0,3,1,tracer);
	}

}