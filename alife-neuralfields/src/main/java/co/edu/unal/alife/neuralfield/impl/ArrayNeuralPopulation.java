package co.edu.unal.alife.neuralfield.impl;

import java.util.Arrays;
import java.util.Collection;

import co.edu.unal.alife.neuralfield.core.NeuralPopulation;
import co.edu.unal.alife.neuralfield.core.NeuralPopulation.Element;

/**
 * @author jjfigueredou
 *
 */
public class ArrayNeuralPopulation implements NeuralPopulation<Number,Double> {

	private int halfSize;
	private Double[] valuesArray;
	private Double[] deltasArray;
	
	/**
	 * 
	 * @param halfSize
	 */
	public ArrayNeuralPopulation(int halfSize) {
		this.halfSize = halfSize;
		valuesArray = new Double[halfSize*2+1];
	}
	
	/**
	 * 
	 * @param zeroCenteredPosition
	 * @return
	 */
	public int calculatePosition(int zeroCenteredPosition) {
		int position = zeroCenteredPosition + halfSize;
		return position;
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#getElementValue(java.lang.Object)
	 */
	@Override
	public Double getElementValue(Number position) {
		return valuesArray[calculatePosition(position.intValue())];
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#setElementValue(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void setElementValue(Number position, Double value) {
		valuesArray[calculatePosition(position.intValue())] = value;	
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation#getElements()
	 */
	@Override
	public Collection<ArrayElement<Number, Double>> getElements() {
		// TODO Auto-generated method stub
		return null;
	}
	
	static class ArrayElement<Number,Double> implements Element<Number, Double> {

		/* (non-Javadoc)
		 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation.Element#getDelta(java.lang.Object)
		 */
		@Override
		public Double getDelta(Number position) {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see co.edu.unal.alife.neuralfield.core.NeuralPopulation.Element#setDelta(java.lang.Object, java.lang.Object)
		 */
		@Override
		public void setDelta(Number position, Double value) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see java.util.Map.Entry#getKey()
		 */
		@Override
		public Number getKey() {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see java.util.Map.Entry#getValue()
		 */
		@Override
		public Double getValue() {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see java.util.Map.Entry#setValue(java.lang.Object)
		 */
		@Override
		public Double setValue(Double value) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
