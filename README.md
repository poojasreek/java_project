# Library Management System

A complete Core Java-based Library Management System applying Object-Oriented Programming (OOP) concepts.

## Features
1. **Add new books** - Keep track of library inventory.
2. **Add students** - Maintain student records.
3. **Issue books** - Assign books to students with timestamps.
4. **Return books** - Return books and calculate fines for late returns (based on a 14-day limit).
5. **Search functionality** - Search for books by ID or title.
6. **Persistence** - Data is automatically saved to and loaded from files in the `lms_data` directory using Java Serialization.

## OOP Concepts Applied
- **Classes and Objects:** Used to model real-world entities like `Book`, `Student`, and `Library`.
- **Encapsulation:** Private fields with public getters/setters in `Book.java` and `Student.java`.
- **Inheritance & Polymorphism:** Overriding the `toString()` method for custom object representation.
- **Abstraction:** Logic is hidden within the `Library` class, with simple methods exposed to `Main`.
- **Exception Handling:** Robust input validation in the menu-driven loop.
- **Collections:** `ArrayList` used for dynamic data management.

## Project Structure
```text
LibraryManagementSystem/
├── src/
│   └── lms/
│       ├── Book.java
│       ├── Student.java
│       ├── Library.java
│       └── Main.java
└── lms_data/ (Created automatically)
    ├── books.dat
    └── students.dat
```

## How to Run
1. Navigate to the `src` directory in your terminal.
2. Compile the project:
   ```bash
   javac lms/*.java
   ```
3. Run the application:
   ```bash
   java lms.Main
   ```
