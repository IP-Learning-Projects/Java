package LinkedList;

public class Node<T> {
    // reference to the next node in the chain, or null if there isn't one.
    private Node next;

    // data carried by this node. could be of any type you need.
    private T data;

    // Node constructor for the head
    Node() {
        next = null;
        this.data = null;
    }

    // Node constructor
    Node(T data) {
        next = null;
        this.data = data;
    }

    // another Node constructor if we want to specify the node to point to.
    @SuppressWarnings("unused")
    Node(T dataValue, Node nextNode) {
        next = nextNode;
        data = dataValue;
    }

    // these methods should be self-explanatory
    T getData() {
        return data;
    }

    @SuppressWarnings("unused")
    public void setData(T data) {
        this.data = data;
    }

    Node getNext() {
        return next;
    }

    void setNext(Node nextNode) {
        next = nextNode;
    }

}

