package co.edu.unal.alife.poc.sbw3.three;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.unal.alife.poc.neuralfield.APocNeuralfieldSpec;
import co.edu.unal.alife.poc.neuralfield.IPocNeuralfieldInputEquation;
import co.edu.unal.alife.poc.neuralfield.PocMexicanHatKernelFunction1D;
import co.edu.unal.alife.poc.sbw3.PocSBW3Lookup;

public class PocSBW3NeuralfieldsInputEquation1D implements
		IPocNeuralfieldInputEquation {

	static final Logger logger = LoggerFactory
			.getLogger(PocSBW3NeuralfieldsInputEquation1D.class);

	APocNeuralfieldSpec spec;
	private PocMexicanHatKernelFunction1D kernelFunction;

	public PocSBW3NeuralfieldsInputEquation1D(APocNeuralfieldSpec spec) {
		super();
		this.spec = spec;
		// TODO: Fix kernelFunction constructor values
		this.kernelFunction = new PocMexicanHatKernelFunction1D(0.05, 0.5, 1);
	}

	/**
	 * Evaluates the input delta, applying a kernel function to the projection
	 * generated with evalProjection().
	 */
	@Override
	public double evalInputDelta(double[] lastSwitchQ, int localIndex) {
		double localPosition = spec.getPosition(localIndex, 0);
		double projection = evalProjection(lastSwitchQ);
		double kernelValue = kernelFunction.evalKernelValue(projection,
				localPosition);
		return kernelValue;
	}

	/**
	 * Evaluates the non-linear projection from (\theta,\phi) to the one
	 * dimensional position in the neuralfield, using the same K values of the
	 * PocSBW3ControllerTwo.
	 * 
	 * @param q
	 * @return
	 */
	private static double evalProjection(double[] q) {
		double k_phi = PocSBW3Lookup.evalKPhi(q);
		return k_phi / PocSBW3Lookup.MAX_K_PHI;
	}
}
