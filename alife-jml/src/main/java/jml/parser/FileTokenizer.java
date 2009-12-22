package jml.parser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
/**
 * Title: FileTokenizer
 * Description: To read input file line by line.
 * Copyright:    Copyright (c) 2004
 * Company: Universidad Nacional de Colombia
 * @author Jonatan Gomez
 * @version 1.0
 */
public class FileTokenizer extends Tokenizer {
/**
 * file name
 */
  protected String fileName;
  /**
   * To read all file
   */
  protected BufferedReader fs = null;

  /**
   * Public constructor, Set file name, 
   * and takes first line of that file (reset) 
   * @param fileName file name 
   */
  public FileTokenizer(String fileName) {
    this.fileName = fileName;
    //reset();
  }
/**
 * Close BufferedReader if file's open, read the file,  
 * create new BufferedReader, assigns file (filename), 
 * and get the first line of it
 */
  protected void reset() {
    try {
      if (fs != null) { fs.close(); }
      FileReader reader = new FileReader(fileName);
      fs = new BufferedReader(reader);
      String line = getLine();
      if (line != null) {
        is = new StringReader(line);
      }
    } catch (Exception e) { e.printStackTrace(); }
  }
/**
 * Read a line of the file, depending of BufferedReader
 * @return A line of the file
 */
  public String getLine() {
    String line = null;
    try {
      line = fs.readLine();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return line;
  }

  /**
   * Reads the available token in the input stream. If there are not more tokens
   * returns ttEOF indicating that the end of the input stream has been reached.
   * If the token is a number then the value of the number is stored in the nval attribute.
   * If the token is not a number the token is stored in the sval attribute.
   * @return Type of the token read. If the parse process fails then TT_ERROR is returned.
   */
  public Token nextToken() {
    Token t = new Token(Token.ttEOF);
    if (is != null) {
      t = super.nextToken();
      if (t.type == Token.ttEOF) {
        String line = getLine();
        if (line != null) {
          is = new StringReader(line);
          t = super.nextToken();
        } else { is = null; }
      }
    }
    return t;
  }

  /**
   * Closes the input stream
   */
  public void close() {
    try {
      if (fs != null) {
        fs.close();
        fs = null;
      }
    } catch (Exception e) {
      System.err.println("[FileTokenizer]" + e.getMessage());
    }
    super.close();
  }

}
