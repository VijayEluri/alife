package jml.object.transformation;

import jml.object.ObjectGenerator;
import jml.parser.Tokenizer;
/**
 * <p>Title: TransformedObjectGenerator</p>
 * <p>Description: .</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class TransformedObjectGenerator extends ObjectGenerator {
  /**
	* Transformation applied to the ObjectSource
	*/
  protected DataTransformation transformation;
  /**
   * ObjectGenerator
   */
  protected ObjectGenerator original;
  /**
   * Constructor: Creates a Transformed Object Generator with an ObjectGenerator and a DataTransformation
   * @param original The object generator 
   * @param transformation Transformation applied to the ObjectSource
   */
  public TransformedObjectGenerator(ObjectGenerator original,
                                     DataTransformation transformation) {
    this.original = original;
    this.transformation = transformation;
  }
  /**
   * Creates a new transformed object 
   * @param tokenizer A simple string tokenizer
   */
  public Object get(Tokenizer tokenizer) {
    return transformation.apply(original.get(tokenizer));
  }
}
