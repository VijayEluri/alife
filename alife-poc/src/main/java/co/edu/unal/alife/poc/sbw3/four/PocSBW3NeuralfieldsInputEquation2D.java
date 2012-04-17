package co.edu.unal.alife.poc.sbw3.four;

import static java.lang.Math.max;
import static java.lang.Math.min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.unal.alife.poc.neuralfield.APocNeuralfieldSpec;
import co.edu.unal.alife.poc.neuralfield.IPocNeuralfieldInputEquation;
import co.edu.unal.alife.poc.neuralfield.PocMexicanHatKernelFunction2D;

public class PocSBW3NeuralfieldsInputEquation2D implements
		IPocNeuralfieldInputEquation {

	static final Logger logger = LoggerFactory
			.getLogger(PocSBW3NeuralfieldsInputEquation2D.class);
	public static final double MIN_THETA = 0.0;
	public static final double MAX_THETA = 0.4;
	public static final double MIN_THETADOT = -0.4;
	public static final double MAX_THETADOT = 0.0;
	public static final int AXIS_LENGTH = 17;
	public static final double INPUT_CONSTANT = 1;

	APocNeuralfieldSpec spec;
	private PocMexicanHatKernelFunction2D kernelFunction;

	public PocSBW3NeuralfieldsInputEquation2D(APocNeuralfieldSpec spec) {
		super();
		this.spec = spec;
		// These values are balanced by trial-and-error.nction constructor values
		this.kernelFunction = new PocMexicanHatKernelFunction2D(0.01, 0.1, 1);
	}

	/**
	 * Evaluates the input delta, applying a kernel function to the projection
	 * generated with evalProjection().
	 */
	@Override
	public double evalInputDelta(double[] lastSwitchQ, int localIndex) {
		double localXPosition = spec.getPosition(localIndex, 0);
		double localYPosition = spec.getPosition(localIndex, 1);
		double xProjection = evalXProjection(lastSwitchQ);
		double yProjection = evalYProjection(lastSwitchQ);
		logger.trace("lastSwitchQ projection (x,y): ({},{})", xProjection,
				yProjection);
		logger.trace("localPosition (x,y): ({},{})", localXPosition,
				localYPosition);
		double kernelValue = kernelFunction.evalKernelValue(xProjection,
				yProjection, localXPosition, localYPosition);
		logger.trace("kernel value: {}",kernelValue);
		/*
		 *  inputDelta = kernelValue * defaultValue * spec.getH(0) * spec.getH(1)
		 *  where defaultValue = INPUT_CONSTANT/(spec.getH(0) * spec.getH(1))
		 */
		return kernelValue*INPUT_CONSTANT;
	}

	/**
	 * Evaluates the linear projection from (\theta dot) to the X-axis position
	 * in the neuralfield (first index).
	 * 
	 * @param q
	 * @return x position
	 */
	private static double evalXProjection(double[] q) {
		// Limit thetadot to [MIN_THETADOT,MAX_THETADOT]
		double thetadot = max(min(q[2], MAX_THETADOT), MIN_THETADOT);
		// Normalize and invert, to be in [0,1]
		return -thetadot / (MAX_THETADOT - MIN_THETADOT);
	}

	/**
	 * Evaluates the linear projection from (\theta) to the Y-axis position in
	 * the neuralfield (second index).
	 * 
	 * @param q
	 * @return y position
	 */
	private static double evalYProjection(double[] q) {
		// Limit thetadot to [MIN_THETADOT,MAX_THETADOT]
		double theta = max(min(q[0], MAX_THETA), MIN_THETA);
		// Normalize to be in [0,1]
		return theta / (MAX_THETA - MIN_THETA);
	}

}
