package treeexpressioncalculator;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author charlie
 */

/**
   A class that evaluates an expression by arranging operands and values into a 
   binary tree, performing a postorder traversal on the tree to put the elements
   into a queue in Reverse Polish Notation form, and then calculating the value 
   of the entire expression by using a stack.
 */
public class TreeEvaluator implements Visitor {
    private ExpressionTokenizer tokenizer;
    private Queue queue;

   /**
      Constructs an evaluator.
      @param anExpression a string containing the expression
      to be evaluated
   */
   public TreeEvaluator(String anExpression) {
      tokenizer = new ExpressionTokenizer(anExpression);
      queue = new LinkedList();
   }
   
   /**
      Evaluates the expression.
      @return the value of the expression.
   */
   public int getExpressionValue() {
       Stack stack = new Stack();
       //make binary tree to represent the current expression
       //then perform a postorder traversal to put data into a queue in RPN
       getExpressionTree().postorder(this);
       //iterate through queue and use a stack to evaluate expression
       while (!queue.isEmpty()) {
           Object next = queue.peek();
           if (next instanceof Integer) {
               //push element to queue if it's an integer
               stack.push(queue.remove());
           } else if (next instanceof String) {
               //pop two numbers off stack, perform operation, then push result
                if (next.equals("+")) {
                    queue.remove();
                    stack.push((int)stack.pop() + (int)stack.pop());
                }
                if (next.equals("-")) {
                    queue.remove();
                    int x1 = (int)stack.pop();
                    int x2 = (int)stack.pop();
                    stack.push(x2 - x1);
                }
                if (next.equals("*")) {
                    queue.remove();
                    stack.push((int)stack.pop() * (int)stack.pop());
                }
                if (next.equals("/")) {
                    queue.remove();
                    int x1 = (int)stack.pop();
                    int x2 = (int)stack.pop();
                    stack.push(x2 / x1);
                }
           }
       }
       return (int)stack.pop();     //pop the answer off the stack
   }
   
   /**
    * Makes a BinaryTree for an expression
    * @return a BinaryTree
    */
   private BinaryTree getExpressionTree() {
       BinaryTree tree = getTermTree();    //a term is part of an expression
       boolean done = false;
       while (!done) {
            String next = tokenizer.peekToken();
            if ("+".equals(next) || "-".equals(next)) {
                tree = new BinaryTree(tokenizer.nextToken(), tree, getTermTree());
            } else {
                done = true;
            }
       }
       return tree;
   }
   
   /**
    * Makes a BinaryTree for a term within an expression
    * @return a BinaryTree
    */
   private BinaryTree getTermTree() {
       BinaryTree tree = getFactorTree();   //a factor is part of a term
       boolean done = false;
       while (!done) {
            String next = tokenizer.peekToken();
            if ("*".equals(next) || "/".equals(next)) {
                tree = new BinaryTree(tokenizer.nextToken(), tree, getFactorTree());
            } else {
                done = true;
            }
       }
       return tree;
   }
   /**
    * Makes a BinaryTree for a factor within a term
    * @return a BinaryTree
    */
   private BinaryTree getFactorTree() {
       BinaryTree tree;
       String next = tokenizer.peekToken();
       //a "(" denotes that we have found an expression in this factor
       if ("(".equals(next)) {
           tokenizer.nextToken();  //skip past (
           tree = getExpressionTree();  
           tokenizer.nextToken();  //skip past )
       } else {
           //we must be looking at an integer, so add it as a leaf
           tree = new BinaryTree(Integer.parseInt(tokenizer.nextToken()));
       }
       return tree;
   }
   
   /**
    * Adds each piece of data in the expression tree to a queue.
    * @param data the data to visit
    */
    @Override
   public void visit(Object data) {
       queue.add(data);
   }
}
