/**
 * 
 */
package test;

import java.util.Iterator;
import java.util.Vector;

import jml.basics.Tracer;
import jml.evolution.Environment;
import jml.evolution.EvolutionaryAlgorithm;
import jml.evolution.Fitness;
import jml.evolution.Genotype;
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
import util.MatrixOperator;
import evolution.RnnPPOrientationFitness;
import evolution.RnnPPOrientationGenotype;
import evolution.RnnParameters;
import evolution.operators.ParametricInputMutation;
import evolution.operators.ParametricRecurrenceMutation;
import gait.InvertedPendulum;
import gait.RNNCOrientation;
import gait.RnnController;



/**
 * @author Juan Figueredo
 *
 */
public class InvertedPendulumOrientationTest {

	public static Environment getEnvironment(int states, double initialValue){
		Fitness f = new RnnPPOrientationFitness(initialValue);
		Genotype g = new RnnPPOrientationGenotype(states);
//		Phenotype p = new RnnPPOrientationPhenotype((RnnPPGenotype)g);
		Phenotype p = new Phenotype();
		return new Environment( g, p, f );	
	}

	public static Operator[] getOperators( Environment env ){
		Operator[] opers;
		RealVectorLimits rvl = new RealVectorLimits(new double[]{-2},new double[]{2},1,1);
		ParametricInputMutation mutationI = new ParametricInputMutation(env, rvl, 0.3);
		ParametricRecurrenceMutation mutationR = new ParametricRecurrenceMutation(env, rvl, 0.3);
		//XOver xover = new XOver(env, new Tournament(env, 2, true, 4));
		opers = new Operator[2];
		opers[0] = mutationI;
		opers[1] = mutationR;
		return opers;
	}

	public static Transformation getTransformation( Operator[] operators,
			Selection selection ){
		return new HAEA( operators, selection );
	}
	
	public static Predicate getCondition( int MAX_ITER ){
		return new ForLoopCondition( MAX_ITER );
	}

	public static EvolutionaryAlgorithm getEA( int POP_SIZE, Environment env,
			int MAX_ITER, Operator[] operators,
			Selection selection ){
		return new EvolutionaryAlgorithm( new Population( env, POP_SIZE ),
				getTransformation( operators, selection ),
				getCondition(MAX_ITER) );
	}
	
	public static Population evolve(  int POP_SIZE, Environment env,
			int MAX_ITER, Operator[] operators,
			Tracer tracer ){
		return evolve( POP_SIZE, env, MAX_ITER, operators,
				new Elitism( env, 1, false, 0.05, 0.05 ), tracer );
	}

	public static Population evolve(  int POP_SIZE, Environment env,
			int MAX_ITER, Operator[] operators,
			Selection selection,
			Tracer tracer ){
		EvolutionaryAlgorithm ea = getEA(POP_SIZE, env, MAX_ITER,
				operators, selection);
		ea.addTracer( tracer );
		ea.init();
		ea.run();
		return ea.getPopulation();
	}

	public static void main(String[] args){
		int states = 4;
		double initialAngle = Math.PI/6;
		Environment env = getEnvironment(states,initialAngle);
		Operator[] opers = getOperators(env);
		Population p = evolve( 100, env, 500, opers, new SimpleConsoleTracer() );
		Vector<Individual> individuals = p.individuals;
		RnnPPOrientationFitness fitness = new RnnPPOrientationFitness(initialAngle);
		double maxFit = -100;
		Individual bestInd = null;
		int i = 0;
		for (Iterator<Individual> iterator = individuals.iterator(); iterator.hasNext();) {
			Individual ind = iterator.next();
			double indFit = fitness.evaluate(ind);
//			RnnController rnnController = (RnnController)ind.getThing();
//			InvertedPendulum cartPole = new InvertedPendulum(rnnController);
//			double[] x0 = {0, initialAngle, 0, 0};
//			double tf = 10;
//			double h = 0.04;
//			double t = 0;
//			double indFit = cartPole.getFitness(x0, h, t, tf, false);
			i++;
			if (indFit>=maxFit) {
				System.out.println(i+": "+indFit);
				maxFit=indFit;
				bestInd=ind;
			}
		}
		System.out.println("best fit: "+bestInd.getFitness());
		RnnParameters rnnParams = (RnnParameters)bestInd.getThing();
		RnnController rnnController = new RNNCOrientation(rnnParams);
		System.out.println("Wa:\n"+MatrixOperator.toString(rnnParams.Wa));
		System.out.println("Wb:\n"+MatrixOperator.toString(rnnParams.Wb));
//		double fit = fitness.evaluate(bestInd);
		InvertedPendulum cartPole = new InvertedPendulum(rnnController);
		double[] x0 = {0, initialAngle, 0, 0};
		double tf = 20;
		double h = 0.04;
		double t = 0;
		double fit = cartPole.getFitness(x0, h, t, tf, true);
		System.out.println("Best Individual Fitness: "+fit);
	}
}
