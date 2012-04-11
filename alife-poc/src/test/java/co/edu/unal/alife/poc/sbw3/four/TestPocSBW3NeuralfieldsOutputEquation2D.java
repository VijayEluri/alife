package co.edu.unal.alife.poc.sbw3.four;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.unal.alife.poc.sbw3.PocSBW3Lookup;

public class TestPocSBW3NeuralfieldsOutputEquation2D {
	static final Logger logger = LoggerFactory
			.getLogger(TestPocSBW3NeuralfieldsOutputEquation2D.class);
	private static final int QN = 4;
	private static final int KN = 17;

	@Test
	public void testEvalOutput() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetK() {
		PocSBW3NeuralfieldSpec2D spec2d = new PocSBW3NeuralfieldSpec2D(QN, KN,
				KN);
		PocSBW3NeuralfieldsOutputEquation2D outputEquation2D = new PocSBW3NeuralfieldsOutputEquation2D(
				spec2d);

		/*
		 * Test [0,-0.2] -> K[8][0]
		 */
		logger.info("Test [0,-0.2] -> [8,0]");
		double theta = 0;
		double thetaDot = -0.2;
		double posX = -thetaDot / 0.4;
		double posY = theta / 0.4;
		int expectedX = (8 * PocSBW3Lookup.K_LENGTH) / 16;
		int expectedY = (0 * PocSBW3Lookup.K_LENGTH) / 16;
		logger.info("expectedX: {}. expectedY: {}", expectedX, expectedY);
		double expectedK = PocSBW3Lookup.K[expectedX][expectedY];
		double k = outputEquation2D.getK(posX, posY);
		logger.info("k: {}", k);
		assertEquals(expectedK, k, Double.MIN_VALUE);

		/*
		 * Test [0.05,-0.35] -> K[1][3]
		 */
		logger.info("Test [0,-0.4] -> [8/16,0/16]");
		expectedX = (8 * PocSBW3Lookup.K_LENGTH) / 16;
		expectedY = (0 * PocSBW3Lookup.K_LENGTH) / 16;
		logger.info("expectedX: {}. expectedY: {}", expectedX, expectedY);
		expectedK = PocSBW3Lookup.K[expectedX][expectedY];
		k = outputEquation2D.getK(0.5, 0);
		logger.info("k: {}", k);
		assertEquals(expectedK, k, Double.MIN_VALUE);

	}

}
