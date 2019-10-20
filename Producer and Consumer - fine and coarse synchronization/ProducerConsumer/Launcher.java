package ProducerConsumer;

import Application.IConfig;
import LinkedList.ILinkedList;
import ProducerConsumer.Item.Item;

/**
 * Executes the Producer-Consumer problem for any type of queue
 */
public class Launcher implements IConfig {

    private ILinkedList<Item> queue;
    private Thread ProducersThread[] = new Thread[numberOfProducers];
    private Thread ConsumersThread[] = new Thread[numberOfConsumers];
    private long executionTime;
    private String type;


    /**
     * Default constructor
     */
    public Launcher(ILinkedList<Item> queue, String type){
        this.queue = queue;
        executionTime = 0;
        this.type = type;
    }

    /**
     * Initializes and starts the producers
     */
    private void initializeProducers(){
        for(int i = 0; i < numberOfProducers ; i++){
            ProducersThread[i] = new Thread(new Producer(i,queue));
        }
        for(int i = 0; i < numberOfProducers ; i++){
            ProducersThread[i].start();
        }
    }

    /**
     * Initializes and starts the consumers
     */
    private void initializeConsumers(){
        for(int i = 0; i < numberOfConsumers ; i++){
            ConsumersThread[i] = new Thread(new Consumer(i,queue));
        }
        for(int i = 0; i < numberOfConsumers ; i++){
            ConsumersThread[i].start();
        }
    }

    /**
     * Joins the producers
     */
    private void joinProducers(){
        for(int i = 0; i < numberOfProducers ; i++){
            try {
                ProducersThread[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Joins the consumers
     */
    private void joinConsumers(){
        for(int i = 0; i < numberOfConsumers ; i++){
            try {
                ConsumersThread[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Prints the execution time
     */
    public void printExecutionTime(){
        System.out.println("Time to execute "+ type +"ProducerConsumer Problem: " + executionTime);
    }

    /**
     * Starts the producers and consumers, joins them , measures the execution time
     */
    public void startProgram(){
        executionTime = System.nanoTime();
        initializeProducers();
        initializeConsumers();
        joinProducers();
        joinConsumers();
        executionTime = System.nanoTime() - executionTime;
        System.out.println(queue);
    }



}
