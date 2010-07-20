package co.edu.unal.alife.pendulum;

import java.util.List;

import jml.evolution.Fitness;
import jml.evolution.Individual;
import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.neuralfield.AbstractDeltaField;
import co.edu.unal.alife.output.Tracer;

public class S1PendulumControllerFitness extends Fitness {
	
	private int N;
	
	private double initialAngle = Math.PI/10;
	private double initialPos = -5.0;
	private double hh = 1.0 / 40;
	private double t0 = 0.0;
	private double tf = 5+hh;
	
	public S1PendulumControllerFitness(int N) {
		super();
		this.N = N;
	}

	@SuppressWarnings("unchecked")
	@Override
	public double evaluate(Individual obj) {
		AbstractDeltaField<Double> field = (AbstractDeltaField<Double>) obj.getThing();

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
		field.getSolver().simulate(t0, tf, hh, field);

		return S1PendulumEquation.getFitness(tracer);
	}

}
