/**
 * 
 */
package co.edu.unal.alife.applications;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Juan Figueredop
 * 
 *
 */
public class InvertedPendulum {
	
	private List<Double> x = Arrays.asList(new Double[]{0.0,0.0,0.0,0.0});
	private static final double M = 1, m = 1, l = 1, g = 9.81;
	
	public static double stdAngle(double angle) {
		return ((angle+PI)%(2*PI)+2*PI) % (2*PI) - PI;
	}
	
	public List<Double> getPendulumDeltas(List<Double> newX, List<Double> inputs, double newT) {	
		double u = 0;
		if (inputs != null) {
			u = inputs.get(0);
		}
		newX.set(1,stdAngle(newX.get(1)));
		Collections.copy(x, newX);
		double tao = 0;
		List<Double> dx = getDx(u, tao);
		return dx;
	}

	/**
	 * @param u
	 * @param tao
	 * @return
	 */
	private List<Double> getDx(double u, double tao) {
		List<Double> dx = new ArrayList<Double>(4);
		dx.add(x.get(2));
		dx.add(x.get(3));
		dx.add((m*l*x.get(3)*x.get(3)*sin(x.get(1))-m*g*cos(x.get(1))*sin(x.get(1))+u) / (M+m*sin(x.get(1))*sin(x.get(1))));
		dx.add(((M+m)*g*sin(x.get(1))-m*l*x.get(3)*x.get(3)*sin(x.get(1))*cos(x.get(1))-u*cos(x.get(1))) / (l*(M+m*sin(x.get(1))*sin(x.get(1)))) - 0.5*x.get(3) + m*l*l*tao);
		return dx;
	}
	
	public double sense() {
		return getDx(0,0).get(3);
	}
	
}
