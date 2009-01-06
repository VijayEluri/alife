/**
 * 
 */
package co.edu.unal.alife.neuralfield.impl;

import java.util.List;
import java.util.Set;

import co.edu.unal.alife.applications.PendulumEquation;
import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.neuralfield.DeltaEquation;
import co.edu.unal.alife.neuralfield.KernelFunction;

/**
 * @author Juan Figueredo
 * 
 */
public class InputEquation implements DeltaEquation<Double> {

	private DeltaPopulation<Double> pendulum;
	private double halfSize;

	/**
	 * @param halfSize
	 * @param pendulum
	 */
	public InputEquation(double halfSize, DeltaPopulation<Double> pendulum) {
		super();
		this.halfSize = halfSize;
		this.pendulum = pendulum;
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.DeltaPopulationEquation#evalEquation(co.edu.unal.alife.neuralfield.DeltaPopulation, java.util.List, java.util.List)
	 */
	@Override
	public void evalEquation(DeltaPopulation<Double> localPopulation,
			List<DeltaPopulation<Double>> populations, List<KernelFunction> kernelList) {
		throw new UnsupportedOperationException();
	}
	
	public static double bipolarSigmoid(double t) {
		return (1 - Math.exp(-t/10)) / (1 + Math.exp(-t/10));
	}
	
	public void applyInput(DeltaPopulation<Double> localPopulation) {
		double thetaDot = pendulum.getElementDelta(PendulumEquation.STATE_THETA);
//		System.out.println("tethaDot\t:"+thetaDot);
		double boundedValue = bipolarSigmoid(thetaDot) * 2 * halfSize;
//		System.out.println("boundedVal\t:"+boundedValue);
		double eqPosition = Math.round(boundedValue - halfSize);
//		System.out.println("eqPosition\t:"+eqPosition);
		Set<Double> positions = localPopulation.getPositions();
		for (Double position : positions) {
			if(position.equals(eqPosition)) {
				localPopulation.setElementValue(position, 1.0);
			} else {
				localPopulation.setElementValue(position, 0.0);
			}
		}
	}

}
