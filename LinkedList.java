import java.util.Iterator;
import java.util.ArrayList;

/**
 * Aidan Kwok
 * Used to create a Stack backed by a Linked List
 */
public class LinkedList<T> implements Stack<T>, Iterable<T>, Queue<T> {
    /*
     * Iterator subclass that goes through the list
     */
    private class LLIterator implements Iterator<T> {
        private Node<T> next;

        /*
         * Constructor
         */
        public LLIterator(Node<T> head) {
            next = head;

        }

        /*
         * Checks if current node has a node after it
         */
        public boolean hasNext() {
            if (next != null) {
                return true;
            } else {
                return false;
            }

        }

        /*
         * Returns data of current node and sets node to node after it)
         */
        public T next() {
            T temp = next.getData();
            next = next.getNext();
            return temp;

        }
    }

    /*
     * Calls constructor
     */
    public Iterator<T> iterator() {
        return new LLIterator(this.head);
    }

    /*
     * Translates a Linked List to an ArrayList
     */
    public ArrayList<T> toArrayList() {
        ArrayList<T> arrayList = new ArrayList<T>();
        Node<T> walker = head;
        for (int x = 0; x < size(); x++) {
            arrayList.add(walker.getData());
            walker = walker.getNext();
        }
        return arrayList;
    }

    private static class Node<T> {
        /*
         * Declares prev, next, and data variable
         */
        Node<T> prev;
        Node<T> next;
        T data;

        /*
         * Constructor used if only item is passed in as argument
         */
        public Node(T item) {
            data = item;
            next = null;
            prev = null;
        }

        /*
         * Constructor used if item and the next Node is passed in as argument
         */
        public Node(T item, Node<T> next, Node<T> prev) {
            data = item;
            this.next = next;
            this.prev = prev;
        }

        /*
         * Gets data of Node
         */
        public T getData() {
            return data;
        }

        /*
         * Links nodes together
         */
        public void setNext(Node<T> newNext) {
            next = newNext;
        }

        /*
         * Gets the node that comes after exisitng node in linked list
         */
        public Node<T> getNext() {
            return next;
        }

        /*
         * Sets a node before current node
         */
        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }
    }

    /*
     * Size of linked list
     * 
     */
    private int size;
    /*
     * First Nodelinked list
     * 
     */
    private Node<T> head;
    private Node<T> tail;

    /*
     * Constructor of Linked List
     */
    public LinkedList() {
        size = 0;
        head = null;
        tail = null;
    }

    /*
     * Adds Node to head of list
     */
    public void add(T item) {
        // Node<T> newNode = new Node<T>(item, head);
        Node<T> newNode = new Node<T>(item);
        newNode.setNext(head);
        head = newNode;
        size++;
    }

    /*
     * Adds item at specific index
     */
    public void add(int index, T item) {
        if (index == 0) {
            add(item);
        } else {
            Node<T> walker = head;
            for (int x = 0; x < index - 1; x++) {
                walker = walker.getNext();
            }
            // Node<T> newNode = new Node<T>(item, walker);
            Node<T> newNode = new Node<T>(item);
            // walker.getNext is really walker.getNext().getNext()
            newNode.setNext(walker.getNext());
            walker.setNext(newNode);
            size++;
        }
    }

    /*
     * Clears a linked list
     */
    public void clear() {
        head = null;
        size = 0;

    }

    /*
     * Checks if a linked lists contains a certain objects
     */
    public boolean contains(Object o) {
        Node<T> walker = head;
        for (int x = 0; x < size; x++) {
            if (walker.getData() == o) {
                return true;
            }
            walker = walker.getNext();
        }
        return false;
    }

    /*
     * Checks if a passed in object has all the same items in the same order as
     * linked list
     */
    public boolean equals(Object o) {
        if (!(o instanceof LinkedList)) {
            return false;
        }
        LinkedList oAsALinkedList = (LinkedList) o;
        Node<T> walker = head;
        for (int x = 0; x < size; x++) {
            if (walker.getData() != oAsALinkedList.get(x)) {
                return false;
            }
            walker = walker.getNext();
        }
        return true;
    }

    /*
     * Gets data of Node at specific index
     * 
     */
    public T get(int index) {
        Node<T> walker = head;
        // At end of for loop, walker is at specificied index
        for (int x = 0; x < index; x++) {
            walker = walker.getNext();
        }
        return walker.getData();
    }

    /*
     * Sees if linked list is empty
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * Removes node at head of linked list
     */
    public T remove() {
        T info = head.getData();
        head = head.getNext();
        size--;
        return info;
    }

    /*
     * Removes node at specific index
     */
    public T remove(int index) {
        if (index == 0) {
            return remove();
        }
        Node<T> walker = head;
        for (int x = 0; x < index - 1; x++) {
            walker = walker.getNext();
        }
        T info = walker.getNext().getData();
        walker.setNext(walker.getNext().getNext());
        size--;
        return info;

    }

    /*
     * Gets size of linked list
     */
    public int size() {
        return size;
    }

    /*
     * Formats linked list to string
     */
    public String toString() {
        String string = "";
        for (int x = 0; x < size; x++) {
            string += get(x) + ", ";
        }

        return string;
    }

    /**
     * This method adds the given {@code data} to the start of the list.
     * 
     * @param data the data to be added into the list.
     */
    public void addFirst(T data) {
        Node<T> node = new Node<T>(data);
        node.setNext(head);
        if (size == 0) {
            tail = node;
        } else {
            head.setPrev(node);
        }
        head = node;
        size++;

    }

    /**
     * This method adds the given {@code data} to the end of the list.
     * 
     * @param data the data to be added into the list.
     */
    public void addLast(T data) {
        Node<T> node = new Node<T>(data);
        if (size == 0) {
            head = node;
        } else {
            tail.setNext(node);
            node.setPrev(tail);
        }
        tail = node;
        size++;
    }

    /**
     * This method returns and removes the first entry of the list.
     * 
     * @return the last entry of the list.
     */
    public T removeFirst() {
        T temp = head.getData();
        head = head.getNext();
        if (size == 1) {
            tail = null;
        } else {
            head.setPrev(null);
        }
        size--;
        return temp;

    }

    /*
     * Method for queue, adds item to end of queue
     */
    @Override
    public void offer(T item) {
        addLast(item);

    }

    /*
     * Removes item from front of queue
     */
    @Override
    public T poll() {
        return removeFirst();

    }

    @Override
    /*
     * Returns front most item of queue
     */
    public T peek() {
        return get(0);
    }

    /*
     * Adds item to top of stack
     */
    @Override
    public void push(T item) {
        addFirst(item);
    }

    @Override
    public T pop() {
        return removeFirst();

    }

    /*
     * Tests
     */
    public static void main(String[] args) {
        Stack<Integer> stack1 = new LinkedList<Integer>();
        stack1.push(1);
        stack1.push(2);
        System.out.println(stack1.pop());
        System.out.println(stack1.peek());
    }
}
