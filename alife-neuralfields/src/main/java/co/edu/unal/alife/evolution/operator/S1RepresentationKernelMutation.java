package co.edu.unal.alife.evolution.operator;

import java.util.List;

import jml.evolution.Environment;
import jml.evolution.real.operators.GaussianMutation;
import jml.random.UniformNumberGenerator;
import co.edu.unal.alife.evolution.params.S1ControllerParameters;

public class S1RepresentationKernelMutation extends GaussianMutation {

	double sigmaDelta;
	double sigmaRepK;
	double sigmaInK;
	Integer affectedElement;

	/**
	 * @param _environment
	 * @param _limits
	 * @param _sigma
	 */
	public S1RepresentationKernelMutation(Environment _environment, double _sigmaDelta, double _sigmaRepK, double _sigmaInK) {
		super(_environment, null, null);
		this.sigmaDelta = _sigmaDelta;
		this.sigmaRepK = _sigmaRepK;
		this.sigmaInK = _sigmaInK;
	}
	
	public S1RepresentationKernelMutation(Environment _environment, double _sigmaDelta, double _sigmaRepK, double _sigmaInK, Integer _affectedElement) {
		this(_environment,_sigmaDelta,_sigmaRepK,_sigmaInK);
		this.affectedElement=_affectedElement;
	}
	@Override
	public Object apply(Object gen) {
		S1ControllerParameters genome = (S1ControllerParameters) gen;
//		System.out.println("Mutación: ");
//		System.out.println("\t Antes: "+genome.getRepParams().toString());
		List<Double> repKs = genome.getRepParams().getRepKs();
		List<Double> deltas = genome.getRepParams().getDeltas();
		List<Double> inKs = genome.getRepParams().getInKs();
		assert (repKs.size() == deltas.size() && repKs.size() == inKs.size());
		int sizeR = repKs.size();
		int sizeD = deltas.size();
		int sizeI = inKs.size();
		
		double minRepKs = genome.getRepParams().getMinKernelRepK();
		double maxRepKs = genome.getRepParams().getMaxKernelRepK();
		double minDeltas = genome.getRepParams().getMinKernelDelta();
		double maxDeltas = genome.getRepParams().getMaxKernelDelta();
		double minInKs = genome.getRepParams().getMinKernelInK();
		double maxInKs = genome.getRepParams().getMaxKernelInK();
		
		int posD = -1;
		int posR = -1;
		int posI = -1;
		
		try {
			if (affectedElement==null) {
				UniformNumberGenerator s = new UniformNumberGenerator(sizeD);
				posD = s.newInt();
				s = new UniformNumberGenerator(sizeR);
				posR = s.newInt();
				s = new UniformNumberGenerator(sizeI);
				posI = s.newInt();
			} else {
				posD = affectedElement;
				posR = affectedElement;
				posI = affectedElement;
			}
//			System.out.println("PosD:"+posD+" posK:"+posK);
			double x1 = deltas.get(posD);
			double x2 = repKs.get(posR);
			double x3 = inKs.get(posI);
			g.setSigma(sigmaDelta);
			double y1 = g.newDouble();
			g.setSigma(sigmaRepK);
			double y2 = g.newDouble();
			g.setSigma(sigmaInK);
			double y3 = g.newDouble();
//			System.out.println("sigmaK="+sigmaK+", sigmaDelta="+sigmaDelta+".");
//			System.out.println("y(K)="+y2+", y(Delta)="+y1+".");
			x1 += y1;
			x2 += y2;
			x3 += y3;
//			System.out.print("minDeltas: "+minDeltas+" maxDeltas: "+maxDeltas);
//			System.out.println(" minKs: "+minKs+" maxKs: "+maxKs);

			if (x1 < minDeltas) {
				x1 = minDeltas;
			} else {
				if (x1 > maxDeltas) {
					x1 = maxDeltas;
				}
			}
			if (x2 < minRepKs) {
				x2 = minRepKs;
			} else {
				if (x2 > maxRepKs) {
					x2 = maxRepKs;
				}
			}
			if (x3 < minInKs) {
				x3 = minInKs;
			} else {
				if (x3 > maxInKs) {
					x3 = maxInKs;
				}
			}
			deltas.set(posD, x1);
			repKs.set(posR, x2);
			inKs.set(posI, x3);
		} catch (Exception e) {
			System.err.println("[Gaussian Mutation]" + e.getMessage());
		}
//		System.out.println("\t Despues: "+genome.getRepParams().toString());
		return new Integer(0);
	}

}