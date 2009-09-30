package test.evolution;

import jml.basics.Tracer;
import jml.evolution.Environment;
import jml.evolution.EvolutionaryAlgorithm;
import jml.evolution.Fitness;
import jml.evolution.GenomeLimits;
import jml.evolution.Genotype;
import jml.evolution.Operator;
import jml.evolution.Phenotype;
import jml.evolution.Population;
import jml.evolution.Selection;
import jml.evolution.Transformation;
import jml.evolution.algorithms.haea.HAEA;
import jml.evolution.binary.BinaryGenotype;
import jml.evolution.binary.VariableLengthBinaryGenotype;
import jml.evolution.binary.fitness.BoundedlyDeceptive;
import jml.evolution.binary.operators.AddGen;
import jml.evolution.binary.operators.DelGen;
import jml.evolution.binary.operators.Mutation;
import jml.evolution.binary.operators.Transposition;
import jml.evolution.binary.operators.XOver;
import jml.evolution.real.RealVectorGenotype;
import jml.evolution.real.RealVectorLimits;
import jml.evolution.real.fitness.Rastrigin;
import jml.evolution.real.operators.AddComponent;
import jml.evolution.real.operators.DelComponent;
import jml.evolution.real.operators.FlipMutation;
import jml.evolution.real.operators.LinearXOver;
import jml.evolution.real.operators.UniformMutation;
import jml.evolution.selections.Elitism;
import jml.evolution.selections.Tournament;
import jml.evolution.tracer.PopulationTracer;
import jml.util.ForLoopCondition;
import jml.util.Predicate;
import jml.util.SimpleConsoleTracer;

/**
 * <p>Title: EATest</p>
 * <p>Description: Test of Ea</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Universidad Nacional de Colombia - The University of Memphis</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class EATest {
	public static Environment getEnvironment(){
//	    Fitness f = new MaxOnes();
//	    Genotype g = new BinaryGenotype( 100 );
//	    Fitness f = new RoyalRoad(8);
//	    Genotype g = new BinaryGenotype( 64 );
	    Fitness f = new BoundedlyDeceptive();
	    
	    Genotype g = new VariableLengthBinaryGenotype(10,100,10);
	   // Genotype g = new BinaryGenotype( 40 );
	    Phenotype p = new Phenotype();
	    return new Environment( g, p, f );
	  }

	  public static Operator[] getOperators( Environment env ){
	    Operator[] opers;
	    XOver xover = new XOver(env, new Tournament(env, 2, true, 4));
	    Mutation mutation = new Mutation(env);
	    Transposition transpose = new Transposition(env);
	    opers = new Operator[3];
	    opers[0] = mutation;
	    opers[1] = xover;
	    opers[2] = transpose;
//	    opers = new Operator[2];
//	    opers[0] = mutation;
//	    opers[1] = xover;
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
	                   new Elitism( env, 1, false, 1.0, 0.0 ), tracer );
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
	    return p;
	  }

	  public static void main( String[] argv ){
	    Environment env = getEnvironment();
	    Operator[] opers = getOperators(env);
	   // GenomeLimits limits;
	    Population p = evolve( 100, env, 100, opers, new SimpleConsoleTracer() );
	  }
}
