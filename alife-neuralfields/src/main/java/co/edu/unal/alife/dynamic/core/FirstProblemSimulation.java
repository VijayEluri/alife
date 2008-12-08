/**
 * 
 */
package co.edu.unal.alife.dynamic.core;

import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import co.edu.unal.alife.dynamics.Derivable;
import co.edu.unal.alife.neuralfield.core.DeltaPopulation;
import co.edu.unal.alife.neuralfield.core.DerivableNeuralField;
import co.edu.unal.alife.neuralfield.core.KernelFunction;
import co.edu.unal.alife.neuralfield.core.MexicanHatKernel;
import co.edu.unal.alife.neuralfield.core.NeuralPopulationEquation;
import co.edu.unal.alife.neuralfield.core.SimpleEquation;
import co.edu.unal.alife.neuralfield.core.StaticEquation;
import co.edu.unal.alife.neuralfield.impl.MapNeuralPopulation;

/**
 * @author Juan Figueredo
 *
 */
public class FirstProblemSimulation implements Derivable<Double>{

	private InvertedPendulum pendulum;
	private DerivableNeuralField<Double> field;
	private List<Double> pendulumValues = new ArrayList<Double>(4);
	
	/**
	 * @param field
	 * @param pendulum
	 * @param pendulumValues
	 */
	public FirstProblemSimulation(DerivableNeuralField<Double> field,
			InvertedPendulum pendulum, List<Double> pendulumValues) {
		super();
		this.field = field;
		this.pendulum = pendulum;
		this.pendulumValues = pendulumValues;
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.dynamics.Derivable#getDeltas(java.util.List, double)
	 */
	@Override
	public List<Double> getDeltas(List<Double> values, double t) {
		List<Double> fieldDeltas = field.getDeltasAsDerivable();
	    List<Double> pendulumDeltas = pendulum.getPendulumDeltas(pendulumValues, null, t);
		List<Double> deltas = new ArrayList<Double>(fieldDeltas.size()+pendulumDeltas.size());
		deltas.addAll(fieldDeltas);
		deltas.addAll(pendulumDeltas);
		return deltas;
	}	

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.dynamics.Derivable#getDeltas(int, java.util.List, double)
	 */
	@Override
	public Double getDeltas(int arg0, List<Double> arg1, double arg2) {
		throw new NotImplementedException();
	}
	
	public static void main(String[] args) {
		int layers = 2;
		int halfSize = 10;
		InvertedPendulum pendulum = new InvertedPendulum();
		
		//Populations setup
		List<DeltaPopulation<Double>> populations = new ArrayList<DeltaPopulation<Double>>(layers);
		MapNeuralPopulation inputPopulation = new MapNeuralPopulation(halfSize);
		MapNeuralPopulation outputPopulation = new MapNeuralPopulation(halfSize);
		populations.add(inputPopulation);
		populations.add(outputPopulation);
		
		//Kernel Matrix setup
		List<List<KernelFunction>> kernelMatrix = new ArrayList<List<KernelFunction>>(layers);
		List<KernelFunction> firstRow = new ArrayList<KernelFunction>(layers);
		List<KernelFunction> secondRow = new ArrayList<KernelFunction>(layers);
		KernelFunction kernelFunction = new MexicanHatKernel();
		firstRow.set(0, kernelFunction);
		firstRow.set(1, kernelFunction);
		secondRow.set(0, kernelFunction);
		secondRow.set(1, null);
		kernelMatrix.add(firstRow);
		kernelMatrix.add(secondRow);
		
		//Equations setup
		List<NeuralPopulationEquation<Double>> equations = new ArrayList<NeuralPopulationEquation<Double>>(layers);
		StaticEquation staticEquation = new StaticEquation();
		SimpleEquation simpleEquation = new SimpleEquation();
		equations.add(staticEquation);
		equations.add(simpleEquation);
		
		
	}
}
