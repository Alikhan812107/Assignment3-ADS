import java.util.Objects; // Import for Objects.equals()

public class MyHashTable<K, V> {

    private static final int DEFAULT_CAPACITY = 11;
    private static final double DEFAULT_LOAD_FACTOR = 0.75; // Load factor to trigger resizing
    private Node<K, V>[] chainArray;
    private int size;
    private int capacity; // Store the current capacity

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    "}";
        }
    }

    public MyHashTable() {
        this(DEFAULT_CAPACITY);
    }

    public MyHashTable(int capacity) {
        this.capacity = capacity;
        this.chainArray = new Node[capacity];
        this.size = 0;
    }

    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % capacity; // Use capacity here
    }

    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int index = hash(key);
        Node<K, V> current = chainArray[index];
        Node<K, V> previous = null;

        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            previous = current;
            current = current.next;
        }

        Node<K, V> newNode = new Node<>(key, value);
        if (previous == null) {
            chainArray[index] = newNode;
        } else {
            previous.next = newNode;
        }
        size++;

        // Check if resizing is needed
        if ((double) size / capacity > DEFAULT_LOAD_FACTOR) {
            resize();
        }
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int index = hash(key);
        Node<K, V> current = chainArray[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public void remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int index = hash(key);
        Node<K, V> current = chainArray[index];
        Node<K, V> previous = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (previous == null) {
                    chainArray[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return;
            }
            previous = current;
            current = current.next;
        }
        // Key not found, no need to do anything
    }

    public boolean contains(V value) {
        for (Node<K, V> head : chainArray) {
            Node<K, V> current = head;
            while (current != null) {
                if (Objects.equals(current.value, value)) { // Use Objects.equals() for null-safe comparison
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }

    public K getKey(V value) {
        for (Node<K, V> head : chainArray) {
            Node<K, V> current = head;
            while (current != null) {
                if (Objects.equals(current.value, value)) {  // Use Objects.equals()
                    return current.key;
                }
                current = current.next;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    private void resize() {
        int newCapacity = capacity * 2; // Double the capacity
        Node<K, V>[] newChainArray = new Node[newCapacity];
        capacity = newCapacity; // Update the capacity

        // Rehash all existing key-value pairs to the new array
        for (Node<K, V> head : chainArray) {
            Node<K, V> current = head;
            while (current != null) {
                int newIndex = hash(current.key); // Use the new capacity
                Node<K, V> next = current.next; // Store next to avoid losing it
                current.next = newChainArray[newIndex]; // Insert at the head of the new list
                newChainArray[newIndex] = current;
                current = next;
            }
        }
        chainArray = newChainArray; // Replace the old array
    }

    public void printTable() { //added for testing
        System.out.println("MyHashTable contents:");
        for (int i = 0; i < capacity; i++) {
            System.out.print("Bucket " + i + ": ");
            Node<K, V> current = chainArray[i];
            while (current != null) {
                System.out.print("(" + current.key + ", " + current.value + ") -> ");
                current = current.next;
            }
            System.out.println("null");
        }
        System.out.println("Size: " + size);
        System.out.println("Capacity: " + capacity);
    }
}

