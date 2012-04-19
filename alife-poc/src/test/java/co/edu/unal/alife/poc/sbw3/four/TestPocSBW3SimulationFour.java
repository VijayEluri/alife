package co.edu.unal.alife.poc.sbw3.four;

import static org.junit.Assert.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.unal.alife.poc.sbw3.APocSBW3Controller;
import co.edu.unal.alife.poc.sbw3.PocSBW3Simulation;
import co.edu.unal.alife.poc.sbw3.PocSBW3SimulationResult;

public class TestPocSBW3SimulationFour {
	static final Logger logger = LoggerFactory
			.getLogger(TestPocSBW3SimulationFour.class);
	static final double TOL = 1e-8;
	static final double T_TOL = 1e-6;

	@Test
	public void testRunWithoutFall() {
		logger.info("\nStart testRunWithFall");
		double time = 300;
		double h = 1d / 17;
		double[] qinit = new double[] { h / 2, -0.4 + h / 2 };
		double gamma = 0.004d;
		APocSBW3Controller controller = new PocSBW3ControllerFour();

		// Should not fall within 5min
		double timeAssert = 300;

		Callable<PocSBW3SimulationResult> sim = new PocSBW3Simulation(qinit,
				gamma, controller, time, true, true);
		ExecutorService pool = Executors.newFixedThreadPool(1);
		Future<PocSBW3SimulationResult> future = pool.submit(sim);
		PocSBW3SimulationResult result = null;
		try {
			result = future.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		assertEquals(
				"PocSBW3SimulationOne->Future->PocSBW3SimulationResult->getT equality assertion failed.",
				timeAssert, result.getT(), T_TOL);
	}
}
