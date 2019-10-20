package LinkedList.CoarseSynchronisedLinkedList;
/**
 * Node used for the Coarse synchronization variant of the problem
 */
public class CoarseNode<T> {
    private CoarseNode<T> next; // reference to the next CoarseNode in the chain, or null if there isn't one.
    private T data; // data carried by this CoarseNode.

    /**
     *     CoarseNode constructor for the head node
     */
    CoarseNode() {
        next = null;
        this.data = null;
    }

    /**
     * CoarseNode constructor
     * @param data the data stored in the node
     */
    CoarseNode(T data) {
        next = null;
        this.data = data;
    }

    /**
     * Returns the data stored in the node
     * @return the data stored in the node
     */
    T getData() {
        return data;
    }

    /**
     * Returns the node the current one points to
     * @return next node
     */
    CoarseNode<T> getNext() {
        return next;
    }

    /**
     * Sets the next node
     * @param nextCoarseNode the next node
     */
    void setNext(CoarseNode<T> nextCoarseNode) {
        next = nextCoarseNode;
    }

    /**
     * Sets the data stored in the node
     * @param data the data stored in the node
     */
    public void setData(T data) {
        this.data = data;
    }

}
