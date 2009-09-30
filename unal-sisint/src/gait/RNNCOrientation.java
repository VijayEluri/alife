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
public class RNNCOrientation extends RnnController {
	
	public RNNCOrientation(RecurrentNeuralNet rnn, double sampleTime){
		super(rnn,sampleTime);
	}
	
	public RNNCOrientation(RnnParameters rnnParam){
		super(rnnParam);
	}
	
	public RNNCOrientation(RnnPPGenotype genotype, Object rnnParam){
		this((RnnParameters)rnnParam);
	}
	
	public double[] eval(double distance, double orientation, double time){
		if (time >= evalTime){
			evalTime += sampleTime;
			double[] orientMeasure = orientSensor.eval(orientation);
			double[] input = new double[orientMeasure.length+1];	
			System.arraycopy(orientMeasure, 0, input, 0, orientMeasure.length);
			input[input.length-1]=0;
			rnn.iterate(input);
		}
		return rnn.getState();
	}		
}
