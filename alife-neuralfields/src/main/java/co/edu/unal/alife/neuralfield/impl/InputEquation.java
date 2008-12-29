/**
 * 
 */
package co.edu.unal.alife.neuralfield.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.alife.applications.InvertedPendulum;
import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.neuralfield.KernelFunction;
import co.edu.unal.alife.neuralfield.DeltaPopulationEquation;

/**
 * @author Juan Figueredo
 * 
 */
public class InputEquation implements DeltaPopulationEquation<Double> {

	private InvertedPendulum pendulum;
	private double halfSize;

	/**
	 * @param halfSize
	 * @param pendulum
	 */
	public InputEquation(double halfSize, InvertedPendulum pendulum) {
		super();
		this.halfSize = halfSize;
		this.pendulum = pendulum;
	}

	
	
	/* (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.DeltaPopulationEquation#evalEquation(co.edu.unal.alife.neuralfield.DeltaPopulation, java.util.List, java.util.List)
	 */
	@Override
	public List<Double> evalEquation(DeltaPopulation<Double> localPopulation,
			List<DeltaPopulation<Double>> populations, List<KernelFunction> kernelList) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static double bipolarSigmoid(double t) {
		return (1 - Math.exp(-t/10)) / (1 + Math.exp(-t/10));
	}
	
	public List<Double> applyInput() {
		double thetaDot = pendulum.sense();
//		System.out.println("tethaDot\t:"+thetaDot);
		double boundedValue = bipolarSigmoid(thetaDot) * halfSize;
//		System.out.println("boundedVal\t:"+boundedValue);
		long eqPosition = Math.round(boundedValue + halfSize);
//		System.out.println("eqPosition\t:"+eqPosition);
		List<Double> list = new ArrayList<Double>();
		for (int i = 0; i < 2 * halfSize + 1; i++) {
			if (i == eqPosition) {
				list.add(1.0);
			} else {
				list.add(0.0);
			}
		}
//		System.out.println("list\t:"+list);
		return list;
	}

}
