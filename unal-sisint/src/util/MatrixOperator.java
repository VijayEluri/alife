/**
 * 
 */
package util;

import java.util.Random;

/**
 * @author Juan Figueredo
 *
 */
public abstract class MatrixOperator {
	
	public static double[][] random(int N, int M, double coef, double bias){
		Random rand = new Random();
		double[][] randomMatrix = new double[N][M];
		for (int i = 0; i < N; i++) {
			double[] ds = randomMatrix[i];
			for (int j = 0; j < M; j++) {
				ds[j] = coef*rand.nextDouble()+bias;
			}
		}
		return randomMatrix;
	}
	
	public static double[] random(int N, double coef, double bias){
		Random rand = new Random();
		double[] randomVector = new double[N];
		for (int i = 0; i < N; i++) {
				randomVector[i] = coef*rand.nextDouble()+bias;
		}
		return randomVector;
	}
	
	public static double[][] zeros(int N, int M){
		double[][] zerosMatrix = new double[N][M];
		for (int i = 0; i < N; i++) {
			double[] ds = zerosMatrix[i];
			for (int j = 0; j < M; j++) {
				ds[j] = 0;
			}
		}
		return zerosMatrix; 
	}
	
	public static double[] zeros(int N){
		double[] zerosVector = new double[N];
		for (int i = 0; i < N; i++) {
			zerosVector[i] = 0;
		}
		return zerosVector;
	}
		
	public static int[][] ones(int N, int M){
		int[][] onesMatrix = new int[N][M];
		for (int i = 0; i < N; i++) {
			int[] ds = onesMatrix[i];
			for (int j = 0; j < M; j++) {
				ds[j] = 1;
			}
		}
		return onesMatrix; 
	}
	
	public static int[] ones(int N){
		int[] onesVector = new int[N];
		for (int i = 0; i < N; i++) {
				onesVector[i] = 1;
		}
		return onesVector;
	}
	
	public static double[][] product(double[][] a, double[][] b) {
		int Na = a.length;
		int Ma = a[0].length;
		int Nb = b.length;
		int Mb = b[0].length;
		double[][] product = new double[Na][Mb];
		if(Ma==Nb){
			double[] bColumnJ = new double[Na];
			for (int j = 0; j < Mb; j++) {
				for (int k = 0; k < Na; k++){
					bColumnJ[k]= b[k][j];
				}
				for (int i = 0; i < Na; i++) {
					double[] aRowI = a[i];
					double productIJ = 0;
					for(int k = 0; k < Na; k++){
						productIJ += aRowI[k] * bColumnJ[k]; 
					}
					product[i][j] = productIJ;
				}
			}
		}
		return product;
	}
	
	public static double innerProduct(double[] a, double[] b){
		int Na = a.length;
		int Nb = b.length;
		double innerProduct = 0;
		if(Na==Nb){
			for (int i = 0; i < a.length; i++) {
				innerProduct += a[i]*b[i];
			}
		}
		return innerProduct;
	}
	
	public static double[] innerProduct(double[][] a, double[] b){
		if(a[0].length != b.length) {
			System.out.println("Warning: Matrix inner dimensions disagree");
		}
		double[] innerProduct = new double[a.length];
		for (int i = 0; i < innerProduct.length; i++) {
			innerProduct[i] = MatrixOperator.innerProduct(a[i], b);
//			System.out.println(" innerProduct["+i+"]:"+innerProduct[i]+" b:"+toString(b)+" | "+toString(a[i]));
		}
		return innerProduct;
	}
	
	public static double[] add(double[] a, double[] b){
		double[] sum = new double[a.length];
		for (int i = 0; i < b.length; i++) {
			sum[i] = a[i] + b[i];
		}
		return sum;
	}
	
	public static double[] addHalf(double[] a, double[] b){
		double[] sum = new double[a.length];
		for (int i = 0; i < b.length; i++) {
			sum[i] = (a[i] + b[i])*0.5;
		}
		return sum;
	}
	
	public static double[][] elementMask(double[][] a, int[][] mask){
		int N = a.length;
		int M = a[0].length;
		double[][] masked = new double[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (mask[i][j] == 0) {
					masked[i][j] = 0;
				} else {
					masked[i][j] = a[i][j];
				}
			}
		}
		return masked;
	}
	
	public static double[][] crossDeletionMask(double[][] a, int[] mask){
		int N = mask.length;
		int n=0;
		for (int i = 0; i < N; i++) {
			n+=mask[i];
		}
		double[][] masked = new double[n][n];
		for (int i = 0, x = 0; i < N; i++) {
			if(mask[x]!=0) {
				for (int j = 0, y = 0; j < N; j++) {
					if(mask[y]!=0){
						masked[x][y]=a[i][j];
						y++;
					}
				}
				x++;
			}		
		}
		return masked;
	}
	
	public static double[][] rowDeletionMask(double[][] a, int[] mask){
		int N = mask.length;
		int M = a[0].length;
		int n=0;
		for (int i = 0; i < N; i++) {
			n+=mask[i];
		}
		
		double[][] masked = new double[n][M];
		for (int i = 0, x=0; i < N; i++) {
			if(mask[x]!=0) {
				for (int j = 0; j < M; j++) {
					masked[i][j]=a[x][j];
				}
				x++;
			}		
		}
		
		return masked;
	}
	
	public static String toString(double[][] a){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < a.length; i++ ){
			double[] aRow = a[i];
			sb.append("\t");
			for (int j = 0; j < a[0].length; j++) {
				sb.append(" "+aRow[j]);
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public static double[][] matrixCopy(double[][] source){
		double[][] copy = new double[source.length][source[0].length];
		for (int i = 0; i < copy.length; i++) {
			double[] dc = copy[i];
			double[] ds = source[i];
			System.arraycopy(ds, 0, dc, 0, dc.length);
		}
		return copy;
	}
	
	public static int[][] matrixCopy(int[][] source){
		int[][] copy = new int[source.length][source[0].length];
		for (int i = 0; i < copy.length; i++) {
			int[] dc = copy[i];
			int[] ds = source[i];
			System.arraycopy(ds, 0, dc, 0, dc.length);
		}
		return copy;
	}
	
	public static String toString(double[] a){
		StringBuffer sb = new StringBuffer();
		sb.append("\t");
		for (int i = 0; i < a.length; i++ ){
			sb.append(" "+a[i]);
		}
		return sb.toString();
	}
	
	public static void main(String[] args){
		double[][] a = {{0.1,0.2,0.3},{0.2,0.4,0.6},{0.3,0.6,0.9}};
		double[] b = {0.1,0.2,0.3};
		double[] c = MatrixOperator.innerProduct(a, b);
		System.out.println(MatrixOperator.toString(c));
	}
}
