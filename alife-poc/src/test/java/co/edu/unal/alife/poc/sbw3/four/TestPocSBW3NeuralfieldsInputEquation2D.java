package co.edu.unal.alife.poc.sbw3.four;

import static org.junit.Assert.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestPocSBW3NeuralfieldsInputEquation2D {
	static final Logger logger = LoggerFactory
			.getLogger(TestPocSBW3NeuralfieldsInputEquation2D.class);
	private static final int QN = 4;
	private static final int KN = 17;

	@Test
	public void testEvalInputDelta() {
		PocSBW3NeuralfieldSpec2D spec2d = new PocSBW3NeuralfieldSpec2D(QN, KN,
				KN);
		PocSBW3NeuralfieldsInputEquation2D inputEquation2D = new PocSBW3NeuralfieldsInputEquation2D(
				spec2d);
		/*
		 * Test [0,-0.4] -> K[16][0]
		 */
		logger.info("Test [0,-0.4] -> K[16][0]");
		double out[] = new double[KN * KN];
		double[] lastSwitchQ = { 0, 0, -0.4, 0 };
		int expectedIndex = QN + (KN * 16 + 0);
		int maxIndex = -1;
		double maxValue = Double.NEGATIVE_INFINITY;
		for (int i = spec2d.getStartIndex(); i <= spec2d.getEndIndex(); i++) {
			double delta = inputEquation2D.evalInputDelta(lastSwitchQ, i);
			out[i - spec2d.getStartIndex()] = delta;
			if (delta > maxValue) {
				maxValue = delta;
				maxIndex = i;
			}
		}
		assertTrue(maxIndex == expectedIndex);
		logger.info("qDot: {}", out);
		
		/*
		 * Test [0,-0.2] -> K[8][0]
		 */
		logger.info("Test [0,-0.2] -> K[8][0]");
		out = new double[KN * KN];
		lastSwitchQ = new double[ ]{ 0, 0, -0.2, 0 };
		expectedIndex = QN + (KN * 8 + 0);
		maxIndex = -1;
		maxValue = Double.NEGATIVE_INFINITY;
		for (int i = spec2d.getStartIndex(); i <= spec2d.getEndIndex(); i++) {
			double delta = inputEquation2D.evalInputDelta(lastSwitchQ, i);
			out[i - spec2d.getStartIndex()] = delta;
			if (delta > maxValue) {
				maxValue = delta;
				maxIndex = i;
			}
		}
		assertTrue(maxIndex == expectedIndex);
		logger.info("qDot: {}", out);

		/*
		 * Test [0,0] -> K[0][0]
		 */
		logger.info("Test [0,0] -> K[0][0]");
		out = new double[KN * KN];
		lastSwitchQ = new double[ ]{ 0, 0, 0, 0 };
		expectedIndex = QN + (KN * 0 + 0);
		maxIndex = -1;
		maxValue = Double.NEGATIVE_INFINITY;
		for (int i = spec2d.getStartIndex(); i <= spec2d.getEndIndex(); i++) {
			double delta = inputEquation2D.evalInputDelta(lastSwitchQ, i);
			out[i - spec2d.getStartIndex()] = delta;
			if (delta > maxValue) {
				maxValue = delta;
				maxIndex = i;
			}
		}
		assertTrue(maxIndex == expectedIndex);
		logger.info("qDot: {}", out);

		/*
		 * Test [0.2,-0.4] -> K[16][8]
		 */
		logger.info("Test [0.2,-0.4] -> K[16][8]");
		out = new double[KN * KN];
		lastSwitchQ = new double[ ]{ 0.2, 0, -0.4, 0 };
		expectedIndex = QN + (KN * 16 + 8);
		maxIndex = -1;
		maxValue = Double.NEGATIVE_INFINITY;
		for (int i = spec2d.getStartIndex(); i <= spec2d.getEndIndex(); i++) {
			double delta = inputEquation2D.evalInputDelta(lastSwitchQ, i);
			out[i - spec2d.getStartIndex()] = delta;
			if (delta > maxValue) {
				maxValue = delta;
				maxIndex = i;
			}
		}
		assertTrue(maxIndex == expectedIndex);
		logger.info("qDot: {}", out);
		
		/*
		 * Test [0.2,-0.2] -> K[8][8]
		 */
		logger.info("Test [0.2,-0.2] -> K[8][8]");
		out = new double[KN * KN];
		lastSwitchQ = new double[ ]{ 0.2, 0, -0.2, 0 };
		expectedIndex = QN + (KN * 8 + 8);
		maxIndex = -1;
		maxValue = Double.NEGATIVE_INFINITY;
		for (int i = spec2d.getStartIndex(); i <= spec2d.getEndIndex(); i++) {
			double delta = inputEquation2D.evalInputDelta(lastSwitchQ, i);
			out[i - spec2d.getStartIndex()] = delta;
			if (delta > maxValue) {
				maxValue = delta;
				maxIndex = i;
			}
		}
		assertTrue(maxIndex == expectedIndex);
		logger.info("qDot: {}", out);
		
		/*
		 * Test [0.4,-0.4] -> K[16][16]
		 */
		logger.info("Test [0.4,-0.4] -> K[16][16]");
		out = new double[KN * KN];
		lastSwitchQ = new double[ ]{ 0.4, 0, -0.4, 0 };
		expectedIndex = QN + (KN * 16 + 16);
		maxIndex = -1;
		maxValue = Double.NEGATIVE_INFINITY;
		for (int i = spec2d.getStartIndex(); i <= spec2d.getEndIndex(); i++) {
			double delta = inputEquation2D.evalInputDelta(lastSwitchQ, i);
			out[i - spec2d.getStartIndex()] = delta;
			if (delta > maxValue) {
				maxValue = delta;
				maxIndex = i;
			}
		}
		logger.info("qDot: {}", out);
		assertTrue(maxIndex == expectedIndex);
	}

}
