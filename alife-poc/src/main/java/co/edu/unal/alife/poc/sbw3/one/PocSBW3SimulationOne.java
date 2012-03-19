package co.edu.unal.alife.poc.sbw3.one;

import static java.lang.Math.cos;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.Callable;

import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.DormandPrince853Integrator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.unal.alife.poc.sbw3.APocSBW3Controller;
import co.edu.unal.alife.poc.sbw3.PocSBW3Equation;
import co.edu.unal.alife.poc.sbw3.PocSBW3FallHandler;
import co.edu.unal.alife.poc.sbw3.PocSBW3SimulationResult;
import co.edu.unal.alife.poc.sbw3.PocSBW3StepHandler;

public class PocSBW3SimulationOne implements Callable<PocSBW3SimulationResult> {

	static final Logger logger = LoggerFactory.getLogger(PocSBW3SimulationOne.class);
	double[] qinit;
	double gamma;
	double tf;
	APocSBW3Controller controller;

	/**
	 * PcSBW3MainOne constructor.
	 * 
	 * @param qi
	 *            initial vector before step switch, in the form [\theta-,
	 *            \phi-].
	 * @param gamma
	 *            slope of walking surface
	 * @param controller
	 * @param tf
	 *            simulation final time
	 * @param qAssert
	 *            the expected final state vector (asserted if not null)
	 */
	public PocSBW3SimulationOne(double[] qi, double gamma,
			APocSBW3Controller controller, double tf) {
		super();
		this.qinit = qi;
		this.gamma = gamma;
		this.controller = controller;
		this.tf = tf;
	}

	@Override
	public PocSBW3SimulationResult call() {
		long t1 = System.currentTimeMillis();
		double theta0 = qinit[0];
		double thetap0 = qinit[1];

		double[] q = new double[] { theta0, 2 * theta0, thetap0,
				(1 - cos(2 * theta0)) * thetap0 };
		logger.info("Initial state q+ (after transition): {}",
				Arrays.toString(q));

		FirstOrderIntegrator dp853 = new DormandPrince853Integrator(1.0e-8,
				100.0, 1.0e-10, 1.0e-10);
		PocSBW3Equation ode = new PocSBW3Equation(gamma,
				controller);
		PocSBW3StepHandler switchTest = new PocSBW3StepHandler(ode);
		PocSBW3FallHandler fallTest = new PocSBW3FallHandler();
		dp853.addEventHandler(switchTest, 0.01, 1e-9, 100);
		dp853.addEventHandler(fallTest, 0.01, 1e-9, 100);
		logger.info("Simulation started at {} ...", new Date());
		double t = dp853.integrate(ode, 0.0, q, tf, q);
		logger.info("Simulation ended!");
		long t2 = System.currentTimeMillis();
		logger.info("Elapsed simulation (real-life) time: {} ms", (t2 - t1));
		logger.info("Final time t_f: {} s", t);
		logger.info("Result q: {}", Arrays.toString(q));
		return new PocSBW3SimulationResult(t, q);
	}

}