import java.util.List;
import java.util.Scanner;

public class StudentManagementApp {
    private StudentManagementSystem system;
    private Scanner scanner;
    
    public StudentManagementApp() {
        this.system = new StudentManagementSystem();
        this.scanner = new Scanner(System.in);
    }
    
    public void run() {
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getIntInput();
            switch (choice) {
                case 1:
                    studentMenu();
                    break;
                case 2:
                    courseMenu();
                    break;
                case 3:
                    enrollmentMenu();
                    break;
                case 4:
                    gradeMenu();
                    break;
                case 5:
                    system.displayStatistics();
                    break;
                case 0:
                    running = false;
                    System.out.println("Thank you for using Student Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
    
    private void displayMainMenu() {
        System.out.println("\n========== STUDENT MANAGEMENT SYSTEM ==========");
        System.out.println("1. Student Management");
        System.out.println("2. Course Management");
        System.out.println("3. Enrollment Management");
        System.out.println("4. Grade Management");
        System.out.println("5. View Statistics");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }
    
    // ==================== STUDENT MENU ====================
    private void studentMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n========== STUDENT MANAGEMENT ==========");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Update Student");
            System.out.println("4. View Student Details");
            System.out.println("5. Search Student by Name");
            System.out.println("6. Display All Students");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int choice = getIntInput();
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    viewStudentDetails();
                    break;
                case 5:
                    searchStudent();
                    break;
                case 6:
                    system.displayAllStudents();
                    break;
                case 0:
                    inMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = getIntInput();
        
        if (system.getStudent(id) != null) {
            System.out.println("Error: Student with ID " + id + " already exists.");
            return;
        }
        
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
        String dob = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Enrollment Date (YYYY-MM-DD): ");
        String enrollDate = scanner.nextLine();
        
        Student student = new Student(id, name, email, phone, dob, address, enrollDate);
        system.addStudent(student);
    }
    
    private void removeStudent() {
        System.out.print("Enter Student ID to remove: ");
        int id = getIntInput();
        system.removeStudent(id);
    }
    
    private void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        int id = getIntInput();
        
        Student student = system.getStudent(id);
        if (student == null) {
            System.out.println("Error: Student not found.");
            return;
        }
        
        System.out.print("Enter new Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter new Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter new Address: ");
        String address = scanner.nextLine();
        
        system.updateStudent(id, name, email, phone, address);
    }
    
    private void viewStudentDetails() {
        System.out.print("Enter Student ID: ");
        int id = getIntInput();
        
        Student student = system.getStudent(id);
        if (student != null) {
            System.out.println("\n" + student);
        } else {
            System.out.println("Error: Student not found.");
        }
    }
    
    private void searchStudent() {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();
        List<Student> results = system.searchStudentByName(name);
        
        if (results.isEmpty()) {
            System.out.println("No students found with that name.");
        } else {
            System.out.println("\n========== SEARCH RESULTS ==========");
            for (Student student : results) {
                System.out.println(student);
            }
        }
    }
    
    // ==================== COURSE MENU ====================
    private void courseMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n========== COURSE MANAGEMENT ==========");
            System.out.println("1. Add Course");
            System.out.println("2. Remove Course");
            System.out.println("3. View Course Details");
            System.out.println("4. Display All Courses");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int choice = getIntInput();
            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    removeCourse();
                    break;
                case 3:
                    viewCourseDetails();
                    break;
                case 4:
                    system.displayAllCourses();
                    break;
                case 0:
                    inMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private void addCourse() {
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine();
        
        if (system.getCourse(courseId) != null) {
            System.out.println("Error: Course with ID " + courseId + " already exists.");
            return;
        }
        
        System.out.print("Enter Course Name: ");
        String courseName = scanner.nextLine();
        System.out.print("Enter Instructor Name: ");
        String instructor = scanner.nextLine();
        System.out.print("Enter Credits: ");
        int credits = getIntInput();
        System.out.print("Enter Schedule (e.g., MWF 10:00-11:00): ");
        String schedule = scanner.nextLine();
        System.out.print("Enter Max Capacity: ");
        int capacity = getIntInput();
        
        Course course = new Course(courseId, courseName, instructor, credits, schedule, capacity);
        system.addCourse(course);
    }
    
    private void removeCourse() {
        System.out.print("Enter Course ID to remove: ");
        String courseId = scanner.nextLine();
        system.removeCourse(courseId);
    }
    
    private void viewCourseDetails() {
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine();
        
        Course course = system.getCourse(courseId);
        if (course != null) {
            System.out.println("\n" + course);
            System.out.println("Available Seats: " + course.getAvailableSeats());
        } else {
            System.out.println("Error: Course not found.");
        }
    }
    
    // ==================== ENROLLMENT MENU ====================
    private void enrollmentMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n========== ENROLLMENT MANAGEMENT ==========");
            System.out.println("1. Enroll Student in Course");
            System.out.println("2. Remove Student from Course");
            System.out.println("3. View Student's Courses");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int choice = getIntInput();
            switch (choice) {
                case 1:
                    enrollStudent();
                    break;
                case 2:
                    unenrollStudent();
                    break;
                case 3:
                    viewStudentCourses();
                    break;
                case 0:
                    inMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private void enrollStudent() {
        System.out.print("Enter Student ID: ");
        int studentId = getIntInput();
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine();
        system.enrollStudentInCourse(studentId, courseId);
    }
    
    private void unenrollStudent() {
        System.out.print("Enter Student ID: ");
        int studentId = getIntInput();
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine();
        system.removeStudentFromCourse(studentId, courseId);
    }
    
    private void viewStudentCourses() {
        System.out.print("Enter Student ID: ");
        int studentId = getIntInput();
        system.displayStudentCourses(studentId);
    }
    
    // ==================== GRADE MENU ====================
    private void gradeMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n========== GRADE MANAGEMENT ==========");
            System.out.println("1. Add Grade to Student");
            System.out.println("2. View Student Grades");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int choice = getIntInput();
            switch (choice) {
                case 1:
                    addGrade();
                    break;
                case 2:
                    viewGrades();
                    break;
                case 0:
                    inMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private void addGrade() {
        System.out.print("Enter Student ID: ");
        int studentId = getIntInput();
        System.out.print("Enter Grade (0-100): ");
        double grade = getDoubleInput();
        system.addGradeToStudent(studentId, grade);
    }
    
    private void viewGrades() {
        System.out.print("Enter Student ID: ");
        int studentId = getIntInput();
        system.displayStudentGrades(studentId);
    }
    
    // ==================== INPUT UTILITIES ====================
    private int getIntInput() {
        try {
            int value = Integer.parseInt(scanner.nextLine());
            return value;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return -1;
        }
    }
    
    private double getDoubleInput() {
        try {
            double value = Double.parseDouble(scanner.nextLine());
            return value;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return -1;
        }
    }
    
    public static void main(String[] args) {
        StudentManagementApp app = new StudentManagementApp();
        app.run();
    }
}
