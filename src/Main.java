import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static StudentManager studentManager = new StudentManager();
    private static ResultAnalyzer resultAnalyzer = new ResultAnalyzer(studentManager);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n==============================================");
            System.out.println("  SMART STUDENT RESULT MANAGEMENT SYSTEM");
            System.out.println("==============================================");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student Details");
            System.out.println("5. Enter Marks");
            System.out.println("6. View Rankings");
            System.out.println("7. Find Class Topper");
            System.out.println("8. Subject-wise Analysis");
            System.out.println("9. Exit");
            System.out.println("==============================================");
            System.out.print("Enter your choice: ");
            
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) continue;
                
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        studentManager.viewAllStudents();
                        break;
                    case 3:
                        searchStudent();
                        break;
                    case 4:
                        updateStudent();
                        break;
                    case 5:
                        enterMarks();
                        break;
                    case 6:
                        resultAnalyzer.displayRankings();
                        break;
                    case 7:
                        resultAnalyzer.findTopper();
                        break;
                    case 8:
                        resultAnalyzer.subjectWiseAnalysis();
                        break;
                    case 9:
                        exit = true;
                        System.out.println("Exiting the system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 9.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine().trim();
        if (id.isEmpty() || name.isEmpty()) {
            System.out.println("ID and Name cannot be empty.");
            return;
        }
        Student student = new Student(id, name);
        studentManager.addStudent(student);
    }

    private static void searchStudent() {
        System.out.print("Enter Student ID to search: ");
        String id = scanner.nextLine().trim();
        Student s = studentManager.searchStudent(id);
        if (s != null) {
            System.out.println("--- Student Found ---");
            System.out.println(s);
            System.out.print("Marks: ");
            int[] marks = s.getMarks();
            if (marks != null) {
                for (int m : marks) {
                    System.out.print(m + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        String id = scanner.nextLine().trim();
        System.out.print("Enter New Name: ");
        String newName = scanner.nextLine().trim();
        if (newName.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }
        studentManager.updateStudentName(id, newName);
    }

    private static void enterMarks() {
        System.out.print("Enter Student ID to add marks: ");
        String id = scanner.nextLine().trim();
        Student s = studentManager.searchStudent(id);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }
        
        System.out.println("Entering marks for: " + s.getName());
        int[] marks = new int[5];
        System.out.println("Enter marks for 5 subjects (out of 100): ");
        for (int i = 0; i < 5; i++) {
            while (true) {
                System.out.print("Subject " + (i + 1) + ": ");
                try {
                    int mark = Integer.parseInt(scanner.nextLine().trim());
                    if (mark < 0 || mark > 100) {
                        System.out.println("Invalid marks. Must be between 0 and 100.");
                        continue;
                    }
                    marks[i] = mark;
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter an integer.");
                }
            }
        }
        studentManager.enterMarks(id, marks);
    }
}
