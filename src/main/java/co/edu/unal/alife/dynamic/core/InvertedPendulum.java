/**
 * 
 */
package co.edu.unal.alife.dynamic.core;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juan Figueredo
 *
 */
public class InvertedPendulum {
	
	public static double stdAngle(double angle) {
		return ((angle+PI)%(2*PI)+2*PI) % (2*PI) - PI;
	}
	
	public List<Double> getPendulumDeltas(List<Double> x, List<Double> inputs, double t) {
		double M = 1, m = 1, l = 1, g = 9.81;
		double u = inputs.get(0);
		x.set(1,stdAngle(x.get(1)));
	//	double u = 10*output[0];
//		double u = 0;
//		System.out.println(u);
		double tao = 0;
		List<Double> dx = new ArrayList<Double>(4);
		dx.set(0, x.get(2));
		dx.set(1,x.get(3));
		dx.set(2,(m*l*x.get(3)*x.get(3)*sin(x.get(1))-m*g*cos(x.get(1))*sin(x.get(1))+u) / (M+m*sin(x.get(1))*sin(x.get(1))));
		dx.set(3,((M+m)*g*sin(x.get(1))-m*l*x.get(3)*x.get(3)*sin(x.get(1))*cos(x.get(1))-u*cos(x.get(1))) / (l*(M+m*sin(x.get(1))*sin(x.get(1)))) - 0.5*x.get(3) + m*l*l*tao);
		return dx;
	}
}
