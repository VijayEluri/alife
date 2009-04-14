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
import co.edu.unal.alife.neuralfield.DeltaEquation;
import co.edu.unal.alife.neuralfield.DeltaField;
import co.edu.unal.alife.neuralfield.KernelFunction;

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
public class MapDeltaPopulation implements DeltaPopulation<Double> {

	private Map<Double, DeltaPopulation.Element> populationMap = new LinkedHashMap<Double, DeltaPopulation.Element>();
	private DeltaPopulation<Double> nextPopulation;

	/**
	 * 
	 */
	public MapDeltaPopulation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MapDeltaPopulation(DeltaPopulation<Double> population) {
		this.populationMap = new LinkedHashMap<Double, DeltaPopulation.Element>(population
				.getPopulationMap());
	}

	public MapDeltaPopulation(int providedSize, boolean isHalfSize) {
		super();
		if (isHalfSize) {
			for (int i = -providedSize; i <= providedSize; i++) {
				Element element = new Element();
				element.setValue(0.0);
				element.setDelta(0.0);
				populationMap.put((double) i, element);
			}
		} else {
			for (int i = 0; i < providedSize; i++) {
				Element element = new Element();
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
			Element element = new Element();
			element.setValue(0.0);
			element.setDelta(0.0);
			populationMap.put(position, element);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.DeltaPopulation#getPositions()
	 */
	@Override
	public Set<Double> getPositions() {
		return populationMap.keySet();
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

//	/*
//	 * (non-Javadoc)
//	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#getTuples()
//	 */
//	// @Override
//	public Set<Entry<Double, DeltaPopulation.Element>> getTuples() {
//		return populationMap.entrySet();
//	}

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

//	/*
//	 * (non-Javadoc)
//	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#getElementDeltas()
//	 */
//	// @Override
//	public List<Double> getElementDeltas() {
//		Collection<DeltaPopulation.Element> elements = getElements();
//		List<Double> deltas = new ArrayList<Double>(elements.size());
//		for (DeltaPopulation.Element element : elements) {
//			deltas.add(element.getDelta());
//		}
//		return deltas;
//	}

//	/*
//	 * (non-Javadoc)
//	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#getElementValues()
//	 */
//	// @Override
//	public List<Double> getElementValues() {
//		Collection<DeltaPopulation.Element> elements = getElements();
//		List<Double> values = new ArrayList<Double>(elements.size());
//		for (DeltaPopulation.Element element : elements) {
//			values.add(element.getValue());
//		}
//		return values;
//	}

//	/*
//	 * (non-Javadoc)
//	 * @see
//	 * co.edu.unal.alife.neuralfield.core.NeuralPopulation#setElementValues(java.util.Collection)
//	 */
//	// @Override
//	public void setElementValues(Collection<Double> elementValues) {
//		Iterator<Double> iterator = elementValues.iterator();
//		Collection<DeltaPopulation.Element> elements = getElements();
//		for (DeltaPopulation.Element element : elements) {
//			element.setValue(iterator.next());
//		}
//	}

	/*
	 * (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.DeltaPopulation#updatePopulationDelta(java.util.List, int,
	 * co.edu.unal.alife.neuralfield.NeuralPopulationEquation, java.util.List)
	 */
	@Override
	public void updatePopulationDelta(DeltaField<Double> environment, int localIndex) {
		List<DeltaPopulation<Double>> populations = environment.getPopulations();
		DeltaEquation<Double> equation = environment.getEquations().get(localIndex);
		List<KernelFunction> kernelList = environment.getKernelMatrix().get(localIndex);
		try {
			equation.evalEquation(this, populations, kernelList);
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.dynamics.DeltaPopulation#getPopulationMap()
	 */
	@Override
	public Map<Double, DeltaPopulation.Element> getPopulationMap() {
		return populationMap;
	}

//	/**
//	 * @param populationMap
//	 *            the populationMap to set
//	 */
//	public void setPopulationMap(Map<Double, DeltaPopulation.Element> populationMap) {
//		this.populationMap = populationMap;
//	}

	/*
	 * (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#getSize()
	 */
	@Override
	public int getSize() {
		return populationMap.size();
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.dynamics.DeltaPopulation#getNextPopulation()
	 */
	@Override
	public DeltaPopulation<Double> getNextPopulation() {
		return nextPopulation;
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.dynamics.DeltaPopulation#setNextPopulation(co.edu.unal.alife.dynamics.DeltaPopulation)
	 */
	@Override
	public void setNextPopulation(DeltaPopulation<Double> nextPopulation) {
		this.nextPopulation = nextPopulation;
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.dynamics.DeltaPopulation#hasNextPopulation()
	 */
	@Override
	public boolean hasNextPopulation() {
		return (nextPopulation != null) ? true : false;
	}

	/* (non-Javadoc)
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(String t) {
		StringBuffer sb = new StringBuffer();
		Set<Double> positions = getPositions();
		for (Double x : positions) {
			sb.append(x+"\t"+t+"\t"+getElementValue(x).toString());
			sb.append("\n");
		}
		return sb.toString();
	}


	/* (non-Javadoc)
	 * @see co.edu.unal.alife.dynamics.DeltaPopulation#toString(java.util.List, java.util.List)
	 */
	@Override
	public String toString(List<String> times, List<DeltaPopulation<Double>> dataSource) {
		StringBuffer sb = new StringBuffer();
		Set<Double> positions = getPositions();
		for (Double x : positions) {
			int j = 0;
			for (DeltaPopulation<Double> pop : dataSource) {
				String t = times.get(j++);
				sb.append(t+"\t"+pop.getElementValue(x).toString());
				sb.append("\n");
			}
			sb.append("\n\n");
		}
		return sb.toString();
	}





	/**
	 * @author Juan Figueredo
	 *
	 */
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

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
//			return "E{Value:"+this.getValue()+",Delta:"+this.getDelta()+"}";
			return this.getValue().toString();
		}
		
		
	};
}
