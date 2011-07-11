/**
 * 
 */
package co.edu.unal.alife.neuralfield.impl;

import java.util.List;
import java.util.Set;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.neuralfield.AbstractDifferentialEquation;
import co.edu.unal.alife.neuralfield.AbstractKernelFunction;

/**
 * @author Juan Figueredo
 * 
 */
/**
 * @author Juan Figueredo
 */
public class SimpleDifferentialEquation extends AbstractDifferentialEquation {

	private double tao = 0.1;
	private double restingPotential = -0.2;
//	private double minValue = -3.0;
//	private double maxValue = 3.0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.unal.alife.neuralfield.impl.DifferentialEquation#evalEquation(
	 * co.edu.unal.alife.dynamics.DeltaPopulation, java.util.List,
	 * java.util.List)
	 */
	@Override
	public void evalEquation(DeltaPopulation localPopulation,
			List<DeltaPopulation> populations,
			List<AbstractKernelFunction> kernelList) {
		Set<Double> localPositions = localPopulation.getPositions();
		// For each element (or tuple) in the local population do...
		for (Double localPosition : localPositions) {
			double localValue = localPopulation.getElementValue(localPosition);
			double totalSum = 0;
			// For each population in the field do...
			for (int i = 0; i < populations.size(); i++) {
				DeltaPopulation population = populations.get(i);
				Set<Double> positions = population.getPositions();
				// System.out.println("In population:"+i);
				AbstractKernelFunction kernelFunction = kernelList.get(i);
				// System.out.println(i + " | " + kernelFunction);
				// Do nothing on null kernel
				if (kernelFunction != null) {
					// System.out.println("SimpleDifferentialEquation.evalEquation: i:"+i);
					double kernelSum = 0;
					// For each element from a given population do...
					// System.out.println("SimpleEquation - Begin Sum");
					for (Double position : positions) {
						double value = population.getElementValue(position);
						// Evaluate the Sum
						double firingRate = heavisideFunction(value);
						if (firingRate > 0.0) {
							double kernelValue = kernelFunction
									.evaluateTransformation(localPosition,
											position);
							kernelSum += kernelValue * firingRate;
						}
					}
					// System.out.println("SimpleEquation - Kernel Sum: "+kernelSum);
					// Totalize the sum
					totalSum += kernelSum;
				}
			}
			totalSum = totalSum / localPopulation.getSize();
			// Evaluate the delta of the element using the totalSum, the
			// localValue and tao
			double delta = (-localValue + totalSum + restingPotential) / tao;
//			if (delta > 0 && localValue > maxValue) {
//				delta = 0;
//			} else if (delta < 0 && localValue < minValue) {
//				delta = 0;
//			}
			// System.out.println("SimpleEquation - Index: "+localPosition+" Delta: "+delta);
			// System.out.println("localValue:"+localValue+" totalSum:"+totalSum);
			// set it as element delta
			localPopulation.setElementDelta(localPosition, delta);
		}
	}
}
