/**
 * 
 */
package co.edu.unal.alife.applications;

import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import co.edu.unal.alife.dynamics.Derivable;
import co.edu.unal.alife.dynamics.RungeKutta4thSolver;
import co.edu.unal.alife.neuralfield.DeltaPopulation;
import co.edu.unal.alife.neuralfield.KernelFunction;
import co.edu.unal.alife.neuralfield.NeuralPopulationEquation;
import co.edu.unal.alife.neuralfield.impl.DerivableNeuralField;
import co.edu.unal.alife.neuralfield.impl.MapNeuralPopulation;
import co.edu.unal.alife.neuralfield.impl.MexicanHatKernel;
import co.edu.unal.alife.neuralfield.impl.SimpleEquation;
import co.edu.unal.alife.neuralfield.impl.StaticEquation;

/**
 * @author Juan Figueredo
 * 
 */
public class FirstProblemSimulation implements Derivable<Double> {

	private InvertedPendulum pendulum;
	private DerivableNeuralField<Double> field;
	private List<Double> pendulumValues = new ArrayList<Double>(4);

	/**
	 * @param field
	 * @param pendulum
	 * @param pendulumValues
	 */
	public FirstProblemSimulation(DerivableNeuralField<Double> field,
			InvertedPendulum pendulum, List<Double> pendulumValues) {
		super();
		this.field = field;
		this.pendulum = pendulum;
		this.pendulumValues = pendulumValues;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.edu.unal.alife.dynamics.Derivable#getDeltas(java.util.List,
	 * double)
	 */
	@Override
	public List<Double> getDeltas(List<Double> values, double t) {
		pendulumValues = values;
		List<Double> fieldDeltas = field.getDeltasAsDerivable();
		List<Double> pendulumDeltas = pendulum.getPendulumDeltas(
				pendulumValues, null, t);
		List<Double> deltas = new ArrayList<Double>(fieldDeltas.size()
				+ pendulumDeltas.size());
		deltas.addAll(fieldDeltas);
		deltas.addAll(pendulumDeltas);
		return deltas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.edu.unal.alife.dynamics.Derivable#getDeltas(int, java.util.List,
	 * double)
	 */
	@Override
	public Double getDeltas(int arg0, List<Double> arg1, double arg2) {
		throw new NotImplementedException();
	}

	public List<Double> inputFunction(double halfSize, double t) {
		double thetaDot = pendulum.getPendulumDeltas(pendulumValues, null, t)
				.get(3);
		double boundedValue = bipolarSigmoid(thetaDot) * halfSize;
		long eqPosition = Math.round(boundedValue + halfSize);
		List<Double> list = new ArrayList<Double>();
		for (int i = 0; i < 2 * halfSize + 1; i++) {
			if (i == eqPosition) {
				list.add(1.0);
			} else {
				list.add(0.0);
			}
		}
		list.add(thetaDot);
		return list;
	}

	public static double bipolarSigmoid(double t) {
		return (1 - Math.exp(-t)) / (1 + Math.exp(-t));
	}

	public static void main(String[] args) {
		int layers = 2;
		int halfSize = 10;
		int mainSize = halfSize * 2 + 1;
		int pendulumStates = 4;
		int derivableSize = 2 * mainSize + pendulumStates;
		double hh = 1 / 100;
		double traceTime = 1 / 20;
		double t0 = 0;

		// Initial values setup
		List<Double> pendulumValues = new ArrayList<Double>(pendulumStates);
		for (int i = 0; i < pendulumStates; i++) {
			pendulumValues.add(0.0);
		}
		List<Double> initialValues = new ArrayList<Double>(derivableSize);
		for (int i = 0; i < derivableSize; i++) {
			pendulumValues.add(0.0);
		}

		InvertedPendulum pendulum = new InvertedPendulum();

		// Populations setup
		List<DeltaPopulation<Double>> populations = new ArrayList<DeltaPopulation<Double>>(
				layers);
		MapNeuralPopulation inputPopulation = new MapNeuralPopulation(halfSize);
		MapNeuralPopulation outputPopulation = new MapNeuralPopulation(halfSize);
		populations.add(inputPopulation);
		populations.add(outputPopulation);

		// Kernel Matrix setup
		List<List<KernelFunction>> kernelMatrix = new ArrayList<List<KernelFunction>>(
				layers);
		List<KernelFunction> firstRow = new ArrayList<KernelFunction>(layers);
		List<KernelFunction> secondRow = new ArrayList<KernelFunction>(layers);
		KernelFunction kernelFunction = new MexicanHatKernel();
		firstRow.set(0, kernelFunction);
		firstRow.set(1, kernelFunction);
		secondRow.set(0, kernelFunction);
		secondRow.set(1, null);
		kernelMatrix.add(firstRow);
		kernelMatrix.add(secondRow);

		// Equations setup
		List<NeuralPopulationEquation<Double>> equations = new ArrayList<NeuralPopulationEquation<Double>>(
				layers);
		StaticEquation staticEquation = new StaticEquation();
		SimpleEquation simpleEquation = new SimpleEquation();
		equations.add(staticEquation);
		equations.add(simpleEquation);
		DerivableNeuralField<Double> field = new DerivableNeuralField<Double>(
				equations, kernelMatrix, populations);

		FirstProblemSimulation problemSimulation = new FirstProblemSimulation(
				field, pendulum, pendulumValues);

		// Solver setup
		RungeKutta4thSolver solver = new RungeKutta4thSolver(initialValues, hh,
				t0, problemSimulation, true, traceTime);

	}
}
