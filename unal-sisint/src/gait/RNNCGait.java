/**
 * 
 */
package gait;

import evolution.RnnPPGenotype;
import evolution.RnnParameters;
import neuralnet.RecurrentNeuralNet;

/**
 * @author Juan Figueredo
 *
 */
public class RNNCGait extends RnnController {
	
	public RNNCGait(RecurrentNeuralNet rnn, double sampleTime){
		super(rnn,sampleTime);
	}
	
	public RNNCGait(RnnParameters rnnParam){
		super(rnnParam);
	}
	
	public RNNCGait(RnnPPGenotype genotype, Object rnnParam){
		this((RnnParameters)rnnParam);
	}
	
	public double[] eval(double distance, double orientation, double time){
		if (time >= evalTime){
			evalTime += sampleTime;
			double[] distMeasure = distSensor.eval(distance);
			double[] orientMeasure = orientSensor.eval(orientation);
			double[] input = new double[distMeasure.length+orientMeasure.length+1];
			System.arraycopy(distMeasure, 0, input, 0, distMeasure.length);
			System.arraycopy(orientMeasure, 0, input, distMeasure.length, orientMeasure.length);
			input[input.length-1]=0;
			rnn.iterate(input);
		}
		return rnn.getState();
	}		
}
