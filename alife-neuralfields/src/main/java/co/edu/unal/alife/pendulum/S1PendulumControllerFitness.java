package co.edu.unal.alife.pendulum;

import java.util.List;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.dynamics.SolverUtility;
import co.edu.unal.alife.neuralfield.DeltaField;
import co.edu.unal.alife.output.Tracer;
import jml.evolution.Fitness;
import jml.evolution.Individual;

public class S1PendulumControllerFitness extends Fitness {
	
	private static final int N = 1 + 2 + 1 + 1;
	
	private double initialAngle = Math.PI/6;
	private double initialPos = -5.0;
	private double hh = 1.0 / 40;
	private double t0 = 0.0;
	private double tf = 10+hh;
	
	public S1PendulumControllerFitness() {
		super();
	}

	@SuppressWarnings("unchecked")
	@Override
	public double evaluate(Individual obj) {
		DeltaField<Double> field = (DeltaField<Double>) obj.getThing();

		// Setup initial values
		List<DeltaPopulation<Double>> pops = field.getPopulations();
		DeltaPopulation<Double> pendulumPopulation = pops.get(pops.size() - 1);
		pendulumPopulation.setElementValue(PendulumEquation.STATE_THETA,
				initialAngle);
		pendulumPopulation
				.setElementValue(PendulumEquation.STATE_X, initialPos);
		Tracer tracer = new Tracer(N);

		// Add Observer
		field.addObserver(tracer);

		// Run simulation
		SolverUtility.simulate(t0, tf, hh, field);

		return S1PendulumEquation.getFitness(tracer);
	}

}
