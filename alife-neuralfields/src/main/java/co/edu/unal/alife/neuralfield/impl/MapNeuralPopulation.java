/**
 * 
 */
package co.edu.unal.alife.neuralfield.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import co.edu.unal.alife.neuralfield.DeltaPopulation;
import co.edu.unal.alife.neuralfield.KernelFunction;
import co.edu.unal.alife.neuralfield.NeuralPopulationEquation;

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
public class MapNeuralPopulation implements DeltaPopulation<Double> {

	private Map<Double, DeltaPopulation.Element> populationMap = new HashMap<Double, DeltaPopulation.Element>();

	/**
	 * 
	 */
	public MapNeuralPopulation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	public MapNeuralPopulation(int halfSize) {
		super();
		for (int i = -halfSize; i <= halfSize; i++) {
			populationMap.put((double) i, new Element());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#getElement(java.lang.Object)
	 */
	@Override
	public DeltaPopulation.Element getElement(Double position) {
		return populationMap.get(position);
	}

	/*
	 * (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#getElementDelta(java.lang.Object)
	 */
	@Override
	public Double getElementDelta(Double position) {
		return populationMap.get(position).getDelta();
	}

	/*
	 * (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#getElements()
	 */
	@Override
	public Collection<DeltaPopulation.Element> getElements() {
		return populationMap.values();
	}

	/*
	 * (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#getElementValue(java.lang.Object)
	 */
	@Override
	public Double getElementValue(Double position) {
		return populationMap.get(position).getValue();
	}

	/*
	 * (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#getTuples()
	 */
	@Override
	public Set<Entry<Double, DeltaPopulation.Element>> getTuples() {
		return populationMap.entrySet();
	}

	/*
	 * (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#setElementDelta(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public void setElementDelta(Double position, Double value) {
		populationMap.get(position).setDelta(value);
	}

	/*
	 * (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#setElementValue(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public void setElementValue(Double position, Double value) {
		populationMap.get(position).setValue(value);
	}

	/*
	 * (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#getElementDeltas()
	 */
	@Override
	public List<Double> getElementDeltas() {
		Collection<DeltaPopulation.Element> elements = getElements();
		List<Double> deltas = new ArrayList<Double>(elements.size());
		for (DeltaPopulation.Element element : elements) {
			deltas.add(element.getDelta());
		}
		return deltas;
	}

	/*
	 * (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#getElementValues()
	 */
	@Override
	public List<Double> getElementValues() {
		Collection<DeltaPopulation.Element> elements = getElements();
		List<Double> values = new ArrayList<Double>(elements.size());
		for (DeltaPopulation.Element element : elements) {
			values.add(element.getValue());
		}
		return values;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * co.edu.unal.alife.neuralfield.core.NeuralPopulation#setElementValues(java.util.Collection)
	 */
	@Override
	public void setElementValues(Collection<Double> elementValues) {
		Iterator<Double> iterator = elementValues.iterator();
		Collection<DeltaPopulation.Element> elements = getElements();
		for (DeltaPopulation.Element element : elements) {
			element.setValue(iterator.next());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.DeltaPopulation#updatePopulationDelta(java.util.List, int,
	 * co.edu.unal.alife.neuralfield.NeuralPopulationEquation, java.util.List)
	 */
	public void updatePopulationDelta(List<DeltaPopulation<Double>> populations,
			int populationIndex, NeuralPopulationEquation<Double> equation,
			List<KernelFunction> kernelList) {
		List<Double> deltas = equation.evalEquation(populations, populationIndex, kernelList);
		Set<Entry<Double, DeltaPopulation.Element>> entrySet = populationMap.entrySet();
		int i = 0;
		if (deltas != null) {
//			System.out.println("MapNeuralPopulation - entrySetSize: " + entrySet.size());
//			System.out.println("MapNeuralPopulation - DeltasSize: " + deltas.size());
			for (Entry<Double, DeltaPopulation.Element> entry : entrySet) {
				Double delta = deltas.get(i++);
				entry.getValue().setDelta(delta);
			}
		} else {
			List<Double> values = equation.applyInput();
			for (Entry<Double, DeltaPopulation.Element> entry : entrySet) {
				Double value = values.get(i++);
				entry.getValue().setValue(value);
				entry.getValue().setDelta(0.0);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#getSize()
	 */
	@Override
	public int getSize() {
		return populationMap.size();
	}

	public class Element implements DeltaPopulation.Element {
		private Double value;
		private Double delta;
		
		/**
		 * 
		 */
		public Element() {
			super();
			value = 0.0;
			delta = 0.0;
		}

		public Double getValue() {
			return value;
		}

		public void setValue(Double value) {
			this.value = value;
		}

		public Double getDelta() {
			return delta;
		}

		public void setDelta(Double delta) {
			this.delta = delta;
		}
	};
}
