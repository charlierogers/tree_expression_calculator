package treeexpressioncalculator;

/**
 *
 * @author charlie
 */

/**
   A class that breaks up a string describing an expression
   into tokens: numbers, parentheses, and operators.
*/
public class ExpressionTokenizer {
   private String input;
   private int start; // The start of the current token
   private int end; // The position after the end of the current token

   /**
      Constructs a tokenizer.
      @param anInput the string to tokenize
   */
   public ExpressionTokenizer(String anInput) {
      input = anInput;
      start = 0;
      end = 0;
      nextToken(); // Find the first token
   }

   /**
      Peeks at the next token without consuming it.
      @return the next token or null if there are no more tokens
   */
   public String peekToken() {
      //don't go past length of input string
      if (start >= input.length()) { return null; }
      else { return input.substring(start, end); }
   }

   /**
      Gets the next token and moves the tokenizer to the following token.
      @return the next token or null if there are no more tokens
   */
   public String nextToken() {
      String r = peekToken();   //gets the next token
      start = end;              //move start index to following token
      //don't go past length of input string
      if (start >= input.length()) { return r; }    
      if (Character.isDigit(input.charAt(start))) {
         end = start + 1;       //move end index to following token
         //keep moving end index forward as long as there is a numeric character
         //to allow tokenizer to handle multi-digit numbers
         while (end < input.length() 
               && Character.isDigit(input.charAt(end))) {
            end++;
         }
      } else {
         end = start + 1;
      }
      return r;      
   }
}
