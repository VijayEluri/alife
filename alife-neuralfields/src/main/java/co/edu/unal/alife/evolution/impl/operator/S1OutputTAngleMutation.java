package co.edu.unal.alife.evolution.impl.operator;

import java.util.List;

import co.edu.unal.alife.evolution.param.S1ControllerParameters;
import jml.evolution.Environment;
import jml.evolution.real.RealVectorLimits;
import jml.evolution.real.operators.GaussianMutation;
import jml.random.UniformNumberGenerator;

public class S1OutputTAngleMutation extends GaussianMutation {

	double sigma;

	/**
	 * @param _environment
	 * @param _limits
	 * @param _sigma
	 */
	public S1OutputTAngleMutation(Environment _environment,
			RealVectorLimits _limits, double _sigma) {
		super(_environment, _limits, _sigma);
		this.sigma = _sigma;
	}

	@Override
	public Object apply(Object gen) {
		S1ControllerParameters genome = (S1ControllerParameters) gen;
		List<Double> doubleTs = genome.getOutParams().getDoubleTs();
		int size = doubleTs.size();
		double min = limits.min[0];
		double max = limits.max[0];
		int pos = -1;
		try {
			UniformNumberGenerator s = new UniformNumberGenerator(size);
			pos = s.newInt();
			double x = doubleTs.get(pos);
			g.setSigma(sigma);
			double y = g.newDouble();
			x += y;
			if (x < min) {
				x = min;
			} else {
				if (x > max) {
					x = max;
				}
			}
			doubleTs.set(pos, x);
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
