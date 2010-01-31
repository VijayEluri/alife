package co.edu.unal.alife.evolution.operator;

import java.util.List;

import jml.evolution.Environment;
import jml.evolution.real.RealVectorLimits;
import jml.evolution.real.operators.GaussianMutation;
import jml.random.UniformNumberGenerator;
import co.edu.unal.alife.evolution.params.S1ControllerParameters;

public class S1InputKsMutation extends GaussianMutation {

	double sigma;

	/**
	 * @param _environment
	 * @param _limits
	 * @param _sigma
	 */
	public S1InputKsMutation(Environment _environment,
			RealVectorLimits _limits, double _sigma) {
		super(_environment, _limits, _sigma);
		this.sigma = _sigma;
	}

	@Override
	public Object apply(Object gen) {
		S1ControllerParameters genome = (S1ControllerParameters) gen;
		List<Double> ks = genome.getInParams().getKs();
		int size = ks.size();
		double min = genome.getInParams().getMinValue();
		double max = genome.getInParams().getMaxValue();
		int pos = -1;
		try {
			UniformNumberGenerator s = new UniformNumberGenerator(size);
			pos = s.newInt();
			double x = ks.get(pos);
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
			ks.set(pos,x);
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
