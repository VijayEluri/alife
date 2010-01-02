package co.edu.unal.alife.neuralfield;


public class S1TopologyUtility {

	public static double positionToAngle(double pos, int points) {
		return Math.PI*(2*(pos/points)-1);
	}

	public static double angleToPosition(double ang, int points) {
		return points*(ang+Math.PI)/(2*Math.PI);
	}
	
	public static double stereoProjection(double ang) {
		double x = Math.tan(ang/2);
		return x;
	}
	
	public static double reverseStereoProjection(double x) {
		double ang = 2*Math.atan(x);
		return ang;
	}
	
	public static double stdAngle(double angle) {
		double outAngle = angle % (2*Math.PI);
		if (outAngle<0) {
			outAngle += 2*Math.PI;
		}
		return outAngle;
	}

}
