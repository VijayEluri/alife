package co.edu.unal.alife.poc.neuralfield;

public interface IPocNeuralfieldInputEquation {

	public abstract double evalInputDelta(double[] lastSwitchQ, int localIndex);

}