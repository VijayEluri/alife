/**
 * 
 */
package co.edu.unal.alife.sbw;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.dynamics.EventDetector;
import co.edu.unal.alife.dynamics.Solver;
import co.edu.unal.alife.dynamics.impl.MapDeltaPopulation;
import co.edu.unal.alife.dynamics.impl.RungeKutta4thSolver;
import co.edu.unal.alife.neuralfield.AbstractDeltaField;
import co.edu.unal.alife.neuralfield.AbstractDifferentialEquation;
import co.edu.unal.alife.neuralfield.AbstractKernelFunction;
import co.edu.unal.alife.neuralfield.DeltaEquation;
import co.edu.unal.alife.neuralfield.impl.SimpleDeltaField;
import co.edu.unal.alife.output.Tracer;

/**
 * @author Juan Figueredo
 */
public class SBWEquation extends AbstractDifferentialEquation {
	
	private static final double	l				= 1, g = 1, gamma = 0.004;
	public static final double	STATE_THETA		= 0.0, STATE_PHI = 1.0,
			STATE_THETADOT = 2.0, STATE_PHIDOT = 3.0;
	private DeltaPopulation[]	actionPopulations;
	private boolean				lastWasEvent	= true;					//We don't want events activated on t=0.0
	private boolean				switched		= false;
	private EventDetector		switchDetector;
	private EventDetector		stopDetector;
	
	/**
	 * Constructor with event detectors and action populations.
	 * 
	 * @param switchDetector
	 *            The detector for switch events.
	 * @param stopDetector
	 *            The detector for stop events.
	 * @param actionPopulations
	 *            The action deltaPopulations.
	 */
	public SBWEquation(EventDetector switchDetector, EventDetector stopDetector,
			DeltaPopulation... actionPopulations) {
		super();
		this.actionPopulations = actionPopulations;
		this.switchDetector = switchDetector;
		this.stopDetector = stopDetector;
	}
	
	@Override
	public boolean requiresApplyInput() {
		return true;
	}
	
	@Override
	public DeltaPopulation applyInput(DeltaPopulation localPopulation) {
		boolean isStop = false;
		boolean isSwitch = false;
		boolean templwe = lastWasEvent;
		
		//Detect events
		isStop = stopDetector.detectEvent(0d, localPopulation, 0);
		isSwitch = switchDetector.detectEvent(0d, localPopulation, 1);
		//System.out.println("SBWEquation.applyInput: (before) isSwitch = "+isSwitch+" isStop = "+isStop);
		lastWasEvent = isSwitch;
		isSwitch = (isSwitch && !templwe); //We don't want consecutive switch events
		//System.out.println("SBWEquation.applyInput: (after)  isSwitch = "+isSwitch+" templwe = "+templwe);
		
		//Switch if it is necessary 
		if (isSwitch) {
			applySwitch(localPopulation);
		}
		
		//Set isTerminal
		localPopulation.setTerminal(isStop);
		
		return localPopulation;
	}
	
	@Override
	public void evalEquation(DeltaPopulation localPopulation,
			List<DeltaPopulation> populations, List<AbstractKernelFunction> kernelList) {
		//Get control actions (is switch-aware)
		double[] us = getU();
		
		//Apply state equation
		getDx(localPopulation, us);
	}
	
	private void applySwitch(DeltaPopulation localPopulation) {
		double theta = localPopulation.getElementValue(STATE_THETA);
		double thetadot = localPopulation.getElementValue(STATE_THETADOT);
		
		//Swap switch
		switched = !switched;
		System.out.println("Apply Switch. switched = " + switched);
		
		//Apply switch to states
		double c2 = cos(2 * theta);
		localPopulation.setElementValue(STATE_THETA, -theta);
		localPopulation.setElementValue(STATE_PHI, -2 * theta);
		localPopulation.setElementValue(STATE_THETADOT, c2 * thetadot);
		localPopulation.setElementValue(STATE_PHIDOT, (1 - c2) * c2 * thetadot);
	}
	
	private double[] getU() {
		double[] us = new double[actionPopulations.length];
		
		//For each population evaluate the averaged max potential and decode as control action
		for (int ii = 0; ii < us.length; ii++) {
			double u = 0;
			DeltaPopulation actionPopulation = actionPopulations[ii];
			while (actionPopulation.hasNextPopulation()) {
				actionPopulation = actionPopulation.getNextPopulation();
			}
			u = findAveragedMax(actionPopulation);
			us[ii] = u;
		}
		
		//Fill missing control actions
		switch (us.length) {
			case 0:
				us = new double[] { 0.0, 0.0 };
				break;
			case 1:
				us = new double[] { us[0], 0.0 };
				break;
			default:
				break;
		}
		
		//If necessary swap control actions
		if (switched) {
			us = new double[] { us[1], us[0] };
		}
		
		return us;
	}
	
	private double findAveragedMax(DeltaPopulation actionPopulation) {
		final int _NEIGHBORS = 2;
		final int _K = 1;
		final double _AMP = 20;
		final double _N2 = _NEIGHBORS * _NEIGHBORS;
		double u = 0;
		if (actionPopulation != null) {
			Set<Double> positions = actionPopulation.getPositions();
			int size = positions.size();
			List<Double> positionList = new ArrayList<Double>(size);
			positionList.addAll(positions);
			// Collections.sort(positionList);
			double maxSoFar = 0;
			double argMaxSoFar = 0.0;
			for (int i = 0; i < size; i++) {
				double sum = 0;
				double N = 0;
				for (int j = -_NEIGHBORS; j <= _NEIGHBORS; j++) {
					if (i + j >= 0 && i + j < positionList.size()) {
						double position = positionList.get(i + j);
						double value = actionPopulation.getElementValue(position);
						double d2 = j * j;
						double w = _K * Math.exp(-d2 / _N2);
						N += w;
						sum += w * value;
					}
				}
				if (sum / N > maxSoFar) {
					argMaxSoFar = positionList.get(i);
					maxSoFar = sum / N;
				}
			}
			// System.out.println(argMaxSoFar + " : " + maxSoFar);
			u = 2 * argMaxSoFar / size * _AMP;
		}
		return u;
	}
	
