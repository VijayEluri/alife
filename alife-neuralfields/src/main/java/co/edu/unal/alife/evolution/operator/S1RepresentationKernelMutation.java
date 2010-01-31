package co.edu.unal.alife.evolution.operator;

import java.util.List;

import jml.evolution.Environment;
import jml.evolution.real.operators.GaussianMutation;
import jml.random.UniformNumberGenerator;
import co.edu.unal.alife.evolution.params.S1ControllerParameters;

public class S1RepresentationKernelMutation extends GaussianMutation {

	double sigmaDelta;
	double sigmaK;
	Integer affectedElement;

	/**
	 * @param _environment
	 * @param _limits
	 * @param _sigma
	 */
	public S1RepresentationKernelMutation(Environment _environment, double _sigmaDelta, double _sigmaK) {
		super(_environment, null, null);
		this.sigmaDelta = _sigmaDelta;
		this.sigmaK = _sigmaK;
	}
	
	public S1RepresentationKernelMutation(Environment _environment, double _sigmaDelta, double _sigmaK, Integer _affectedElement) {
		this(_environment,_sigmaDelta,_sigmaK);
		this.affectedElement=_affectedElement;
	}
	@Override
	public Object apply(Object gen) {
		S1ControllerParameters genome = (S1ControllerParameters) gen;
//		System.out.println("Mutación: ");
//		System.out.println("\t Antes: "+genome.getRepParams().toString());
		List<Double> ks = genome.getRepParams().getKs();
		List<Double> deltas = genome.getRepParams().getDeltas();
		assert (ks.size() == deltas.size());
		int sizeK = ks.size();
		int sizeD = deltas.size();
		double minDeltas = genome.getRepParams().getMinKernelDelta();
		double maxDeltas = genome.getRepParams().getMaxKernelDelta();
		double minKs = genome.getRepParams().getMinKernelK();
		double maxKs = genome.getRepParams().getMaxKernelK();
		int posD = -1;
		int posK = -1;
		try {
			if (affectedElement==null) {
				UniformNumberGenerator s = new UniformNumberGenerator(sizeD);
				posD = s.newInt();
				s = new UniformNumberGenerator(sizeK);
				posK = s.newInt();
			} else {
				posD = affectedElement;
				posK = affectedElement;
			}
//			System.out.println("PosD:"+posD+" posK:"+posK);
			double x1 = deltas.get(posD);
			double x2 = ks.get(posK);
			g.setSigma(sigmaDelta);
			double y1 = g.newDouble();
			g.setSigma(sigmaK);
			double y2 = g.newDouble();
//			System.out.println("sigmaK="+sigmaK+", sigmaDelta="+sigmaDelta+".");
//			System.out.println("y(K)="+y2+", y(Delta)="+y1+".");
			x1 += y1;
			x2 += y2;
//			System.out.print("minDeltas: "+minDeltas+" maxDeltas: "+maxDeltas);
//			System.out.println(" minKs: "+minKs+" maxKs: "+maxKs);

			if (x1 < minDeltas) {
				x1 = minDeltas;
			} else {
				if (x1 > maxDeltas) {
					x1 = maxDeltas;
				}
			}
			if (x2 < minKs) {
				x2 = minKs;
			} else {
				if (x2 > maxKs) {
					x2 = maxKs;
				}
			}
			deltas.set(posD, x1);
			ks.set(posK, x2);
		} catch (Exception e) {
			System.err.println("[Gaussian Mutation]" + e.getMessage());
		}
//		System.out.println("\t Despues: "+genome.getRepParams().toString());
		return new Integer(0);
	}

}