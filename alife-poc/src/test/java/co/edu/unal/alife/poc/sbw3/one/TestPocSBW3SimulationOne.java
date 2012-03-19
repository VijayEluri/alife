package co.edu.unal.alife.poc.sbw3.one;

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
import co.edu.unal.alife.poc.sbw3.PocSBW3SimulationResult;

public class TestPocSBW3SimulationOne {
	static final Logger logger = LoggerFactory
			.getLogger(TestPocSBW3SimulationOne.class);
	static final double TOL = 1e-8;
	static final double T_TOL = 1e-6;

	@Test
	public void testRun() {
		logger.info("\nStart testRun");
		double time = 1;
		double[] qinit = new double[] { 0, 0 };
		double gamma = 0.004;
		APocSBW3Controller controller = new PocSBW3ControllerOne();
		/**
		 * From alife-matlab: sbw3_fitness([0 81.1293],[0 0],true), with Tol =
		 * 1e-12 and time=1.
		 */
		double[] qAssert = new double[] { -0.002172314996943,
				-0.296167733150634, -0.004700784275364, -0.002567482685666 };

		Callable<PocSBW3SimulationResult> sim = new PocSBW3SimulationOne(qinit,
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
		double time = 14.781589013421927;
		double[] qinit = new double[] { 0, 0 };
		double gamma = 0.004;
		APocSBW3Controller controller = new PocSBW3ControllerOne();
		/**
		 * From alife-matlab: sbw3_fitness([0 81.1293],[0 0],true), with Tol =
		 * 1e-12 and time=10 (which simulates up to time=14.781589013421927).
		 */
		double[] qAssert = new double[] { -0.148994671205207,
				-0.297989342410413, -0.155047578276319, -0.001698804732674 };

		Callable<PocSBW3SimulationResult> sim = new PocSBW3SimulationOne(qinit,
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
	public void testRunWithFall() {
		logger.info("\nStart testRunWithFall");
		double time = 10;
		double[] qinit = new double[] { 0, 0 };
		double gamma = 0.004d;
		double k_phi = 0d;
		APocSBW3Controller controller = new PocSBW3ControllerOne(k_phi);
		/**
		 * From alife-matlab: sbw3_fitness([0 0],[0 0],true), with Tol = 1e-12
		 * and time=10.
		 */
		double timeAssert = 6.722359460085634d;
		double[] qAssert = new double[] { -1.570796326794916,
				-1.082837004367210, -1.417033513607968, -1.556610074585741 };

		Callable<PocSBW3SimulationResult> sim = new PocSBW3SimulationOne(qinit,
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
				timeAssert, result.getT(), T_TOL);

		assertArrayEquals(
				"PocSBW3SimulationOne->Future->PocSBW3SimulationResult->getQ equality assertion failed.",
				qAssert, result.getQ(), TOL);
	}
}
