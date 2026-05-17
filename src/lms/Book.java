package lms;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Book class represents a book in the library.
 * It implements Serializable to allow saving to a file.
 * We use Encapsulation by keeping fields private and providing getters/setters.
 */
public class Book implements Serializable {
    // Unique identifier for serialization backwards compatibility
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String title;
    private String author;
    private boolean isAvailable;
    private String issuedToStudentId;
    private long issueTime; 
    
    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;       // By default, a new book is available
        this.issuedToStudentId = null; // Not issued to anyone initially
        this.issueTime = 0;
    }
    
    // Getters and Setters (Encapsulation)
    public String getId() { 
        return id; 
    }
    
    public String getTitle() { 
        return title; 
    }
    
    public String getAuthor() { 
        return author; 
    }
    
    public boolean isAvailable() { 
        return isAvailable; 
    }
    
    public void setAvailable(boolean available) { 
        isAvailable = available; 
    }
    
    public String getIssuedToStudentId() { 
        return issuedToStudentId; 
    }
    
    public void setIssuedToStudentId(String studentId) { 
        this.issuedToStudentId = studentId; 
    }
    
    public long getIssueTime() { 
        return issueTime; 
    }
    
    public void setIssueTime(long issueTime) { 
        this.issueTime = issueTime; 
    }
    
    // Methods to change book state
    public void issueBook(String studentId) {
        this.isAvailable = false;
        this.issuedToStudentId = studentId;
        this.issueTime = System.currentTimeMillis(); // Current time in milliseconds
    }
    
    public void returnBook() {
        this.isAvailable = true;
        this.issuedToStudentId = null;
        this.issueTime = 0;
    }

    // Overriding toString (Polymorphism) for easy display
    @Override
    public String toString() {
        String status = isAvailable ? "Available" : "Issued to Student ID: " + issuedToStudentId;
        String dateStr = "";
        if (!isAvailable && issueTime > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateStr = ", Issued On: " + sdf.format(new Date(issueTime));
        }
        return "Book ID: " + id + " | Title: '" + title + "' | Author: '" + author + "' | Status: " + status + dateStr;
    }
}
