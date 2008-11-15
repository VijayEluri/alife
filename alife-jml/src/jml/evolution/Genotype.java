package jml.evolution;


/**
 * <p>Title: Genotype </p>
 * <p>Description: An individual genotype </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public abstract class Genotype {
	/**
	   * Environment of the Genotype
	   */
  protected Environment environment;

  /**
   * Creates a new genome of the given genotype
   * @return Object The new genome
   */
  public abstract Object newInstance();

  /**
   * Returns the number of genes in the individual's genome
   * @param genome
   * @return Number of genes in the individual's genome
   */
  public abstract int size(Object genome);
}
