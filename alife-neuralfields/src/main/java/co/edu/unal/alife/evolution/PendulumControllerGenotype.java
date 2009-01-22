/**
 * 
 */
package co.edu.unal.alife.evolution;

import jml.evolution.Genotype;

/**
 * @author Juan Figueredo
 */
public class PendulumControllerGenotype extends Genotype {

	private int size;
	private int minValue;
	private int maxValue;

	/**
	 * @param maxValue
	 * @param minValue
	 * @param size
	 */
	public PendulumControllerGenotype(int size, int maxValue, int minValue) {
		super();
		this.maxValue = maxValue;
		this.minValue = minValue;
		this.size = size;
	}

	/*
	 * (non-Javadoc)
	 * @see jml.evolution.Genotype#newInstance()
	 */
	@Override
	public Object newInstance() {
		PendulumControllerParameters parameters = new PendulumControllerParameters(size, minValue, maxValue);
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
		return size;
	}

}
