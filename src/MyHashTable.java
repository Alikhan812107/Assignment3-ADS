public class MyHashTable<K, V> {

    private static final int DEFAULT_CAPACITY = 11;
    private Node<K, V>[] chainArray;
    private int size;


    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }


    public MyHashTable() {
        this(DEFAULT_CAPACITY);
    }

    public MyHashTable(int capacity) {
        this.chainArray = new Node[capacity];
        this.size = 0;
    }


    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % chainArray.length;
    }

    public void put(K key, V value) {
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
        newNode.next = chainArray[index];
        chainArray[index] = newNode;
        size++;
    }

    public V get(K key) {
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
                if (current.value.equals(value)) {
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
                if (current.value.equals(value)) {
                    return current.key;
                }
                current = current.next;
            }
        }
        return null;
    }

    public int size() {
        return this.size;
    }
}

