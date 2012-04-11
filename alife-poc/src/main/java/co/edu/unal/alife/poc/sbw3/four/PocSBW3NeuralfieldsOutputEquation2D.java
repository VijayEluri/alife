package co.edu.unal.alife.poc.sbw3.four;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.unal.alife.poc.PocUtils;
import co.edu.unal.alife.poc.neuralfield.APocNeuralfieldSpec;
import co.edu.unal.alife.poc.neuralfield.IPocNeuralfieldOutputEquation;
import co.edu.unal.alife.poc.sbw3.PocSBW3Lookup;

public class PocSBW3NeuralfieldsOutputEquation2D implements
		IPocNeuralfieldOutputEquation {

	protected static final Logger logger = LoggerFactory
			.getLogger(PocSBW3NeuralfieldsOutputEquation2D.class);

	APocNeuralfieldSpec spec;

	public PocSBW3NeuralfieldsOutputEquation2D(APocNeuralfieldSpec spec) {
		super();
		this.spec = spec;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.unal.alife.poc.sbw3.three.IPocNeuralfieldOutputEquation#evalOutput
	 * (double[])
	 */
	@Override
	public double evalOutput(double t, double[] q) {
		double accK = 0;
		double acc = 0;
		// Calculate the weighted average of K values given the neuralfield
		// activation
		for (int i = spec.getStartIndex(); i <= spec.getEndIndex(); i++) {
			double xPosition = spec.getPosition(i, 0);
			double yPosition = spec.getPosition(i, 1);
			logger.trace("xPosition = {}, yPosition = {}.", xPosition,
					yPosition);
			double value = PocUtils.heavisideFunction(q[i]);
			double k = getK(xPosition, yPosition);
			accK += k * value;
			acc += value;
		}
		double meanK = 0;
		if (acc > 0.01) {
			meanK = accK / acc;
		}
		logger.debug("t: {}, meanK: {}", t, meanK);
		return meanK;
	}

	protected double getK(double x, double y) {
		// TODO: Move to Spec
		int xIndex = (int) ((x - spec.getH(0) / 2) * PocSBW3Lookup.K_LENGTH);
		int yIndex = (int) ((y - spec.getH(1) / 2) * PocSBW3Lookup.K_LENGTH);
		logger.trace("x: {} -> xIndex: {}. y: {} -> yIndex: {}",
				new String[] { Double.toString(x), Integer.toString(xIndex),
						Double.toString(y), Integer.toString(yIndex) });
		return PocSBW3Lookup.K[xIndex][yIndex];
	}

}
