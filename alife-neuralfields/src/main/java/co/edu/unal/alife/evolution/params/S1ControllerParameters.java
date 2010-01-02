package co.edu.unal.alife.evolution.params;

public class S1ControllerParameters {
	private int inSize;
	private int hiSize;
	private int outSize;
	private S1InputParameters inParams;
	private S1RepresentationParameters repParams;
	private S1OutputParameters outParam;

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

	public S1ControllerParameters(int inCardinality, int repCardinality,
			int outCardinality, double minKVal, double maxKVal,
			double minCholeskyVal, double maxCholeskyVal, double minTAngVal,
			double maxTAngVal, double minAlphaVal, double maxAlphaVal) {
		this.inSize = inCardinality;
		this.hiSize = repCardinality;
		this.outSize = outCardinality;
		this.inParams = new S1InputParameters(inCardinality, repCardinality,
				minKVal, maxKVal);
		this.repParams = new S1RepresentationParameters(repCardinality,
				minCholeskyVal, maxCholeskyVal);
		this.outParam = new S1OutputParameters(repCardinality, outCardinality,
				minTAngVal, maxTAngVal, minAlphaVal, maxAlphaVal);
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
