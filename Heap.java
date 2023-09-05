import java.util.Comparator;

/*
 * Aidan Kwok
 * Creates heap backed by a priority queue
 */
public class Heap<T> implements PriorityQueue<T> {
    /*
     * Creates node
     */
    private static class Node<T> {
        Node<T> left, right, parent;
        T data;

        public Node(T data, Node<T> left, Node<T> right, Node<T> parent) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    private int size;
    private Node<T> root, last;
    private Comparator<T> comparator;

    /*
     * Constructor with no parameters
     */
    public Heap() {
        this(null, false);

    }

    /*
     * Constructor that determines if heap will be a maxHeap
     */
    public Heap(boolean maxHeap) {
        this(null, maxHeap);

    }

    /*
     * Constructor with comparator
     */
    public Heap(Comparator<T> comparator) {
        this(comparator, false);
    }

    /*
     * Constructor that determines if heap will be a maxHeap and has comparator
     */
    public Heap(Comparator<T> comparator, boolean maxHeap) {
        root = null;
        last = null;
        size = 0;
        if (comparator != null) {
            this.comparator = comparator;
        } else {
            this.comparator = new Comparator<T>() {
                @Override
                @SuppressWarnings("unchecked")
                // Used for a minHeap where root is smallest number
                public int compare(T o1, T o2) {

                    return ((Comparable<T>) o1).compareTo(o2);
                }
            };
        }
        if (maxHeap) {
            // Used for a maxHeap where root is highest number
            this.comparator = new Comparator<T>() {
                @Override
                public int compare(T o1, T o2) {

                    return Heap.this.comparator.compare(o2, o1);
                }
            };
        }
    }
    /*
     * Adds item into heap
     */

    @Override
    public void offer(T item) {
        if (size == 0) {
            root = new Node<T>(item, null, null, null);
            last = root;
            size++;
            return;
        }
        if (size % 2 == 0) {
            last.parent.right = new Node<T>(item, null, null, last.parent);
            last = last.parent.right;
        } else {
            Node<T> curNode = last;
            while (curNode != root && curNode == curNode.parent.right) {
                curNode = curNode.parent;
            }
            // When would this ever be the root
            if (curNode != root) {
                curNode = curNode.parent.right;
            }
            while (curNode.left != null) {
                curNode = curNode.left;
            }
            curNode.left = new Node<T>(item, null, null, curNode);
            last = curNode.left;
        }
        size++;
        bubbleUp(last);
    }

    /*
     * Swaps data of two nodes
     */
    private void swap(Node<T> node1, Node<T> node2) {
        T temp = node1.data;
        node1.data = node2.data;
        node2.data = temp;
    }

    /*
     * Check if child has higher priority than parents, grandparents, etc
     */
    private void bubbleUp(Node<T> curNode) {
        if (curNode == root) {
            return;
        }
        T myData = curNode.data;
        T parentData = curNode.parent.data;
        if (comparator.compare(myData, parentData) < 0) {
            swap(curNode, curNode.parent);
            bubbleUp(curNode.parent);

        }

        
    }

    /*
     * Returns size of heap
     */
    @Override
    public int size() {
        return size;
    }

    /*
     * Checks value of root or highest priority item
     */
    @Override
    public T peek() {
        return root.data;
    }

    /*
     * Removes root and replaces it with last item added to heap, last item gets
     * bubbled down
     */
    @Override
    public T poll() {
        T temp = root.data;
        if (size == 1) {
            root = null;
            last = null;
            size--;
            return temp;
        }
        root.data = last.data;
        if (size % 2 != 0) {
            last = last.parent.left;
            last.parent.right = null;
        } else {
            Node<T> curNode = last;
            while (curNode != root && curNode == curNode.parent.left) {
                curNode = curNode.parent;
            }
            if (curNode != root) {
                curNode = curNode.parent.left;
            }
            while (curNode.right != null) {
                curNode = curNode.right;
            }
            last.parent.left = null;
            last = curNode;
        }
        size--;
        bubbleDown(root);
        return temp;
    }

    /*
     * Checks if an item has less priority than its children
     */
    public void bubbleDown(Node<T> curNode) {
        if (curNode.left == null) {
            return;
        } else if (curNode.right == null) {
            if (comparator.compare(curNode.left.data, curNode.data) < 0) {
                swap(curNode, curNode.left);
                // curNode.left has curNode's data and curNode has curNode.left's data
                bubbleDown(curNode.left);

            }
        } else {
            if (comparator.compare(curNode.left.data, curNode.right.data) < 0) {
                if (comparator.compare(curNode.left.data, curNode.data) < 0) {
                    swap(curNode, curNode.left);
                    bubbleDown(curNode.left);
                }
            } else {
                if (comparator.compare(curNode.right.data, curNode.data) < 0) {
                    swap(curNode, curNode.right);
                    bubbleDown(curNode.right);
                }
            }
        }
    }

    /*
     * Checks to see if item is in proper spot, does it have less priority than
     * parent but more priority than child
     */
    @Override
    public void updatePriority(T item) {
        if (size == 0) {
            return;
        } else if (root.data.equals(item)) {
            bubbleDown(root);
        } else {

            Node<T> curNode = root;
            Node<T> temp = updatePriority(item, curNode);
            if (temp != null) {
                if (temp.parent != null && comparator.compare(temp.data, temp.parent.data) < 0) {
                    bubbleUp(temp);
                } else {
                    bubbleDown(temp);
                }
            }

        }
    }

    /*
     * Helper recursive method for updatePriority, find items that needs to be
     * checked
     */
    public Node<T> updatePriority(T item, Node<T> curNode) {
        if (comparator.compare(item, curNode.data) == 0) {
            return curNode;
        }
        if (curNode.left != null) {
            Node<T> result = updatePriority(item, curNode.left);
            if (result != null) return result;
        }
        if (curNode.right != null) {
            Node<T> result = updatePriority(item, curNode.right);
            if (result != null) return result;
        }
        return null;
    }

    // /*
    // * Tests
    // */
    // public static void main(String[] args){
    // Heap<Integer> h1 = new Heap<Integer>();
    // h1.offer(5);
    // h1.offer(10);
    // h1.offer(15);
    // h1.offer(20);
    // h1.offer(25);
    // h1.offer(30);
    // h1.offer(35);
    // h1.updatePriority(35);
    // }

}
