import java.io.Serializable;

public class Student implements Serializable, Comparable<Student> {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private int[] marks; // Array for 5 subjects
    private int total;
    private double average;
    private String grade;
    private String status;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.marks = new int[5]; // Assuming 5 fixed subjects for simplicity
        this.grade = "N/A";
        this.status = "N/A";
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int[] getMarks() { return marks; }
    public void setMarks(int[] marks) { this.marks = marks; }

    public int getTotal() { return total; }
    public double getAverage() { return average; }
    public String getGrade() { return grade; }
    public String getStatus() { return status; }

    public void calculateResult() {
        total = 0;
        boolean failed = false;
        
        for (int mark : marks) {
            total += mark;
            if (mark < 40) { // Assuming 40 is passing marks per subject
                failed = true;
            }
        }
        average = (double) total / marks.length;

        if (failed) {
            status = "FAIL";
            grade = "F";
        } else {
            status = "PASS";
            if (average >= 90) grade = "A+";
            else if (average >= 80) grade = "A";
            else if (average >= 70) grade = "B";
            else if (average >= 60) grade = "C";
            else if (average >= 50) grade = "D";
            else grade = "E";
        }
    }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.total, other.total); // Ascending order
    }

    @Override
    public String toString() {
        return String.format("ID: %-10s | Name: %-20s | Total: %-4d | Avg: %-6.2f | Grade: %-2s | Status: %s",
                id, name, total, average, grade, status);
    }
}
