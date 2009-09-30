package test.evolution.dfa;

import jml.math.abs.Order;

public class SequenceOrder extends Order{
  /**
   * Determines if the object is less than (in some order) the given object
   * @param x Comparison object
   * @return true if the object is less than the given object x. false in other case
   */
  public boolean lessThan( Object x, Object y ){
    return (((Sequence)x).length()<((Sequence)y).length());
  }

  /**
   * Determines if the object is equal to the given object
   * @param x Comparison object
   * @return true if the object is equal to the given object x. false in other case
   */
  public boolean equalTo( Object x, Object y ){
    return (((Sequence)x).length()==((Sequence)y).length());
  }
}
