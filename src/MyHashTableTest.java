import java.util.Random;

public class MyHashTableTest {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();
        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            int randomId = random.nextInt(20000);
            String randomName = "Name_" + random.nextInt(100);
            MyTestingClass key = new MyTestingClass(randomId, randomName);

            int randomStudentId = random.nextInt(500);
            String[] majors = {"Computer Science", "Engineering", "Biology", "Mathematics"};
            String randomMajor = majors[random.nextInt(majors.length)];
            Student value = new Student(randomStudentId, randomMajor);

            table.put(key, value);

        }

        System.out.println("Initial distribution:");
        table.printTable();

        System.out.println("\nDistribution after (potential) tuning:");
        System.out.println("Note: To see the effect of hashCode tuning, please modify the hashCode() method in MyTestingClass.java and re-run this test.");
    }
}