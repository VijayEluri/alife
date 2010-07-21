/**
 * 
 */
package co.edu.unal.alife.test;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import jml.basics.Tracer;
import jml.evolution.Environment;
import jml.evolution.EvolutionaryAlgorithm;
import jml.evolution.Fitness;
import jml.evolution.Individual;
import jml.evolution.Operator;
import jml.evolution.Phenotype;
import jml.evolution.Population;
import jml.evolution.Selection;
import jml.evolution.Transformation;
import jml.evolution.algorithms.haea.HAEA;
import jml.evolution.selections.Elitism;
import jml.util.ForLoopCondition;
import jml.util.Predicate;
import jml.util.SimpleConsoleTracer;
import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.evolution.impl.S1ControllerGenotype;
import co.edu.unal.alife.evolution.impl.S1ControllerPhenotypeForPendulum;
import co.edu.unal.alife.evolution.impl.operator.S1RepresentationKernelMutation;
import co.edu.unal.alife.evolution.param.S1ControllerParameters;
import co.edu.unal.alife.neuralfield.AbstractDeltaField;
import co.edu.unal.alife.neuralfield.impl.MapDeltaPopulation;
import co.edu.unal.alife.output.PendulumFrame;
import co.edu.unal.alife.pendulum.PendulumEquation;
import co.edu.unal.alife.pendulum.S1PendulumControllerFitness;

/**
 * @author Juan Figueredo
 */
public class CircleFieldsEvolution {

	static final int _points = 20;
	static final int _noInputs = 4;
	static final int _noGoals = 2;
	static final int _noOutputs = 1;
	static final int _N = _noGoals + _noGoals + 1 + 1;

	static final double _minCholeskyVal = 0.2;
	static final double _maxCholeskyVal = 5.0;
	static final double _minKVal = 0.5;
	static final double _maxKVal = 2;
	static final double _minTVal = 0;
	static final double _maxTVal = 0;
	static final double _minAlphaVal = 0;
	static final double _maxAlphaVal = 1;

	static double[] _Chol1 = { 1, 1 };
	static double[] _Chol2 = { 0, 0 };
	static double[] _maps = { 0d, 1.0d, 0d, 0.0d, 0.8d, 0.0d, 0.2d, 0.0d };
	static double[] _alphas = { 1.0, 0.0 };
	static double _minKernelRepK = 0.0d;
	static double _maxKernelRepK = 1.0d;
	static double _minKernelInK = 2.0d;
	static double _maxKernelInK = 5.0d;
	static double _minKernelDelta = 0.1d;
	static double _maxKernelDelta = 1.0d;
	static Integer _affectedRecurrentPop = 0;

	public static Environment getEnvironment(S1ControllerGenotype g, int points) {
		Fitness f = new S1PendulumControllerFitness(_N);
		Phenotype p = new S1ControllerPhenotypeForPendulum(points,
				new MapDeltaPopulation());
		return new Environment(g, p, f);
	}

	public static Operator[] getOperators(Environment env) {
		Operator[] opers;

		double sigmaRepKs = 0.1;
		double sigmaDeltas = 0.1;
		double sigmaInKs = 0.3;
		double sigmaRepKs2 = 0.3;
		double sigmaDeltas2 = 0.3;
		double sigmaInKs2 = 0.9;

		S1RepresentationKernelMutation mutation = new S1RepresentationKernelMutation(
				env, sigmaDeltas, sigmaRepKs, sigmaInKs, _affectedRecurrentPop);
		S1RepresentationKernelMutation mutation2 = new S1RepresentationKernelMutation(
				env, sigmaDeltas2, sigmaRepKs2, sigmaInKs2,
				_affectedRecurrentPop);

		// S1InputKsMutation ksMutation = new S1InputKsMutation(env, rvlKs,
		// sigmaKs);
		// S1RepresentationCholeskyMutation choleskyMutation = new
		// S1RepresentationCholeskyMutation(
		// env, rvlChol, sigmaChol);
		// S1OutputAlphaMutation alphaMutation = new S1OutputAlphaMutation(env,
		// rvlAlpha, sigmaAlpha);
		opers = new Operator[2];
		opers[0] = mutation;
		opers[1] = mutation2;
		// opers[0] = ksMutation;
		// opers[1] = choleskyMutation;
		// opers[2] = alphaMutation;
		return opers;
	}

	public static Transformation getTransformation(Operator[] operators,
			Selection selection) {
		return new HAEA(operators, selection);
	}

