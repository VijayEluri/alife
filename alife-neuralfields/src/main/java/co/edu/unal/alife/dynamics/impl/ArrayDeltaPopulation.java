package co.edu.unal.alife.dynamics.impl;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import co.edu.unal.alife.dynamics.AbstractDeltaPopulation;
import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.neuralfield.impl.SimpleElement;

/**
 * @author Juan Figueredo
 * 
 *         Class that implements an Array-based Population.
 * 
 */
public class ArrayDeltaPopulation extends AbstractDeltaPopulation {
	private Element[]	elementArray;
	private double[]	positionArray;
	private Set<Double>	positionSet;
	private boolean		isHalfSize;
	private int			size;
	private boolean		isTerminal	= false;
	
	/**
	 * Creates an ArrayDeltaPopulation of providedSize. If isHalfSize, then the positions are created from -providedSize
	 * to
	 * +providedSize. Otherwise they are created from 0 to providedSize.
	 * 
	 * @param providedSize
	 * @param isHalfSize
	 */
	public ArrayDeltaPopulation(int providedSize, boolean isHalfSize) {
		super();
		this.isHalfSize = isHalfSize;
		if (isHalfSize) {
			size = providedSize * 2 + 1;
			positionArray = new double[size];
			elementArray = new Element[size];
			for (int i = -providedSize; i <= providedSize; i++) {
				int j = i + providedSize;
				Element element = new SimpleElement();
				positionArray[j] = i;
				elementArray[j] = element;
			}
		} else {
			size = providedSize;
			positionArray = new double[size];
			elementArray = new Element[size];
			for (int i = 0; i < providedSize; i++) {
				int j = i;
				Element element = new SimpleElement();
				positionArray[j] = i;
				elementArray[j] = element;
			}
		}
		
		positionSet = new LinkedHashSet<Double>();
		for (int i = 0; i < size; i++) {
			positionSet.add(positionArray[i]);
		}
		positionSet = Collections.unmodifiableSet(positionSet);
	}
	
	/**
	 * Creates an ArrayDeltaPopulation of providedSize as half size. The positions are created from -providedSize to
	 * +providedSize. The total size is 2 * providedSize + 1.
	 * 
	 * @param providedSize
	 * @param isHalfSize
	 */
	public ArrayDeltaPopulation(int halfSize) {
		this(halfSize, true);
	}
	
	@Override
	public DeltaPopulation cloneEmpty(int size) {
		return new ArrayDeltaPopulation(size, false);
	}
	
	@Override
	public DeltaPopulation cloneEmpty() {
		int providedSize;
		if (this.isHalfSize) {
			providedSize = (this.getSize() - 1) / 2;
		} else {
			providedSize = this.getSize();
		}
		return new ArrayDeltaPopulation(providedSize, this.isHalfSize);
	}
	
	@Override
	public double getElementDelta(Double position) {
		return getElement(position).getDelta();
	}
	
	@Override
	public double getElementValue(Double position) {
		return getElement(position).getValue();
	}
	
	@Override
	public Set<Double> getPositions() {
		return positionSet;
	}
	
	@Override
	public int getSize() {
		return size;
	}
	
	@Override
	public void setElementDelta(Double position, Double value) {
		getElement(position).setDelta(value);
	}
	
	@Override
	public void setElementValue(Double position, Double value) {
		getElement(position).setValue(value);
	}
	
	public boolean isTerminal() {
		return isTerminal;
	}
	
	public void setTerminal(boolean isTerminal) {
		this.isTerminal = isTerminal;
	}
	
	private Element getElement(Double position) {
		if (isHalfSize) {
			int halfSize = (elementArray.length - 1) / 2;
			int index = position.intValue() + halfSize;
			return elementArray[index];
		} else {
			return elementArray[position.intValue()];
		}
	}
	
}
