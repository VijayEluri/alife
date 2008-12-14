/**
 * 
 */
package co.edu.unal.alife.applications;

import java.util.ArrayList;
import java.util.Arrays;
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
import co.edu.unal.alife.neuralfield.impl.InputEquation;
import co.edu.unal.alife.output.core.PendulumTracer;
import co.edu.unal.alife.output.core.SimpleOutputTracer;
import co.edu.unal.alife.output.core.Visualizer;

/**
 * @author Juan Figueredo
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
	public FirstProblemSimulation(DerivableNeuralField<Double> field, InvertedPendulum pendulum,
			List<Double> pendulumValues) {
		super();
		this.field = field;
		this.pendulum = pendulum;
		this.pendulumValues = pendulumValues;
	}

	/*
	 * (non-Javadoc)
	 * @see co.edu.unal.alife.dynamics.Derivable#getDeltas(java.util.List, double)
	 */
	@Override
	public List<Double> getDeltas(List<Double> values, double t) {
		pendulumValues = values;
		List<Double> fieldDeltas = field.evaluateSimulableAsDerivable(values);
		List<Double> pendulumDeltas = pendulum.getPendulumDeltas(pendulumValues, null, t);
		List<Double> deltas = new ArrayList<Double>(fieldDeltas.size() + pendulumDeltas.size());
		deltas.addAll(fieldDeltas);
		deltas.addAll(pendulumDeltas);
		return deltas;
	}

	/*
	 * (non-Javadoc)
	 * @see co.edu.unal.alife.dynamics.Derivable#getDeltas(int, java.util.List, double)
	 */
	@Override
	public Double getDeltas(int arg0, List<Double> arg1, double arg2) {
		throw new NotImplementedException();
	}


	public static void main(String[] args) {
		int layers = 2;
		int halfSize = 10;
		int mainSize = halfSize * 2 + 1;
		int pendulumStates = 4;
		int derivableSize = 2 * mainSize + pendulumStates;
		double initialAngle = 0.2;
		double hh = 1.0/1000;
		int traceSkip = 100;
		double t0 = 0.0;
		double tf = 10.0;

		// Initial values setup
		List<Double> pendulumValues = Arrays.asList(new Double[]{0.0, initialAngle, 0.0, 0.0});
		List<Double> fieldValues = new ArrayList<Double>(mainSize);
		for (int i = 0; i < mainSize; i++) {
			fieldValues.add(0.0);
		}
		List<Double> initialValues = new ArrayList<Double>(derivableSize);
		initialValues.addAll(pendulumValues);
		initialValues.addAll(fieldValues);
		initialValues.addAll(fieldValues);

		InvertedPendulum pendulum = new InvertedPendulum();

		// Populations setup
		List<DeltaPopulation<Double>> populations = new ArrayList<DeltaPopulation<Double>>(layers);
		MapNeuralPopulation inputPopulation = new MapNeuralPopulation(halfSize);
		MapNeuralPopulation outputPopulation = new MapNeuralPopulation(halfSize);
		populations.add(inputPopulation);
		populations.add(outputPopulation);

		// Kernel Matrix setup
		List<List<KernelFunction>> kernelMatrix = new ArrayList<List<KernelFunction>>(layers);
		List<KernelFunction> firstRow = new ArrayList<KernelFunction>(layers);
		List<KernelFunction> secondRow = new ArrayList<KernelFunction>(layers);
		KernelFunction kernelFunction = new MexicanHatKernel();
		firstRow.add(kernelFunction);
		firstRow.add(kernelFunction);
		secondRow.add(kernelFunction);
		secondRow.add(null);
		kernelMatrix.add(firstRow);
		kernelMatrix.add(secondRow);

		// Equations setup
		List<NeuralPopulationEquation<Double>> equations = new ArrayList<NeuralPopulationEquation<Double>>(
				layers);
		SimpleEquation simpleEquation = new SimpleEquation();
		InputEquation staticEquation = new InputEquation(halfSize,pendulum);
		equations.add(simpleEquation);
		equations.add(staticEquation);
		DerivableNeuralField<Double> field = new DerivableNeuralField<Double>(equations,
				kernelMatrix, populations);

		FirstProblemSimulation problemSimulation = new FirstProblemSimulation(field, pendulum,
				pendulumValues);

		// Solver setup
		System.out.println("Initial Values " + initialValues);
		RungeKutta4thSolver solver = new RungeKutta4thSolver(initialValues, hh, t0,
				problemSimulation, true, traceSkip);
		Visualizer visualizer = new PendulumTracer(pendulumStates,mainSize,mainSize);
		solver.addObserver(visualizer);

		// Run simulation
		solver.simulate(tf);

	}
}
