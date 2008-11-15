package jml.math.sparse;
import java.util.Enumeration;
import java.util.Vector;

import jml.basics.Cloner;
import jml.structures.SparseValue;
import jml.structures.SparseVector;

/**
 * <p>Title: SparseRealVector</p>
 * <p>Description: Stores a vector with the positions of a vector where that positions</p>
 * have a value different of the default value, the value maybe different for every position.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class SparseRealVector extends SparseVector {
/**
 * Get all the vector including the position with default value
 * @return All the vector including the position with default value
 */
  public double[] fullValues() {
    double d = ((SparseReal) this.getDefaultValue()).value;
    double[] c = new double[this.dimension()];
    for (int i = 0; i < this.dimension(); i++) {
      c[i] = d;
    }
    SparseReal x;
    Enumeration iter = elements();
    while (iter.hasMoreElements()) {
      x = (SparseReal) iter.nextElement();
      c[x.getIndex()] = x.value;
    }
    return c;
  }
  /**
   * Sets the dimension and the values
   * @param n The dimension
   * @param val The vector with the positions
   */
  public void init(int n, Vector val) {
    this.setDimension(n);
    this.setValues(val);
  }
/**
 * Creates a SparseRealVector with a default value equal to 0.0
 * @param n The dimension of the vector val
 * @param val The vector with the positions
 */
  public SparseRealVector(int n, Vector val) {
    super(n, val, new SparseReal(-1, 0.0));
  }
/**
 * Get the value of a position
 * @param index The index of the positions
 * @return The value of the position index
 */
  public double get(int index) {
    return ((SparseReal) super.get(new SparseValue(index))).value;
  }
/**
 * Adds a new SparseReal () 
 * @param index The position with a different value
 * @param x Value stored in the position
 */
  public void set(int index, double x) {
    super.set(new SparseReal(index, x));
  }

  /**
   * Substracts to each component of the point the given value.
   *  xi = xi - x for all i=1..n
   * @param x The substraction factor
   */
  public void substract(double x) {
    SparseReal y;
    Enumeration iter = elements();
    while (iter.hasMoreElements()) {
      y = (SparseReal) iter.nextElement();
      y.value -= x;
    }
    ((SparseReal) this.getDefaultValue()).value -= x;
  }

  /**
   * Adds to each component of the point the given value.
   *  xi = xi + x for all i=1..n
   * @param x The addition factor
   */
  public void sum(double x) {
    SparseReal y;
    Enumeration iter = elements();
    while (iter.hasMoreElements()) {
      y = (SparseReal) iter.nextElement();
      y.value += x;
    }
    ((SparseReal) this.getDefaultValue()).value += x;
  }

  /**
   * Multiplies each component of the point by the given value.
   *  xi = xi * x for all i=1..n
   * @param x The multiplication factor
   */
  public void multiply(double x) {
    SparseReal y;
    Enumeration iter = elements();
    while (iter.hasMoreElements()) {
      y = (SparseReal) iter.nextElement();
      y.value *= x;
    }
    ((SparseReal) this.getDefaultValue()).value *= x;
  }

  /**
   * Divides each component of the point by the given value.
   *  xi = xi / x for all i=1..n
   * @param x The division factor
   */
  public void divide(double x) {
    SparseReal y;
    Enumeration iter = elements();
    while (iter.hasMoreElements()) {
      y = (SparseReal) iter.nextElement();
      y.value /= x;
    }
    ((SparseReal) this.getDefaultValue()).value /= x;
  }

  /**
   * Raises each component of the point to the power x
   *  xi = xi ^ x for all i=1..n
   * @param x The exponent
   */
  public void pow(double x) {
    if (x == 0.0) {
      this.getValues().clear();
      ((SparseReal) this.getDefaultValue()).value = 1.0;
    } else {
      SparseReal y;
      Enumeration iter = elements();
      while (iter.hasMoreElements()) {
        y = (SparseReal) iter.nextElement();
        y.value = Math.pow(y.value, x);
      }
      ((SparseReal) this.getDefaultValue()).value = Math.pow(((SparseReal) this.getDefaultValue()).value, x);
    }
  }
