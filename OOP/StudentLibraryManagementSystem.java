
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

/*
 * Student Library Management System
 * Single-file Java console application for school practical project.
 * Features implemented:
 * - Admin login (default admin/admin123)
 * - Add / Update books
 * - Student registration and login
 * - Search books (by name / author / id)
 * - Issue book (decrements available quantity)
 * - Return book (increments available quantity and calculates fine)
 * - View issued books (by admin) and view + calculate fine (by student)
 * - Persistence using Java Serialization (books.dat, students.dat)
 *
 * How to compile
 * javac StudentLibraryManagementSystem.java
 * java StudentLibraryManagementSystem
 *
 * Default admin credentials: username=admin password=admin123
 * Configurable values: FINE_PER_DAY and DEFAULT_ISSUE_DAYS in Library
 */
public class StudentLibraryManagementSystem {

    public static void main(String[] args) {
        Library library = Library.loadFromDisk();
        library.runConsole();
        library.saveToDisk();
        System.out.println("Exiting. Data saved.");
    }
}

// ---------- Models ----------
class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    int id;
    String title;
    String author;
    int totalQuantity;
    int availableQuantity;

    public Book(int id, String title, String author, int qty) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.totalQuantity = qty;
        this.availableQuantity = qty;
    }

    public String toString() {
        return String.format("ID:%d | %s by %s | total:%d | available:%d", id, title, author, totalQuantity, availableQuantity);
    }
}

class IssueRecord implements Serializable {

    private static final long serialVersionUID = 1L;
    int bookId;
    LocalDate issueDate;
    LocalDate dueDate;

    public IssueRecord(int bookId, LocalDate issueDate, LocalDate dueDate) {
        this.bookId = bookId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }
}

class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    String studentId; // unique
    String name;
    String password;
    // Map bookId -> IssueRecord
    Map<Integer, IssueRecord> issued = new HashMap<>();

    public Student(String studentId, String name, String password) {
        this.studentId = studentId;
        this.name = name;
        this.password = password;
    }

    public String toString() {
        return String.format("%s (%s)", name, studentId);
    }
}

// ---------- Library: core logic and persistence ----------
class Library implements Serializable {

    private static final long serialVersionUID = 1L;

    Map<Integer, Book> books = new HashMap<>();
    Map<String, Student> students = new HashMap<>();

    // admin credentials (simple)
    String adminUser = "admin";
    String adminPass = "admin123";

    // configuration
    static final int FINE_PER_DAY = 5; // currency units per day
    static final int DEFAULT_ISSUE_DAYS = 14; // days allowed

    transient Scanner scanner = new Scanner(System.in);

    // filenames
    static final String BOOKS_FILE = "books.dat";
    static final String STUDENTS_FILE = "students.dat";
    static final String LIBRARY_FILE = "library.dat";

