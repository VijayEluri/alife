/**
 * 
 */
package co.edu.unal.alife.pendulum;

import jml.evolution.Fitness;
import jml.evolution.Individual;
import co.edu.unal.alife.dynamics.AbstractSolver;
import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.neuralfield.DeltaField;
import co.edu.unal.alife.output.Tracer;

/**
 * @author Juan Figueredo
 *
 */
public class PendulumControllerFitness extends Fitness {
	
	private static final int N = 3;
	
	private double initialAngle = Math.PI/6;
	private double initialPos = -5.0;
	private double hh = 1.0 / 40;
	private double t0 = 0.0;
	private double tf = 10+hh;
	
	/**
	 * 
	 */
	public PendulumControllerFitness() {
	}
	
	/**
	 * @param hh
	 * @param initialAngle
	 * @param initialPos
	 * @param t0
	 * @param tf
	 */
	public PendulumControllerFitness(double hh, double initialAngle, double initialPos, double t0,
			double tf) {
		this();
		this.hh = hh;
		this.initialAngle = initialAngle;
		this.initialPos = initialPos;
		this.t0 = t0;
		this.tf = tf;
	}



	/* (non-Javadoc)
	 * @see jml.evolution.Fitness#evaluate(jml.evolution.Individual)
	 */
	@Override
	public double evaluate(Individual obj) {
		//TODO:Verify line
		PendulumController controller = (PendulumController)obj.getThing();
		DeltaField<Double> field = controller.getField();
		
		// Setup initial values
		DeltaPopulation<Double> pendulumPopulation = field.getPopulations().get(2);
		pendulumPopulation.setElementValue(PendulumEquation.STATE_THETA, initialAngle);
		pendulumPopulation.setElementValue(PendulumEquation.STATE_X, initialPos);
		Tracer tracer = new Tracer(N);
		
		// Add Observer
		field.addObserver(tracer);

		// Run simulation
		AbstractSolver.simulate(t0, tf, hh, field);
		
		return PendulumEquation.getFitness(tracer);
	}

}
