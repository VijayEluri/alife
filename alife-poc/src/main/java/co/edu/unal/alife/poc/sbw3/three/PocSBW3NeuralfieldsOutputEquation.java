package co.edu.unal.alife.poc.sbw3.three;

import co.edu.unal.alife.poc.neuralfield.APocNeuralfieldSpec;
import co.edu.unal.alife.poc.neuralfield.IPocNeuralfieldOutputEquation;


public class PocSBW3NeuralfieldsOutputEquation implements IPocNeuralfieldOutputEquation {
	public static final double MAX_K_PHI = 81.1293;

	APocNeuralfieldSpec spec;

	public PocSBW3NeuralfieldsOutputEquation(APocNeuralfieldSpec spec) {
		super();
		this.spec = spec;
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.poc.sbw3.three.IPocNeuralfieldOutputEquation#evalOutput(double[])
	 */
	@Override
	public double evalOutput(double[] q) {
		double u = 0;
		double maxValue = Double.NEGATIVE_INFINITY;
		int maxValueIndex = -1;
		for (int i = spec.getStartIndex(); i <= spec.getEndIndex(); i++) {
			double value = q[i];
			if (value > maxValue) {
				maxValueIndex = i;
				maxValue = value;
			}
		}
		u = spec.getPosition(maxValueIndex,0) * MAX_K_PHI;
		return u;
	}

}
