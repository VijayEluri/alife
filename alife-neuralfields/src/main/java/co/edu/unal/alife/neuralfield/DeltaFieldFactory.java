package co.edu.unal.alife.neuralfield;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.dynamics.RungeKutta4thSolver;
import co.edu.unal.alife.evolution.params.S1ControllerParameters;
import co.edu.unal.alife.neuralfield.impl.S1KernelParameter;
import co.edu.unal.alife.neuralfield.impl.MapDeltaPopulation;
import co.edu.unal.alife.neuralfield.impl.S1MexicanHatMetricKernel;
import co.edu.unal.alife.neuralfield.impl.S1ActionEquation;
import co.edu.unal.alife.neuralfield.impl.S1InputEquationForPendulum;
import co.edu.unal.alife.neuralfield.impl.SimpleDeltaField;
import co.edu.unal.alife.neuralfield.impl.SimpleDifferentialEquation;
import co.edu.unal.alife.pendulum.SystemEquation;

public class DeltaFieldFactory {

	// private final static int N = 4;

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
	 * coding of inputs, a group of differential Representation Populations receiving the
	 * inputs with differential dynamics, and a non-differential Action
	 * Population which evaluates the output as a centroid vector
	 * decodification. It includes as fourth population the System Population
	 * provided as parameter, and connects it to the input and action
	 * populations.
	 * 
	 * Each element of each population is represented parametricaly by a
	 * function of phi (being phi in [0,1)). In this way, all populations are
	 * homeomorphic. They are not isometric because of the different metrics
	 * used for each one.
	 * 
	 * A default RK4 Solver is provided.
	 * 
	 * @param controllerParams
	 *            A parameter object that contains all the values required to
	 *            build the neural field controller.
	 * @param points
	 *            The number of [0,1) subdivisions
	 * @param systemPopulation
	 *            The population that represents the controlled system. The
	 *            action over this population will be set to take input from the
	 *            Action Layer.
	 * @param systemEquation
	 * @param inputEquation
	 *            The input equation propagating the system state to the
	 *            inputPopulation
	 * @param inputEquation
	 *            TODO
	 * @return A delta field composed of the populations.
	 */
	public DeltaField<Double> buildLayeredS1Field(
			S1ControllerParameters controllerParams, int points,
			DeltaPopulation<Double> systemPopulation,
			SystemEquation systemEquation,
			S1InputEquationForPendulum inputEquation) {
		// Number of populations per layer
		int noInputs = controllerParams.getInSize();
		int noGoals = controllerParams.getHiSize();
		int noOutputs = controllerParams.getOutSize();
		// Number of populations
		final int N = 1 + noGoals + 1 + 1; // Input+Representation[]+Action+System

		// Population construction
		List<DeltaPopulation<Double>> populations = new ArrayList<DeltaPopulation<Double>>(
				N);
		MapDeltaPopulation inputPopulation = new MapDeltaPopulation(points,
				false);
		populations.add(inputPopulation);
		// Creation and addition of representation populations
		for (int i = 0; i < noGoals; i++) {
			MapDeltaPopulation repPopulation = new MapDeltaPopulation(points,
					false);
			populations.add(repPopulation);
		}
		MapDeltaPopulation actionPopulation = new MapDeltaPopulation(noOutputs,
				false);
		populations.add(actionPopulation);
		populations.add(systemPopulation);

		// The action population should be referenced from the system equation
		// (in order to apply output values)
		systemEquation.setActionPopulation(actionPopulation);

		// Equation creation
		List<DeltaEquation<Double>> equations = new ArrayList<DeltaEquation<Double>>(
				N);
		equations.add(inputEquation);
		// Creation and addition of representation equations
		for (int i = 0; i < noGoals; i++) {
			equations.add(new SimpleDifferentialEquation());
		}
		NonDifferentialEquation actionEquation = new S1ActionEquation(
				populations.subList(1, 1 + noGoals), controllerParams
						.getOutParams().getTransformations());
		equations.add(actionEquation);
		equations.add(systemEquation);

		// Kernel matrix creation
		List<List<KernelFunction>> kernelMatrix = new ArrayList<List<KernelFunction>>(
				N);
		List<KernelFunction> inputRow = null;
		kernelMatrix.add(inputRow);
		// We get the lists of parameters for kernel contruction: rep2rep
		List<Double> cholesky1s = controllerParams.getRepParams()
				.getCholesky1s();
		List<Double> cholesky2s = controllerParams.getRepParams()
				.getCholesky2s();
		List<Double> deltas = controllerParams.getRepParams().getDeltas();
		List<Double> hs = controllerParams.getRepParams().getHs();
		List<Double> ks = controllerParams.getRepParams().getKs();
		// And for input2rep (alphas are Ks in input kernels)
		List<Double> alphas = controllerParams.getInParams().getKs();
		// Addition and creation of in2rep and rep2rep kernel lists
		S1KernelParameter kernelParameter;
		for (int i = 0; i < noGoals; i++) {
			ArrayList<KernelFunction> goalKernelRow = new ArrayList<KernelFunction>();
			// Cholesky matrix contructed so that |L|=1;
			double[][] L = { { cholesky1s.get(i), 0 },
					{ cholesky2s.get(i), 1 / cholesky1s.get(i) } };
			// Input2rep kernels
			for (int j = 0; j < noInputs; j++) {
				kernelParameter = new S1KernelParameter(L, hs.get(i), deltas
						.get(i), alphas.get(i * noInputs + j));
				goalKernelRow.add(new S1MexicanHatMetricKernel(kernelParameter,
						points));
			}
			// Rep2rep kernel
			kernelParameter = new S1KernelParameter(L, hs.get(i), deltas
					.get(i), ks.get(i));
			goalKernelRow.add(new S1MexicanHatMetricKernel(kernelParameter,
					points));
			kernelMatrix.add(goalKernelRow);
		}
		List<KernelFunction> actionRow = null;
		kernelMatrix.add(actionRow);
		List<KernelFunction> systemRow = null;
		kernelMatrix.add(systemRow);

		// Solver and field creation
		RungeKutta4thSolver solver = new RungeKutta4thSolver();
		DeltaField<Double> field = new SimpleDeltaField(equations,
				kernelMatrix, populations, solver);

		return field;
	}

