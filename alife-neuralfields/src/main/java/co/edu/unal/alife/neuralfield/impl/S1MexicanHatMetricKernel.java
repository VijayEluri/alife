package co.edu.unal.alife.neuralfield.impl;

/**
 * @author jjfigueredou
 * 
 */
public class S1MexicanHatMetricKernel extends MexicanHatKernel {

	/**
	 * Metric tensor
	 */
	private double[][] g;
	/**
	 * The distances between discrete points
	 */
	private double[][] distance;
	/**
	 * The number of discrete points in field
	 */
	private int points;
	/**
	 * The supremum of the distances between 2 points in the field
	 */
	private double radius;

	public S1MexicanHatMetricKernel(S1KernelParameter parameterObject,
			int _points) {
		this.points = _points;
		this.delta = parameterObject.getDelta();
		this.k = parameterObject.getK();
		this.H0 = parameterObject.getH0();
		double[][] L = parameterObject.getCholeskyDecomposition();
		// g=LL*
		g = new double[][] { { L[0][0] * L[0][0], L[0][0] * L[1][0] },
				{ L[0][0] * L[1][0], L[1][0] * L[1][0] + L[1][1] * L[1][1] } };
		double dphi = 2 * Math.PI / points;
		distance = new double[points][points];
		radius = 0;
		// Here we fill the (i,i+1)-diagonal of distance[][] and the first row
		// (0,1...)
		for (int i = 0; i < points - 1; i++) {
			int j = i + 1;
			double phi0 = (j - 1) * dphi;
			double phi1 = phi0 + dphi / 2;
			double phi2 = phi0 + dphi;

			// Simpson's rule
			double s = dphi / 6 * (ds(phi0) + 4 * ds(phi1) + ds(phi2));
			radius += s;
			// First row
			if (i > 0) {
				distance[0][j] = distance[0][j - 1] + s;
			}
			distance[i][j] = s;
		}
		double phi0 = (points - 1) * dphi;
		double phi1 = phi0 + dphi / 2;
		double phi2 = phi0 + dphi;
		double s = dphi / 6 * (ds(phi0) + 4 * ds(phi1) + ds(phi2));
		distance[0][points - 1] = s;
		radius += s;
		// Here we fill the rest of the upper triangle
		for (int i = 1; i < points; i++) {
			for (int j = i + 2; j < points; j++) {
				distance[i][j] = distance[i - 1][j] - distance[i - 1][i];
			}
		}
	}

	private double ds(double phi) {
		double c2phi = Math.cos(2 * phi);
		double s2phi = Math.sin(2 * phi);
		double a = (g[0][0] + g[0][1]) / 2;
		double b = (g[1][1] - g[0][0]) / 2;
		double c = (g[0][1] + g[1][0]) / 2;
		double d = 2 * (a + b * c2phi - c * s2phi);
		return d;
	}

	/**
	 * Evaluates the distance of the vectors parameterized by x, y in
	 * [0,points). The vectors have the form p(x)=[cos(2*pi*x); sin(2*pi*x)] and
	 * the metric tensor (in this case a constant tensor along the field) is the
	 * attribute g.
	 * 
	 * @param x
	 * @param y
	 **/
	@Override
	public double squareDistance(Double x, Double y) {
		int xIndex = (int) (x.doubleValue());
		int yIndex = (int) (y.doubleValue());
		if (xIndex > yIndex) {
			int temp = xIndex;
			xIndex = yIndex;
			yIndex = temp;
		}
		double dxy = distance[xIndex][yIndex];
		double dyx = radius - dxy;
		// Given a connected field we evaluate the distance as the minimum path
		// length
		double d = (dyx > dxy) ? dxy : dyx;
		return d * d;
	}


	public static void main(String[] args) {
		double[][] L = { { 1, 0}, { 0, 1 } };
		S1KernelParameter kernelParameter = new S1KernelParameter(L, 0.1, 2, 2);
		int points2 = 20;
		S1MexicanHatMetricKernel kernel = new S1MexicanHatMetricKernel(
				kernelParameter, points2);
		double hh = 2 * Math.PI / points2;
		for (int i = 0; i < points2; i++) {
			for (int j = 0; j < points2; j++) {
				if (i == 0) {
					double ds = kernel.squareDistance(i * 1.0, j * 1.0);
					ds = Math.sqrt(ds);
				 	System.out.println("Distancia entre " + (i * hh) + " y "
							+ (j * hh) + " es: " + ds);
				}
			}
		}
		System.out.println();
		for (int i = 0; i < points2; i++) {
			for (int j = 0; j < points2; j++) {
				if (i == 0) {
					double ds = kernel.evaluateTransformation(i*1.0, j*1.0);
					ds = Math.sqrt(ds);
				 	System.out.println("Kernel entre " + (i * hh) + " y "
							+ (j * hh) + " es: " + ds);
				}
			}
		}
	}
}
