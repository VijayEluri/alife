package mts.test;

import java.util.Arrays;
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
import jml.evolution.real.RealVectorGenotype;
import jml.evolution.real.RealVectorLimits;
import jml.evolution.real.operators.GaussianMutation;
import jml.evolution.real.operators.SimpleXOver;
import jml.evolution.selections.Elitism;
import jml.evolution.selections.Tournament;
import jml.util.ForLoopCondition;
import jml.util.Predicate;
import mts.evolution.MTSFitness;
import mts.evolution.UselessTracer;

public class EATest {
	
	static final int MaxFreq = 20;
	
	public static Environment getEnvironment(){
	    Fitness f = new MTSFitness();
	    double[] min = {0,0,0,0};
	    double[] max = {MaxFreq,MaxFreq,MaxFreq,MaxFreq};
	    RealVectorLimits lims = new RealVectorLimits(min,max,4,4);
	    Genotype g = new RealVectorGenotype(lims);
	    Phenotype p = new Phenotype();
	    return new Environment( g, p, f );
	  }

	  public static Operator[] getOperators( Environment env ){
		double[] min = {0,0,0,0};
		double[] max = {MaxFreq,MaxFreq,MaxFreq,MaxFreq};
		RealVectorLimits lims = new RealVectorLimits(min,max,4,4);
		Operator[] opers;
	    SimpleXOver xover = new SimpleXOver(env, new Tournament(env, 2, true, 4));
	    GaussianMutation mutation = new GaussianMutation(env,lims,0.5);
	    opers = new Operator[2];
	    opers[0] = mutation;
	    opers[1] = xover;
	    return opers;
	  }

	  public static Transformation getTransformation( Operator[] operators,
	                                                  Selection selection ){
	    return new HAEA( operators, selection );
	  }

	  public static Predicate getCondition( int MAX_ITER ){
	    return new ForLoopCondition( MAX_ITER );
	  }

	  public static EvolutionaryAlgorithm getHAEA( int POP_SIZE, Environment env,
	                                               int MAX_ITER, Operator[] operators ){
	    return new EvolutionaryAlgorithm( new Population( env, POP_SIZE ),
	                                      getTransformation( operators,
	                                             new Elitism( env, 1, false, 1.0, 0.0 ) ),
	                                      getCondition(MAX_ITER) );
	  }

	  public static EvolutionaryAlgorithm getHAEA( int POP_SIZE, Environment env,
	                                               int MAX_ITER, Operator[] operators,
	                                               Selection selection ){
	    return new EvolutionaryAlgorithm( new Population( env, POP_SIZE ),
	                                      getTransformation( operators, selection ),
	                                      getCondition(MAX_ITER) );
	  }

	  public static Population evolve( int POP_SIZE, Environment env,
	                                   int MAX_ITER, Operator[] operators,
	                                   Selection selection ){
	    return evolve( POP_SIZE, env, MAX_ITER, operators, selection, null );
	  }

	  public static Population evolve(  int POP_SIZE, Environment env,
	                                    int MAX_ITER, Operator[] operators,
	                                    Tracer tracer ){
	    return evolve( POP_SIZE, env, MAX_ITER, operators,
	                   new Elitism( env, 1, false, 0.01, 0.01 ), tracer );
	  }

	  public static Population evolve(  int POP_SIZE, Environment env,
	                                    int MAX_ITER, Operator[] operators,
	                                    Selection selection,
	                                    Tracer tracer ){
	    Population p = null;
	    EvolutionaryAlgorithm ea = getHAEA(POP_SIZE, env, MAX_ITER,
	                                       operators, selection);
	    ea.addTracer( tracer );
	    ea.init();
//	    if( gui != null ) {
//	      ea.start();
//	    }else{
	      ea.run();
//	      p = ea.getPopulation();
//	    }
	    p = ea.getPopulation();
	    return p;
	  }

	  public static void main( String[] argv ){
	    Environment env = getEnvironment();
	    Operator[] opers = getOperators(env);
	for (int i = 0; i < 100; i++) {
		// GenomeLimits limits;
		//FileAndConsoleTracer tracer = new FileAndConsoleTracer("bestFit.out");
		UselessTracer tracer = new UselessTracer();
		Population p = evolve(100, env, 100, opers, tracer);
		//tracer.close();
		Vector<Individual> inds = p.individuals;
		double bestFit = -100000000;
		int index = -1;
		int tempIndex = -1;
		MTSFitness fitness = new MTSFitness();
		for (Iterator iter = inds.iterator(); iter.hasNext();) {
			tempIndex++;
			double tempFit = 0;
			Individual element = (Individual) iter.next();
			double[] F = (double[]) element.getThing();
			tempFit = fitness.eval(F);
			if (tempFit >= bestFit) {
				bestFit = tempFit;
				index = tempIndex;
			}
			//System.out.println(Arrays.toString(F));
		}
		double[] F = (double[]) inds.get(index).getThing();
		System.out.println(Arrays.toString(F)+" "+bestFit+";");
		if(i==99)
			fitness.stats(F);
	}	
    }  
}
