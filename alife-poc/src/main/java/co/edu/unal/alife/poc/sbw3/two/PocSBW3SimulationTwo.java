package co.edu.unal.alife.poc.sbw3.two;

import static java.lang.Math.cos;

import java.util.Arrays;

import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.DormandPrince853Integrator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.unal.alife.poc.sbw3.PocSBW3Equation;
import co.edu.unal.alife.poc.sbw3.PocSBW3FallHandler;
import co.edu.unal.alife.poc.sbw3.PocSBW3StepHandler;

public class PocSBW3SimulationTwo implements Runnable {

	static final Logger logger = LoggerFactory.getLogger(PocSBW3SimulationTwo.class);
	double[] qi;
	double gamma;
	double tf;
	PocSBW3ControllerTwo controller;

	public PocSBW3SimulationTwo(double[] qi, double gamma, PocSBW3ControllerTwo controller,
			double tf) {
		super();
		this.qi = qi;
		this.gamma = gamma;
		this.controller = controller;
		this.tf = tf;
	}

	@Override
	public void run() {
		long t1 = System.currentTimeMillis();
		double theta0 = qi[0];
		double thetap0 = qi[1];

		double[] q = new double[] { theta0, 2 * theta0, thetap0,
				(1 - cos(2 * theta0)) * thetap0 };

		FirstOrderIntegrator dp853 = new DormandPrince853Integrator(1.0e-8,
				100.0, 1.0e-10, 1.0e-10);
		PocSBW3Equation ode = new PocSBW3Equation(gamma,
				controller);
		PocSBW3StepHandler switchHandler = new PocSBW3StepHandler(ode);
		PocSBW3FallHandler fallHandler = new PocSBW3FallHandler();
		dp853.addEventHandler(switchHandler, 0.01, 0.0001, 100);
		dp853.addEventHandler(fallHandler, 0.01, 0.0001, 100);
		double t = dp853.integrate(ode, 0.0, q, tf, q);
		logger.info("DONE");
		logger.info("Final time t_f: {} s", t);
		logger.info("Result q: {}", Arrays.toString(q));
		long t2 = System.currentTimeMillis();
		logger.info("Elapsed simulation (real-life) time: {} ms",(t2-t1));
	}

	public static void main(String[] args) {
		//TODO: FIX IT (doesn't match matlab result)
		double[] qi = new double[] { 0, 0 };
		double gamma = 0.004;
		PocSBW3ControllerTwo controller = new PocSBW3ControllerTwo();
		
		double time = 20;
		PocSBW3SimulationTwo main = new PocSBW3SimulationTwo(qi, gamma, controller, time);
		Thread thread = new Thread(main);
		thread.start();
	}

}