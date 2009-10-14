package co.edu.unal.alife.neuralfield.impl;

/**
 * @author jjfigueredou
 *
 */
public class MexicanHatS1MetricKernel extends MexicanHatKernel {

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

	public MexicanHatS1MetricKernel(MS1MKernelParameter parameterObject, int _points) {
		this.points = _points;
		double[][] L = parameterObject.getCholeskyDecomposition();
		//g=LL*
		g = new double[][] { { L[0][0] * L[0][0], L[0][0] * L[1][0] },
				{ L[0][0] * L[1][0], L[1][0] * L[1][0] + L[1][1] * L[1][1] } };
		double dphi = 2 * Math.PI / points;
		distance = new double[points][points];
		int i = 0;
		double s = 0;
		radius = 0;
		//Here we fill the diagonal of distance[][] and the first row
		for (int j = i + 1; j < points; j++) {
			double phi0 = (j - 1) * dphi;
			double phi1 = phi0 + dphi / 2;
			double phi2 = phi0 + dphi;

			// Simpson's rule
			s = dphi / 6 * (ds(phi0) + 4 * ds(phi1) + ds(phi2));
			radius += s;

			if (j > i + 1) {
				distance[i][j] = distance[i][j - 1] + s;
			}
			distance[j - 1][j] = s;
		}
		double phi0 = (points - 1) * dphi;
		double phi1 = phi0 + dphi / 2;
		double phi2 = phi0 + dphi;
		radius += dphi / 6 * (ds(phi0) + 4 * ds(phi1) + ds(phi2));
		//Herre we fill the rest of the upper triangle
		for (i = 1; i < points; i++) {
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
		return a + b * c2phi - c * s2phi;
	}

	/**
	 * Evaluates the distance of the vectors parameterized by x, y in [0,points). The
	 * vectors have the form p(x)=[cos(2*pi*x); sin(2*pi*x)] and the metric
	 * tensor (in this case a constant tensor along the field) is the attribute
	 * g.
	 * 
	 * @param x
	 * @param y
	 **/
	@Override
	public double squareDistance(Double x, Double y) {
		int xIndex = (int)x.doubleValue();
		int yIndex = (int)y.doubleValue();
		if (xIndex > yIndex) {
			int temp = xIndex;
			xIndex = yIndex;
			yIndex = temp;
		}
		double dxy = distance[xIndex][yIndex];
		double dyx = radius - dxy;
		//Given a connected field we evaluate the distance as the minimum path length
		double d = (dyx > dxy) ? dxy : dyx;
		return d*d;
	}

}
