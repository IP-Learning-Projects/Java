package LinkedList.FineSynchronisedLinkedList;

import Application.IConfig;
import LinkedList.ILinkedList;

import java.util.concurrent.Semaphore;

/**
 * Fine Synchronized queue
 * @param <T> type of data to hold
 */
public class FineSynchronizedLinkedList<T> implements IConfig, ILinkedList<T> {


    private FineNode<T> head;
    private int finishedProducers;
    private int finishedConsumers;
    private Semaphore notEmpty = new Semaphore(0);


    /**
     * Default constructor
     */
    public FineSynchronizedLinkedList() {
        head = new FineNode<T>();
        finishedConsumers = 0;
        finishedProducers = 0;
    }


    /**
     * Searches for a spot as close to the end of the queue as possible.
     * If it encounters a node that is locked then the search stops and it will add the new node between the current and the Next node.
     * If the end of the queue is encountered then the new node will be put at the end of the queue.
     * @param data data to add
     */
    public void push(T data) {

        FineNode<T> currentFineNode = head;
        currentFineNode.lock();

        FineNode<T> tempFineNode = new FineNode<>(data);
        tempFineNode.lock();

        FineNode<T> nextNode = currentFineNode.getNext();

        if(nextNode == null){
            currentFineNode.setNext(tempFineNode);
            notEmpty.release();
            currentFineNode.unlock();
            tempFineNode.unlock();
            return;
        }

        nextNode.lock();
        while (nextNode.getNext() != null && !nextNode.getNext().isLocked()) {
            nextNode.getNext().lock();
            currentFineNode.unlock();
            currentFineNode = nextNode;
            nextNode = currentFineNode.getNext();

        }

        if(nextNode.getNext() == null){
            nextNode.setNext(tempFineNode);
        }else{
            tempFineNode.setNext(nextNode);
            currentFineNode.setNext(tempFineNode);
        }
        notEmpty.release();
        currentFineNode.unlock();
        nextNode.unlock();
        tempFineNode.unlock();
    }

    /**
     * Searches for a spot as close to the end of the queue as possible.
     * If it encounters a node that is locked then the search stops and it will remove the node before it.
     * If the end of the queue is encountered then it will remove the last node from the queue.
     * @return the data contained in the removed node
     */
    public T pop(){

        while(!notEmpty.tryAcquire()){
            if(!checkNotFinished() && notEmpty.availablePermits() == 0){
                return null;
            }
        }

        FineNode<T> currentFineNode = head;
        currentFineNode.lock();
        FineNode<T> nextNode = currentFineNode.getNext();
        nextNode.lock();

        while (nextNode.getNext() != null && !nextNode.getNext().isLocked()) {
            nextNode.getNext().lock();
            currentFineNode.unlock();
            currentFineNode = nextNode;
            nextNode = currentFineNode.getNext();
        }

        T removedNodeData;
        if(nextNode.getNext() == null){
            removedNodeData = nextNode.getData();
            currentFineNode.setNext(null);
        }else{
            removedNodeData = nextNode.getData();
            FineNode<T> tempNode = nextNode.getNext();
            nextNode.setNext(null);
            currentFineNode.setNext(tempNode);
        }

        currentFineNode.unlock();
        nextNode.unlock();
        return removedNodeData;

    }


    /**
     * Checks if the producers or if the consumers finished their task
     * If it is true then all the threads finishes their current task then stops
     * @return true or false
     */
    public synchronized boolean checkNotFinished(){
        if(finishedConsumers == numberOfConsumers){
            return false;
        }

        if(finishedProducers == numberOfProducers){
            return false;
        }

        return true;
    }

    /**
     * Increments the number of finished Consumer threads
     */
    public synchronized void incrementFinishedConsumers(){
        finishedConsumers++;
    }

    /**
     * Increments the number of finished Produce threads
     */
    public synchronized void incrementFinishedProducers(){
        finishedProducers++;

    }

    /**
     * Overloaded method that prints the content of the queue as well as its size
     * @return the content of the queue as a string
     */
    public String toString() {
        String output = "";
        int numberOfNodes = 0;

        FineNode<T> currentNode = head.getNext();
        while (currentNode != null) {
            numberOfNodes++;
            output += "[" + currentNode.getData().toString() + "]";
            currentNode = currentNode.getNext();
        }
        System.out.println("The size of the queue is " + numberOfNodes);
        return output;
    }

}
