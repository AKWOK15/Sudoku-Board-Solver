import java.util.ArrayList;
/*
 * Aidan Kwok
 * Creates a hashMap uisng chaining to handle collisions that inherits from MapSet.java
 */
public class HashMap<K, V> implements MapSet<K, V> {
    private static class Node<K, V> extends KeyValuePair<K, V> {
        Node<K, V> next;
        Node<K, V> prev;

        /*
         * Node constructor
         */
        public Node(K key, V value, Node<K, V> next, Node<K, V> prev) {
            super(key, value);
            this.next = next;
            this.prev = prev;

        }
    }

    private Node<K, V>[] buckets;
    private int size;
    private double maxLoadFactor;

    /*
     * Constructor with two parameters
     */
    @SuppressWarnings("unchecked")
    public HashMap(int initialCapacity, double maxLoadFactor) {
        size = 0;
        // This creates yellow squiglly line, is that an issue
        // (Node<K, V>[]) , specificies what type of Node the array holds
        buckets = (Node<K, V>[]) new Node[initialCapacity];
        this.maxLoadFactor = maxLoadFactor;
    }

    /*
     * Constructor with one parameter
     */
    public HashMap(int initialCapacity) {
        this(initialCapacity, .75);

    }

    /*
     * Constructor with no parameters
     */
    public HashMap() {
        this(16);

    }

    /*
     * Returns number of buckets
     */
    public int capacity() {
        return buckets.length;
    }

    /*
     * Returns index of array that key will be put into
     */
    private int hash(K key) {
        return Math.abs(key.hashCode() % capacity());
    }

    @Override
    /*
     * Puts an item in bucket
     */
    public V put(K key, V value) {
        int index = hash(key);
        if (buckets[index] == null) {
            buckets[index] = new Node<K, V>(key, value, null, null);

        } else {
            for (Node<K, V> curNode = buckets[index]; curNode != null; curNode = curNode.next) {
                if (curNode.getKey().equals(key)) {
                    V oldVal = curNode.getValue();
                    curNode.setValue(value);
                    return oldVal;
                }
            }
            // This links the items of abucket together, bucket can have multiple items but
            // we only look at first
            buckets[index] = new Node<K, V>(key, value, buckets[index], null);
            buckets[index].next.prev = buckets[index];

        }
        if (++size >= maxLoadFactor * capacity()) {
            resize(capacity() * 2);
        }
        return null;
    }

    /*
     * Returns size of bucket with most items
     */
    @Override
    public int maxDepth() {
        int maxDepth = 0;
        for (int x = 0; x < capacity(); x++) {
            int temp = 0;
            Node<K, V> curNode = buckets[x];
            while (curNode != null) {
                curNode = curNode.next;
                temp++;
            }
            if (temp > maxDepth) {
                maxDepth = temp;
            }
        }
        return maxDepth;
    }

    /*
     * Resizes buckets array list to new size
     */
    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        Node<K, V>[] temp = buckets;
        buckets = (Node<K, V>[]) new Node[newSize];
        // Need to reset size or else since I'm calling put, size will keep increasing
        size = 0;
        for (Node<K, V> item : temp) {
            while (item != null) {
                put(item.getKey(), item.getValue());
                item = item.next;
            }
        }

    }

    /*
     * Checks if buckets array list of linked lists contains a certain key
     */
    @Override
    public boolean containsKey(K key) {
        // Check code in lecture
        int index = hash(key);
        if (buckets[index] == null) {
            return false;
        }
        for (Node<K, V> curNode = buckets[index]; curNode != null; curNode = curNode.next) {
            if (curNode.getKey().equals(key)) {
                return true;
            }

        }
        return false;
    }

    /*
     * Returns the value of a key
     */
    @Override
    public V get(K key) {
        int index = hash(key);
        if (buckets[index] == null) {
            return null;
        }
        for (Node<K, V> curNode = buckets[index]; curNode != null; curNode = curNode.next) {
            if (curNode.getKey().equals(key)) {
                return curNode.getValue();
            }

        }
        return null;

    }

    /*
     * Removes a Node from a bucket
     */
    @Override
    public V remove(K key) {
        // keep track of previous Node
        int index = hash(key);
        if (buckets[index] == null) {
            return null;
        }
        for (Node<K, V> curNode = buckets[index]; curNode != null; curNode = curNode.next) {
            if (curNode.getKey().equals(key)) {
                if (buckets[index].next == null) {
                    buckets[index] = null;
                } else {
                    if (curNode.prev != null) {
                        curNode.prev.next = curNode.next;
                    }
                    if (curNode.next != null) {
                        curNode.next.prev = curNode.prev;
                    }
                }
                if (size-- <= maxLoadFactor * capacity() / 4) {
                    resize(capacity()/2);
                }
                return curNode.getValue();
            }
        }
        return null;
    }

    /*
     * Returns ArrayList of all the keys in the map
     */
    @Override
    public ArrayList<K> keySet() {
        ArrayList<K> output = new ArrayList<K>();
        // why does this for loop work without an iterator
        for (Node<K, V> item : buckets) {
            while (item != null) {
                output.add(item.getKey());
                item = item.next;
            }
        }
        return output;

    }

    /*
     * Returns ArrayList of all the values in the map
     */
    @Override
    public ArrayList<V> values() {
        ArrayList<V> output = new ArrayList<V>();
        for (Node<K, V> item : buckets) {
            while (item != null) {
                output.add(item.getValue());
                item = item.next;
            }
        }
        return output;
    }

    @Override
    // Why do we use <MapSet.KeyValuePair<K, V>> instead of <Node<K,V>>
    /*
     * Returns arraylist of each key mapped to a value
     */
    public ArrayList<KeyValuePair<K, V>> entrySet() {
        ArrayList<KeyValuePair<K, V>> output = new ArrayList<KeyValuePair<K, V>>();
        for (Node<K, V> item : buckets) {
            while (item != null) {
                output.add(item);
                item = item.next;
            }
        }
        return output;

    }

    /*
     * Returns number of Nodes in buckets array list, this is not the length of the
     * array list
     */
    @Override
    public int size() {
        return size;
    }

    /*
     * Resets size to 0, buckets to empty array list of size 16 and maxLoadFactor to
     * 0.75
     */
    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        size = 0;
        buckets = (Node<K, V>[]) new Node[16];
        this.maxLoadFactor = 0.75;
    }

    /*
     * Formats HashMap to a string
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Amount of buckets: " + capacity() + "\n");
        for (int x = 0; x < capacity(); x++) {
            sb.append("Index " + x + ": ");
            Node<K,V> curNode = buckets[x];
            while (curNode != null) {
                sb.append(curNode);
                curNode = curNode.next;
            }
            sb.append("\n");
        }
        return sb.delete(sb.length() - 1, sb.length()).toString();
    }
    /*
     * Testing
     */

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>(8, 0.75);
        map.put("ai", 1);
        map.put("ib", 1);
        map.put("tp", 1);
        map.put("kc", 1);
        map.put("ob", 1);
        System.out.println(map);
}

}