	public static Predicate getCondition(int MAX_ITER) {
		return new ForLoopCondition(MAX_ITER);
	}

	public static EvolutionaryAlgorithm getEA(int POP_SIZE, Environment env,
			int MAX_ITER, Operator[] operators, Selection selection) {
		return new EvolutionaryAlgorithm(new Population(env, POP_SIZE),
				getTransformation(operators, selection), getCondition(MAX_ITER));
	}

	public static Population evolve(int POP_SIZE, Environment env,
			int MAX_ITER, Operator[] operators, Tracer tracer) {
		return evolve(POP_SIZE, env, MAX_ITER, operators, new Elitism(env, 1,
				false, 0.05, 0.05), tracer);
	}

	public static Population evolve(int POP_SIZE, Environment env,
			int MAX_ITER, Operator[] operators, Selection selection,
			Tracer tracer) {
		EvolutionaryAlgorithm ea = getEA(POP_SIZE, env, MAX_ITER, operators,
				selection);
		ea.addTracer(tracer);
		ea.init();
		ea.run();
		return ea.getPopulation();
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		System.out.println("-- " + new Date());
		S1ControllerGenotype genotype = new S1ControllerGenotype(_points,
				_noInputs, _noGoals, _noOutputs, _Chol1, _Chol2, _maps,
				_alphas, _minKernelRepK, _maxKernelRepK, _minKernelDelta,
				_maxKernelDelta, _minKernelInK, _maxKernelInK);
		Environment env = getEnvironment(genotype, _points);
		// TODO:Continuar
		Operator[] opers = getOperators(env);
		int popsize = 100;
		int maxiter = 300;
		System.out.println("-- " + "Population Size: " + popsize);
		System.out.println("-- " + "Max iterations: " + maxiter);
		Population p = evolve(popsize, env, maxiter, opers,
				new SimpleConsoleTracer());
		Vector<Individual> individuals = p.individuals;
		S1PendulumControllerFitness fitness = (S1PendulumControllerFitness) env
				.getFitness();
		double maxFit = -100;
		Individual bestInd = null;
		int i = 0;
		for (Iterator<Individual> iterator = individuals.iterator(); iterator
				.hasNext();) {
			Individual ind = iterator.next();
			double indFit = fitness.evaluate(ind);
			// RnnController rnnController = (RnnController)ind.getThing();
			// InvertedPendulum cartPole = new InvertedPendulum(rnnController);
			// double[] x0 = {0, initialAngle, 0, 0};
			// double tf = 10;
			// double h = 0.04;
			// double t = 0;
			// double indFit = cartPole.getFitness(x0, h, t, tf, false);
			i++;
			if (indFit >= maxFit) {
				System.out.println(i + ": " + indFit);
				maxFit = indFit;
				bestInd = ind;
			}
		}
		System.out.println("best fit: " + maxFit);
		S1ControllerParameters params = (S1ControllerParameters) bestInd
				.getGenome();
		System.out.println(params);
		// System.out.println("I:\n" );
		// System.out.println("P:\n" );
		// double fit = fitness.evaluate(bestInd);

		AbstractDeltaField field = (AbstractDeltaField) bestInd
				.getThing();
		// Object genome = bestInd.getGenome();
		// Setup initial values
		double initialAngle = 0 * Math.PI / 10;
		double initialPos = 0.0;
		List<DeltaPopulation> pops = field.getPopulations();
		DeltaPopulation pendulumPopulation = pops.get(pops.size() - 1);
		pendulumPopulation.setElementValue(PendulumEquation.STATE_THETA,
				initialAngle);
		pendulumPopulation
				.setElementValue(PendulumEquation.STATE_X, initialPos);
		co.edu.unal.alife.output.Tracer tracer = new co.edu.unal.alife.output.Tracer(
				_N);

		// field.addObserver(printer);
		field.addObserver(tracer);

		// Run simulation
		double t0 = 0;
		double tf = 5;
		double h = 0.05;
		field.getSolver().simulate(t0, tf, h, field);

		// String[] filenames =
		// {"inputPopulation_evo","fieldPopulation_evo","pendulum_evo"}; /3d
		// String[] filenames = { null, null, null, null, "pendulum_ijcnn20102"
		// }; // 2d
		// tracer.printToFiles(filenames, true);
		System.out.println("-- " + new Date());
		// Run animation
		new PendulumFrame(0, 5.0, 4, 2.0, 5, tracer);
	}

}
