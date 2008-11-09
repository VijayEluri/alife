package co.edu.unal.alife.neuralfield;

import java.util.List;

import co.edu.unal.alife.dynamic.CoordinateVector;



/**
 * @author jjfigueredou
 *
 */
public interface NeuralPopulation {

	/**
	 * @param position
	 * @return
	 */
	public abstract double getElementValue(CoordinateVector position);
	
	/**
	 * 
	 */
	public abstract void setElementValue(CoordinateVector position, double value);
	
	/**
	 * @return
	 */
	public abstract List<CoordinateVector> getElementPositions();
	
}
