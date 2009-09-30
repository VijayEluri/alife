/**
 * 
 */
package gait;

import util.MatrixOperator;

/**
 * @author Juan Figueredo
 *
 */
public class DistanceFuzzySensor extends FuzzySensor {

	private double[] sigmaI;
	private double[] s;
	private double sigmaThreshold;
	
	public DistanceFuzzySensor() {
		this(new double[]{-2,0,2},2);
	}

	/**
	 * @param sigmaLimits
	 */
	public DistanceFuzzySensor(double[] sigmaI, double sigmaThreshold) {
		this.sigmaI = sigmaI;
		this.sigmaThreshold = sigmaThreshold;
		s = new double[3];
	}

	/* (non-Javadoc)
	 * @see dynamics.FuzzySensor#eval(double)
	 */
	@Override
	public double[] eval(double measure) {
		for (int i = 0; i < s.length; i++) {
			s[i]=(sigmaThreshold - Math.min(sigmaThreshold, Math.abs(sigmaI[i]-measure))) / sigmaThreshold;
		}
		return s;
	}
	
	public static void main(String[] args){
		DistanceFuzzySensor dfs = new DistanceFuzzySensor();
		for(double i=-2;i<2.1;i+=0.1){
			double[] distMeasure = dfs.eval(i);
			System.out.println("i: "+i+"\t"+MatrixOperator.toString(distMeasure));
		}
	}
}
