package co.edu.unal.alife.evolution.impl.operator;

import java.util.List;

import co.edu.unal.alife.evolution.param.S1ControllerParameters;
import jml.evolution.Environment;
import jml.evolution.real.RealVectorLimits;
import jml.evolution.real.operators.GaussianMutation;
import jml.random.UniformNumberGenerator;

public class S1RepresentationCholeskyMutation extends GaussianMutation {

	double sigma;

	/**
	 * @param _environment
	 * @param _limits
	 * @param _sigma
	 */
	public S1RepresentationCholeskyMutation(Environment _environment,
			RealVectorLimits _limits, double _sigma) {
		super(_environment, _limits, _sigma);
		this.sigma = _sigma;
	}

	@Override
	public Object apply(Object gen) {
		S1ControllerParameters genome = (S1ControllerParameters) gen;
		List<Double> cholesky1s = genome.getRepParams().getCholesky1s();
		List<Double> cholesky2s = genome.getRepParams().getCholesky2s();
		assert (cholesky1s.size() == cholesky2s.size());
		int size = cholesky1s.size();
		double min = genome.getRepParams().getMinChoVal();
		double max = genome.getRepParams().getMaxChoVal();
		int pos = -1;
		try {
			UniformNumberGenerator s = new UniformNumberGenerator(size);
			pos = s.newInt();
			double x1 = cholesky1s.get(pos);
			double x2 = cholesky2s.get(pos);
			g.setSigma(sigma);
			double y1 = g.newDouble();
			double y2 = g.newDouble();
			x1 += y1;
			x2 += y2;
			if (x1 < min) {
				x1 = min;
			} else {
				if (x1 > max) {
					x1 = max;
				}
			}
			if (x2 < min) {
				x2 = min;
			} else {
				if (x2 > max) {
					x2 = max;
				}
			}
			cholesky1s.set(pos, x1);
			cholesky2s.set(pos, x2);
		} catch (Exception e) {
			System.err.println("[Gaussian Mutation]" + e.getMessage());
		}
		return new Integer(pos);
	}

	@Override
	public void setSigma(double arg0) {
		this.sigma = arg0;
	}

}