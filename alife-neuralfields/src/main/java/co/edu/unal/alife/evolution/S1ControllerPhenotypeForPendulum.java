package co.edu.unal.alife.evolution;

import jml.evolution.Phenotype;
import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.evolution.params.S1ControllerParameters;
import co.edu.unal.alife.neuralfield.DeltaFieldFactory;
import co.edu.unal.alife.neuralfield.impl.MapDeltaPopulation;
import co.edu.unal.alife.neuralfield.impl.S1InputEquationForPendulum;
import co.edu.unal.alife.pendulum.S1PendulumEquation;
import co.edu.unal.alife.pendulum.SystemEquation;

public class S1ControllerPhenotypeForPendulum extends Phenotype {

	private int points;

	@Override
	public Object get(Object genome) {
		DeltaFieldFactory factory = DeltaFieldFactory.getInstance();
		DeltaPopulation<Double> systemPopulation = new MapDeltaPopulation(4,
				false);
		SystemEquation systemEquation = new S1PendulumEquation();

		S1InputEquationForPendulum inputEquation = new S1InputEquationForPendulum(
				systemPopulation);
		return factory.buildLayeredS1Field((S1ControllerParameters) genome,
				points, systemPopulation, systemEquation, inputEquation);
	}
}
