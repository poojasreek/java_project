package lms;

import java.util.Scanner;

/**
 * Main application class
 * This contains the interactive console menu for the user.
 */
public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("=========================================");
        System.out.println("Welcome to the Library Management System");
        System.out.println("=========================================");

        while (!exit) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Add New Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book (by ID or Title)");
            System.out.println("4. Issue Book to Student");
            System.out.println("5. Return Book (Calculate Fine)");
            System.out.println("6. Display Issued Books Details");
            System.out.println("7. Add New Student Profile");
            System.out.println("8. View All Students Profile");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = -1;
            try {
                // Read complete line and parse to avoid scanner trailing newline issues
                String input = scanner.nextLine();
                // Simple validation guard
                if (input.trim().isEmpty()) {
                    continue;
                }
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    String bookId = scanner.nextLine();
                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Book Author: ");
                    String author = scanner.nextLine();
                    // Demonstrating object creation (Instantiating Class)
                    library.addBook(new Book(bookId, title, author));
                    break;
                    
                case 2:
                    System.out.println("--- All Available & Issued Books ---");
                    library.displayAllBooks();
                    break;
                    
                case 3:
                    System.out.println("1. Search by ID");
                    System.out.println("2. Search by Title");
                    System.out.print("Enter choice (1 or 2): ");
                    String searchChoice = scanner.nextLine();
                    
                    // Conditionals block
                    if (searchChoice.equals("1")) {
                        System.out.print("Enter Book ID: ");
                        String idToSearch = scanner.nextLine();
                        Book foundBook = library.searchBookById(idToSearch);
                        if (foundBook != null) {
                            System.out.println("Book Found: " + foundBook);
                        } else {
                            System.out.println("Book not found in library inventory.");
                        }
                    } else if (searchChoice.equals("2")) {
                        System.out.print("Enter Book Title: ");
                        String titleToSearch = scanner.nextLine();
                        library.searchBooksByTitle(titleToSearch);
                    } else {
                        System.out.println("Invalid choice!");
                    }
                    break;
                    
                case 4:
                    System.out.print("Enter Book ID to Issue: ");
                    String issueBookId = scanner.nextLine();
                    System.out.print("Enter Student ID: ");
                    String issueStudentId = scanner.nextLine();
                    library.issueBook(issueBookId, issueStudentId);
                    break;
                    
                case 5:
                    System.out.print("Enter Book ID to Return: ");
                    String returnBookId = scanner.nextLine();
                    library.returnBook(returnBookId);
                    break;
                    
                case 6:
                    System.out.println("--- Currently Issued Books ---");
                    library.displayIssuedBooks();
                    break;
                    
                case 7:
                    System.out.print("Enter Student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Enter Student Name: ");
                    String studentName = scanner.nextLine();
                    System.out.print("Enter Student Email: ");
                    String studentEmail = scanner.nextLine();
                    library.addStudent(new Student(studentId, studentName, studentEmail));
                    break;
                    
                case 8:
                    System.out.println("--- Registered Students ---");
                    library.displayAllStudents();
                    break;
                    
                case 0:
                    exit = true;  // Break the loop
                    System.out.println("Exiting System. Data automatically saved. Goodbye!");
                    break;
                    
                default:
                    System.out.println("Invalid choice! Please select an option from 0 to 8.");
            }
        }
        
        scanner.close(); // Clean up resource (Best practice)
    }
}
