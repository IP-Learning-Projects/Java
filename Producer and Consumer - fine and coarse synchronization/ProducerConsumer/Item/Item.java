package ProducerConsumer.Item;

/**
 * Item class used to create objects for the queues
 */
public class Item {
    private String id;

    public Item(String  id){
        this.id = id;
    }

    /**
     * Overloaded method used to print the content of the object instead of the reference to it
     * @return the id of the item
     */
    public String toString() {
        return id;
    }
}
