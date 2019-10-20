package LinkedList.CoarseSynchronisedLinkedList;
import Application.IConfig;
import LinkedList.ILinkedList;

/**
 * Coarse Synchronized queue
 * @param <T> type of data to hold
 */
public class CoarseSynchronizedLinkedList<T> implements IConfig, ILinkedList<T> {
    private CoarseNode<T> head;
    private int finishedProducers;
    private int finishedConsumers;


    /**
     * Default constructor
     */
    public CoarseSynchronizedLinkedList() {
        head = new CoarseNode<T>();
        finishedConsumers = 0;
        finishedProducers = 0;
    }


    /**
     * Append a new element at the end of the queue
     * @param data data to be inserted in the new element
     */
    public synchronized void push(T data) {

        CoarseNode<T> tempCoarseNode = new CoarseNode<>(data);
        CoarseNode<T> currentCoarseNode = head;

        while (currentCoarseNode.getNext() != null) {
            currentCoarseNode = currentCoarseNode.getNext();
        }
        currentCoarseNode.setNext(tempCoarseNode);
        notify();
    }

    /**
     * Removes the element at the end of the queue
     * @return the data contained in the removed node
     */
    public synchronized T pop() {
        CoarseNode<T> currentCoarseNode = head;
        while(currentCoarseNode.getNext() == null){
            if(!checkNotFinished()){
                return null;
            }
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        while (currentCoarseNode.getNext() != null ){
            if(currentCoarseNode.getNext().getNext() == null){
                break;
            }else{
                currentCoarseNode = currentCoarseNode.getNext();
            }
        }
        T removedNodeData = currentCoarseNode.getNext().getData();
        currentCoarseNode.setNext(null);

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
        notifyAll();
        finishedConsumers++;
    }

    /**
     * Increments the number of finished Produce threads
     */
    public synchronized void incrementFinishedProducers(){
        notifyAll();
        finishedProducers++;
    }

    /**
     * Overloaded method that prints the content of the queue as well as its size
     * @return the content of the queue as a string
     */
    public String toString() {
        String output = "";
        int numberOfNodes = 0;

        CoarseNode<T> currentNode = head.getNext();
        while (currentNode != null) {
            numberOfNodes++;
            output += "[" + currentNode.getData().toString() + "]";
            currentNode = currentNode.getNext();
        }
        System.out.println("The size of the queue is " + numberOfNodes);
        return output;
    }


}
