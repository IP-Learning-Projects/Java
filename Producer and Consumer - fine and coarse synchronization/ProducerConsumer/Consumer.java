package ProducerConsumer;

import Application.IConfig;
import LinkedList.CoarseSynchronisedLinkedList.CoarseSynchronizedLinkedList;
import LinkedList.ILinkedList;
import ProducerConsumer.Item.Item;

/**
 * Creates Consumer threads that pops items out of the queue
 */
public class Consumer implements Runnable, IConfig {

    private int id;
    private int runCount;
    private ILinkedList<Item> queue;

    /**
     * Default constructor
     * @param id the id of the consumer
     * @param queue the queue used by the consumer
     */
    Consumer(int id, ILinkedList<Item> queue){
        this.id = id;
        this.runCount = 0;
        this.queue = queue;
    }

    /**
     * Run method of the Thread.
     * As long as the Producers are working or the number of loops is lower than the defined one
     * The thread will continue consuming items
     */
    public void run(){
        while(runCount < threadRunCount && queue.checkNotFinished()){
            Item item = queue.pop();
            if(item != null){
                System.out.println("The Item with the id " + item + " has been consumed by consumer " + id) ;
                runCount++;
            }
        }
        queue.incrementFinishedConsumers();
        System.out.println("Consumer " + Thread.currentThread().getName() + " has finished execution");
    }

}
