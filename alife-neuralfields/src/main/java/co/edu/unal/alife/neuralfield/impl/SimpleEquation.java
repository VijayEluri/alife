/**
 * 
 */
package co.edu.unal.alife.neuralfield.impl;

import java.util.List;
import java.util.Set;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.neuralfield.DeltaEquation;
import co.edu.unal.alife.neuralfield.KernelFunction;

/**
 * @author Juan Figueredo
 * 
 */
/**
 * @author Juan Figueredo
 */
public class SimpleEquation implements DeltaEquation<Double> {

	private double tao = 1;
	private double restingPotential = -0.2;

	/*
	 * (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulationEquation#evalEquation
	 * (java.util.List, java.util.List)
	 */
	@Override
	public void evalEquation(DeltaPopulation<Double> localPopulation,
			List<DeltaPopulation<Double>> populations, List<KernelFunction> kernelList) {
		Set<Double> localPositions = localPopulation.getPositions();
		// For each element (or tuple) in the local population do...
		for (Double localPosition : localPositions) {
			double localValue = localPopulation.getElementValue(localPosition);
			double totalSum = 0;
			// For each population in the field do...
			for (int i = 0; i < populations.size(); i++) {
				DeltaPopulation<Double> population = populations.get(i); 
				Set<Double> positions = population.getPositions();
				KernelFunction kernelFunction = kernelList.get(i);
				// System.out.println(i + " | " + kernelFunction);
				// Do nothing on null kernel
				if (kernelFunction != null) {
					double kernelSum = 0;
					// For each element from a given population do...
					// System.out.println("SimpleEquation - Begin Sum");
					for (Double position : positions) {
						double value = population.getElementValue(position);
						// Evaluate the Sum
						double firingRate = firingRateFunction(value);
						if (firingRate > 0.0) {
							double kernelValue = kernelFunction.evaluateKernel(localPosition, position);
							kernelSum += kernelValue * firingRate;
						}
					}
					// System.out.println("SimpleEquation - Kernel Sum: "+kernelSum);
					// Totalize the sum
					totalSum += kernelSum;
				}
			}
			// Evaluate the delta of the element using the totalSum, the
			// localValue and tao
			double delta = (-localValue + totalSum - restingPotential) / tao;
			// System.out.println("SimpleEquation - Index: "+localIndex+" Delta: "+delta);
			// set it as element delta
			localPopulation.setElementDelta(localPosition, delta);
		}
	}

	/*
	 * (non-Javadoc)
	 * @seeco.edu.unal.alife.neuralfield.NeuralPopulationEquation#inputFunction(co.edu.unal.alife.
	 * applications.InvertedPendulum, java.util.List, double, double)
	 */
	@Override
	public void applyInput(DeltaPopulation<Double> deltaPopulation) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Heaviside firing rate function
	 * 
	 * @param value
	 * @return the firing rate for the given value
	 */
	public static Double firingRateFunction(Double value) {
		return value > 0 ? 1.0 : 0.0;
	}
}