	public static void main(String[] args) {
		//
		// // Discrete points in fields
		// int points = 20;
		// // Number of actions performed
		// int actions = 1;
		//
		// // Simulation parameters
		// double hh = 1.0 / 40;
		// double t0 = 0.0;
		// double tf = 10 + hh;
		// double initialAngle = Math.PI / 6;
		// double initialPos = -5.0;
		//
		// DeltaFieldFactory factory = DeltaFieldFactory.getInstance();
		//
		// // KernelMatrix Parameters
		// double[][] choleskyIn = { { 1, 0 }, { 0, 1 } };
		// double[][] choleskyRep = { { 1, 0 }, { 0, 1 } };
		// double h0 = 0.05;
		// double delta = 4;
		// double k = 0.05;
		// double[][] abActParams = { { -1 }, { 0 } };
		// // KernelParameter Construction
		// MS1MKernelParameter in2repParams = new
		// MS1MKernelParameter(choleskyIn, h0, delta, k);
		// MS1MKernelParameter rep2repParams = new
		// MS1MKernelParameter(choleskyRep, h0, delta, k);
		// // KernelMatrix construction
		// List<List<KernelFunction>> kernelMatrix =
		// factory.buildSimpleS1KernelMatrix(in2repParams, rep2repParams,
		// abActParams, points);
		//
		// // System (pendulum) population construction
		// DeltaPopulation<Double> systemPopulation = new MapDeltaPopulation(4,
		// false);
		// systemPopulation.setElementValue(PendulumEquationWithS1.STATE_THETA,
		// initialAngle);
		// systemPopulation.setElementValue(PendulumEquationWithS1.STATE_X,
		// initialPos);
		// // PendulumEquation Construction
		// SystemEquation systemEquation = new PendulumEquationWithS1();
		//
		// // Field construction
		// DeltaField<Double> field = factory.buildSimpleS1Field(points,
		// kernelMatrix, systemPopulation, systemEquation,
		// actions);
		//
		// // Monitoring
		// Tracer tracer = new Tracer(N);
		// // field.addObserver(printer);
		// field.addObserver(tracer);
		//
		// // Run simulation
		// SolverUtility.simulate(t0, tf, hh, field);
		//
		// // String[] filenames =
		// // {"inputPopulation","fieldPopulation","pendulum"};
		// // String[] filenames = { "inputPopulation_old",
		// "fieldPopulation_old",
		// // "pendulum_old" };
		// // tracer.printToFiles(filenames);
		// Date dt = new Date();
		// String[] filenames = { "inpPopulation_S1_" + dt, "repPopulation_S1_"
		// + dt, "actPopulation_S1_" + dt,
		// "pendPopulation_S1_" + dt }; // 2d
		// // String[] filenames = {
		// // "inputPopulation_ijcnn","fieldPopulation_ijcnn",null}; //3d
		// tracer.printToFiles(filenames,true);
		//
		// // Run animation
		// new PendulumFrame(0,3,1,tracer);
	}

}