	/**
	 * Implements the state equation of the system. Receives a deltaPopulation and an array of control actions.
	 * 
	 * @param deltaPopulation
	 * @param us
	 */
	protected void getDx(DeltaPopulation deltaPopulation, double[] us) {
		double theta = deltaPopulation.getElementValue(STATE_THETA);
		double phi = deltaPopulation.getElementValue(STATE_PHI);
		double thetadot = deltaPopulation.getElementValue(STATE_THETADOT);
		double phidot = deltaPopulation.getElementValue(STATE_PHIDOT);
		double tao_theta = us[0];
		double tao_phi = us[1];
		
		deltaPopulation.setElementDelta(STATE_THETA, thetadot);
		deltaPopulation.setElementDelta(STATE_PHI, phidot);
		double thetadotdot = tao_theta + (g / l) * sin(theta - gamma);
		deltaPopulation.setElementDelta(STATE_THETADOT, thetadotdot);
		deltaPopulation.setElementDelta(STATE_PHIDOT, -tao_phi + (1 - cos(phi))
				* thetadotdot + thetadot * thetadot * sin(phi) + (g / l)
				* sin(theta - phi - gamma));
		
	}
	
	/**
	 * Set the deltaPopulations used to calculate the control actions
	 * 
	 * @param actionPopulations
	 */
	public void setActionPopulations(DeltaPopulation[] actionPopulations) {
		this.actionPopulations = actionPopulations;
	}
//	
//	public static void main(String[] args) {
//		SBWEquation equation = new SBWEquation(new SBWColissionDetector(),
//				new SBWFallDetector());
//		DeltaPopulation systemPopulation = new MapDeltaPopulation(4, false);
//		double theta0 = 0.4;
//		double thetadot0 = -0.434;
//		systemPopulation.setElementValue(SBWEquation.STATE_THETA, theta0);
//		systemPopulation.setElementValue(SBWEquation.STATE_PHI, 2 * theta0);
//		systemPopulation.setElementValue(SBWEquation.STATE_THETADOT, thetadot0);
//		systemPopulation.setElementValue(SBWEquation.STATE_PHIDOT, (1 - cos(2 * theta0))* thetadot0);
//		
//		System.out.println(systemPopulation.toString());
//		equation.getDx(systemPopulation, new double[] { 0d, 0d });
//		double dtheta = systemPopulation.getElementDelta(STATE_THETA);
//		double dphi = systemPopulation.getElementDelta(STATE_PHI);
//		double dthetadot = systemPopulation.getElementDelta(STATE_THETADOT);
//		double dphidot = systemPopulation.getElementDelta(STATE_PHIDOT);
//		System.out.println("[" + dtheta + "\t" + dphi + "\t" + dthetadot + "\t" + dphidot
//				+ "]");
//	}
	
	public static void main(String[] args) {
		int N = 1;
		
		// System (SBW) population construction
		DeltaPopulation systemPopulation = new MapDeltaPopulation(4, false);
		
		// SBWEquation Construction
		AbstractDifferentialEquation systemEquation = new SBWEquation(
				new SBWColissionDetector(), new SBWFallDetector());
		
		//Set-up lists
		List<DeltaEquation> eqs = new ArrayList<DeltaEquation>();
		eqs.add(systemEquation);
		List<List<AbstractKernelFunction>> kernelMatrix = new ArrayList<List<AbstractKernelFunction>>();
		kernelMatrix.add(null);
		List<DeltaPopulation> pops = new ArrayList<DeltaPopulation>();
		pops.add(systemPopulation);
		Solver solver = new RungeKutta4thSolver();
		
		AbstractDeltaField field = new SimpleDeltaField(eqs, kernelMatrix, pops, solver);
		
		// Monitoring
		Tracer tracer = new Tracer(N);
		field.addObserver(tracer);
		
		// Run simulation
		double t0 = 0;
		double tf = 5;
		double h = 0.0001;
		double theta0 = 0.4;
		double thetadot0 = -0.434;
		
		systemPopulation = field.getPopulations().get(N - 1);
		systemPopulation.setElementValue(SBWEquation.STATE_THETA, theta0);
		systemPopulation.setElementValue(SBWEquation.STATE_PHI, 2 * theta0);
		systemPopulation.setElementValue(SBWEquation.STATE_THETADOT, thetadot0);
		systemPopulation.setElementValue(SBWEquation.STATE_PHIDOT, (1 - cos(2 * theta0))
				* thetadot0);
		solver = field.getSolver();
		
		long t1 = System.currentTimeMillis();
		
		boolean isTerminal = solver.simulate(t0, tf, h, field);
		
		long t2 = System.currentTimeMillis();
		
		System.out.println("Time elapsed: "+(t2-t1)+" ms");
		
		System.out.println("isTerminal ? " + isTerminal);
		
//		System.out.println(tracer.toString());
		List<List<DeltaPopulation>> data = tracer.getData();
		List<List<String>> labels = tracer.getLabels();
		int size = data.get(N - 1).size();
		System.out.println(size);
		for (int i = 0; i < size; i++) {
			if (i % 100 == 0) {
				System.out.print(labels.get(N - 1).get(i) + "\t");
				System.out.println(data.get(N - 1).get(i).toString());
			}
		}
	}
}
