/**
 * 
 */
package co.edu.unal.alife.pendulum;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import co.edu.unal.alife.dynamics.DeltaPopulation;
import co.edu.unal.alife.neuralfield.KernelFunction;
import co.edu.unal.alife.output.Tracer;

/**
 * @author Juan Figueredo
 */
public class PendulumEquation extends SystemEquation {

	private static final double M = 1, m = 1, l = 1, g = 9.81, halfSize = 10, Amp = 20;
	public static final double STATE_X = 0.0, STATE_THETA = 1.0, STATE_V = 2.0, STATE_OMEGA = 3.0;
	DeltaPopulation<Double> actionPopulation;

	/**
	 * @param actionPopulation
	 */
	public PendulumEquation(DeltaPopulation<Double> actionPopulation) {
		super();
		this.actionPopulation = actionPopulation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.unal.alife.neuralfield.DeltaPopulationEquation#evalEquation(co
	 * .edu.unal.alife.dynamics .DeltaPopulation, java.util.List,
	 * java.util.List)
	 */
	@Override
	public void evalEquation(DeltaPopulation<Double> localPopulation, List<DeltaPopulation<Double>> populations,
			List<KernelFunction> kernelList) {
		double u = 0;
		while (actionPopulation.hasNextPopulation()) {
			actionPopulation = actionPopulation.getNextPopulation();
		}
		int neighbors = 2;
		int k = 1;
		double n2 = neighbors * neighbors;
		if (actionPopulation != null) {
			Set<Double> positions = actionPopulation.getPositions();
			List<Double> positionList = new ArrayList<Double>(positions.size());
			positionList.addAll(positions);
			// Collections.sort(positionList);
			double maxSoFar = 0;
			double argMaxSoFar = 0.0;
			for (int i = 0; i < positionList.size(); i++) {
				double sum = 0;
				double N = 0;
				for (int j = -neighbors; j <= neighbors; j++) {
					if (i + j >= 0 && i + j < positionList.size()) {
						double position = positionList.get(i + j);
						double value = actionPopulation.getElementValue(position);
						double d2 = j * j;
						double w = k * Math.exp(-d2 / n2);
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
			u = (argMaxSoFar) / halfSize * Amp;
		}
		// localPopulation
		// .setElementValue(STATE_X,
		// stdAngle(localPopulation.getElementValue(STATE_X)));
		int maxPos = 5;
		if (localPopulation.getElementValue(STATE_X) > maxPos) {
			localPopulation.setElementValue(STATE_X, (localPopulation.getElementValue(STATE_X) % maxPos) - maxPos);
		}
		if (localPopulation.getElementValue(STATE_X) < -maxPos) {
			localPopulation.setElementValue(STATE_X, maxPos - (localPopulation.getElementValue(STATE_X) % maxPos));
		}
		localPopulation.setElementValue(STATE_THETA, stdAngle(localPopulation.getElementValue(STATE_THETA)));
		double tao = 0;
		getDx(localPopulation, u, tao);
	}

	public static double stdAngle(double angle) {
		return ((angle + PI) % (2 * PI) + 2 * PI) % (2 * PI) - PI;
	}

	/**
	 * @param u
	 * @param tao
	 * @return
	 */
	@Override
	protected void getDx(DeltaPopulation<Double> deltaPopulation, double u, double tao) {
		double theta = deltaPopulation.getElementValue(STATE_THETA);
		double v = deltaPopulation.getElementValue(STATE_V);
		// System.out.println("v: "+v);
		double omega = deltaPopulation.getElementValue(STATE_OMEGA);
		deltaPopulation.setElementDelta(STATE_X, v);
		deltaPopulation.setElementDelta(STATE_THETA, omega);
		double st = sin(theta);
		double ct = cos(theta);
		// System.out.println(u);
		deltaPopulation
				.setElementDelta(STATE_V, (m * l * omega * omega * st - m * g * ct * st + u) / (M + m * st * st));
		deltaPopulation.setElementDelta(STATE_OMEGA, ((M + m) * g * st - m * l * omega * omega * st * ct - u * ct)
				/ (l * (M + m * st * st)) - 0.5 * omega + m * l * l * tao);
		// / (l * (M + m * st * st)) + tao/(m * l * l));
	}

	@Override
	public void setActionPopulation(DeltaPopulation<Double> actionPopulation) {
		this.actionPopulation = actionPopulation;
	}

	 public static double getFitness(Tracer tracer) {
	 DeltaPopulation<Double> pendulumData = tracer.getData().get(2).get(0);
	
	 double val = 0;
	 while (pendulumData.hasNextPopulation()) {
	 Double xValue =
	 pendulumData.getElementValue(S1PendulumEquation.STATE_X);
	 Double thetaValue =
	 pendulumData.getElementValue(S1PendulumEquation.STATE_THETA);
	
	 double ang = stdAngle(thetaValue);
	 val += ang * ang * ang * ang + abs(xValue) / 10;
	
	 pendulumData = pendulumData.getNextPopulation();
	 }
	
	 val /= tracer.getData().get(2).size();
	
	 double fitness = 100 - val * 100 / (PI * PI * PI * PI + 2);
	
	 return fitness;
	 }
}
