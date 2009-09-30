/**
 * 
 */
package mts.evolution;

import java.util.Arrays;

import jml.evolution.real.RealVectorFitness;

/**
 * @author Juan Figueredo
 *
 */
public class MTSFitness extends RealVectorFitness {
	
	int M = 10;
	int N = 4;
	int[][] P =      {{1,     1,     1,     1},
					  {0,     1,     1,     0},
					  {1,     0,     1,     0},
					  {0,     1,     1,     0},
					  {1,     0,     1,     1},
					  {0,     1,     1,     1},
					  {1,     0,     1,     0},
					  {0,     1,     1,     0},
					  {1,     0,     1,     0},
					  {1,     1,     1,     1}};
	double[] d = {0.2,  0.05,  0.1,  0.02,  0.13,  0.15,  0.02,  0.03,  0.1,  0.2};
	int[] l =    {300,  50,    30,   20,    90,    10,    100,   30,    70,   300};		
	double lambda0 = 1;
	double lambda1 = 1;
	//double F_T = 4;
	double tau = 1;
	double v_0 = 60; 
	
	double[] dSum;
	double L;
	int[] m;
	
	double[][] gamma;
	double Fit0 = 0;
	double Fit1 = 0;
	double Tw = 0;
	double Tv = 0;
	/**
	 * 
	 */
	public MTSFitness() {
		dSum = new double [N];
		for (int a = 0; a < N; a++) {
			double dSum_a = 0;
			for (int b = 0; b < M; b++) {
				dSum_a += d[b]*P[b][a]; 
			}
			dSum[a] = dSum_a;
		}
		//System.out.println(Arrays.toString(dSum));
		m = new int[N];
		for (int a = 0; a < N; a++) {
			int m_a = 0;
			for (int b = 0; b < M; b++) {
				m_a += P[b][a]; 
			}
			m[a] = m_a;
		}
		//System.out.println(Arrays.toString(m));
		L = 0;
		for (int b = 0; b < M; b++) {
			L += l[b];
		}
		//System.out.println(L);
	}
	

	/**
	 * @param m
	 * @param n
	 * @param p
	 * @param d
	 * @param l
	 * @param lambda
	 * @param f_t
	 * @param tau
	 */
	public MTSFitness(int m, int n, int[][] p, double[] d, int[] l, double lambda, double tau, int v_0) {
		this();
		M = m;
		N = n;
		P = p;
		this.d = d;
		this.l = l;
		this.lambda1 = lambda;
		//F_T = f_t;
		this.tau = tau;
		this.v_0 = v_0;
	}

	
	/* (non-Javadoc)
	 * @see jml.evolution.Fitness#evaluate(jml.evolution.Individual)
	 */
	@Override
	public double eval(double[] obj) {
		double[] f = obj;
		double[] denGamma = new double[M];
		for (int b = 0; b < M; b++) {
			double denGamma_b = 0;
			for (int a = 0; a < N; a++) {
				denGamma_b += f[a]*dSum[a]*P[b][a];
			}
			denGamma[b] = denGamma_b;
		}
		gamma = new double[M][N];
		for (int b = 0; b < M; b++) {
			for (int a = 0; a < N; a++) {
				gamma[b][a] = f[a]*dSum[a]*P[b][a]/denGamma[b];
			}
		}
		Fit0 = 0;
		Tv = 0; Tw = 0;
		for (int b = 0; b < M; b++) {
			double fit0a = 0;
			double tv=0, tw=0;
			for (int a = 0; a < N; a++) {
				double dist = 0;
				for (int j = 0; j < M; j++) {
					dist += Math.abs(b-j)*d[j]*P[j][a];
				}
				fit0a += ( m[a]/(2*f[a]) + dist/dSum[a] ) * gamma[b][a];
				tw += m[a]/(2*f[a]) * gamma[b][a];
				tv += dist/dSum[a]* gamma[b][a];
			}
			Fit0 += fit0a*l[b];
			Tv += tv*l[b]; Tw += tw*l[b];
		}
		Fit0 *= tau/L;
		Tv *= tau/L; Tw *= tau/L;
		Fit1 = 0;
		for (int a = 0; a < N; a++) {
			double fit1b = 0;
			for (int b = 0; b < M; b++) {
				fit1b += l[b]*gamma[b][a];
			}
			double fit1a = (f[a]*v_0-fit1b);
			fit1a *= fit1a;
			Fit1 += fit1a;
		}
		double Fitness = - lambda0*Fit0 - lambda1/v_0*Math.sqrt(Fit1);
		return Fitness;
	}

	public void stats(double[] f) {
		for (int j = 0; j < gamma[0].length; j++) {
			System.out.print("     "+j+"    ");
		}
		System.out.println();
		for (int i = 0; i < gamma.length; i++) {
			System.out.print(i);
			for (int j = 0; j < gamma[0].length; j++) {
				System.out.printf ("    %6.4f", gamma[i][j]);
			}
			System.out.print("\n");
		}
		System.out.println("Tiempo de en el Sistema Promedio: "+Fit0);
		System.out.println("Tiempo de Espera Promedio: "+Tw);
		System.out.println("Tiempo de Viaje Promedio: "+Tv);
		System.out.println("Error de Ajuste: "+(Math.sqrt(Fit1)/(v_0*N)));
		System.out.println("Mejor Individuo: "+Arrays.toString(f));
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

}
