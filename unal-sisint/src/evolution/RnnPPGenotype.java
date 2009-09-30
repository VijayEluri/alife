package evolution;

import util.MatrixOperator;
import jml.evolution.Genotype;

public class RnnPPGenotype extends Genotype {
	
	protected int states;
	protected boolean isAdaptive = false;
	
	/**
	 * @param states
	 */
	public RnnPPGenotype(int states) {
		super();
		this.states = states;
	}

	@Override
	public Object newInstance() {
		int inputs = 3;
		double[][] Wa = MatrixOperator.random(states, states, 0.2, -0.1);
		double[][] Wb = MatrixOperator.random(states, inputs+1, 4, -2);
		int[][] Aa = MatrixOperator.ones(states, states);
		int[][] Ab = MatrixOperator.ones(states, inputs+1);
		int[] An = MatrixOperator.ones(states);
		double sampleTime = 0.025;
		RnnParameters rnnParam = new RnnParameters(Aa,Ab,An,Wa,Wb,states,inputs,sampleTime);
		return rnnParam;
	}

	@Override
	public int size(Object genome) {
		// TODO Auto-generated method stub
		return 0;
	}

}
