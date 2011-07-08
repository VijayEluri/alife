package co.edu.unal.alife.dynamics;

import java.util.List;
import java.util.Set;

import co.edu.unal.alife.neuralfield.AbstractDeltaField;
import co.edu.unal.alife.neuralfield.AbstractKernelFunction;
import co.edu.unal.alife.neuralfield.DeltaEquation;

/**
 * @author Juan Figueredo
 * 
 *         Abstract class that implements common functionality of DeltaPopulation implementations, such as toString,
 *         nextPopulation accesors, and updatePopulationDelta.
 * 
 */
public abstract class AbstractDeltaPopulation implements DeltaPopulation {
	private DeltaPopulation	nextPopulation;
	
	@Override
	public DeltaPopulation getNextPopulation() {
		return nextPopulation;
	}
	
	@Override
	public void setNextPopulation(DeltaPopulation nextPopulation) {
		this.nextPopulation = nextPopulation;
	}
	
	@Override
	public boolean hasNextPopulation() {
		return (nextPopulation != null) ? true : false;
	}
	
	@Override
	public String toString() {
		Set<Double> positions = this.getPositions();
		StringBuffer sb = new StringBuffer();
		for (Double position : positions) {
			sb.append(this.getElementValue(position));
			sb.append("\t");
		}
		return sb.toString();
	}
	
	@Override
	public void updatePopulationDelta(AbstractDeltaField environment, int localIndex)
			throws UnsupportedOperationException {
		List<DeltaPopulation> populations = environment.getPopulations();
		DeltaEquation equation = environment.getEquations().get(localIndex);
		List<AbstractKernelFunction> kernelList = environment.getKernelMatrix().get(localIndex);
		equation.evalEquation(this, populations, kernelList); //may throw UnsupportedOperationException
	}
}
