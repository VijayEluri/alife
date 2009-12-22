/**
 * 
 */
package evolution;

import jml.evolution.Individual;
import gait.InvertedPendulumGait;
import gait.RNNCGait;
import gait.RNNCOrientation;
import gait.RnnController;

/**
 * @author Juan Figueredo
 *
 */
public class RnnPPGaitFitness extends RnnPPFitness {
	
	private double initialAngle = 0;
	private double initialPos = 0;
	private RnnParameters rnnParamsOrient;
	
	/**
	 * @param initialDistance
	 */
	public RnnPPGaitFitness(RnnParameters rnnParamsOrient, double initialAngle, double initialPos) {
		this.initialAngle = initialAngle;
		this.initialPos = initialPos;
		this.rnnParamsOrient = rnnParamsOrient;
	}

	/* (non-Javadoc)
	 * @see jml.evolution.Fitness#evaluate(jml.evolution.Individual)
	 */
	@Override
	public double evaluate(Individual obj) {
//		RnnController rnnController = (RnnController)obj.getThing();
		RnnController rnnControllerOrient = new RNNCOrientation(rnnParamsOrient);
		RnnController rnnControllerGait = new RNNCGait((RnnParameters)obj.getThing());
		InvertedPendulumGait cartPole = new InvertedPendulumGait(rnnControllerOrient, rnnControllerGait);
		double[] x0 = {initialPos, initialAngle, 0, 0};
		double tf = 40;
		double h = 0.1;
		double t = 0;
		double fit = cartPole.getFitness(x0, h, t, tf, false);
		return fit;
	}
}
