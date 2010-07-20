package co.edu.unal.alife.evolution.param;

public class S1ControllerParameters {
	private int inSize;
	private int hiSize;
	private int outSize;
	private S1InputParameters inParams;
	private S1RepresentationParameters repParams;
	private S1OutputParameters outParam;

	public S1ControllerParameters() {
		// TODO Auto-generated constructor stub
	}

	public S1ControllerParameters(S1InputParameters inParams,
			S1RepresentationParameters repParams, S1OutputParameters outParam) {
		super();
		this.inSize = inParams.getInCardinality();
		this.hiSize = repParams.getPrCardinality();
		this.outSize = outParam.getOutCardinality();
		this.inParams = inParams;
		this.repParams = repParams;
		this.outParam = outParam;
	}

	public S1ControllerParameters(int noInputs, int noGoals, int noOutputs,
			double[] Chol1, double[] Chol2, double[] maps, double[] alphas,
			double minKernelRepK, double maxKernelRepK, double minKernelDelta,
			double maxKernelDelta, double minKernelInK, double maxKernelInk) {
		this.inSize = noInputs;
		this.hiSize = noGoals;
		this.outSize = noOutputs;
		this.inParams = new S1InputParameters(this.inSize, this.hiSize, maps);
		this.repParams = new S1RepresentationParameters(hiSize, Chol1, Chol2,
				minKernelRepK, maxKernelRepK, minKernelDelta, maxKernelDelta, minKernelInK, maxKernelInk);
		this.outParam = new S1OutputParameters(hiSize, outSize, alphas);
	}

	public S1ControllerParameters(int inCardinality, int repCardinality,
			int outCardinality, double minKVal, double maxKVal,
			double minCholeskyVal, double maxCholeskyVal, double minAlphaVal,
			double maxAlphaVal) {
		this.inSize = inCardinality;
		this.hiSize = repCardinality;
		this.outSize = outCardinality;
		this.inParams = new S1InputParameters(inCardinality, repCardinality,
				minKVal, maxKVal);
		this.repParams = new S1RepresentationParameters(repCardinality,
				minCholeskyVal, maxCholeskyVal);
		this.outParam = new S1OutputParameters(repCardinality, outCardinality,
				minAlphaVal, maxAlphaVal);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		S1ControllerParameters out = new S1ControllerParameters();
		out.inParams = (S1InputParameters) this.inParams.clone();
		out.repParams = (S1RepresentationParameters) this.repParams.clone();
		out.outParam = (S1OutputParameters) this.outParam.clone();
		out.inSize = this.inSize;
		out.hiSize = this.hiSize;
		out.outSize = this.outSize;
		return out;
	}

	@Override
	public String toString() {
		String s = "{" + inParams.toString() + "}\n {" + repParams.toString()
				+ "}\n {" + outParam.toString() + "}";
		return s;
	}

	public int getInSize() {
		return inSize;
	}

	public int getHiSize() {
		return hiSize;
	}

	public int getOutSize() {
		return outSize;
	}

	public S1InputParameters getInParams() {
		return inParams;
	}

	public S1RepresentationParameters getRepParams() {
		return repParams;
	}

	public S1OutputParameters getOutParams() {
		return outParam;
	}
	

}
