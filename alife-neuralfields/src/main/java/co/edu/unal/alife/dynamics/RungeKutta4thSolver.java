package co.edu.unal.alife.dynamics;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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

	/**
	 * @param x
	 * @param h
	 * @param t
	 * @param f
	 * @param isTraceEnabled
	 * @param tracePeriod
	 */
	public RungeKutta4thSolver(List<Double> x, double h, double t, Derivable<Double> f,
			boolean isTraceEnabled, int traceSkip) {
		super(x, h, t, f, isTraceEnabled, traceSkip);
	}

	/*
	 * (non-Javadoc)
	 * @see solver.ODESolver#step()
	 */
	@Override
	public List<Double> step() {
		// System.out.println(x);
		List<Double> xAtK2 = new ArrayList<Double>(x.size());
		List<Double> xAtK3 = new ArrayList<Double>(x.size());
		List<Double> xAtK4 = new ArrayList<Double>(x.size());
		List<Double> k1dh = f.getDeltas(x, t);
		List<Double> k2dh;
		List<Double> k3dh;
		List<Double> k4dh;
		List<Double> xnext = new ArrayList<Double>(x.size());
		for (int i = 0; i < x.size(); i++) {
			xAtK2.add(x.get(i) + h / 2 * k1dh.get(i));
		}
		k2dh = f.getDeltas(xAtK2, t + h / 2);
		for (int i = 0; i < x.size(); i++) {
			xAtK3.add(i, x.get(i) + h / 2 * k2dh.get(i));
		}
		k3dh = f.getDeltas(xAtK3, t + h / 2);
		for (int i = 0; i < x.size(); i++) {
			xAtK4.add(i, x.get(i) + h * k3dh.get(i));
		}
		k4dh = f.getDeltas(xAtK4, t + h);
		for (int i = 0; i < x.size(); i++) {
			double value = x.get(i) + h / 6
					* (k1dh.get(i) + 2 * k2dh.get(i) + 2 * k3dh.get(i) + k4dh.get(i));
			xnext.add(i, value);
		}
		return xnext;
	}

	/*
	 * (non-Javadoc)
	 * @see co.edu.unal.alife.dynamics.Solver#step(co.edu.unal.alife.neuralfield.DeltaField, int)
	 */
	@Override
	public DeltaPopulation<Double> step(DeltaField<Double> field, int localIndex) {
		DeltaPopulation<Double> population = field.getPopulations().get(localIndex);
		int size = population.getSize();
		DeltaPopulation<Double> workingPopulation = new MapNeuralPopulation(size);
		Set<Double> positions = workingPopulation.getPositions();
		Map<Double, Double> k1dh = new LinkedHashMap<Double, Double>(size);
		Map<Double, Double> k2dh = new LinkedHashMap<Double, Double>(size);
		Map<Double, Double> k3dh = new LinkedHashMap<Double, Double>(size);
		
		population.updatePopulationDelta(field, localIndex);
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
			population.setElementValue(position, newValue);
		}
		return population;
	}

}