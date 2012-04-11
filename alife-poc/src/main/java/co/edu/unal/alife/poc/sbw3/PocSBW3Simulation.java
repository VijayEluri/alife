package co.edu.unal.alife.poc.sbw3;

import static java.lang.Math.cos;

import java.util.Date;
import java.util.concurrent.Callable;

import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.events.EventHandler;
import org.apache.commons.math3.ode.nonstiff.DormandPrince853Integrator;
import org.apache.commons.math3.ode.sampling.FixedStepHandler;
import org.apache.commons.math3.ode.sampling.StepNormalizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PocSBW3Simulation implements Callable<PocSBW3SimulationResult> {

	private static final double T_TOL = 1.0e-8;
	private static final double TOL = 1.0e-10;
	private static final double TRACE_STEP_SIZE = 0.1;

	protected static final Logger logger = LoggerFactory
			.getLogger(PocSBW3Simulation.class);

	protected double[] qinit;
	protected double gamma;
	protected double tf;
	protected APocSBW3Controller controller;
	protected PocSBW3Equation ode;
	protected FirstOrderIntegrator solver;

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
	 */
	public PocSBW3Simulation(double[] qi, double gamma,
			APocSBW3Controller controller, double tf) {
		this(qi, gamma, controller, tf, false, false);
	}

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
	 * @param stepHandler
	 *            FixedStepHandler used for capturing data on each step
	 * @param samplingStep
	 *            the step used for sampling with the given stepHandler
	 */
	public PocSBW3Simulation(double[] qi, double gamma,
			APocSBW3Controller controller, double tf,
			boolean stepTrace, boolean eventTrace) {
		super();
		this.qinit = qi;
		this.gamma = gamma;
		this.controller = controller;
		this.tf = tf;
		buildSimulation(stepTrace, eventTrace);
	}

	protected void buildSimulation(boolean stepTrace, boolean eventTrace) {
		solver = new DormandPrince853Integrator(T_TOL, 100.0, TOL, TOL);
		ode = new PocSBW3Equation(gamma, controller);
		EventHandler switchHandler;
		EventHandler fallHandler = new PocSBW3FallHandler();
		if (stepTrace) {
			double traceStepSize = Math.min(tf/100,TRACE_STEP_SIZE);
			FixedStepHandler stepHandler = new PocSBW3WriterStepHandler(controller);
			StepNormalizer normalizer = new StepNormalizer(traceStepSize, stepHandler);
			solver.addStepHandler(normalizer);
		}
		if(eventTrace) {
			switchHandler = new PocSBW3WriterSwitchHandler(ode, controller);
		}else{
			switchHandler = new PocSBW3SwitchHandler(ode, controller);
		}
		solver.addEventHandler(switchHandler, 0.01, 1e-9, 100);
		solver.addEventHandler(fallHandler, 0.01, 1e-9, 100);
	}

	@Override
	public PocSBW3SimulationResult call() {
		long t1 = System.currentTimeMillis();
		double theta0 = qinit[0];
		double thetap0 = qinit[1];

		double[] q = new double[ode.getDimension()];
		q[0] = theta0;
		q[1] = 2 * theta0;
		q[2] = thetap0;
		q[3] = (1 - cos(2 * theta0)) * thetap0;
		controller.init(0, q);
		logger.info("Initial state q+ (after transition): {}",
				q);
		logger.info("Simulation started at {} ...", new Date());
		double t = solver.integrate(ode, 0.0, q, tf, q);
		logger.info("Simulation ended!");
		long t2 = System.currentTimeMillis();
		logger.info("Elapsed simulation (real-life) time: {} ms", (t2 - t1));
		logger.info("Final time t_f: {} s", t);
		logger.info("Result q: {}", q);
		return new PocSBW3SimulationResult(t, q);
	}

}