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
import evolution.RnnPPGaitFitness;
import evolution.RnnPPGaitGenotype;
import evolution.RnnParameters;
import evolution.operators.ParametricInputMutation;
import evolution.operators.ParametricRecurrenceMutation;
import gait.InvertedPendulumGait;
import gait.RNNCGait;
import gait.RNNCOrientation;
import gait.RnnController;



/**
 * @author Juan Figueredo
 *
 */
public class InvertedPendulumGaitTest {

	public static Environment getEnvironment(int states, RnnParameters rnnParams, double initialAngle, double initialPos){	
		Fitness f = new RnnPPGaitFitness(rnnParams, initialAngle, initialPos);
		Genotype g = new RnnPPGaitGenotype(states);
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
		int inputs = 3;
		double initialAngle = 0.2;
		double initialPos = 0;
		
		double[][] Wa =
			{{0.2978972381811489, 0.9366206846368561, 1.0304436588411479, -1.0005010238090737},
			 {-1.0655532056228658, -0.3123316970229699, 0.6909249526332872, -1.1937583341906937},
			 {-0.6634871624938676, 0.296479688202929, -1.4686117688468778, 1.598518918628947},
			 {-1.4955721387954775, 0.2005394234064246, -1.7677155185222055, 0.9665036889519723}};

		double[][] Wb =
			{{-1.1927504373699986, -0.34100780064656355, 1.8590731838315233, 1.7710599044163886},
			 {0.5944676093556893, -1.553124056141122, -0.6887477198598351, -1.3220182602314297},
			 {-1.826724540142632, 1.0235313213615673, -1.105444422810157, 1.5901469741641239},
			 {-0.01573548330314667, -0.4722144544376241, 1.036182408657993, 1.9211306729305129}};
		
//		double[][] Wa = 
//	        {{ 0.8684211993659257,-0.9225310746765683, 0.17694620277789186, 1.833062391569837},
//	         {-1.9689462551484365, 0.5844140464229981, 1.047802157585688,-1.1197558461535482},
//	         {-0.9844208778631591,1.908468338982464,-0.20194207143100007,0.4066400345551151},
//	         {1.7197889790412573,-0.9942870230274723,-0.3783054079545005, 0.7405632530854107}};
//		double[][] Wb =
//			 {{-1.378636947764309,-0.07089486375103382,1.5174665951942816,0.5455487187422841},
//			  {0.3943279641588311,-0.4224388993020587,0.5337264233728174,-1.3013468606411203},
//			  {1.038520939434914,1.3825021700069389,1.5736651592145896,0.2930459580606324},
//			  {0.49059355496956014,0.7356628894946855,-1.8798396405549722,-0.3256178132777374}};
		int[][] Aa = MatrixOperator.ones(states, states);
		int[][] Ab = MatrixOperator.ones(states, inputs+1);
		int[] An = MatrixOperator.ones(states);
		RnnParameters rnnParamOrient = new RnnParameters(Aa,Ab,An,Wa,Wb,states,inputs,0.025);		
		
		Environment env = getEnvironment(states,rnnParamOrient,initialAngle,initialPos);
		Operator[] opers = getOperators(env);
		Population p = evolve( 50, env, 250, opers, new SimpleConsoleTracer() );
		Vector<Individual> individuals = p.individuals;
		RnnPPGaitFitness fitness = new RnnPPGaitFitness(rnnParamOrient,initialAngle,initialPos);
		double maxFit = -100;
		Individual bestInd = null;
		int i = 0;
		for (Iterator<Individual> iterator = individuals.iterator(); iterator.hasNext();) {
			Individual ind = iterator.next();
			double indFit = fitness.evaluate(ind);
			i++;
			if (indFit>=maxFit) {
				System.out.println(i+": "+indFit);
				maxFit=indFit;
				bestInd=ind;
			}
		}
		System.out.println("best fit: "+bestInd.getFitness());
		RnnParameters rnnParams = (RnnParameters)bestInd.getThing();
		RnnController rnnControllerOrient = new RNNCOrientation(rnnParamOrient);
		RnnController rnnControllerGait = new RNNCGait(rnnParams);
		System.out.println("Wa:\n"+MatrixOperator.toString(rnnParams.Wa));
		System.out.println("Wb:\n"+MatrixOperator.toString(rnnParams.Wb));
//		double fit = fitness.evaluate(bestInd);
		InvertedPendulumGait cartPole = new InvertedPendulumGait(rnnControllerOrient,rnnControllerGait);
		double[] x0 = {0, initialAngle, 0, 0};
		double tf = 20;
		double h = 0.04;
		double t = 0;
		double fit = cartPole.getFitness(x0, h, t, tf, true);
		System.out.println("Best Individual Fitness: "+fit);
	}
}
