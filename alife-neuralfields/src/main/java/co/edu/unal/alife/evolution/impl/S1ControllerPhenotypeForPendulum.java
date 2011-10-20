package co.edu.unal.alife.evolution.impl;

import jml.evolution.Phenotype;
import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.dynamics.impl.SystemEquation;
import co.edu.unal.alife.evolution.param.S1ControllerParameters;
import co.edu.unal.alife.neuralfield.impl.DeltaFieldFactory;
import co.edu.unal.alife.pendulum.S1PendulumEquation;

public class S1ControllerPhenotypeForPendulum extends Phenotype {

	private static final int SYSTEM_ORDER = 4;
	private int points;
	private DeltaPopulation refSystemPopulation;

	public S1ControllerPhenotypeForPendulum(int points,
			DeltaPopulation refSystemPopulation) {
		super();
		this.points = points;
		this.refSystemPopulation = refSystemPopulation;
	}

	@Override
	public Object get(Object genome) {
		DeltaFieldFactory factory = DeltaFieldFactory.getInstance();
		DeltaPopulation systemPopulation = refSystemPopulation
				.cloneEmpty(SYSTEM_ORDER);
		SystemEquation systemEquation = new S1PendulumEquation();

		return factory.buildLayeredS1Field((S1ControllerParameters) genome,
				points, systemPopulation, systemEquation);
	}
}
