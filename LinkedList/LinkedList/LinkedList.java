package LinkedList;

public class LinkedList<T> {

    private int counter;
    private Node<T> head;

    // Default constructor
    public LinkedList() {
        head = new Node<T>();
        counter = 0;
    }



    private int getCounter() {
        return counter;
    }

    private void incrementCounter() {
        counter++;
    }

    private void decrementCounter() {
        counter--;
    }

    // returns the number of elements in this list.
    public int size() {
        return getCounter();
    }

    // appends the specified element to the end of this list.
    public void add(T data) {

        Node<T> tempNode = new Node<>(data);
        Node<T> currentNode = head;

        // starting at the head node, crawl to the end of the list and then add element after last node
        while (currentNode.getNext() != null) {
            currentNode = currentNode.getNext();
        }

        // the last node's "next" reference set to our new node
        currentNode.setNext(tempNode);

        // increment the number of elements variable
        incrementCounter();
    }

    // inserts the specified element at the specified position in this list
    public void add(T data, int index) {
        Node<T> tempNode = new Node<>(data);
        Node<T> currentNode = head;

        // crawl to the requested index or the last element in the list, whichever comes first
        for (int i = 0; i < index && currentNode.getNext() != null; i++) {
            currentNode = currentNode.getNext();
        }

        // set the new node's next-node reference to this node's next-node reference
        tempNode.setNext(currentNode.getNext());

        // now set this node's next-node reference to the new node
        currentNode.setNext(tempNode);

        // increment the number of elements variable
        incrementCounter();
    }

    public T get(int index)
    // returns the element at the specified position in this list.
    {
        // index must be 1 or higher
        if (index < 0) {
            return null;
        }

        Node<T> currentNode;
        currentNode = head.getNext();
        for (int i = 0; i < index; i++) {
            if (currentNode.getNext() == null) {
                return null;
            }
            currentNode = currentNode.getNext();
        }
        return currentNode.getData();

    }

    // removes the element at the specified position in this list.
    public boolean remove(int index) {

        // if the index is out of range, exit
        if (index < 0 || index > size())
            return false;

        Node<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            if (currentNode.getNext() == null) {
                return false;
            }
            currentNode = currentNode.getNext();
        }
        currentNode.setNext(currentNode.getNext().getNext());

        // decrement the number of elements variable
        decrementCounter();
        return true;

    }



    //overloaded method in order to print the entire list instead of the reference to objects
    public String toString() {
        String output = "";

        Node currentNode = head.getNext();
        while (currentNode != null) {
            output += "[" + currentNode.getData().toString() + "]";
            currentNode = currentNode.getNext();
        }

        return output;
    }
}
