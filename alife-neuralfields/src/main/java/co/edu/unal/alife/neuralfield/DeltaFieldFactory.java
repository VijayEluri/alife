package co.edu.unal.alife.neuralfield;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.dynamics.RungeKutta4thSolver;
import co.edu.unal.alife.dynamics.SolverUtility;
import co.edu.unal.alife.evolution.params.S1ControllerParameters;
import co.edu.unal.alife.evolution.params.S1InputParameters;
import co.edu.unal.alife.evolution.params.S1OutputParameters;
import co.edu.unal.alife.evolution.params.S1RepresentationParameters;
import co.edu.unal.alife.neuralfield.impl.MapDeltaPopulation;
import co.edu.unal.alife.neuralfield.impl.S1ActionEquation;
import co.edu.unal.alife.neuralfield.impl.S1InputEquationForPendulum;
import co.edu.unal.alife.neuralfield.impl.S1KernelParameter;
import co.edu.unal.alife.neuralfield.impl.S1MexicanHatMetricKernel;
import co.edu.unal.alife.neuralfield.impl.SimpleDeltaField;
import co.edu.unal.alife.neuralfield.impl.SimpleDifferentialEquation;
import co.edu.unal.alife.output.PendulumFrame;
import co.edu.unal.alife.output.Tracer;
import co.edu.unal.alife.pendulum.S1PendulumEquation;
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
	 * coding of inputs, a group of differential Representation Populations
	 * receiving the inputs with differential dynamics, and a non-differential
	 * Action Population which evaluates the output as a centroid vector
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
			SystemEquation systemEquation) {
		// Number of varibles fedback by the system
		int noInputs = controllerParams.getInSize();
		// Number of goal to pursue. Also the number of input and rep
		// populations (one input per rep)
		int noGoals = controllerParams.getHiSize();
		// Number of outputs (or actions/forces applied to the system)
		int noOutputs = controllerParams.getOutSize();
		// Number of populations
		final int N = noGoals + noGoals + 1 + 1; // Input[]+Representation[]+Action+System

		// Population construction
		List<DeltaPopulation<Double>> populations = new ArrayList<DeltaPopulation<Double>>(
				N);
		// Creation and addition of input and representation populations
		for (int i = 0; i < noGoals; i++) {
			MapDeltaPopulation inPopulation = new MapDeltaPopulation(points,
					false);
			populations.add(inPopulation);
		}
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
		// Creation and addition of input equations
		for (int i = 0; i < noGoals; i++) {
			List<Double> subKs = controllerParams.getInParams().getSubMaps(i);
			equations.add(new S1InputEquationForPendulum(systemPopulation,
					subKs));
		}
		// Creation and addition of representation equations
		for (int i = 0; i < noGoals; i++) {
			equations.add(new SimpleDifferentialEquation());
		}
		// We configure the action equation inputs only from repPopulations
		NonDifferentialEquation actionEquation = new S1ActionEquation(
				populations.subList(noGoals, noGoals + noGoals),
				controllerParams.getOutParams().getTransformations());
		equations.add(actionEquation);
		equations.add(systemEquation);

		// Kernel matrix creation
		List<List<KernelFunction>> kernelMatrix = new ArrayList<List<KernelFunction>>(
				N);
		// Kernel construction for sys2in. i.e. null (nonDifferential eq) for each inputPop
		for (int i = 0; i < noGoals; i++) {
			List<KernelFunction> inputRow = null;
			kernelMatrix.add(inputRow);	
		}
		// We get the lists of parameters for kernel contruction: rep2rep
		List<Double> cholesky1s = controllerParams.getRepParams()
				.getCholesky1s();
		List<Double> cholesky2s = controllerParams.getRepParams()
				.getCholesky2s();
		List<Double> deltas = controllerParams.getRepParams().getDeltas();
		List<Double> hs = controllerParams.getRepParams().getHs();
		List<Double> repKs = controllerParams.getRepParams().getRepKs();
		List<Double> inKs = controllerParams.getRepParams().getInKs();
		// And for input2rep (alphas are Ks in input kernels) --> Falso
		// List<Double> inputKs = controllerParams.getInParams().getKs();
		// Addition and creation of in2rep and rep2rep kernel lists
		S1KernelParameter kernelParameter;
		for (int i = 0; i < noGoals; i++) {
			ArrayList<KernelFunction> goalKernelRow = new ArrayList<KernelFunction>();
			// Cholesky matrix contructed so that |L|=1;
			double[][] L = { { cholesky1s.get(i), 0 },
					{ cholesky2s.get(i), 1 / cholesky1s.get(i) } };
			// Input2rep kernels
			for (int j = 0; j < noGoals; j++) {
				// the i-th repPop only recieves input from the i-th inputPop
				if (i == j) {
					kernelParameter = new S1KernelParameter(L, hs.get(i),
							deltas.get(i), inKs.get(i));
					goalKernelRow.add(new S1MexicanHatMetricKernel(
							kernelParameter, points));
				} else {
					goalKernelRow.add(null);
				}
			}
			// Rep2rep kernel
			for (int j = 0; j < noGoals; j++) {
				// the i-th repPop only connects with itself (and inputs, see
				// above)
				if (i == j) {
					kernelParameter = new S1KernelParameter(L, hs.get(i),
							deltas.get(i), repKs.get(i));
					goalKernelRow.add(new S1MexicanHatMetricKernel(
							kernelParameter, points));
				} else {
					goalKernelRow.add(null);
				}
			}
			//We add a null for the kernel with the actionPopulation
			goalKernelRow.add(null);
			//and another for the kernel with the systemPopulation
			goalKernelRow.add(null);
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
		int inCard = 4;
		int repCard = 2;
		int outCard = 1;
		int N = 2*repCard + 1 +1;
		// Discrete points in fields
		int points = 20;

		double[] inMaps = { 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d };
		S1InputParameters inputParameters = new S1InputParameters(inCard,
				repCard, inMaps);

		double[] repKs = { 0.1, 0.0 };
		double[] inKs = { 2.0, 0.0 };
		double[] deltas = { 1.0, 0.1 };
		double[] hs = { 0.2, 0.1 };
		double[] cholesky1s = { 1.0, 1.0 };
		double[] cholesky2s = { 0.0, 0.0 };
		S1RepresentationParameters representationParameters = new S1RepresentationParameters(
				repCard, cholesky1s, cholesky2s, repKs, hs, deltas, inKs);

		double[] alphas = { 1.0, 0.0 };
		S1OutputParameters outputParameters = new S1OutputParameters(repCard,
				outCard, alphas);

		S1ControllerParameters parameters = new S1ControllerParameters(
				inputParameters, representationParameters, outputParameters);

		DeltaFieldFactory factory = DeltaFieldFactory.getInstance();

		// System (pendulum) population construction
		DeltaPopulation<Double> systemPopulation = new MapDeltaPopulation(4,
				false);

		// PendulumEquation Construction
		SystemEquation systemEquation = new S1PendulumEquation();

		// Field construction
		DeltaField<Double> field = factory.buildLayeredS1Field(parameters,
				points, systemPopulation, systemEquation);

//		DeltaPopulation<Double> rep1 = field.getPopulations().get(2);
//		rep1.setElementValue(1.0*points/2, 1.0);
		// Monitoring
		Tracer tracer = new Tracer(N);
		field.addObserver(tracer);

		// Run simulation
		double t0 = 0;
		double tf = 10.0;
		double h = 0.025;
		double initialAngle = 1*Math.PI / 20.0;
		double initialPos = 0.0;

		DeltaPopulation<Double> pendulum = field.getPopulations().get(N-1);
		pendulum.setElementValue(S1PendulumEquation.STATE_THETA, initialAngle);
		pendulum.setElementValue(S1PendulumEquation.STATE_X, initialPos);
		SolverUtility.simulate(t0, tf, h, field);

		double fitness = S1PendulumEquation.getFitness(tracer);
		System.out.println(fitness);
		// String[] filenames =
		// {"inputPopulation_evo","fieldPopulation_evo","pendulum_evo"}; /3d
		// String[] filenames = { null, null, null, null, "pendulum_ijcnn20102"
		// }; // 2d
		// tracer.printToFiles(filenames, true);
		// System.out.println("-- "+new Date());
		// Run animation
		new PendulumFrame(2, 1.0, 4, 5.0, 5, tracer);

	}
}
