

/**
 * A class that implements a queue using a linked list
 */
public class Queue<AnyType> {
    
    private int size;
    private Node<AnyType> first;
    private Node<AnyType> last;
    
    /**
     * A constructor with no given value, setting the first and last to null and the size to 0
     */
    public Queue() {
        first = null;
        last = null;
        size = 0;
    }
    
    
    /**
     * A Method to insert an element into the queue
     * Inserts the element in the back of the linked list
     */
    public void insert(AnyType element) {
        if (size == 0) {
            last = new Node<AnyType>(element);
            first = last;
            size++;
        }
        else {
            Node<AnyType> newNode = new Node<AnyType>(element);
            last.next = newNode;
            last = newNode;
            size++;
        }
    }
    
    
    /**
     * A Method to remove an element from the queue
     * Removes the first element in the linked list
     */
    public AnyType remove() {
        if (size == 0)
            return null;
        else {
            Node<AnyType> oldFirst = first;
            first = first.next;
            size--;
            return oldFirst.element;
        }
    }
    
    
    /**
     * A Method to check the first element in the queue without removing it
     */
    public AnyType peek() {
        if (first == null)
            return null;
        else    
            return first.element;
    }
    
    /**
     * A private method to get an element in the linked list, only to be used for unit testing
     */
    private AnyType getElement(int index) {
        Node<AnyType> currentNode = first;
        for (int i=0; i<index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.element;
    }
    
    /**
     * A Default-Visibility Method to convert the queue into an array, only to be used to unit testing
     */
    Object[] toArray() {
        Object[] array = new Integer[size];
        for (int i=0; i<size; i++) {
            array[i] = getElement(i);
        }
        return array;
    }
    
    /**
     * A Method to check if the queue is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * A Method to clear the queue
     */
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }
    
    
    public int getSize() {
        return size;
    }
    
    
    
    private static class Node<AnyType> {
        
        //Instance Variable
        AnyType element;
        Node<AnyType> next;
        
        /**
         * A constructor for a node given an element of type AnyType
         */
        public Node(AnyType e) {   
            element = e;
            next = null;
        }
        
        /**
         * A constructor for a node given an element of type AnyType and a next Node
         */
        public Node(AnyType e, Node<AnyType> n) {
            element = e;
            next = n;
        }
    }
    
    
}
