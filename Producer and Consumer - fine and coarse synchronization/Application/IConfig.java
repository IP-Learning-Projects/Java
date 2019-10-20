package Application;

/**
 * Variables used to configure the execution of the program
 */
public interface IConfig {
    int numberOfProducers = 4;
    int numberOfConsumers = Runtime.getRuntime().availableProcessors(); // variable gets the number of available Processors on the local machine
    int threadRunCount = 100; // number of times each thread can loop to produce or consume an item

}
