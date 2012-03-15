package co.edu.unal.alife.poc.sbw3.one;

import static java.lang.Math.cos;

import java.util.Arrays;

import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.DormandPrince853Integrator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.unal.alife.poc.sbw3.APocSBW3Controller;
import co.edu.unal.alife.poc.sbw3.PocSBW3Equation;
import co.edu.unal.alife.poc.sbw3.PocSBW3FallTest;
import co.edu.unal.alife.poc.sbw3.PocSBW3SwitchTest;

public class PocSBW3MainOne implements Runnable {

	static final Logger logger = LoggerFactory.getLogger(PocSBW3MainOne.class);
	double[] qi;
	double gamma;
	double tf;
	APocSBW3Controller controller;

	public PocSBW3MainOne(double[] qi, double gamma, APocSBW3Controller controller,
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
		FirstOrderDifferentialEquations ode = new PocSBW3Equation(gamma,
				controller);
		PocSBW3SwitchTest switchTest = new PocSBW3SwitchTest();
		PocSBW3FallTest fallTest = new PocSBW3FallTest();
		dp853.addEventHandler(switchTest, 0.01, 0.0001, 100);
		dp853.addEventHandler(fallTest, 0.01, 0.0001, 100);
		double t = dp853.integrate(ode, 0.0, q, tf, q);
		logger.info("DONE");
		logger.info("Final time t_f: {} s", t);
		logger.info("Result q: {}", Arrays.toString(q));
		long t2 = System.currentTimeMillis();
		logger.info("Elapsed simulation (real-life) time: {} ms",(t2-t1));
	}

	public static void main(String[] args) {
		double[] qi = new double[] { 0, 0 };
		double gamma = 0.004;
		APocSBW3Controller controller = new PocSBW3ControllerOne();
		
		double time = 20;
		PocSBW3MainOne main = new PocSBW3MainOne(qi, gamma, controller, time);
		Thread thread = new Thread(main);
		thread.start();
	}

}