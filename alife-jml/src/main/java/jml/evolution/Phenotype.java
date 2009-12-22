package jml.evolution;

/**
 * <p>Title: Phenotype </p>
 * <p>Description: An individual Phenotype </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class Phenotype {
	/**
	   * Environment of the Phenotype
	   */
  protected Environment environment;

  /**
   * Default constructor
   */
  public Phenotype() { }

  /**
   * Generates a thing from the given genome
   * @param genome Genome of the thing to be expressed
   * @return A thing expressed from the genome
   */
  public Object get(Object genome) { return genome; }

  /**
   * Generates a genome from the given thing
   * @param thing A thing expressed from the genome
   * @return Genome of the thing
   */
  public Object set(Object thing) { return (Object)thing; }

}