    // ---------- Console UI ----------
    public void runConsole() {
        System.out.println("Welcome to Student Library Management System");
        boolean running = true;
        while (running) {
            System.out.println("\nMain Menu: 1) Admin Login  2) Student Login/Register  3) Search Books  4) Exit");
            System.out.print("Choose option: ");
            String ch = scanner.nextLine().trim();
            switch (ch) {
                case "1":
                    if (adminLogin()) {
                        adminMenu();
                    }
                    break;
                case "2":
                    studentEntry();
                    break;
                case "3":
                    searchBooksMenu();
                    break;
                case "4":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // ---------- Admin ----------
    boolean adminLogin() {
        System.out.print("Admin username: ");
        String u = scanner.nextLine().trim();
        System.out.print("Admin password: ");
        String p = scanner.nextLine().trim();
        if (u.equals(adminUser) && p.equals(adminPass)) {
            System.out.println("Admin login successful.");
            return true;
        }
        System.out.println("Wrong admin credentials.");
        return false;
    }

    void adminMenu() {
        boolean a = true;
        while (a) {
            System.out.println("\nAdmin Menu: 1) Add Book  2) Update Book  3) Search Books  4) View Issued Books  5) Logout");
            System.out.print("Choose option: ");
            String ch = scanner.nextLine().trim();
            switch (ch) {
                case "1":
                    addBookConsole();
                    break;
                case "2":
                    updateBookConsole();
                    break;
                case "3":
                    searchBooksMenu();
                    break;
                case "4":
                    viewAllIssuedBooks();
                    break;
                case "5":
                    a = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    void addBookConsole() {
        try {
            System.out.print("Enter book ID (integer): ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            if (books.containsKey(id)) {
                System.out.println("Book with that ID already exists.");
                return;
            }
            System.out.print("Enter title: ");
            String title = scanner.nextLine();
            System.out.print("Enter author: ");
            String author = scanner.nextLine();
            System.out.print("Enter quantity: ");
            int qty = Integer.parseInt(scanner.nextLine().trim());
            Book b = new Book(id, title, author, qty);
            books.put(id, b);
            System.out.println("Book added: " + b);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
        }
    }

    void updateBookConsole() {
        System.out.print("Enter book ID to update: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            Book b = books.get(id);
            if (b == null) {
                System.out.println("Book not found.");
                return;
            }
            System.out.println("Current: " + b);
            System.out.print("New title (or press Enter to keep): ");
            String t = scanner.nextLine();
            if (!t.isEmpty()) {
                b.title = t;
            }
            System.out.print("New author (or press Enter to keep): ");
            String a = scanner.nextLine();
            if (!a.isEmpty()) {
                b.author = a;
            }
            System.out.print("Adjust total quantity (enter integer, can be negative to reduce): ");
            String q = scanner.nextLine();
            if (!q.isEmpty()) {
                int delta = Integer.parseInt(q.trim());
                int newTotal = b.totalQuantity + delta;
                if (newTotal < 0) {
                    newTotal = 0;
                }
                // adjust available proportionally (naive)
                int used = b.totalQuantity - b.availableQuantity;
                b.totalQuantity = newTotal;
                b.availableQuantity = Math.max(0, newTotal - used);
            }
            System.out.println("Updated: " + b);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }

    void viewAllIssuedBooks() {
        System.out.println("\nIssued Books Report:");
        boolean any = false;
        for (Student s : students.values()) {
            if (!s.issued.isEmpty()) {
                any = true;
                System.out.println("Student: " + s);
                for (IssueRecord r : s.issued.values()) {
                    Book b = books.get(r.bookId);
                    System.out.printf("  Book: %s | Issued: %s | Due: %s | Fine(if today): %d\n",
                            (b == null ? "(deleted book)" : b.title),
                            r.issueDate.format(DateTimeFormatter.ISO_DATE),
                            r.dueDate.format(DateTimeFormatter.ISO_DATE),
                            calculateFine(r.dueDate, LocalDate.now()));
                }
            }
        }
        if (!any) {
            System.out.println("No issued books currently.");
        }
    }

    // ---------- Student registration / login ----------
    void studentEntry() {
        System.out.println("\n1) Register  2) Login  3) Back");
        System.out.print("Choose: ");
        String ch = scanner.nextLine().trim();
        switch (ch) {
            case "1":
                registerStudentConsole();
                break;
            case "2":
                studentLoginConsole();
                break;
            default:
                return;
        }
    }

    void registerStudentConsole() {
        System.out.print("Enter student ID (unique): ");
        String sid = scanner.nextLine().trim();
        if (students.containsKey(sid)) {
            System.out.println("Student ID already exists.");
            return;
        }
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter password: ");
        String pass = scanner.nextLine();
        Student s = new Student(sid, name, pass);
        students.put(sid, s);
        System.out.println("Registered: " + s);
    }

    void studentLoginConsole() {
        System.out.print("Student ID: ");
        String sid = scanner.nextLine().trim();
        System.out.print("Password: ");
        String pass = scanner.nextLine().trim();
        Student s = students.get(sid);
        if (s == null) {
            System.out.println("No such student. Register first.");
            return;
        }
        if (!s.password.equals(pass)) {
            System.out.println("Wrong password.");
            return;
        }
        System.out.println("Welcome, " + s.name);
        studentMenu(s);
    }

    void studentMenu(Student s) {
        boolean run = true;
        while (run) {
            System.out.println("\nStudent Menu: 1) Search Books 2) Issue Book 3) Return Book 4) My Issued Books 5) Logout");
            System.out.print("Choose: ");
            String ch = scanner.nextLine().trim();
            switch (ch) {
                case "1":
                    searchBooksMenu();
                    break;
                case "2":
                    issueBookConsole(s);
                    break;
                case "3":
                    returnBookConsole(s);
                    break;
                case "4":
                    viewStudentIssued(s);
                    break;
                case "5":
                    run = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // ---------- Search ----------
    void searchBooksMenu() {
        System.out.println("\nSearch by: 1) ID  2) Title keyword  3) Author keyword  4) Back");
        System.out.print("Choose: ");
        String ch = scanner.nextLine().trim();
        switch (ch) {
            case "1":
                System.out.print("Enter ID: ");
                try {
                    int id = Integer.parseInt(scanner.nextLine().trim());
                    Book b = books.get(id);
                    if (b != null) {
                        System.out.println(b); 
                    }else {
                        System.out.println("Not found.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid ID.");
                }
                break;
            case "2":
                System.out.print("Enter title keyword: ");
                String tk = scanner.nextLine().toLowerCase();
                books.values().stream().filter(b -> b.title.toLowerCase().contains(tk)).forEach(System.out::println);
                break;
            case "3":
                System.out.print("Enter author keyword: ");
                String ak = scanner.nextLine().toLowerCase();
                books.values().stream().filter(b -> b.author.toLowerCase().contains(ak)).forEach(System.out::println);
                break;
            default:
                return;
        }
    }

    // ---------- Issue / Return ----------
    void issueBookConsole(Student s) {
        System.out.print("Enter book ID to issue: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            String res = issueBook(s.studentId, id);
            System.out.println(res);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
        }
    }

    String issueBook(String studentId, int bookId) {
        Student s = students.get(studentId);
        if (s == null) {
            return "Student not found.";
        }
        Book b = books.get(bookId);
        if (b == null) {
            return "Book not found.";
        }
        if (b.availableQuantity <= 0) {
            return "No copies available.";
        }
        if (s.issued.containsKey(bookId)) {
            return "You already have this book issued.";
        }
        LocalDate issueDate = LocalDate.now();
        LocalDate dueDate = issueDate.plusDays(DEFAULT_ISSUE_DAYS);
        IssueRecord r = new IssueRecord(bookId, issueDate, dueDate);
        s.issued.put(bookId, r);
        b.availableQuantity -= 1;
        saveToDisk();
        return String.format("Issued '%s' to %s. Due on %s.", b.title, s.name, dueDate.format(DateTimeFormatter.ISO_DATE));
    }

    void returnBookConsole(Student s) {
        System.out.print("Enter book ID to return: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            String res = returnBook(s.studentId, id);
            System.out.println(res);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
        }
    }

    String returnBook(String studentId, int bookId) {
        Student s = students.get(studentId);
        if (s == null) {
            return "Student not found.";
        }
        IssueRecord r = s.issued.get(bookId);
        if (r == null) {
            return "This book is not issued to you.";
        }
        Book b = books.get(bookId);
        if (b != null) {
            b.availableQuantity += 1;
        }
        long fine = calculateFine(r.dueDate, LocalDate.now());
        s.issued.remove(bookId);
        saveToDisk();
        if (fine > 0) {
            return String.format("Returned. Fine due: %d (overdue %d days).", fine, ChronoUnit.DAYS.between(r.dueDate, LocalDate.now())); 
        }else {
            return "Returned on time. No fine.";
        }
    }

    void viewStudentIssued(Student s) {
        if (s.issued.isEmpty()) {
            System.out.println("You have no issued books.");
            return;
        }
        System.out.println("Your issued books:");
        for (IssueRecord r : s.issued.values()) {
            Book b = books.get(r.bookId);
            long fine = calculateFine(r.dueDate, LocalDate.now());
            System.out.printf("  ID:%d | %s | Issued:%s | Due:%s | Fine(if today):%d\n",
                    r.bookId,
                    (b == null ? "(deleted book)" : b.title),
                    r.issueDate.format(DateTimeFormatter.ISO_DATE),
                    r.dueDate.format(DateTimeFormatter.ISO_DATE),
                    fine);
        }
    }

    long calculateFine(LocalDate due, LocalDate today) {
        if (!today.isAfter(due)) {
            return 0L;
        }
        long days = ChronoUnit.DAYS.between(due, today);
        return days * FINE_PER_DAY;
    }

    // ---------- Persistence ----------
    public void saveToDisk() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(LIBRARY_FILE))) {
            oos.writeObject(this);
        } catch (IOException e) {
            System.out.println("Failed to save library: " + e.getMessage());
        }
    }

    public static Library loadFromDisk() {
        File f = new File(LIBRARY_FILE);
        if (!f.exists()) {
            System.out.println("No saved data found. Starting new library.");
            return new Library();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            Library lib = (Library) ois.readObject();
            lib.scanner = new Scanner(System.in);
            System.out.println("Loaded library data.");
            return lib;
        } catch (Exception e) {
            System.out.println("Failed to load saved data: " + e.getMessage());
            return new Library();
        }
    }
}
