package co.edu.unal.alife.neuralfield;

public class S1TopologyUtility {

	public static double positionToAngle(double pos) {
		return Math.PI*(2*pos-1);
	}

	public static double angleToPosition(double ang) {
		return (ang+Math.PI)/(2*Math.PI);
	}
	
	public static double stereoProjection(double ang) {
		double x = Math.tan(ang/2);
		return x;
	}
	
	public static double reverseStereoProjection(double x) {
		double ang = 2*Math.atan(x);
		return ang;
	}

}
