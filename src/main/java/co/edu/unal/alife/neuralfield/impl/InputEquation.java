/**
 * 
 */
package co.edu.unal.alife.neuralfield.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.alife.applications.InvertedPendulum;
import co.edu.unal.alife.neuralfield.DeltaPopulation;
import co.edu.unal.alife.neuralfield.KernelFunction;
import co.edu.unal.alife.neuralfield.NeuralPopulationEquation;

/**
 * @author Juan Figueredo
 * 
 */
public class InputEquation implements NeuralPopulationEquation<Double> {

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.unal.alife.neuralfield.core.NeuralPopulationEquation#evalEquation
	 * (java.util.List, java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Double> evalEquation(List<DeltaPopulation<Double>> populations,
			int localIndex, List<KernelFunction> kernelList) {
		return null;
	}
	
	public static double bipolarSigmoid(double t) {
		return (1 - Math.exp(-t)) / (1 + Math.exp(-t));
	}
	
	public List<Double> applyInput() {
		double thetaDot = pendulum.sense();
		
		double boundedValue = bipolarSigmoid(thetaDot) * halfSize;
		long eqPosition = Math.round(boundedValue + halfSize);
		List<Double> list = new ArrayList<Double>();
		for (int i = 0; i < 2 * halfSize + 1; i++) {
			if (i == eqPosition) {
				list.add(1.0);
			} else {
				list.add(0.0);
			}
		}
		list.add(thetaDot);
		return list;
	}

}
