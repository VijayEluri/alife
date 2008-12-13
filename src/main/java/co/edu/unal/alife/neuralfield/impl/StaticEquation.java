/**
 * 
 */
package co.edu.unal.alife.neuralfield.impl;

import java.util.List;

import co.edu.unal.alife.neuralfield.DeltaPopulation;
import co.edu.unal.alife.neuralfield.KernelFunction;
import co.edu.unal.alife.neuralfield.NeuralPopulationEquation;

/**
 * @author Juan Figueredo
 * 
 */
public class StaticEquation implements NeuralPopulationEquation<Double> {

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

}
