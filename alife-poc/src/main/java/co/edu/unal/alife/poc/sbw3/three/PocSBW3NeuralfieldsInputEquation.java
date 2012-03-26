package co.edu.unal.alife.poc.sbw3.three;

import static java.lang.Math.floor;
import static java.lang.Math.max;
import static java.lang.Math.min;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.unal.alife.poc.neuralfield.APocNeuralfieldSpec;
import co.edu.unal.alife.poc.neuralfield.IPocNeuralfieldInputEquation;
import co.edu.unal.alife.poc.neuralfield.PocMexicanHatKernelFunction;

public class PocSBW3NeuralfieldsInputEquation implements
		IPocNeuralfieldInputEquation {

	static final Logger logger = LoggerFactory
			.getLogger(PocSBW3NeuralfieldsInputEquation.class);
	public static final double MAX_K_PHI = 81.1293;
	public static final int K_LENGTH = 17;
	public static double[][] K = {
			{ 2.3778, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 2.723, 2.4523, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 3.1803, 3.0232, 2.5328, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 3.7749, 3.6286, 3.3026, 2.581, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0 },
			{ 6.0097, 4.4173, 4.0703, 3.5241, 2.6073, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0 },
			{ 8.9022, 5.9395, 4.9967, 4.4954, 3.7541, 2.6345, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0 },
			{ 12.3883, 8.4926, 6.1158, 5.5513, 4.8867, 4.0112, 2.6737, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0 },
			{ 16.484, 11.4924, 8.1435, 6.8517, 6.1142, 5.265, 4.2263, 2.7407,
					0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 21.1921, 14.9441, 10.7805, 8.6072, 7.6152, 6.673, 5.6421, 4.4402,
					0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 26.5106, 18.8513, 13.7683, 10.9619, 9.5883, 8.4167, 7.2419,
					6.0208, 4.6809, 2.8883, 0, 0, 0, 0, 0, 0, 0 },
			{ 32.4488, 23.2096, 17.103, 13.6668, 11.9849, 10.6276, 9.2526,
					7.8343, 6.4072, 4.886, 2.9269, 0, 0, 0, 0, 0, 0 },
			{ 39.0085, 28.0265, 20.7887, 16.6725, 14.6853, 13.1732, 11.6852,
					10.1115, 8.4528, 6.8027, 5.1107, 2.9716, 0, 0, 0, 0, 0 },
			{ 46.1874, 33.3003, 24.8246, 19.9709, 17.6622, 15.9911, 14.406,
					12.7535, 10.9839, 9.0978, 7.207, 5.3486, 3.0662, 0, 0, 0, 0 },
			{ 53.9869, 39.0311, 29.2107, 23.5595, 20.9068, 19.0658, 17.38,
					15.6592, 13.8304, 11.8658, 9.764, 7.6255, 5.5657, 3.1891,
					0, 0, 0 },
			{ 62.4088, 45.2198, 33.9472, 27.4386, 24.4165, 22.3932, 20.6011,
					18.8073, 16.9267, 14.9167, 12.757, 10.4467, 8.0652, 5.8058,
					3.2527, 0, 0 },
			{ 71.455, 51.8684, 39.0361, 31.6041, 28.1895, 25.9713, 24.0634,
					22.1946, 20.2593, 18.2105, 16.0125, 13.6585, 11.1434,
					8.5264, 6.058, 3.3457, 0 },
			{ 81.1293, 58.9723, 44.476, 36.0599, 32.2255, 29.7978, 27.7678,
					25.8184, 23.8253, 21.7315, 19.5019, 17.1205, 14.5702,
					11.854, 9.0109, 6.3139, 3.5082 } };

	APocNeuralfieldSpec spec;
	private PocMexicanHatKernelFunction kernelFunction;

	public PocSBW3NeuralfieldsInputEquation(APocNeuralfieldSpec spec) {
		super();
		this.spec = spec;
		// TODO: Fix kernelFunction constructor values
		this.kernelFunction = new PocMexicanHatKernelFunction(0.05, 0.5, 1);
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
		boolean isT = logger.isTraceEnabled();

		// Discretize q2 in steps of size 0.025 and Offset by +0.5
		double q2 = -q[2] / 0.025 + 0.5;
		logger.trace("q2 = {}.", q2);
		// i1a: get the floor(.) of q2 and limit to [0,K_LENGHT-1]
		int i1a = (int) max(min(floor(q2), K_LENGTH - 1), 0);
		// i1b: the right neighbor of i1a and less or equal than K_LENGTH-1
		int i1b = min(i1a + 1, K_LENGTH - 1);
		logger.trace("Rows: i1a = {}, i1b = {}.", i1a, i1b);
		// w1* are calculated as the distance of q2 to its closest integers
		double w1b = q2 - floor(q2);
		double w1a = 1 - w1b;
		RealMatrix w1Matrix = new Array2DRowRealMatrix(
				new double[] { w1a, w1b }).transpose();
		logger.trace("w1Matrix = {}", isT ? w1Matrix.toString() : null);

		// Discretize q1 in steps of size 0.025 and Offset by -0.5
		double q1 = q[0] / 0.025 - 0.5;
		logger.trace("q1 = {}.", q1);
		// i2a: get the floor(.) of q1 and limit to [0,K_LENGHT-1]
		int i2a = (int) max(min(floor(q1), K_LENGTH - 1), 0);
		// i2b: the neighbor below of i2a and less or equal than K_LENGTH-1
		int i2b = min(i2a + 1, K_LENGTH - 1);
		logger.trace("Cols: i2a = {}, i2b = {}.", i2a, i2b);
		// w2* are calculated as the distance of q2 to its closest integers
		double w2b = q1 - floor(q1);
		double w2a = 1 - w2b;
		RealMatrix w2Matrix = new Array2DRowRealMatrix(
				new double[] { w2a, w2b });
		logger.trace("w2Matrix = {}", isT ? w2Matrix.toString() : null);

		// p_i is the submatrix of K used for interpolation
		double[][] p_i = { { K[i1a][i2a], K[i1a][i2b] },
				{ K[i1b][i2a], K[i1b][i2b] } };
		RealMatrix pMatrix = new Array2DRowRealMatrix(p_i);
		logger.trace("pMatrix = {}", pMatrix.toString());

		RealMatrix kMatrix = w1Matrix.multiply(pMatrix.multiply(w2Matrix));
		logger.trace("kMatrix = w1Matrix * pMatrix * w2Matrix = {}",
				isT ? kMatrix.toString() : null);

		double k_phi = kMatrix.getEntry(0, 0);
		return k_phi / MAX_K_PHI;
	}
}
