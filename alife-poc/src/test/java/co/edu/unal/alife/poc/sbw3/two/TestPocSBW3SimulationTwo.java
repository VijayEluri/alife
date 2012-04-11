package co.edu.unal.alife.poc.sbw3.two;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

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

public class TestPocSBW3SimulationTwo {
	static final Logger logger = LoggerFactory
			.getLogger(TestPocSBW3SimulationTwo.class);
	static final double TOL = 1e-8;
	static final double T_TOL = 1e-6;

	@Test
	public void testRun() {
		logger.info("\nStart testRun");
		double[] qinit = new double[] { 0, 0 };
		double gamma = 0.004;
		APocSBW3Controller controller = new PocSBW3ControllerTwo();
		/**
		 * From alife-matlab: sbw4_main2([0 0]), with Tol = 1e-12 and time=4.
		 */
		double time = 4;
		double[] qAssert = new double[] { -0.105205390431593,
				-0.217690971613171, -0.109077815975517, -0.015052213314171 };

		Callable<PocSBW3SimulationResult> sim = new PocSBW3Simulation(qinit,
				gamma, controller, time);
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
				time, result.getT(), TOL);

		assertArrayEquals(
				"PocSBW3SimulationOne->Future->PocSBW3SimulationResult->getQ equality assertion failed.",
				qAssert, result.getQ(), TOL);
	}

	@Test
	public void testRunWitSwitch() {
		logger.info("\nStart testRunWithSwitch");
		double[] qinit = new double[] { 0, 0 };
		double gamma = 0.004;
		APocSBW3Controller controller = new PocSBW3ControllerTwo();
		
		/**
		 * From alife-matlab: sbw4_main2([0 0]), with Tol = 1e-12 and time=5.
		 */
		double time = 11.473241591023712;
		double[] qAssert = new double[] { -0.127204882205017,
				-0.254409764410028, -0.138601614928556, -0.015278646716122 };

		Callable<PocSBW3SimulationResult> sim = new PocSBW3Simulation(qinit,
				gamma, controller, time);
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
				time, result.getT(), T_TOL);

		assertArrayEquals(
				"PocSBW3SimulationOne->Future->PocSBW3SimulationResult->getQ equality assertion failed.",
				qAssert, result.getQ(), TOL);
	}

	@Test
	public void testRunWithoutFall() {
		logger.info("\nStart testRunWithFall");
		double time = 600;
		double[] qinit = new double[] { 0, 0 };
		double gamma = 0.004d;
		APocSBW3Controller controller = new PocSBW3ControllerTwo();
		
		// Should not fall within 1h
		double timeAssert = 600;

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
