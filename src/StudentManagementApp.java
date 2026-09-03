import java.util.List;
import java.util.Scanner;

public class StudentManagementApp {
    private StudentManagemenT system;
    private Scanner scanner;
    
    public StudentManagementApp() {
        this.system = new StudentManagemenT();
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
                    System.out.println("\nThank you for using the Student Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose an option from the menu.");
            }
        }
        scanner.close();
    }
    
    private void displayMainMenu() {
        System.out.println("\n==============================================");
        System.out.println("          STUDENT MANAGEMENT SYSTEM           ");
        System.out.println("==============================================");
        System.out.println("  1. Student Management");
        System.out.println("  2. Course Management");
        System.out.println("  3. Enrollment Management");
        System.out.println("  4. Grade Management");
        System.out.println("  5. View System Statistics");
        System.out.println("  0. Exit");
        System.out.println("==============================================");
        System.out.print("Enter your choice: ");
    }
    
    // ==================== STUDENT MENU ====================
    private void studentMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n----------------------------------------------");
            System.out.println("              STUDENT MANAGEMENT              ");
            System.out.println("----------------------------------------------");
            System.out.println("  1. Add Student");
            System.out.println("  2. Remove Student");
            System.out.println("  3. Update Student Information");
            System.out.println("  4. View Student Details");
            System.out.println("  5. Search Student by Name");
            System.out.println("  6. Display All Students");
            System.out.println("  0. Back to Main Menu");
            System.out.println("----------------------------------------------");
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
        System.out.println("\n--- Add New Student ---");
        System.out.print("Enter Student ID (numbers only): ");
        int id = getIntInput();
        if (id < 0) return;
        
        if (system.getStudent(id) != null) {
            System.out.println("Error: Student with ID " + id + " already exists.");
            return;
        }
        
        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine().trim();
        System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
        String dob = scanner.nextLine().trim();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine().trim();
        System.out.print("Enter Enrollment Date (YYYY-MM-DD): ");
        String enrollDate = scanner.nextLine().trim();
        
        Student student = new Student(id, name, email, phone, dob, address, enrollDate);
        system.addStudent(student);
    }
    
    private void removeStudent() {
        System.out.println("\n--- Remove Student ---");
        System.out.print("Enter Student ID to remove: ");
        int id = getIntInput();
        if (id < 0) return;
        system.removeStudent(id);
    }
    
    private void updateStudent() {
        System.out.println("\n--- Update Student Information ---");
        System.out.print("Enter Student ID to update: ");
        int id = getIntInput();
        if (id < 0) return;
        
        Student existing = system.getStudent(id);
        if (existing == null) {
            System.out.println("Error: Student with ID " + id + " not found.");
            return;
        }
        
        System.out.println("Current Info: " + existing);
        System.out.print("Enter new Name (or press Enter to keep '" + existing.getName() + "'): ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) name = existing.getName();
        
        System.out.print("Enter new Email (or press Enter to keep '" + existing.getEmail() + "'): ");
        String email = scanner.nextLine().trim();
        if (email.isEmpty()) email = existing.getEmail();
        
        System.out.print("Enter new Phone (or press Enter to keep '" + existing.getPhone() + "'): ");
        String phone = scanner.nextLine().trim();
        if (phone.isEmpty()) phone = existing.getPhone();
        
        System.out.print("Enter new Address (or press Enter to keep '" + existing.getAddress() + "'): ");
        String address = scanner.nextLine().trim();
        if (address.isEmpty()) address = existing.getAddress();
        
        system.updateStudent(id, name, email, phone, address);
    }
    
    private void viewStudentDetails() {
        System.out.print("Enter Student ID: ");
        int id = getIntInput();
        if (id < 0) return;
        
        Student student = system.getStudent(id);
        if (student != null) {
            System.out.println("\n========== STUDENT DETAILS ==========");
            System.out.println("ID:              " + student.getStudentId());
            System.out.println("Name:            " + student.getName());
            System.out.println("Email:           " + student.getEmail());
            System.out.println("Phone:           " + student.getPhone());
            System.out.println("Date of Birth:   " + student.getDateOfBirth());
            System.out.println("Address:         " + student.getAddress());
            System.out.println("Enrollment Date: " + student.getEnrollmentDate());
            System.out.println("Enrolled Courses:" + student.getCourses().size());
            System.out.println("GPA:             " + String.format("%.2f", student.calculateGPA()));
            System.out.println("Grade Point:     " + student.getGradePoint());
        } else {
            System.out.println("Error: Student with ID " + id + " not found.");
        }
    }
    
    private void searchStudent() {
        System.out.print("Enter student name to search: ");
        String name = scanner.nextLine().trim();
        List<Student> results = system.searchStudentByName(name);
        
        if (results.isEmpty()) {
            System.out.println("No students found matching '" + name + "'.");
        } else {
            System.out.println("\n========== SEARCH RESULTS (" + results.size() + " found) ==========");
            for (Student student : results) {
                System.out.println(student);
            }
        }
    }
    
    // ==================== COURSE MENU ====================
    private void courseMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n----------------------------------------------");
            System.out.println("              COURSE MANAGEMENT               ");
            System.out.println("----------------------------------------------");
            System.out.println("  1. Add Course");
            System.out.println("  2. Remove Course");
            System.out.println("  3. View Course Details");
            System.out.println("  4. Display All Courses");
            System.out.println("  0. Back to Main Menu");
            System.out.println("----------------------------------------------");
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
        System.out.println("\n--- Add New Course ---");
        System.out.print("Enter Course ID (e.g., CS101): ");
        String courseId = scanner.nextLine().trim();
        if (courseId.isEmpty()) {
            System.out.println("Error: Course ID cannot be empty.");
            return;
        }
        
        if (system.getCourse(courseId) != null) {
            System.out.println("Error: Course with ID " + courseId + " already exists.");
            return;
        }
        
        System.out.print("Enter Course Name: ");
        String courseName = scanner.nextLine().trim();
        System.out.print("Enter Instructor Name: ");
        String instructor = scanner.nextLine().trim();
        System.out.print("Enter Credits: ");
        int credits = getIntInput();
        if (credits < 0) return;
        
        System.out.print("Enter Schedule (e.g., MWF 10:00-11:00 AM): ");
        String schedule = scanner.nextLine().trim();
        System.out.print("Enter Max Capacity: ");
        int capacity = getIntInput();
        if (capacity <= 0) {
            System.out.println("Error: Capacity must be greater than 0.");
            return;
        }
        
        Course course = new Course(courseId, courseName, instructor, credits, schedule, capacity);
        system.addCourse(course);
    }
    
    private void removeCourse() {
        System.out.println("\n--- Remove Course ---");
        System.out.print("Enter Course ID to remove: ");
        String courseId = scanner.nextLine().trim();
        system.removeCourse(courseId);
    }
    
    private void viewCourseDetails() {
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine().trim();
        
        Course course = system.getCourse(courseId);
        if (course != null) {
            System.out.println("\n========== COURSE DETAILS ==========");
            System.out.println("ID:                 " + course.getCourseId());
            System.out.println("Name:               " + course.getCourseName());
            System.out.println("Instructor:         " + course.getInstructor());
            System.out.println("Credits:            " + course.getCredits());
            System.out.println("Schedule:           " + course.getSchedule());
            System.out.println("Current Enrollment: " + course.getCurrentEnrollment() + " / " + course.getMaxCapacity());
            System.out.println("Available Seats:    " + course.getAvailableSeats());
        } else {
            System.out.println("Error: Course with ID " + courseId + " not found.");
        }
    }
    
    // ==================== ENROLLMENT MENU ====================
    private void enrollmentMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n----------------------------------------------");
            System.out.println("            ENROLLMENT MANAGEMENT             ");
            System.out.println("----------------------------------------------");
            System.out.println("  1. Enroll Student in Course");
            System.out.println("  2. Remove Student from Course");
            System.out.println("  3. View Student's Enrolled Courses");
            System.out.println("  0. Back to Main Menu");
            System.out.println("----------------------------------------------");
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
        System.out.println("\n--- Enroll Student in Course ---");
        System.out.print("Enter Student ID: ");
        int studentId = getIntInput();
        if (studentId < 0) return;
        
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine().trim();
        system.enrollStudentInCourse(studentId, courseId);
    }
    
    private void unenrollStudent() {
        System.out.println("\n--- Remove Student from Course ---");
        System.out.print("Enter Student ID: ");
        int studentId = getIntInput();
        if (studentId < 0) return;
        
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine().trim();
        system.removeStudentFromCourse(studentId, courseId);
    }
    
    private void viewStudentCourses() {
        System.out.print("Enter Student ID: ");
        int studentId = getIntInput();
        if (studentId < 0) return;
        system.displayStudentCourses(studentId);
    }
    
    // ==================== GRADE MANAGEMENT ====================
    private void gradeMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n----------------------------------------------");
            System.out.println("               GRADE MANAGEMENT               ");
            System.out.println("----------------------------------------------");
            System.out.println("  1. Add Grade to Student");
            System.out.println("  2. View Student Grades & GPA");
            System.out.println("  0. Back to Main Menu");
            System.out.println("----------------------------------------------");
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
        System.out.println("\n--- Add Grade to Student ---");
        System.out.print("Enter Student ID: ");
        int studentId = getIntInput();
        if (studentId < 0) return;
        
        System.out.print("Enter Grade (0 - 100): ");
        double grade = getDoubleInput();
        if (grade < 0) return;
        
        system.addGradeToStudent(studentId, grade);
    }
    
    private void viewGrades() {
        System.out.print("Enter Student ID: ");
        int studentId = getIntInput();
        if (studentId < 0) return;
        system.displayStudentGrades(studentId);
    }
    
    // ==================== INPUT UTILITIES ====================
    private int getIntInput() {
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid integer: ");
            }
        }
    }
    
    private double getDoubleInput() {
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }
    
    public static void main(String[] args) {
        StudentManagementApp app = new StudentManagementApp();
        app.run();
    }
}
