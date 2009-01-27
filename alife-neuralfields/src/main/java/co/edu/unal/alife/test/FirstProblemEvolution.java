/**
 * 
 */
package co.edu.unal.alife.test;

import java.util.Iterator;
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
import jml.evolution.real.RealVectorLimits;
import jml.evolution.selections.Elitism;
import jml.util.ForLoopCondition;
import jml.util.Predicate;
import jml.util.SimpleConsoleTracer;
import co.edu.unal.alife.dynamics.AbstractSolver;
import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.evolution.PendulumControllerGenotype;
import co.edu.unal.alife.evolution.PendulumControllerInputMutation;
import co.edu.unal.alife.evolution.PendulumControllerMutation;
import co.edu.unal.alife.evolution.PendulumControllerParameters;
import co.edu.unal.alife.evolution.PendulumControllerPhenotype;
import co.edu.unal.alife.evolution.PendulumControllerProcessingMutation;
import co.edu.unal.alife.neuralfield.DeltaField;
import co.edu.unal.alife.output.PendulumFrame;
import co.edu.unal.alife.pendulum.PendulumController;
import co.edu.unal.alife.pendulum.PendulumControllerFitness;
import co.edu.unal.alife.pendulum.PendulumEquation;

/**
 * @author Juan Figueredo
 */
public class FirstProblemEvolution {
	public static Environment getEnvironment(int halfSize) {
		Fitness f = new PendulumControllerFitness();
		PendulumControllerGenotype g = new PendulumControllerGenotype(halfSize);
		Phenotype p = new PendulumControllerPhenotype(g,halfSize);
		return new Environment(g, p, f);
	}

	public static Operator[] getOperators(Environment env) {
		Operator[] opers;
		RealVectorLimits rvl = new RealVectorLimits(new double[] { -0.4 }, new double[] { 0.8 }, 1, 1);
		PendulumControllerMutation mutationI = new PendulumControllerInputMutation(env, rvl, 0.3);
		PendulumControllerMutation mutationP = new PendulumControllerProcessingMutation(env, rvl, 0.3);
		// XOver xover = new XOver(env, new Tournament(env, 2, true, 4));
		opers = new Operator[2];
		opers[0] = mutationI;
		opers[1] = mutationP;
		return opers;
	}

	public static Transformation getTransformation(Operator[] operators, Selection selection) {
		return new HAEA(operators, selection);
	}

	public static Predicate getCondition(int MAX_ITER) {
		return new ForLoopCondition(MAX_ITER);
	}

	public static EvolutionaryAlgorithm getEA(int POP_SIZE, Environment env, int MAX_ITER,
			Operator[] operators, Selection selection) {
		return new EvolutionaryAlgorithm(new Population(env, POP_SIZE), getTransformation(
				operators, selection), getCondition(MAX_ITER));
	}

	public static Population evolve(int POP_SIZE, Environment env, int MAX_ITER,
			Operator[] operators, Tracer tracer) {
		return evolve(POP_SIZE, env, MAX_ITER, operators, new Elitism(env, 1, false, 0.05, 0.05),
				tracer);
	}

	public static Population evolve(int POP_SIZE, Environment env, int MAX_ITER,
			Operator[] operators, Selection selection, Tracer tracer) {
		EvolutionaryAlgorithm ea = getEA(POP_SIZE, env, MAX_ITER, operators, selection);
		ea.addTracer(tracer);
		ea.init();
		ea.run();
		return ea.getPopulation();
	}

	public static void main(String[] args) {
		int size = 10;
		Environment env = getEnvironment(size);
		Operator[] opers = getOperators(env);
		Population p = evolve(1, env, 100, opers, new SimpleConsoleTracer());
		Vector<Individual> individuals = p.individuals;
		PendulumControllerFitness fitness = (PendulumControllerFitness)env.getFitness();
		double maxFit = -100;
		Individual bestInd = null;
		int i = 0;
		for (Iterator<Individual> iterator = individuals.iterator(); iterator.hasNext();) {
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
		System.out.println("best fit: " + bestInd.getFitness());
		PendulumControllerParameters params = (PendulumControllerParameters)bestInd.getGenome();
		PendulumController controller = new PendulumController(size,params);
//		System.out.println("I:\n" );
//		System.out.println("P:\n" );
		// double fit = fitness.evaluate(bestInd);
		
		double initialAngle = Math.PI/6;
		double initialPos = -5.0;
		DeltaField<Double> field = controller.getField();
		DeltaPopulation<Double> pendulumPopulation = field.getPopulations().get(2);
		pendulumPopulation.setElementValue(PendulumEquation.STATE_THETA, initialAngle);
		pendulumPopulation.setElementValue(PendulumEquation.STATE_X, initialPos);
		
		co.edu.unal.alife.output.Tracer tracer = new co.edu.unal.alife.output.Tracer(4);
		//field.addObserver(printer);
		field.addObserver(tracer);

		// Run simulation
		double t0 = 0;
		double tf = 10;
		double h = 0.04;
		AbstractSolver.simulate(t0, tf, h, field);
		System.out.println(bestInd.getGenome());
		// Run animation
		new PendulumFrame(tracer);
	}

}
