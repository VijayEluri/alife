package evolution;

import util.MatrixOperator;
import jml.evolution.Genotype;

public class RnnPPOrientationGenotype extends RnnPPGenotype {
		
	/**
	 * @param states
	 */
	public RnnPPOrientationGenotype(int states) {
		super(states);
	}

	@Override
	public Object newInstance() {
		int inputs = 3;
//		double[][] Wa = MatrixOperator.random(states, states, 0.2, -0.1);
//		double[][] Wb = MatrixOperator.random(states, inputs+1, 4, -2);
		double[][] Wa = MatrixOperator.random(states, states, 4/Math.sqrt(states), -2/Math.sqrt(states));
		double[][] Wb = MatrixOperator.random(states, inputs+1, 4, -2);
		int[][] Aa = MatrixOperator.ones(states, states);
		int[][] Ab = MatrixOperator.ones(states, inputs+1);
		int[] An = MatrixOperator.ones(states);
		double sampleTime = 0.025;
		RnnParameters rnnParam = new RnnParameters(Aa,Ab,An,Wa,Wb,states,inputs,sampleTime);
		return rnnParam;
	}
}
