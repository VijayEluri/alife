/**
 * 
 */
package co.edu.unal.alife.neuralfield.impl;

import co.edu.unal.alife.neuralfield.S1SymmetryTransformation;

/**
 * @author Juan Figueredo
 * 
 */
public class S1ActionTransformation {

	private S1SymmetryTransformation transformation;
	private double alpha;

	public S1ActionTransformation(S1SymmetryTransformation transformation,
			double alpha) {
		this.transformation = transformation;
		this.alpha = alpha;
	}

	public double evaluateTransformation(double theta) {
		return transformation.transform(theta);
	}
	
	public double evaluateWeight(double theta) {
		return alpha*theta;
	}

}
