package gait;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.max;
import static java.lang.Math.signum;
import static java.lang.Math.sin;
import evolution.RnnParameters;
import graphics.AnimationFrameGait;

import java.util.Vector;

import solver.Derivable;
import solver.StaticRungeKutta4th;
import util.MatrixOperator;


public class InvertedPendulumGait implements Derivable{
	
	private RnnController rnnControllerOrient;
	private RnnController rnnControllerGait;
	public static final double Xmin = -5, Xmax = 5, CLimite = 100, KLimite = 10;

	public InvertedPendulumGait(RnnController rnnControllerOrient, RnnController rnnControllerGait) {
		super();
		this.rnnControllerOrient = rnnControllerOrient;
		this.rnnControllerGait = rnnControllerGait;
	}

	/* (non-Javadoc)
	 * @see solver.Derivable#derX(double[], double)
	 */
	public double[] derX(double[] x, double t) {
		double M = 1, m = 1, l = 1, g = 9.81;
		double ex = rFun(t)-x[0];
		x[1]=stdAngle(x[1]);
		double[] outputU = rnnControllerOrient.eval(ex, x[1], t);
		double u = 10*outputU[0];
		
		if(x[0]<=Xmin)
			u = KLimite*(Xmin-x[0])-CLimite*x[2]*heaviside(-x[2]);
		if(x[0]>=Xmax)
			u = KLimite*(Xmax-x[0])-CLimite*x[2]*heaviside(x[2]);
		
		double[] outputTao = rnnControllerGait.eval(ex, x[1], t);
		double tao = outputTao[0];
		double[] dx = new double[4];
		dx[0] = x[2];
		dx[1] = x[3];
		dx[2] = (m*l*x[3]*x[3]*sin(x[1])-m*g*cos(x[1])*sin(x[1])+u) / (M+m*sin(x[1])*sin(x[1]));
		dx[3] = ((M+m)*g*sin(x[1])-m*l*x[3]*x[3]*sin(x[1])*cos(x[1])-u*cos(x[1])) / (l*(M+m*sin(x[1])*sin(x[1]))) - 0.5*x[3] + m*l*l*tao;
		return dx;
	}
	
	public static int heaviside(double val) {
		return (int)max(signum(val),0);
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
		double time = 0;
		for (double[] ds : data) {
			double ex = InvertedPendulumGait.rFun(time)-ds[0];
			double ang = stdAngle(ds[1]);
			val += ang*ang*ang*ang + ex*ex*ex*ex;
			time += h;
		}
		val /= data.size();
//		double[] firstDat = data.elementAt(0);
//		System.out.println(data.size()+" "+firstDat.length);
		double fitness = 100 - val * 100 / (PI*PI*PI*PI+Xmax*Xmax*Xmax*Xmax);
	    if (plot) {
			new AnimationFrameGait(tracer);
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
		double inX = -0.5;
		double inTh = 0.15;
		double[] x0 = {inX, inTh, 0, 0};
		double tf = 20;
		double h = 0.040;
		double t = 0;
		boolean plot = true;
		
		int inputs = 3;
		int inputs1 = 6;
		int states = 4;
		double[][] Wa1 =
			{{1.604879067044462, -0.06698319120170915, -0.4855039536332497, -1.6463697729442281},
			 {-0.1508555027139935, -1.1633273424851498, -1.5361604082059377, 1.8490996808093532},
			 {1.5019263937164515, -1.7154870969477831, -1.1076963503391224, 0.653139960470051},
			 {-0.3287593885039741, 0.18241533590063463, 1.4275746947299077, 0.10599309214638364}};
		double [][] Wb1 =
		   {{0.5205722836571449, 1.602006734981042, 1.474021744620905, 0.7029363860778166, -1.7662218984901301, 1.3269871426626625, 0.937294352424161},
			{1.7326280156653886, -0.16553932807700944, 1.9132462693786159, 0.8639005131749351, 0.9804107468052208, 0.29346407414838405, -0.49189539908467816},
			{-1.7837178637741613, 0.8222290261245866, 1.071141360142279, 0.9790362694632848, -1.966617295556647, 0.5656290940450313, 1.0883036094423408},
			{1.2448034289232153, -1.3460741108755885, 0.23733885699098156, 0.3772736213946253, 1.0818905319058985, -0.3862446682372642, -1.2570625238957844}};
		double[][] Wa = 
		    {{ 0.8684211993659257,-0.9225310746765683, 0.17694620277789186, 1.833062391569837},
		     {-1.9689462551484365, 0.5844140464229981, 1.047802157585688,-1.1197558461535482},
		     {-0.9844208778631591,1.908468338982464,-0.20194207143100007,0.4066400345551151},
		     {1.7197889790412573,-0.9942870230274723,-0.3783054079545005, 0.7405632530854107}};
		double[][] Wb =
			 {{-1.378636947764309,-0.07089486375103382,1.5174665951942816,0.5455487187422841},
			  {0.3943279641588311,-0.4224388993020587,0.5337264233728174,-1.3013468606411203},
			  {1.038520939434914,1.3825021700069389,1.5736651592145896,0.2930459580606324},
			  {0.49059355496956014,0.7356628894946855,-1.8798396405549722,-0.3256178132777374}};
		
//		double[][] Wa = MatrixOperator.random(states, states, 4/Math.sqrt(states), -2/Math.sqrt(states));
//		double[][] Wa1 = MatrixOperator.random(states, states, 4/Math.sqrt(states), -2/Math.sqrt(states));
//		System.out.println(MatrixOperator.toString(Wa));
//		double[][] Wb = MatrixOperator.random(states, inputs+1, 4, -2);
//		double[][] Wb1 = MatrixOperator.random(states, inputs+1, 4, -2);
		System.out.println(MatrixOperator.toString(Wb));
		int[][] Aa = MatrixOperator.ones(states, states);
		int[][] Ab = MatrixOperator.ones(states, inputs+1);
		int[][] Ab1 = MatrixOperator.ones(states, inputs1+1);
		int[] An = MatrixOperator.ones(states);
		//int[] An = {1,1,0,0,0,0,0,0};
		RnnParameters rnnParamOrient = new RnnParameters(Aa,Ab,An,Wa,Wb,states,inputs,0.025);
		RnnParameters rnnParamGait = new RnnParameters(Aa,Ab1,An,Wa1,Wb1,states,inputs1,0.025);
//		RnnParameters rnnParam = 
		RnnController rnnCOrient;
		RnnController rnnCGait;
		try {
			rnnCOrient = new RNNCOrientation((RnnParameters) rnnParamOrient.clone());
			rnnCGait = new RNNCGait((RnnParameters) rnnParamGait.clone());
			InvertedPendulumGait cartPole = new InvertedPendulumGait(rnnCOrient, rnnCGait);
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

