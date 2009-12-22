package util;

import java.util.Enumeration;
import java.util.Vector;

import jml.basics.Cloner;
import jml.evolution.Environment;
import jml.evolution.Individual;
import jml.evolution.Operator;
import jml.evolution.Population;
import jml.evolution.Selection;
import jml.evolution.Transformation;

public class SimpleEA extends Transformation {

	private Selection selection;
	private Operator[] operators;
	private double[] rates;
	

	public SimpleEA(Operator[] oper, Selection sel, double[] rates) {
		this.selection = sel;
		this.operators = oper;
		this.rates = rates;
	}

	@Override
	public Population apply(Population population){
		Population newPopulation;
		Environment env = population.getEnvironment();
	    population.evalFitness();
		int populationSize = population.size();
		Vector newPopulationVector = new Vector();
		for(int i=0;i<populationSize;i++){
			Vector populationGenerated;
			for (int j = 0; j < operators.length; j++) {
				Operator o = operators[j];
				if(Math.random() < rates[j]){
					populationGenerated = o.apply( population, i );
					Enumeration iter = populationGenerated.elements();
			        while( iter.hasMoreElements() ){
			          ((Individual)iter.nextElement()).evalFitness(env);
			        }
					Individual parent = population.get(i);
			        Individual par = (Individual)Cloner.clone(parent);
			        par.setThing(parent.getThing());
					populationGenerated.add(0, par);
					Population p = new Population( env, populationGenerated );		
					populationGenerated = selection.choose( p );
					
					double pf = parent.getFitness();
			        iter = populationGenerated.elements();
			        while( iter.hasMoreElements() ){
			          Individual child = (Individual)iter.nextElement();
			          double f = child.getFitness();
			          if( pf < child.getFitness() ){
			          }else{
			            newPopulationVector.add(child);
			          }
				    }
			    }
		    }
		}
		return new Population(population.getEnvironment(),newPopulationVector);
	}
}
