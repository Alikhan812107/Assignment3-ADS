class Student {
    private int studentId;
    private String major;

    public Student(int studentId, String major) {
        this.studentId = studentId;
        this.major = major;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getMajor() {
        return major;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", major='" + major + '\'' +
                '}';
    }
}