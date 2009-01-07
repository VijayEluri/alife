/**
 * 
 */
package co.edu.unal.alife.neuralfield.impl;

import java.util.ArrayList;
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
		return (1 - Math.exp(-t)) / (1 + Math.exp(-t));
	}
	
	public void applyInput(DeltaPopulation<Double> localPopulation) {
		double thetaDot = pendulum.getElementDelta(PendulumEquation.STATE_THETA);
//		System.out.println("tethaDot\t:"+thetaDot);
		double boundedValue = bipolarSigmoid(thetaDot) * halfSize;
//		System.out.println("boundedVal\t:"+boundedValue);
		double eqPosition = Math.round(boundedValue);
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
	
	public static void main(String[] args) {
		List<Double> testVals = new ArrayList<Double>(21);
		List<Double> boundedVals = new ArrayList<Double>(21);
		double current = -Math.PI;
		double step = Math.PI/5;
		for (int i = 0; i < 11; i++) {
			testVals.add(current);
			double bipolarSigmoid = bipolarSigmoid(current);
			boundedVals.add(bipolarSigmoid);
			System.out.println(current+":"+bipolarSigmoid);
			current += step;
		}
		System.out.println(-3.141592653589793/-0.15580032922161896);
	}

}
