package co.edu.unal.alife.neuralfield;

public abstract class NeuralFieldEquation {
	
	private KernelFunction kernelFunction;

	/**
	 * @return
	 */
	public KernelFunction getKernelFunction() {
		return kernelFunction;
	}

	/**
	 * @param kernelFunction
	 */
	public void setKernelFunction(KernelFunction kernelFunction) {
		this.kernelFunction = kernelFunction;
	}
}
