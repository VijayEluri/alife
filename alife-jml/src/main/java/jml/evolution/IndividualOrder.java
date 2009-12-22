package jml.evolution;
import jml.math.abs.Order;

public class IndividualOrder extends Order{
  /**
   * Determines if the object is less than (in some order) the given object
   * @param x Comparison object
   * @return true if the object is less than the given object x. false in other case
   */
  public boolean lessThan(Object x, Object y) {
    return (((Individual) x).getFitness() < ((Individual) y).getFitness());
  }

  /**
   * Determines if the object is equal to the given object
   * @param x Comparison object
   * @return true if the object is equal to the given object x. false in other case
   */
  public boolean equalTo(Object x, Object y) {
    return (((Individual) x).getFitness() == ((Individual) y).getFitness());
  }
}
