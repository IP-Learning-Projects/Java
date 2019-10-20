package LinkedList.FineSynchronisedLinkedList;

import java.util.concurrent.locks.ReentrantLock;
/**
 * Node used for the Fine synchronization variant of the problem
 */
public class FineNode<T> {
    private FineNode<T> next; // reference to the next FineNode in the chain, or null if there isn't one.
    private T data;  // data carried by this FineNode.
    private ReentrantLock lock = new ReentrantLock(true); // lock used to lock the node when in use

    /**
     *     FineNode constructor for the head node
     */
    FineNode() {
        next = null;
        this.data = null;
    }


    /**
     * FineNode constructor
     * @param data the data stored in the node
     */
    FineNode(T data) {
        next = null;
        this.data = data;

    }

    /**
     * Locks the node
     */
    void lock(){
        lock.lock();
    }
    /**
     * Unlocks the node
     */
    void unlock(){
        lock.unlock();
    }

    /**
     * Checks if the node is locked
     * If it is locked it checks if the owner of the lock is the current thread
     * @return true/false if the current thread can use the node or not
     */
    boolean isLocked(){
        if(lock.isLocked()){
            return !lock.isHeldByCurrentThread();
        }
        return false;
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
    FineNode<T> getNext() {
        return next;
    }

    /**
     * Sets the next node
     * @param nextFineNode the next node
     */

    void setNext(FineNode<T> nextFineNode) {
        next = nextFineNode;
    }

    /**
     * Sets the data stored in the node
     * @param data the data stored in the node
     */
    public void setData(T data) {
        this.data = data;
    }

}
