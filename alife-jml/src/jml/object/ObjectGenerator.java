package jml.object;
import jml.parser.Tokenizer;

/**
 * <p>Title: ObjectGenerator </p>
 * <p>Description: Abstract class, for object generators </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.2
 */

public abstract class ObjectGenerator {
  /**
   * Creates a object with a tokenizer
   * @param tokenizer A strign tokenizer
   * @return A object
   */	
  public abstract Object get(Tokenizer tokenizer);
}