/**
 * Return the next element of the SparseRealVector.
 * @param iter The enumerator
 * @return The next element of the SparseRealVector
 */
  private SparseReal next(Enumeration iter) {
    if (iter.hasMoreElements()) { return (SparseReal) iter.nextElement(); }
    return null;
  }


  /**
   * Adds to the point the given point. The addition process is component by component
   *  xi = xi + yi for all i=1..n
   * @param y The point to be added
   */
  public void sum(SparseRealVector y) {
    Vector v = new Vector();

    Enumeration iter1 = elements();
    Enumeration iter2 = y.elements();
    SparseReal a = next(iter1);
    SparseReal b = next(iter2);

    while (a != null && b != null) {
      if (this.getOrder().equalTo(a, b)) {
        a.value += b.value;
        v.add(a);
        a = next(iter1);
        b = next(iter2);
      } else {
        if (this.getOrder().lessThan(a, b)) {
          v.add(a);
          a = next(iter1);
        } else {
          v.add(Cloner.clone(b));
          b = next(iter2);
        }
      }
    }

    while (a != null) {
      v.add(a);
      a = next(iter1);
    }

    while (b != null) {
      v.add(Cloner.clone(b));
      b = next(iter2);
    }

    this.setValues(v);
    ((SparseReal) this.getDefaultValue()).value += ((SparseReal) y.getDefaultValue()).value;
  }

  /**
  * Substracts to the point the given point. The substraction process is component by component
  *  xi = xi - yi for all i=1..n
  * @param y The point to be substracted
  */
  public void substract(SparseRealVector y) {
    Vector v = new Vector();

    Enumeration iter1 = elements();
    Enumeration iter2 = y.elements();
    SparseReal a = next(iter1);
    SparseReal b = next(iter2);

    while (a != null && b != null) {
      if (this.getOrder().equalTo(a, b)) {
        a.value -= b.value;
        v.add(a);
        a = next(iter1);
        b = next(iter2);
      } else {
        if (this.getOrder().lessThan(a, b)) {
          v.add(a);
          a = next(iter1);
        } else {
          b = (SparseReal) Cloner.clone(b);
          b.value = -b.value;
          v.add(b);
          b = next(iter2);
        }
      }
    }

    while (a != null) {
      v.add(a);
      a = next(iter1);
    }

    while (b != null) {
      b = (SparseReal) Cloner.clone(b);
      b.value = -b.value;
      v.add(b);
      b = next(iter2);
    }

    this.setValues(v);
    ((SparseReal) this.getDefaultValue()).value -= ((SparseReal) y.getDefaultValue()).value;
  }

 /**
   * Multiplies the i-th component of the point by the i-th component of the given point
   *  xi = xi * yi for all i=1..min(dim(x), dim(y))
   * @param y The multiplication vector
   */
  public void directProduct(SparseRealVector y) {
    Vector v = new Vector();
    SparseReal xd = (SparseReal) this.getDefaultValue();
    SparseReal yd = (SparseReal) y.getDefaultValue();


    Enumeration iter1 = elements();
    Enumeration iter2 = y.elements();
    SparseReal a = next(iter1);
    SparseReal b = next(iter2);

    while (a != null && b != null) {
      if (this.getOrder().equalTo(a, b)) {
        a.value = a.value * (b.value + yd.value) + xd.value * b.value;
        v.add(a);
        a = next(iter1);
        b = next(iter2);
      } else {
        if (this.getOrder().lessThan(a, b)) {
          a.value *= yd.value;
          v.add(a);
          a = next(iter1);
        } else {
          b = (SparseReal) Cloner.clone(b);
          b.value *= xd.value;
          v.add(b);
          b = next(iter2);
        }
      }
    }

    while (a != null) {
      a.value *= yd.value;
      v.add(a);
      a = next(iter1);
    }

    while (b != null) {
      b = (SparseReal) Cloner.clone(b);
      b.value *= xd.value;
      v.add(b);
      b = next(iter2);
    }

    this.setValues(v);
    ((SparseReal) this.getDefaultValue()).value *= yd.value;
  }
/**
 * Converts the SparseReal structure to a String 
 * @return A string with the SparseReal structure
 */
  public String toString() {
    StringBuffer sb = new StringBuffer();
    Enumeration iter = elements();
    while (iter.hasMoreElements()) {
      sb.append(iter.nextElement().toString());
      sb.append(" ");
    }
    return sb.toString();
  }
}
