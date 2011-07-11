package co.edu.unal.alife.dynamics.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import co.edu.unal.alife.dynamics.AbstractFixedStepSolver;
import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.neuralfield.AbstractDeltaField;

/**
 * @author Juan Figueredo
 */
public class RungeKutta4thSolver extends AbstractFixedStepSolver {
	
	public RungeKutta4thSolver() {
		super();
	}
	
	/*
	 * (non-Javadoc)
	 * @see
	 * co.edu.unal.alife.dynamics.Solver#step(co.edu.unal.alife.neuralfield.
	 * DeltaField, int)
	 */
	@Override
	public DeltaPopulation step(AbstractDeltaField field, int localIndex, double h)
			throws UnsupportedOperationException {
		DeltaPopulation population = field.getPopulations().get(localIndex);
		int size = population.getSize();
		Set<Double> positions = population.getPositions();
		DeltaPopulation workingPopulation = population.cloneEmpty();
		Map<Double, Double> k1dh = new LinkedHashMap<Double, Double>(size);
		Map<Double, Double> k2dh = new LinkedHashMap<Double, Double>(size);
		Map<Double, Double> k3dh = new LinkedHashMap<Double, Double>(size);
		
		field.updatePopulationDelta(population, localIndex);
		for (Double position : positions) {
			double originalValue = population.getElementValue(position);
			double originalDelta = population.getElementDelta(position);
			k1dh.put(position, originalDelta);
			double newValue = originalValue + h / 2 * originalDelta;
			workingPopulation.setElementValue(position, newValue);
		}
		
		field.updatePopulationDelta(workingPopulation, localIndex);
		for (Double position : positions) {
			double originalValue = population.getElementValue(position);
			double workingDelta = workingPopulation.getElementDelta(position);
			k2dh.put(position, workingDelta);
			double newValue = originalValue + h / 2 * workingDelta;
			workingPopulation.setElementValue(position, newValue);
		}
		
		field.updatePopulationDelta(workingPopulation, localIndex);
		for (Double position : positions) {
			double originalValue = population.getElementValue(position);
			double workingDelta = workingPopulation.getElementDelta(position);
			k3dh.put(position, workingDelta);
			double newValue = originalValue + h * workingDelta;
			workingPopulation.setElementValue(position, newValue);
		}
		
		field.updatePopulationDelta(workingPopulation, localIndex);
		for (Double position : positions) {
			double originalValue = population.getElementValue(position);
			double workingDelta = workingPopulation.getElementDelta(position);
			double newValue = originalValue
					+ (h / 6)
					* (k1dh.get(position) + 2 * k2dh.get(position) + 2
							* k3dh.get(position) + workingDelta);
			// System.out.println("RK4Solver: localIndex="+localIndex+" position="+position+" originalValue="+originalValue+" delta="+(newValue-originalValue));
			workingPopulation.setElementValue(position, newValue);
		}
		population.setNextPopulation(workingPopulation);
		return workingPopulation;
	}
	
}