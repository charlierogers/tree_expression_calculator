package treeexpressioncalculator;

/**
 *
 * @author charlie
 */

/**
   An interface to facilitate performing a certain action each time an element 
   in a data structure is visited
 */
public interface Visitor {
    /**
     * Supply code for this method to perform an action desired each time an 
     * element is visited in a traversal
     * @param data the data to visit
     */
    public void visit(Object data);
}
