/**
 * 
 */
package co.edu.unal.alife.evolution;

import jml.evolution.Phenotype;
import co.edu.unal.alife.pendulum.PendulumController;

/**
 * @author Juan Figueredo
 *
 */
public class PendulumControllerPhenotype extends Phenotype {

	private int halfSize;
	
	/**
	 * @param genotype
	 * @param halfSize
	 */
	public PendulumControllerPhenotype(PendulumControllerGenotype genotype, int halfSize) {
		super();
		this.halfSize = halfSize;
	}

	/* (non-Javadoc)
	 * @see jml.evolution.Phenotype#get(java.lang.Object)
	 */
	@Override
	public Object get(Object genome) {
		return new PendulumController(halfSize,(PendulumControllerParameters)genome);
	}
}
