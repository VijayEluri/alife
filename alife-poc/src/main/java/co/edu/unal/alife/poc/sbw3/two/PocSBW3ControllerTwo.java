package co.edu.unal.alife.poc.sbw3.two;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.unal.alife.poc.sbw3.APocSBW3Controller;
import co.edu.unal.alife.poc.sbw3.PocSBW3Lookup;

/**
 * This controller uses lookup table of feedback loop gain values. It approaches
 * a sliding-mode controller, where in each leg switch a new proportional
 * control gain is evaluated based on a table (it is locally a linear
 * controller).
 * 
 * It is a port of the work developed in alife-matlab (SBW3 and 4).
 * 
 * @author shrein
 * 
 */
public class PocSBW3ControllerTwo extends APocSBW3Controller {

	static final Logger logger = LoggerFactory
			.getLogger(PocSBW3ControllerTwo.class);
	public static final int N = 0;
	private double[] currentK;

	public PocSBW3ControllerTwo() {
		super(N);
	}

	@Override
	public double[] evaluateK(double t, double[] q, double[] r) {
		return currentK;
	}

	@Override
	public void computeDerivatives(double t, double[] q, double[] qDot) {
		// N/A
	}

	/*
	 * Evalutes k = (k_theta, k_phi) = (0, k_phi) using a look-up table. Valid
	 * values for q are in the range ([0.0,0.4],[-0.4,0.0]). q[0] is used for
	 * column selection, and q[1] for row selection. Output k_phi is
	 * interpolated linearly.
	 */
	@Override
	protected void switchEvent(double t, double[] q) {
		double k_phi = PocSBW3Lookup.evalKPhi(q);

		currentK = new double[] { 0, k_phi + 0.1 };
		if (logger.isDebugEnabled()) {
			logger.debug(
					"t = {}\t currentK = {}\t q = {}",
					new String[] { Double.toString(t),
							Arrays.toString(currentK), Arrays.toString(q) });
		}
	}

	@Override
	public String toString(String TOKEN, String COMMENT) {
		StringBuffer sb = new StringBuffer();
		sb.append(COMMENT);
		sb.append("Simple lookup controller with K:");
		for (int i = 0; i < PocSBW3Lookup.K_LENGTH; i++) {
			sb.append("\n");
			for (int j = 0; j < PocSBW3Lookup.K_LENGTH; j++) {
				sb.append(PocSBW3Lookup.K[i][j]);
				sb.append(TOKEN);
			}
		}
		sb.append("\n");
		sb.append(COMMENT);
		sb.append("t");
		sb.append(TOKEN);
		sb.append("theta");
		sb.append(TOKEN);
		sb.append("phi");
		sb.append(TOKEN);
		sb.append("thetaDot");
		sb.append(TOKEN);
		sb.append("phiDot");
		sb.append("\n");
		return sb.toString();
	}

}
