/**
 * 
 */
package gait;

import util.MatrixOperator;


/**
 * @author Juan Figueredo
 *
 */
public class OrientationFuzzySensor extends FuzzySensor {

	private double[] tethaI;
	private double tethaThreshold;
	private double[] s;
	private double amplification;
	
	public OrientationFuzzySensor() {
//		this(new double[]{-2*Math.PI/3,-Math.PI/3,0,Math.PI/3,2*Math.PI/3},Math.PI/3);
		this(new double[]{-Math.PI/2,0,Math.PI/2},Math.PI);
		
	}
	
	public OrientationFuzzySensor(double tethaThreshold){
//		this(new double[]{-2*Math.PI/3,-Math.PI/3,0,Math.PI/3,2*Math.PI/6},tethaThreshold);
		this(new double[]{-Math.PI/2,0,Math.PI/2},Math.PI);
	}
	
	/**
	 * @param tethaI
	 * @param tethaThreshold
	 */
	public OrientationFuzzySensor(double[] tethaI, double tethaThreshold) {
		s = new double[tethaI.length];
		this.tethaI = tethaI;
		this.tethaThreshold = tethaThreshold;
		this.amplification = 2;
	}

	/* (non-Javadoc)
	 * @see dynamics.FuzzySensor#eval(double)
	 */
	@Override
	public double[] eval(double measure) {
		for (int i = 0; i < s.length; i++) {
			s[i]=(tethaThreshold - Math.min(tethaThreshold, Math.abs(tethaI[i]-measure))) / tethaThreshold;
			s[i]*=amplification;
			//System.out.print(" s["+i+"]:"+s[i]+" tetha["+i+"]:"+tethaI[i]);
		}
		//System.out.print(" measure:"+measure);
		return s;
	}
	
	public static void main(String[] args){
		OrientationFuzzySensor ofs = new OrientationFuzzySensor();
		for(double i=-Math.PI;i<Math.PI;i+=Math.PI/6){
			double[] orientMeasure = ofs.eval(i);
			System.out.println("i: "+i+"\t"+MatrixOperator.toString(orientMeasure));
		}
	}
}
