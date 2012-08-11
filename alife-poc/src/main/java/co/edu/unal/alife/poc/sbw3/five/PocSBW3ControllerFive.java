package co.edu.unal.alife.poc.sbw3.five;

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
public class PocSBW3ControllerFive extends APocSBW3Controller {

	static final Logger logger = LoggerFactory
			.getLogger(PocSBW3ControllerFive.class);
	public static final int N = 17;
	public static final int N2 = N * N;
	private double[] lastSwitchQ;
	private APocNeuralfieldSpec spec;

	public PocSBW3ControllerFive() {
		super(N2);
		this.spec = new PocSBW3NeuralfieldSpec2D(4, N, N);
	}

	@Override
	public double[] evaluateK(double t, double[] q, double[] r) {
		double[] k = { 0, spec.evalOutput(t, q) };
		return k;
	}

	@Override
	public void computeDerivatives(double t, double[] q, double[] qDot) {
		spec.computeDerivatives(q, lastSwitchQ, qDot);
		// logger.debug("Time: {}.\t q: {}", t, q);
		logger.debug("Time: {}.\t qDot: {}", t, qDot);
	}

	/**
	 * Updates the lastSwitchQ used as neuralfield input.
	 */
	@Override
	protected void switchEvent(double t, double[] q) {
		lastSwitchQ = Arrays.copyOf(q, 4);
		logger.debug("t = {}\t lastSwitchQ = {}", t, lastSwitchQ);
	}

	@Override
	public void init(double t0, double[] y0) {
		super.init(t0, y0);
		spec.init(t0, y0);
	}

	@Override
	public String toString(String TOKEN, String COMMENT) {
		return spec.toString(TOKEN, COMMENT);
	}

}
