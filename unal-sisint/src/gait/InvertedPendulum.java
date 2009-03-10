package gait;

import static java.lang.Math.*;
import graphics.AnimationFrame;

import java.util.Arrays;
import java.util.Vector;

import solver.Derivable;
import solver.StaticRungeKutta4th;
import util.MatrixOperator;
import gait.RnnController;
import evolution.RnnParameters;


public class InvertedPendulum implements Derivable{
	
	private RnnController rnnController;

	public InvertedPendulum(RnnController rnnController) {
		super();
		this.rnnController = rnnController;
	}

	/* (non-Javadoc)
	 * @see solver.Derivable#derX(double[], double)
	 */
	public double[] derX(double[] x, double t) {
		double M = 1, m = 1, l = 1, g = 9.81;
		double ex = rFun(t)-x[0];
		x[1]=stdAngle(x[1]);
		double[] output = rnnController.eval(ex, x[1], t);
		double u = 20*output[0];
//		double u = 0;
//		System.out.println(u);
		double tao = 0;
		double[] dx = new double[4];
		dx[0] = x[2];
		dx[1] = x[3];
		dx[2] = (m*l*x[3]*x[3]*sin(x[1])-m*g*cos(x[1])*sin(x[1])+u) / (M+m*sin(x[1])*sin(x[1]));
		dx[3] = ((M+m)*g*sin(x[1])-m*l*x[3]*x[3]*sin(x[1])*cos(x[1])-u*cos(x[1])) / (l*(M+m*sin(x[1])*sin(x[1]))) - 0.5*x[3] + m*l*l*tao;
		return dx;
	}

	public static double rFun(double t) {
		double y = 2*sin(t/4);
		return y;
	}
	
	/* (non-Javadoc)
	 * @see solver.Derivable#derX(int, double[], double)
	 */
	public double derX(int i, double[] x, double t) {
		double[] result = derX(x,t);
		return result[i];
	}
	
	public double getFitness(double[] x0, double h, double t0, double tf, boolean plot){
		PerformanceTracer tracer = new PerformanceTracer(4);
		double hh = h/20;
		StaticRungeKutta4th ode = new StaticRungeKutta4th(x0,hh,t0,this,tracer);
		ode.simulate(tf-t0,true,h);
//		System.out.println(Arrays.toString(result));
		Vector<double[]> data = tracer.getData();
		double val = 0;
		for (double[] ds : data) {
			double ang = stdAngle(ds[1]);
			val += ang*ang*ang*ang + abs(ds[0])/10;
		}
		val /= data.size();
//		double[] firstDat = data.elementAt(0);
//		System.out.println(data.size()+" "+firstDat.length);
		double fitness = 100 - val * 100 / (PI*PI*PI*PI+2);
	    if (plot) {
			new AnimationFrame(tracer);
			tracer.printToFile("rnnPendulum");
		}
		return fitness;
	}
	
	public static double stdAngle(double angle) {
		return ((angle+PI)%(2*PI)+2*PI) % (2*PI) - PI;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double inX = 0;
		double inTh = Math.PI/6;
		double[] x0 = {inX, inTh, 0, 0};
		double tf = 10;
		double h = 1.0 / 40;
		double t = 0;
		boolean plot = true;
		
		int inputs = 3;
		int states = 4;
//		double[][] Wa = MatrixOperator.random(states, states, 4/Math.sqrt(states), -2/Math.sqrt(states));
//		System.out.println(MatrixOperator.toString(Wa));
//		double[][] Wb = MatrixOperator.random(states, inputs+1, 4, -2);
		double[][] Wa= { 
			 {0.17993353817580982, 1.4130997349445487, -1.9435505077560689, 1.6312705543544266},
			 {0.40926669953590755, 0.19885670858324245, -1.1383987601905887, 1.0149197702992137},
			 {-1.1708561820279613, 0.47114859978784374, -1.0956059366431403, -0.8976035013127555},
			 {1.3752597082847537, -1.5740825037159785, 1.9997617503972815, 1.6792545734410194}
		};
		double[][] Wb = {
			 {-1.417122488485966, 0.05059659250117754, 1.3188796274177417, 0.11979732022748113},
			 {0.5242583922387385, 0.6384127780672371, 0.6381543305619606, -0.8197718690221629},
			 {-0.6458599472218354, -1.8187941642663854, 1.8490410642002968, 0.675624268325258},
			 {-1.7901882015170116, 1.7265691497604791, -1.815313744540032, -0.07867380148358949}
		};
//		System.out.println(MatrixOperator.toString(Wb));
		int[][] Aa = MatrixOperator.ones(states, states);
		int[][] Ab = MatrixOperator.ones(states, inputs+1);
		int[] An = MatrixOperator.ones(states);
		//int[] An = {1,1,0,0,0,0,0,0};
		RnnParameters rnnParam = new RnnParameters(Aa,Ab,An,Wa,Wb,states,inputs,0.025);
//		RnnParameters rnnParam = 
		RnnController rnnController;
		try {
			rnnController = new RNNCOrientation((RnnParameters) rnnParam.clone());
			InvertedPendulum cartPole = new InvertedPendulum(rnnController);
			System.out.println("Simulation started...");
			double intime = System.currentTimeMillis();
			double fitness = cartPole.getFitness(x0, h, t, tf, plot);
			double elapsedtime = System.currentTimeMillis()-intime;
	        System.out.println("Fitness="+fitness);
	        System.out.println("Elapsed time: "+elapsedtime+" ms");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}

