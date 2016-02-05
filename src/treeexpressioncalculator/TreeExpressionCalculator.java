package treeexpressioncalculator;

import java.util.Scanner;

/**
 *
 * @author charlie
 */

/**
   A program that calculates the value of an expression 
   consisting of numbers, arithmetic operators, and parentheses.
*/
public class TreeExpressionCalculator
{
   public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter an expression: ");
        String input = in.nextLine();
        TreeEvaluator e = new TreeEvaluator(input);
        System.out.println(input + "=" + e.getExpressionValue());
   }
}