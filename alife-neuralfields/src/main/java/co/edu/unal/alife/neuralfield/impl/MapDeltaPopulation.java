/**
 * 
 */
package co.edu.unal.alife.neuralfield.impl;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.neuralfield.AbstractDeltaField;
import co.edu.unal.alife.neuralfield.AbstractKernelFunction;
import co.edu.unal.alife.neuralfield.DeltaEquation;

/**
 * @author jjfigueredou
 * Class that implements a Map-based Neural Population.
 * 
 * @param <K>
 * @param <Double>
 */
/**
 * @author Juan Figueredo
 */
public class MapDeltaPopulation implements DeltaPopulation {

	private Map<Double, DeltaPopulation.Element> populationMap = new LinkedHashMap<Double, DeltaPopulation.Element>();
	private DeltaPopulation nextPopulation;

	/**
	 * 
	 */
	public MapDeltaPopulation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MapDeltaPopulation(int providedSize, boolean isHalfSize) {
		super();
		if (isHalfSize) {
			for (int i = -providedSize; i <= providedSize; i++) {
				SimpleElement element = new SimpleElement();
				element.setValue(0.0);
				element.setDelta(0.0);
				populationMap.put((double) i, element);
			}
		} else {
			for (int i = 0; i < providedSize; i++) {
				SimpleElement element = new SimpleElement();
				element.setValue(0.0);
				element.setDelta(0.0);
				populationMap.put((double) i, element);
			}
		}
	}

	public MapDeltaPopulation(int halfSize) {
		this(halfSize, true);
	}

	public MapDeltaPopulation(Set<Double> positions) {
		for (Double position : positions) {
			SimpleElement element = new SimpleElement();
			element.setValue(0.0);
			element.setDelta(0.0);
			populationMap.put(position, element);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.edu.unal.alife.neuralfield.DeltaPopulation#getPositions()
	 */
	@Override
	public Set<Double> getPositions() {
		return populationMap.keySet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.unal.alife.neuralfield.core.NeuralPopulation#getElementDelta(java
	 * .lang.Object)
	 */
	@Override
	public Double getElementDelta(Double position) {
		return populationMap.get(position).getDelta();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.unal.alife.neuralfield.core.NeuralPopulation#getElementValue(java
	 * .lang.Object)
	 */
	@Override
	public Double getElementValue(Double position) {
		return populationMap.get(position).getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.unal.alife.neuralfield.core.NeuralPopulation#setElementDelta(java
	 * .lang.Object, java.lang.Object)
	 */
	@Override
	public void setElementDelta(Double position, Double value) {
		populationMap.get(position).setDelta(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.unal.alife.neuralfield.core.NeuralPopulation#setElementValue(java
	 * .lang.Object, java.lang.Object)
	 */
	@Override
	public void setElementValue(Double position, Double value) {
		populationMap.get(position).setValue(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.unal.alife.neuralfield.DeltaPopulation#updatePopulationDelta(java
	 * .util.List, int, co.edu.unal.alife.neuralfield.NeuralPopulationEquation,
	 * java.util.List)
	 */
	@Override
	public void updatePopulationDelta(AbstractDeltaField environment,
			int localIndex) {
		List<DeltaPopulation> populations = environment
				.getPopulations();
		DeltaEquation equation = environment.getEquations().get(
				localIndex);
		List<AbstractKernelFunction> kernelList = environment.getKernelMatrix()
				.get(localIndex);
		try {
			// System.out.println("MapDeltaPopulattion: localIndex="+localIndex);
			equation.evalEquation(this, populations, kernelList);
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}

	/**
	 * Gets the map that backs the delta population.
	 * 
	 * @return the population map
	 */
	public Map<Double, DeltaPopulation.Element> getPopulationMap() {
		return populationMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#getSize()
	 */
	@Override
	public int getSize() {
		return populationMap.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.edu.unal.alife.dynamics.DeltaPopulation#getNextPopulation()
	 */
	@Override
	public DeltaPopulation getNextPopulation() {
		return nextPopulation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.unal.alife.dynamics.DeltaPopulation#setNextPopulation(co.edu.unal
	 * .alife.dynamics.DeltaPopulation)
	 */
	@Override
	public void setNextPopulation(DeltaPopulation nextPopulation) {
		this.nextPopulation = nextPopulation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.edu.unal.alife.dynamics.DeltaPopulation#hasNextPopulation()
	 */
	@Override
	public boolean hasNextPopulation() {
		return (nextPopulation != null) ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		Collection<DeltaPopulation.Element> values = populationMap.values();
		StringBuffer sb = new StringBuffer();
		for (DeltaPopulation.Element element : values) {
			sb.append(element.toString());
			sb.append("\t");
		}
		return sb.toString();
	}

	@Override
	public DeltaPopulation cloneEmpty(int size) {
		return new MapDeltaPopulation(size, false);
	}

	
	@Override
	public DeltaPopulation cloneEmpty(Set<Double> positions) {
		// TODO Auto-generated method stub
		return new MapDeltaPopulation(positions);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(String t) {
		StringBuffer sb = new StringBuffer();
		Set<Double> positions = getPositions();
		for (Double x : positions) {
			sb.append(x + "\t" + t + "\t" + getElementValue(x).toString());
			sb.append("\n");
		}
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.edu.unal.alife.dynamics.DeltaPopulation#toString(java.util.List,
	 * java.util.List)
	 */
	@Override
	public String toString(List<String> times,
			List<DeltaPopulation> dataSource) {
		StringBuffer sb = new StringBuffer();
		Set<Double> positions = getPositions();
		for (Double x : positions) {
			int j = 0;
			for (DeltaPopulation pop : dataSource) {
				String t = times.get(j++);
				sb.append(t + "\t" + pop.getElementValue(x).toString());
				sb.append("\n");
			}
			sb.append("\n\n");
		}
		return sb.toString();
	};
}
