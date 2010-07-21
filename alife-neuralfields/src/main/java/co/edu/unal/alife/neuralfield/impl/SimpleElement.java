package co.edu.unal.alife.neuralfield.impl;

import co.edu.unal.alife.dynamics.DeltaPopulation;

/**
 * @author Juan Figueredo
 * 
 */
public class SimpleElement implements DeltaPopulation.Element {
	private Double value;
	private Double delta;

	/**
	 * 
	 */
	public SimpleElement() {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// return "E{Value:"+this.getValue()+",Delta:"+this.getDelta()+"}";
		return this.getValue().toString();
	}
}