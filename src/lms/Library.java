package lms;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * The Library class contains logic to manage Books and Students collections.
 * It uses File Handling to make sure data is persisted.
 */
public class Library {
    // Dynamic collections (ArrayList) for books and students
    private List<Book> books;
    private List<Student> students;
    
    // Directory and file names for storage
    private final String DATA_DIR = "lms_data";
    private final String BOOKS_FILE = DATA_DIR + "/books.dat";
    private final String STUDENTS_FILE = DATA_DIR + "/students.dat";
    
    // Core configurations
    private final double FINE_PER_DAY = 10.0; // 10 units of currency per day late
    private final int MAX_DAYS_ALLOWED = 14;

    public Library() {
        books = new ArrayList<>();
        students = new ArrayList<>();
        
        // Ensure data directory exists
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        loadData(); // Load records from files at startup
    }

    public void addBook(Book book) {
        for (Book b : books) {
            if (b.getId().equals(book.getId())) {
                System.out.println("Error: A book with this ID already exists.");
                return;
            }
        }
        books.add(book);
        saveData(); // Save changes
        System.out.println("Book added successfully.");
    }

    public void addStudent(Student student) {
        for (Student s : students) {
            if (s.getId().equals(student.getId())) {
                System.out.println("Error: A student with this ID already exists.");
                return;
            }
        }
        students.add(student);
        saveData();
        System.out.println("Student added successfully.");
    }

    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
            return;
        }
        for (Book b : books) {
            System.out.println(b.toString());
        }
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students registered.");
            return;
        }
        for (Student s : students) {
            System.out.println(s.toString());
        }
    }

    public Book searchBookById(String id) {
        for (Book b : books) {
            if (b.getId().equalsIgnoreCase(id)) {
                return b;
            }
        }
        return null;
    }

    public void searchBooksByTitle(String title) {
        boolean found = false;
        for (Book b : books) {
            // Case-insensitive check
            if (b.getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found matching the title '" + title + "'.");
        }
    }

    public void issueBook(String bookId, String studentId) {
        Book book = searchBookById(bookId);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        if (!book.isAvailable()) {
            System.out.println("Book is already issued to Student ID: " + book.getIssuedToStudentId());
            return;
        }

        boolean studentExists = false;
        for (Student s : students) {
            if (s.getId().equalsIgnoreCase(studentId)) {
                studentExists = true;
                break;
            }
        }
        if (!studentExists) {
            System.out.println("Student not found. Please register the student first.");
            return;
        }

        book.issueBook(studentId);
        saveData();
        System.out.println("Book issued successfully to Student: " + studentId);
    }

    public void returnBook(String bookId) {
        Book book = searchBookById(bookId);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        if (book.isAvailable()) {
            System.out.println("This book is not currently issued.");
            return;
        }

        long issueTime = book.getIssueTime();
        long currentTime = System.currentTimeMillis();
        
        // Calculate difference in milliseconds, then convert to days
        long diffInMillis = Math.abs(currentTime - issueTime);
        long daysIssued = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
        
        System.out.println("Book was issued for " + daysIssued + " days.");
        
        if (daysIssued > MAX_DAYS_ALLOWED) {
            long lateDays = daysIssued - MAX_DAYS_ALLOWED;
            double fine = lateDays * FINE_PER_DAY;
            System.out.println("Book is late by " + lateDays + " days.");
            System.out.println("Fine applied: Rs." + fine);
        } else {
            System.out.println("Book returned on time. No fine.");
        }

        book.returnBook();
        saveData();
        System.out.println("Book returned successfully.");
    }
    
    public void displayIssuedBooks() {
        boolean found = false;
        for (Book b : books) {
            if (!b.isAvailable()) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books are currently issued.");
        }
    }

    /**
     * File Handling: Load records using ObjectInputStream
     */
    @SuppressWarnings("unchecked")
    private void loadData() {
        try {
            File booksFile = new File(BOOKS_FILE);
            if (booksFile.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(booksFile))) {
                    books = (List<Book>) ois.readObject();
                }
            }

            File studentsFile = new File(STUDENTS_FILE);
            if (studentsFile.exists()) {
                try (ObjectInputStream ois2 = new ObjectInputStream(new FileInputStream(studentsFile))) {
                    students = (List<Student>) ois2.readObject();
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading existing records: " + e.getMessage());
        }
    }

    /**
     * File Handling: Save records using ObjectOutputStream
     */
    private void saveData() {
        try {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BOOKS_FILE))) {
                oos.writeObject(books);
            }

            try (ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(STUDENTS_FILE))) {
                oos2.writeObject(students);
            }
        } catch (Exception e) {
            System.out.println("Error saving records: " + e.getMessage());
        }
    }
}
