public class BSTTest {
    public static void main(String[] args) {
        BST<Integer, String> intStringBST = new BST<>();

        System.out.println("--- Integer/String BST Tests ---");
        intStringBST.put(5, "E");
        intStringBST.put(3, "C");
        intStringBST.put(7, "G");
        intStringBST.put(2, "B");
        intStringBST.put(4, "D");
        intStringBST.put(6, "F");
        intStringBST.put(8, "H");
        intStringBST.put(3, "C Updated");

        System.out.println("Size: " + intStringBST.size());
        System.out.println("Get(4): " + intStringBST.get(4));
        System.out.println("Get(9): " + intStringBST.get(9));

        System.out.println("\nIn-order traversal:");
        for (BST.Entry<Integer, String> entry : intStringBST) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        intStringBST.delete(3);
        System.out.println("\nSize after deleting 3: " + intStringBST.size());
        System.out.println("Get(3) after delete: " + intStringBST.get(3));

        intStringBST.delete(7);
        System.out.println("Size after deleting 7: " + intStringBST.size());
        System.out.println("Get(7) after delete: " + intStringBST.get(7));

        System.out.println("\nIn-order traversal after deletions:");
        for (BST.Entry<Integer, String> entry : intStringBST) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        System.out.println("\n--- String/Student BST Tests ---");
        BST<String, Student> stringStudentBST = new BST<>();
        stringStudentBST.put("Charlie", new Student(101, "Math"));
        stringStudentBST.put("Alice", new Student(102, "Science"));
        stringStudentBST.put("Bob", new Student(103, "History"));

        System.out.println("Size: " + stringStudentBST.size());
        System.out.println("Get(\"Bob\"): " + stringStudentBST.get("Bob"));
        System.out.println("Get(\"David\"): " + stringStudentBST.get("David"));

        System.out.println("\nIn-order traversal (by name):");
        for (BST.Entry<String, Student> entry : stringStudentBST) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        stringStudentBST.delete("Alice");
        System.out.println("\nSize after deleting Alice: " + stringStudentBST.size());
        System.out.println("Get(\"Alice\") after delete: " + stringStudentBST.get("Alice"));

        System.out.println("\nIn-order traversal after deletion:");
        for (BST.Entry<String, Student> entry : stringStudentBST) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }
}