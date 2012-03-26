package co.edu.unal.alife.poc.sbw3.three;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.unal.alife.poc.neuralfield.APocNeuralfieldSpec;
import co.edu.unal.alife.poc.sbw3.APocSBW3Controller;

/**
 * This controller uses a one-dimensional neural field. The neural field is fed
 * with the normalized control input given by the K matrix of the
 * PocSBW3ControllerTwo (so it doesn't 'calculates' the feedback loop gain), but
 * it provides a soft transition of gains and filters noise.
 * 
 * It is mostly a PoC of the 1D Neural field applied to the SBW3 problem.
 * 
 * @author shrein
 * 
 */
public class PocSBW3ControllerThree extends APocSBW3Controller {

	static final Logger logger = LoggerFactory
			.getLogger(PocSBW3ControllerThree.class);
	public static int N = 17;
	private double[] lastSwitchQ;
	private APocNeuralfieldSpec spec;

	public PocSBW3ControllerThree() {
		super(N);
		this.spec = new PocSBW3NeuralfieldSpec1D(4,N);
	}

	@Override
	public double[] evaluateK(double t, double[] q, double[] r) {
		double[] k = { 0, spec.evalOutput(q) };
		return k;
	}

	@Override
	public void computeDerivatives(double t, double[] q, double[] qDot) {
		for (int i = spec.getStartIndex(); i <= spec.getEndIndex(); i++) {
			double homogeneousDelta = spec.evalHomogeneousDelta(q, i);
			double inputDelta = spec.evalInputDelta(lastSwitchQ, i);
			qDot[i] = homogeneousDelta + inputDelta;
		}
	}

	/**
	 * Updates the lastSwitchQ used as neuralfield input.
	 */
	@Override
	protected void switchEvent(double t, double[] q) {
		lastSwitchQ = Arrays.copyOf(q, 4);
		logger.debug("t = {}\t lastSwitchQ = {}", t,
				Arrays.toString(lastSwitchQ));
	}

}
