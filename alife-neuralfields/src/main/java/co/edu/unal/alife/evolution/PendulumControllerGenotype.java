/**
 * 
 */
package co.edu.unal.alife.evolution;

import jml.evolution.Genotype;

/**
 * @author Juan Figueredo
 */
public class PendulumControllerGenotype extends Genotype {

	private int halfSize;
	private double minValue = -0.2;
	private double maxValue = 1.0;
	
	/**
	 * @param size
	 */
	public PendulumControllerGenotype(int size) {
		super();
		this.halfSize = size;
	}

	/**
	 * @param maxValue
	 * @param minValue
	 * @param halfSize
	 */
	public PendulumControllerGenotype(int halfSize, double maxValue, double minValue) {
		super();
		this.maxValue = maxValue;
		this.minValue = minValue;
		this.halfSize = halfSize;
	}

	/*
	 * (non-Javadoc)
	 * @see jml.evolution.Genotype#newInstance()
	 */
	@Override
	public Object newInstance() {
		PendulumControllerParameters parameters = new PendulumControllerParameters(2*halfSize+1, minValue, maxValue);
		return parameters;
	}

	/*
	 * (non-Javadoc)
	 * @see jml.evolution.Genotype#size(java.lang.Object)
	 */
	@Override
	public int size(Object genome) {
		return this.getSize();
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return halfSize;
	}

}
