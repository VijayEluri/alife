/**
 * 
 */
package evolution;

import jml.evolution.Fitness;
import jml.evolution.Individual;

/**
 * @author Juan Figueredo
 *
 */
public abstract class RnnPPFitness extends Fitness {

	/* (non-Javadoc)
	 * @see jml.evolution.Fitness#evaluate(jml.evolution.Individual)
	 */
	@Override
	public abstract double evaluate(Individual obj) ;

}
