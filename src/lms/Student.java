package lms;

import java.io.Serializable;

/**
 * The Student class represents a member/student of the library.
 * It implements Serializable to allow saving to a file.
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String name;
    private String email;

    // Constructor to initialize Student object
    public Student(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters for accessing fields (Encapsulation)
    public String getId() { 
        return id; 
    }
    
    public String getName() { 
        return name; 
    }
    
    public String getEmail() { 
        return email; 
    }

    // Overriding toString (Polymorphism) to effectively print Student details
    @Override
    public String toString() {
        return "Student ID: " + id + " | Name: " + name + " | Email: " + email;
    }
}
