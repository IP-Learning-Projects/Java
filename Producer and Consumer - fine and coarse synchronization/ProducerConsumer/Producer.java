package ProducerConsumer;

import Application.IConfig;
import LinkedList.FineSynchronisedLinkedList.FineSynchronizedLinkedList;
import LinkedList.ILinkedList;
import ProducerConsumer.Item.Item;

/**
 * Creates Producer threads that pushes items out of the queue
 */
public class Producer implements Runnable, IConfig {

    private int id;
    private int runCount;
    private ILinkedList<Item> queue;
    /**
     * Default constructor
     * @param id the id of the consumer
     * @param queue the queue used by the consumer
     */
    Producer(int id, ILinkedList<Item> queue){
        this.id = id;
        this.runCount = 0;
        this.queue = queue;
    }

    /**
     * Run method of the Thread.
     * As long as the Consumers are working or the number of loops is lower than the defined one
     * The thread will continue producing items
     */
    public void run(){
        while(runCount < threadRunCount && queue.checkNotFinished()){
            Item item = new Item("F" + id + runCount);
            queue.push(item);
            System.out.println("The Item with the id " + item + " has been produced by producer " + id) ;
            runCount++;
        }
        queue.incrementFinishedProducers();
        System.out.println("Producer " + Thread.currentThread().getName() + " has finished execution");
    }
}
