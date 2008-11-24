/**
 * 
 */
package neuralnet;

import util.MatrixOperator;

/**
 * @author Juan Figueredo
 *
 */
public class RecurrentNeuralNet {
	private double[][] recurrentWeights;
	private double[][] inputWeights;
	private double[] state;
	private int stateDimension;
	private int inputDimension;
	private TransferFunction transferFunction;
	
	/**
	 * @param recurrentWeights
	 * @param inputWeights
	 * @param state
	 * @param stateDimension
	 * @param inputDimension
	 * @param transferFunction
	 */
	public RecurrentNeuralNet(double[][] recurrentWeights, double[][] inputWeights, double[] state, int stateDimension, int inputDimension, TransferFunction transferFunction) {
		this.recurrentWeights = recurrentWeights;
		this.inputWeights = inputWeights;
		this.state = state;
		this.stateDimension = stateDimension;
		this.inputDimension = inputDimension;
		this.transferFunction = transferFunction;
	}

	public RecurrentNeuralNet(int[][] Aa, int[][] Ab, int[] An, double[][] Wa, double[][] Wb, int Q, int m){
		double[][] a = MatrixOperator.crossDeletionMask(MatrixOperator.elementMask(Wa,Aa),An);
		double[][] b = MatrixOperator.rowDeletionMask(MatrixOperator.elementMask(Wb,Ab),An);				
		double[] s = new double[a.length];
		for (int i = 0; i < s.length; i++) {
			s[i]=0;
		}
		this.transferFunction = new TanhTransferFunction();
		this.recurrentWeights = a;
		this.inputWeights = b;
		this.inputDimension = m;
		this.stateDimension = a.length;
		this.state = s;
	}
	
	public void iterate(double[] input) {
//		double[] recurrentPotential = MatrixOperator.innerProduct(this.recurrentWeights, this.state);
//    	System.out.println("? "+MatrixOperator.toString(inputWeights)+"\n"+MatrixOperator.toString(input));
//		System.out.println("! "+MatrixOperator.toString(MatrixOperator.innerProduct(inputWeights,input)));
//		System.out.println("RP: "+MatrixOperator.toString(recurrentPotential));
		double[] inputPotential = MatrixOperator.innerProduct(this.inputWeights, input);
//		System.out.println("IP: "+MatrixOperator.toString(inputPotential));
		double[] potential = MatrixOperator.add(inputPotential, inputPotential);
//		System.out.println("P: "+potential[0]+" "+potential[1]);
		state = this.transferFunction.eval(potential);
//		System.out.println("S: "+state[0]+" "+state[1]);
	}
	
	public int getInputDimension() {
		return inputDimension;
	}

	public double[][] getInputWeights() {
		return inputWeights;
	}

	public double[][] getRecurrentWeights() {
		return recurrentWeights;
	}

	public double[] getState() {
		return state;
	}

	public int getStateDimension() {
		return stateDimension;
	}

	public TransferFunction getTransferFunction() {
		return transferFunction;
	}

	public static void main(String[] args){
		int iterations = 100000;
		int inputs = 3;
		int states = 2;
		double[] outputSequence = new double[states];
		double[][] recurrentWeights = MatrixOperator.random(states, states, 4, -2);
		double[][] inputWeights = MatrixOperator.random(states, inputs+1, 4, -2);
		double[] state = {0,0};
		RecurrentNeuralNet rnn = new RecurrentNeuralNet(recurrentWeights, inputWeights, state, states, inputs, new TanhTransferFunction());
		for (int i = 0; i < iterations; i++) {
			double[] input = MatrixOperator.random(inputs+1, 2, -1);
			input[0] = 1;
			rnn.iterate(input);
			outputSequence = rnn.getState();
		}
		//System.out.println(MatrixOperator.toString(inputSequence));
		//System.out.println("***********************************\n");
		//System.out.println(MatrixOperator.toString(outputSequence));
		System.out.println(MatrixOperator.toString(outputSequence));
		
	}
}
