/**
 * 
 */
package gait;

import java.util.Arrays;

import util.MatrixOperator;
import neuralnet.RecurrentNeuralNet;
import evolution.RnnPPGenotype;
import evolution.RnnParameters;

/**
 * @author Juan Figueredo
 *
 */
public class RnnController {

	protected RecurrentNeuralNet rnn;
	protected  RnnParameters rnnParam;
	protected DistanceFuzzySensor distSensor;
	protected OrientationFuzzySensor orientSensor;
	protected double sampleTime;
	protected double evalTime = 0;
	
	public RnnController(RecurrentNeuralNet rnn, double sampleTime){
		this.rnn = rnn;
		distSensor = new DistanceFuzzySensor();
		orientSensor = new OrientationFuzzySensor();
		this.sampleTime = sampleTime;
		this.evalTime = 0;
	}
	
	public RnnController(RnnParameters rnnParam){
		rnn = new RecurrentNeuralNet(rnnParam.Aa,rnnParam.Ab,rnnParam.An,rnnParam.Wa,rnnParam.Wb,rnnParam.states,rnnParam.inputs);
		distSensor = new DistanceFuzzySensor();
		orientSensor = new OrientationFuzzySensor();
		this.evalTime = 0;
		this.rnnParam = rnnParam;
		sampleTime = rnnParam.sampleTime;
	}
	
	public RnnController(RnnPPGenotype genotype, Object rnnParam){
		this((RnnParameters)rnnParam);
	}
	
	public double[] eval(double distance, double orientation, double time){
		if (time >= evalTime){
			evalTime += sampleTime;
			double[] distMeasure = distSensor.eval(distance);
//			double[] orientMeasure = orientSensor.eval(orientation);
			double[] orientMeasure = orientSensor.eval(orientation);
//			double[] input = new double[orientMeasure.length+1];
			double[] input = new double[distMeasure.length+orientMeasure.length+1];
			System.arraycopy(distMeasure, 0, input, 0, distMeasure.length);
			System.arraycopy(orientMeasure, 0, input, distMeasure.length, orientMeasure.length);
//			System.out.println(distMeasure.length+orientMeasure.length+1);
//			System.arraycopy(orientMeasure, 0, input, 0, orientMeasure.length);
			input[input.length-1]=0;
			//System.out.println("time: "+time+" "+input[0]+" "+input[1]+" "+input[2]);
			rnn.iterate(input);
//			System.out.println(MatrixOperator.toString(input));
			//System.out.println("state:"+MatrixOperator.toString(rnn.getState()));
		}
		//System.out.println(" rnn.getState(): "+rnn.getState()[0]+" "+rnn.getState()[1]+" "+rnn.getState()[2]+" "+rnn.getState()[3]+" "+rnn.getState()[4]+" "+rnn.getState()[5]+" "+rnn.getState()[6]+" "+rnn.getState()[7]);
		return rnn.getState();
	}

	public RnnParameters getRnnParam() {
		return rnnParam;
	}

	public double getEvalTime() {
		return evalTime;
	}

	public void setEvalTime(double evalTime) {
		this.evalTime = evalTime;
	}
	
	
	
		
}
