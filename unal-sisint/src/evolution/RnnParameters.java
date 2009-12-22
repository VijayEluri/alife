package evolution;

import util.MatrixOperator;

public class RnnParameters {
	public int[][] Aa;
	public int[][] Ab;
	public int[] An;
	public double[][] Wa;
	public double[][] Wb;
	public int states;
	public int inputs;
	public double sampleTime;
	
	/**
	 * @param aa
	 * @param ab
	 * @param an
	 * @param wa
	 * @param wb
	 * @param states
	 * @param inputs
	 */
	public RnnParameters(int[][] aa, int[][] ab, int[] an, double[][] wa, double[][] wb, int states, int inputs, double sampleTime) {
		Aa = aa;
		Ab = ab;
		An = an;
		Wa = wa;
		Wb = wb;
		this.states = states;
		this.inputs = inputs;
		this.sampleTime = sampleTime;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		int[][] Aa = MatrixOperator.matrixCopy(this.Aa);
		int[][] Ab = MatrixOperator.matrixCopy(this.Ab);
		int[] An = new int[this.An.length];
		System.arraycopy(this.An, 0, An, 0, An.length);
		double[][] Wa = MatrixOperator.matrixCopy(this.Wa);
		double[][] Wb = MatrixOperator.matrixCopy(this.Wb);
		int states = this.states;
		int inputs = this.inputs;
		double sampleTime = this.sampleTime;
		return new RnnParameters(Aa,Ab,An,Wa,Wb,states,inputs,sampleTime);
	}
	
}
