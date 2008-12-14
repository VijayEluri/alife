/**
 * 
 */
package co.edu.unal.alife.neuralfield.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import co.edu.unal.alife.neuralfield.DeltaPopulation;
import co.edu.unal.alife.neuralfield.KernelFunction;
import co.edu.unal.alife.neuralfield.NeuralPopulationEquation;
import co.edu.unal.alife.neuralfield.DeltaPopulation.Element;

/**
 * @author Juan Figueredo
 * 
 */
/**
 * @author Juan Figueredo
 */
public class SimpleEquation implements NeuralPopulationEquation<Double> {

	private double tao = 1;

	/*
	 * (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulationEquation#evalEquation
	 * (java.util.List, java.util.List)
	 */
	@Override
	public List<Double> evalEquation(List<DeltaPopulation<Double>> populations, int localIndex,
			List<KernelFunction> kernelList) {
		Set<Entry<Double, Element>> localTuples = populations.get(localIndex).getTuples();
		List<Double> deltas = new ArrayList<Double>(localTuples.size());
		// For each element (or tuple) in the local population do...
		for (Entry<Double, Element> localEntry : localTuples) {
			double localPos = localEntry.getKey();
			double localValue = localEntry.getValue().getValue();
			double totalSum = 0;
			// For each population in the field do...
			for (int i = 0; i < populations.size(); i++) {
				Set<Entry<Double, Element>> tuples = populations.get(i).getTuples();
				KernelFunction kernelFunction = kernelList.get(i);
//				System.out.println(i + " | " + kernelFunction);
				// Do nothing on null kernel
				if (kernelFunction != null) {
					double kernelSum = 0;
					// For each element from a given population do...
					for (Entry<Double, Element> entry : tuples) {
						double pos = entry.getKey();
						double value = entry.getValue().getValue();
						// Evaluate the Sum
						double kernelValue = kernelFunction.evaluateKernel(localPos, pos);
						kernelSum += kernelValue * firingRateFunction(value);
					}
					// Totalize the sum
					totalSum += kernelSum;
				}
			}
			// Evaluate the delta of the element using the totalSum, the
			// localValue and tao
			double delta = (-localValue + totalSum) / tao;
			// and add it to the list of deltas.
			deltas.add(delta);
		}
		return deltas;
	}
	

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.NeuralPopulationEquation#inputFunction(co.edu.unal.alife.applications.InvertedPendulum, java.util.List, double, double)
	 */
	@Override
	public List<Double> applyInput() {
		throw new NotImplementedException();
	}



	/**
	 * Heaviside firing rate function
	 * 
	 * @param value
	 * @return the firing rate for the given value
	 */
	public static Double firingRateFunction(Double value) {
		return value > 0 ? value : 0;
	}
}
