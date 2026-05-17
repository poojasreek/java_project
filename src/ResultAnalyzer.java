import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResultAnalyzer {
    private StudentManager manager;

    public ResultAnalyzer(StudentManager manager) {
        this.manager = manager;
    }

    public void displayRankings() {
        List<Student> students = new ArrayList<>(manager.getStudents());
        if (students.isEmpty()) {
            System.out.println("No records found for ranking.");
            return;
        }
        
        // Sort students in descending order to get rankings
        students.sort(Collections.reverseOrder()); 
        
        System.out.println("--- Student Rankings ---");
        System.out.println("------------------------------------------------------------------");
        int rank = 1;
        for (Student s : students) {
            System.out.printf("Rank %-3d | %-20s | ID: %-10s | Total: %-4d | Grade: %-2s%n", 
                              rank, s.getName(), s.getId(), s.getTotal(), s.getGrade());
            rank++;
        }
        System.out.println("------------------------------------------------------------------");
    }

    public void findTopper() {
        List<Student> students = manager.getStudents();
        if (students.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        
        Student topper = Collections.max(students);
        System.out.println("--- Class Topper ---");
        System.out.println("Name       : " + topper.getName());
        System.out.println("ID         : " + topper.getId());
        System.out.println("Total Marks: " + topper.getTotal());
        System.out.println("Percentage : " + String.format("%.2f", topper.getAverage()) + "%");
        System.out.println("Grade      : " + topper.getGrade());
        System.out.println("--------------------");
    }
    
    public void subjectWiseAnalysis() {
        List<Student> students = manager.getStudents();
        if (students.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        
        int numSubjects = 5; 
        int[] totalMarksPerSubject = new int[numSubjects];
        int[] highestMarksPerSubject = new int[numSubjects];
        
        for (Student s : students) {
            int[] marks = s.getMarks();
            if (marks == null || marks.length != numSubjects) continue;
            
            for (int i = 0; i < numSubjects; i++) {
                totalMarksPerSubject[i] += marks[i];
                if (marks[i] > highestMarksPerSubject[i]) {
                    highestMarksPerSubject[i] = marks[i];
                }
            }
        }
        
        System.out.println("--- Subject-wise Analysis ---");
        System.out.println("------------------------------------------------------");
        for (int i = 0; i < numSubjects; i++) {
            double avg = (double) totalMarksPerSubject[i] / students.size();
            System.out.printf("Subject %d | Average Marks: %-6.2f | Highest Marks: %-3d%n", 
                              (i + 1), avg, highestMarksPerSubject[i]);
        }
        System.out.println("------------------------------------------------------");
    }
}
