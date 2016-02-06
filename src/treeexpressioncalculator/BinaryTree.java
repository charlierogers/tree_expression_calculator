package treeexpressioncalculator;

/**
 *
 * @author charlie
 */

/**
   A binary tree in which each node has two children.
*/
public class BinaryTree {
   private Node root;

   /**
      Constructs an empty tree.
   */
   public BinaryTree() { root = null; } 

   /**
      Constructs a tree with one node and no children.
      @param rootData the data for the root
   */
   public BinaryTree(Object rootData) {
      root = new Node();
      root.data = rootData;
      root.left = null;
      root.right = null;
   }

   /**
      Constructs a binary tree.
      @param rootData the data for the root
      @param left the left subtree
      @param right the right subtree
   */
   public BinaryTree(Object rootData, BinaryTree left, BinaryTree right) {
      root = new Node();
      root.data = rootData;
      root.left = left.root;
      root.right = right.root;
   }
   
   /**
      A class that stores an object and points to two other Nodes
   */
   class Node {
      public Object data;
      public Node left;
      public Node right;
   }
   
   /**
      Gets the left subtree of this tree.
      @return the left child of the root
   */
   public BinaryTree left() { 
      BinaryTree result = new BinaryTree();
      result.root = root.left; 
      return result;
   }

   /**
      Gets the right subtree of this tree.
      @return the right child of the root
   */
   public BinaryTree right() { 
      BinaryTree result = new BinaryTree();
      result.root = root.right; 
      return result;
   }
   
   /**
    * Runs a postorder tree traversal on the current tree
    * @param v the Visitor to be used to visit each node in the traversal
    */
   public void postorder(Visitor v) {
       postorder(root, v);  //call helper function with root as argument
   }
   
   /**
    * Runs a postorder tree traversal
    * @param n the node from which to start
    * @param v the Visitor to be used to visit each node in the traversal 
    */
   private void postorder(Node n, Visitor v) {
       if (n == null) {return;}     //have reached a leaf
       postorder(n.left, v);        //do postorder traversal on left subtree
       postorder(n.right, v);       //do postorder traversal on right subtree
       v.visit(n.data);             //visit the data at the current node
   }
}
