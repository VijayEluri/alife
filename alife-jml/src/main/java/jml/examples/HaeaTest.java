package jml.examples;

import javax.swing.JFrame;

import jml.basics.Tracer;
import jml.evolution.Environment;
import jml.evolution.EvolutionaryAlgorithm;
import jml.evolution.Fitness;
import jml.evolution.Genotype;
import jml.evolution.Operator;
import jml.evolution.Phenotype;
import jml.evolution.Population;
import jml.evolution.Selection;
import jml.evolution.Transformation;
import jml.evolution.algorithms.haea.HAEA;
import jml.evolution.binary.BinaryGenotype;
import jml.evolution.binary.VariableLengthBinaryGenotype;
import jml.evolution.real.RealVectorGenotype;
import jml.evolution.real.RealVectorLimits;
import jml.evolution.selections.Elitism;
import jml.evolution.tracer.PopulationTracer;
import jml.util.ForLoopCondition;
import jml.util.Predicate;
import jml.util.SimpleConsoleTracer;

public class HaeaTest {
	
	Genotype genotype;
	Phenotype phenotype;
	Fitness fitness;
	Environment environment;
	Operator[] operator;
	int population;
	int iterator;
	
	public HaeaTest(){
		genotype=null;
		phenotype=null;
		fitness=null;
		environment=null;
		population=1;
		iterator=1;
	}
	
	 /**
    * Singleton dessign pattern.
    */
   private static HaeaTest instance;
	
	
	/**
    * Returns the only one instance allwoed for the class.
    * @return The class instance.
    */
   public static synchronized HaeaTest getInstance() {
       if(instance == null){
           instance = new HaeaTest();
       }
       return instance;
           
   }
   
   public static void createEnvironment() {
	   
	   
   }
   
   public  void setGenotypeBit(int lenght){
 	 genotype = new BinaryGenotype( lenght );
   }
   
   public  void setGenotypeBit(int min, int max, int delta){
	   genotype = new VariableLengthBinaryGenotype(10,100,10);
   }
   
   public  void setGenotypeReal(double min[], double max[]){
	   genotype = new RealVectorGenotype(new RealVectorLimits(min,max,1,1));
   }
   
   public  void setPhenotype(){
	   phenotype = new Phenotype();
   }
   
   public  void setFitness(Fitness fit){
	   fitness = fit;
   }
   
   public Genotype getGenotype(){
	   return genotype;
   }
   
   public Phenotype getPhenotype(){
	   return phenotype;
   }
   
   public Fitness getFitness(){
	   return fitness;
   }
   
   public void setEnvironment(){
	   environment= new Environment(genotype, phenotype, fitness );
   }
   
   public Environment getEnvironment(){
	   return environment;
   }
   
   public void setOperator(Operator[] opera){
	   operator=opera;
 
   }
   
   public Operator[] getOperator(){
	   return operator;
 
   }
   
   public void setPopulator(int popu){
	   population=popu;
 
   }
   
   public void setIterator(int ite){
	   iterator=ite;
 
   }
   
   public Population run(){
	   
	   return evolve( population, environment, iterator, operator, new PopulationTracer() );
   }
   
   public Transformation getTransformation( Operator[] operators,
           Selection selection ){
return new HAEA( operators, selection );
}

public Predicate getCondition( int MAX_ITER ){
return new ForLoopCondition( MAX_ITER );
}

public EvolutionaryAlgorithm getHAEA( int POP_SIZE, Environment env,
        int MAX_ITER, Operator[] operators ){
return new EvolutionaryAlgorithm( new Population( env, POP_SIZE ),
getTransformation( operators,
      new Elitism( env, 1, false, 1.0, 0.0 ) ),
getCondition(MAX_ITER) );
}

public EvolutionaryAlgorithm getHAEA( int POP_SIZE, Environment env,
        int MAX_ITER, Operator[] operators,
        Selection selection ){
return new EvolutionaryAlgorithm( new Population( env, POP_SIZE ),
getTransformation( operators, selection ),
getCondition(MAX_ITER) );
}

public Population evolve( int POP_SIZE, Environment env,
int MAX_ITER, Operator[] operators,
Selection selection ){
return evolve( POP_SIZE, env, MAX_ITER, operators, selection, null );
}

public Population evolve(  int POP_SIZE, Environment env,
int MAX_ITER, Operator[] operators,
Tracer tracer ){
return evolve( POP_SIZE, env, MAX_ITER, operators,
new Elitism( env, 1, false, 1.0, 0.0 ), tracer );
}

public Population evolve(  int POP_SIZE, Environment env,
int MAX_ITER, Operator[] operators,
Selection selection,
Tracer tracer ){
Population p = null;
EvolutionaryAlgorithm ea = getHAEA(POP_SIZE, env, MAX_ITER,
operators, selection);
ea.addTracer( tracer );
ea.init();
//if( gui != null ) {
//ea.start();
//}else{
ea.run();
//p = ea.getPopulation();
//}
return p;
}
}
