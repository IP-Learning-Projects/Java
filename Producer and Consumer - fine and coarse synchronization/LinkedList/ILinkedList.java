package LinkedList;

/**
 * Interface used as a wrapper for the queues
 * @param <T> type of data to hold in the queue
 */
public interface ILinkedList<T> {
    void push(T data);
    T pop();
    boolean checkNotFinished();
    void incrementFinishedConsumers();
    void incrementFinishedProducers();
    String toString();
}
