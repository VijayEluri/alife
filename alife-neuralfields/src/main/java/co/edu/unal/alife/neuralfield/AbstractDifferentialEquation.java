package co.edu.unal.alife.neuralfield;

import java.util.List;

import co.edu.unal.alife.dynamics.DeltaPopulation;

public abstract class AbstractDifferentialEquation implements DeltaEquation {

	public AbstractDifferentialEquation() {
		super();
	}

	/**
	 * Heaviside firing rate function
	 * 
	 * @param value
	 * @return the firing rate for the given value
	 */
	public static Double heavisideFunction(Double value) {
		return value > 0 ? 1.0 : 0.0;
	}

	@Override
	public DeltaPopulation applyInput(DeltaPopulation deltaPopulation) {
		throw new UnsupportedOperationException();
	}


	/* (non-Javadoc)
	 * @see co.edu.unal.alife.neuralfield.DeltaEquation#evalEquation(co.edu.unal.alife.dynamics.DeltaPopulation, java.util.List, java.util.List)
	 */
	public abstract void evalEquation(DeltaPopulation localPopulation,
			List<DeltaPopulation> populations, List<AbstractKernelFunction> kernelList)
			throws UnsupportedOperationException;

	@Override
	public boolean isDifferential() {
		return true;
	}

	@Override
	public boolean requiresApplyInput() {
		return false;
	}

	
}