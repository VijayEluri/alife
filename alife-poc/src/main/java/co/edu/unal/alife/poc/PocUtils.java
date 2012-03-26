package co.edu.unal.alife.poc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PocUtils {

	static final Logger logger = LoggerFactory.getLogger(PocUtils.class);
	static final double TOL = 1e-9;
	
	public static double squareDistance(double x, double y) {
		return (x - y) * (x - y);
	}
	
	public static double heavisideFunction(double value) {
		return value > 0 ? 1.0 : 0.0;
	}
	
	public static double bipolarSigmoid(double alpha, double t) {
		return (1 - Math.exp(-alpha * t)) / (1 + Math.exp(-alpha * t));
	}
}
