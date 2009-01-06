package co.edu.unal.alife.dynamics;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import co.edu.unal.alife.neuralfield.DeltaField;
import co.edu.unal.alife.neuralfield.impl.MapNeuralPopulation;

/**
 * @author Juan Figueredo
 */
public class RungeKutta4thSolver extends AbstractSolver<Double, Double> {

	public RungeKutta4thSolver() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see co.edu.unal.alife.dynamics.Solver#step(co.edu.unal.alife.neuralfield.DeltaField, int)
	 */
	@Override
	public DeltaPopulation<Double> step(DeltaField<Double> field, int localIndex, double h) throws UnsupportedOperationException {
		DeltaPopulation<Double> population = field.getPopulations().get(localIndex);
		int size = population.getSize();
		Set<Double> positions = population.getPositions();
		DeltaPopulation<Double> workingPopulation = new MapNeuralPopulation(positions);
		Map<Double, Double> k1dh = new LinkedHashMap<Double, Double>(size);
		Map<Double, Double> k2dh = new LinkedHashMap<Double, Double>(size);
		Map<Double, Double> k3dh = new LinkedHashMap<Double, Double>(size);
		
		try {
			population.updatePopulationDelta(field, localIndex);
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		for (Double position : positions) {
			Double originalValue = population.getElementValue(position);
			Double originalDelta = population.getElementDelta(position);
			k1dh.put(position, originalDelta);
			Double newValue = originalValue + h / 2 * originalDelta;
			workingPopulation.setElementValue(position, newValue);
		}
		
		workingPopulation.updatePopulationDelta(field, localIndex);
		for (Double position : positions) {
			Double originalValue = population.getElementValue(position);
			Double workingDelta = workingPopulation.getElementDelta(position);
			k2dh.put(position, workingDelta);
			Double newValue = originalValue + h / 2 * workingDelta;
			workingPopulation.setElementValue(position, newValue);
		}
		
		workingPopulation.updatePopulationDelta(field, localIndex);
		for (Double position : positions) {
			Double originalValue = population.getElementValue(position);
			Double workingDelta = workingPopulation.getElementDelta(position);
			k3dh.put(position, workingDelta);
			Double newValue = originalValue + h * workingDelta;
			workingPopulation.setElementValue(position, newValue);
		}
		
		workingPopulation.updatePopulationDelta(field, localIndex);
		for (Double position : positions) {
			Double originalValue = population.getElementValue(position);
			Double workingDelta = workingPopulation.getElementDelta(position);
			Double newValue = originalValue
					+ (h / 6)
					* (k1dh.get(position) + 2 * k2dh.get(position) + 2 * k3dh.get(position) + workingDelta);
			workingPopulation.setElementValue(position, newValue);
		}
		return workingPopulation;
	}

}