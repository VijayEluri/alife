package evolution;

import jml.evolution.Phenotype;
import gait.RnnController;

public class RnnPPPhenotype extends Phenotype {
	
	protected RnnPPGenotype genotype;
	
	public RnnPPPhenotype(RnnPPGenotype genotype){
		this.genotype = genotype;
	}
	
	@Override
	public Object get(Object genome) {
		return new RnnController(genotype,genome);
	}
}
