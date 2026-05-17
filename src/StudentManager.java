import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private List<Student> students;
    private final String FILE_NAME = "students.dat";

    public StudentManager() {
        students = new ArrayList<>();
        loadFromFile();
    }

    public void addStudent(Student student) {
        for (Student s : students) {
            if (s.getId().equalsIgnoreCase(student.getId())) {
                System.out.println("Student with ID " + student.getId() + " already exists!");
                return;
            }
        }
        students.add(student);
        saveToFile();
        System.out.println("Student added successfully.");
    }

    public void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        for (Student s : students) {
            System.out.println(s);
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------");
    }

    public Student searchStudent(String id) {
        for (Student s : students) {
            if (s.getId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }

    public void updateStudentName(String id, String newName) {
        Student s = searchStudent(id);
        if (s != null) {
            s.setName(newName);
            saveToFile();
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void enterMarks(String id, int[] marks) {
        Student s = searchStudent(id);
        if (s != null) {
            s.setMarks(marks);
            s.calculateResult();
            saveToFile();
            System.out.println("Marks entered & result calculated successfully for " + s.getName());
        } else {
            System.out.println("Student not found. Cannot enter marks.");
        }
    }

    public List<Student> getStudents() {
        return students;
    }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                students = (List<Student>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading data: " + e.getMessage());
            }
        }
    }
}
