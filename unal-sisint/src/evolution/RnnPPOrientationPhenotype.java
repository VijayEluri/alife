package evolution;

import gait.RNNCOrientation;

public class RnnPPOrientationPhenotype extends RnnPPPhenotype {
	
	public RnnPPOrientationPhenotype(RnnPPGenotype genotype){
		super(genotype);
	}
	
	@Override
	public Object get(Object genome) {
		return new RNNCOrientation(genotype,genome);
	}
}
