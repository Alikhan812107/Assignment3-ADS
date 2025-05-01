import java.util.Objects;

public class MyHashTable<K, V> {

    //  A generic hash table implementation using separate chaining to handle collisions.
    //
    //  Provides key-value storage with O(1) average-case time complexity for
    //  insertion, retrieval, and deletion.
    //
    //  Supports dynamic resizing to maintain
    //  performance as the number of elements grows.
    //
    //  Type Parameters:
    //      K: The type of keys.  Must be non-null.
    //      V: The type of values.

    private static final int DEFAULT_CAPACITY = 11;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;
    private Node<K, V>[] chainArray;
    private int size;
    private int capacity;

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
        return Math.abs(hashCode) % capacity;
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

        // Here I have checking if resizing is needed
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

    }

    public boolean contains(V value) {
        for (Node<K, V> head : chainArray) {
            Node<K, V> current = head;
            while (current != null) {
                if (Objects.equals(current.value, value)) {
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
                if (Objects.equals(current.value, value)) {
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
        int newCapacity = capacity * 2;
        Node<K, V>[] newChainArray = new Node[newCapacity];
        capacity = newCapacity;


        for (Node<K, V> head : chainArray) {
            Node<K, V> current = head;
            while (current != null) {
                int newIndex = hash(current.key);
                Node<K, V> next = current.next;
                current.next = newChainArray[newIndex];
                newChainArray[newIndex] = current;
                current = next;
            }
        }
        chainArray = newChainArray;
    }

    public void printTable() {
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

