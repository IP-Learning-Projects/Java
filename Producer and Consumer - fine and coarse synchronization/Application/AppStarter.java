package Application;

import LinkedList.CoarseSynchronisedLinkedList.CoarseSynchronizedLinkedList;
import LinkedList.FineSynchronisedLinkedList.FineSynchronizedLinkedList;
import ProducerConsumer.Item.Item;
import ProducerConsumer.Launcher;

/**
 * Class that is used to start the Producer Consumer Problem for both variations, with coarse synchronization and with fine synchronization
 */
class AppStarter implements IConfig {

    private Launcher coarseLauncher;
    private Launcher fineLauncher;

    /**
     * Constructor for the class
     */
    AppStarter(){
        coarseLauncher = new Launcher(new CoarseSynchronizedLinkedList<Item>(),"Coarse");
        fineLauncher = new Launcher(new FineSynchronizedLinkedList<Item>(),"Fine");
    }

    /**
     * Prints the execution time of both variants
     */
    private void printExecutionTime(){
        coarseLauncher.printExecutionTime();
        fineLauncher.printExecutionTime();
    }

    /**
     * Prints the characteristics, like number of producers and number of consumers
     */
    private void printExecutionCharacteristics(){
        System.out.println("Number of Producers: " + numberOfProducers);
        System.out.println("Number of Consumers: " + numberOfConsumers);
        System.out.println("Number of Iterations per Thread: " + threadRunCount);

    }

    /**
     * Starts the execution of the 2 variants of the problem and prints their execution time
     */
    void startProgram(){
        coarseLauncher.startProgram();
        fineLauncher.startProgram();
        printExecutionCharacteristics();
        printExecutionTime();
    }





}
