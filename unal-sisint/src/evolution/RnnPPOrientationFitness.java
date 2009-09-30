/**
 * 
 */
package evolution;

import jml.evolution.Individual;
import gait.InvertedPendulum;
import gait.RNNCOrientation;
import gait.RnnController;

/**
 * @author Juan Figueredo
 *
 */
public class RnnPPOrientationFitness extends RnnPPFitness {
	
	private double initialAngle = 0;
	/**
	 * @param initialDistance
	 */
	public RnnPPOrientationFitness(double initialAngle) {
		this.initialAngle = initialAngle;
	}

	/* (non-Javadoc)
	 * @see jml.evolution.Fitness#evaluate(jml.evolution.Individual)
	 */
	@Override
	public double evaluate(Individual obj) {
//		RnnController rnnController = (RnnController)obj.getThing();
		RnnController rnnController = new RNNCOrientation((RnnParameters)obj.getThing());
		InvertedPendulum cartPole = new InvertedPendulum(rnnController);
		double initialDistance = 0;
		double[] x0 = {-initialDistance/2, initialAngle, 0, 0};
		double tf = 40;
		double h = 0.1;
		double t = 0;
		double fit = cartPole.getFitness(x0, h, t, tf, false);
		return fit;
	}

}
