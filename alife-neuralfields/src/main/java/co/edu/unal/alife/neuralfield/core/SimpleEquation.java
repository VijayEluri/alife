/**
 * 
 */
package co.edu.unal.alife.neuralfield.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import co.edu.unal.alife.neuralfield.core.DeltaPopulation.Element;

/**
 * @author Juan Figueredo
 * 
 */
/**
 * @author Juan Figueredo
 * 
 */
public class SimpleEquation implements
		NeuralPopulationEquation<Integer, Double> {

	private double tao = 1;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.unal.alife.neuralfield.core.NeuralPopulationEquation#evalEquation
	 * (java.util.List, java.util.List)
	 */
	@Override
	public List<Double> evalEquation(
			List<DeltaPopulation<Integer, Double>> populations,
			Integer localIndex, List<KernelFunction> kernelList) {
		Set<Entry<Integer, Element<Double>>> localTuples = populations.get(
				localIndex).getTuples();
		List<Double> deltas = new ArrayList<Double>(localTuples.size());
		// For each element (or tuple) in the local population do...
		for (Entry<Integer, Element<Double>> localEntry : localTuples) {
			double localPos = localEntry.getKey();
			double localValue = localEntry.getValue().getValue();
			double totalSum = 0;
			// For each population in the field do...
			for (int i = 0; i < populations.size(); i++) {
				Set<Entry<Integer, Element<Double>>> tuples = populations
						.get(i).getTuples();
				KernelFunction kernelFunction = kernelList.get(i);
				double kernelSum = 0;
				// For each element from a given population do...
				for (Entry<Integer, Element<Double>> entry : tuples) {
					double pos = entry.getKey();
					double value = entry.getValue().getValue();
					// Evaluate the Sum
					double kernelValue = (Double) kernelFunction
							.evaluateKernel(localPos, pos);
					kernelSum += kernelValue * firingRateFunction(value);
				}
				// Totalize the sum
				totalSum += kernelSum;
			}
			// Evaluate the delta of the element using the totalSum, the
			// localValue and tao
			double delta = (-localValue + totalSum) / tao;
			// and add it to the list of deltas.
			deltas.add(delta);
		}
		return deltas;
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
