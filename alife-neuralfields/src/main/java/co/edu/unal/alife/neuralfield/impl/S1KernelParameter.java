package co.edu.unal.alife.neuralfield.impl;

public class S1KernelParameter {
	private double[][] choleskyDecomposition;
	private double h0;
	private double delta;
	private double k;

	public S1KernelParameter(double[][] choleskyDecompositions, double h0, double delta, double k) {
		this.choleskyDecomposition = choleskyDecompositions;
		this.h0 = h0;
		this.delta = delta;
		this.k = k;
	}

	public double[][] getCholeskyDecomposition() {
		return choleskyDecomposition;
	}

	public void setCholeskyDecompositions(double[][] choleskyDecomposition) {
		this.choleskyDecomposition = choleskyDecomposition;
	}

	public double getH0() {
		return h0;
	}

	public void setH0(double h0) {
		this.h0 = h0;
	}

	public double getDelta() {
		return delta;
	}

	public void setDelta(double delta) {
		this.delta = delta;
	}

	public double getK() {
		return k;
	}

	public void setK(double k) {
		this.k = k;
	}
}