package test.parser;

import java.io.FileReader;

import jml.parser.NumberDFA;
import jml.parser.TokenDFA;
import jml.parser.Tokenizer;
import jml.parser.WhiteSpaceDFA;

public class TokenizerTest {
  public static void main(String[] argv) {
    try{
      TokenDFA[] dfa = new TokenDFA[2];
      dfa[0] = new NumberDFA(true);
      dfa[1] = new WhiteSpaceDFA();
      Tokenizer tok =  new Tokenizer( dfa, new FileReader("C:/a.txt"));
      System.out.println(tok.getTokens());
    } catch (Exception e){ e.printStackTrace(); }
  }
}
