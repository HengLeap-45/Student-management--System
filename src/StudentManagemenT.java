import java.io.*;
import java.util.*;

public class StudentManagemenT {
    private Map<Integer, Student> students;
    private Map<String, Course> courses;
    private final String STUDENTS_FILE = "students.dat";
    private final String COURSES_FILE = "courses.dat";
    
    // Constructor
    public StudentManagemenT() {
        this.students = new HashMap<>();
        this.courses = new HashMap<>();
        loadData();
    }
    
    // ==================== STUDENT MANAGEMENT ====================
    
    public void addStudent(Student student) {
        if (students.containsKey(student.getStudentId())) {
            System.out.println("Error: Student with ID " + student.getStudentId() + " already exists.");
            return;
        }
        students.put(student.getStudentId(), student);
        System.out.println("Student added successfully: " + student.getName());
        saveData();
    }
    
    public void removeStudent(int studentId) {
        if (students.remove(studentId) != null) {
            System.out.println("Student removed successfully.");
            saveData();
        } else {
            System.out.println("Error: Student with ID " + studentId + " not found.");
        }
    }
    
    public Student getStudent(int studentId) {
        return students.get(studentId);
    }
    
    public void updateStudent(int studentId, String name, String email, String phone, String address) {
        Student student = students.get(studentId);
        if (student != null) {
            student.setName(name);
            student.setEmail(email);
            student.setPhone(phone);
            student.setAddress(address);
            System.out.println("Student information updated successfully.");
            saveData();
        } else {
            System.out.println("Error: Student with ID " + studentId + " not found.");
        }
    }
    
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("\n========== ALL STUDENTS ==========");
        for (Student student : students.values()) {
            System.out.println(student);
        }
    }
    
    public List<Student> searchStudentByName(String name) {
        List<Student> result = new ArrayList<>();
        for (Student student : students.values()) {
            if (student.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(student);
            }
        }
        return result;
    }
    
    // ==================== COURSE MANAGEMENT ====================
    
    public void addCourse(Course course) {
        if (courses.containsKey(course.getCourseId())) {
            System.out.println("Error: Course with ID " + course.getCourseId() + " already exists.");
            return;
        }
        courses.put(course.getCourseId(), course);
        System.out.println("Course added successfully: " + course.getCourseName());
        saveData();
    }
    
    public void removeCourse(String courseId) {
        if (courses.remove(courseId) != null) {
            System.out.println("Course removed successfully.");
            saveData();
        } else {
            System.out.println("Error: Course with ID " + courseId + " not found.");
        }
    }
    
    public Course getCourse(String courseId) {
        return courses.get(courseId);
    }
    
    public void displayAllCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        System.out.println("\n========== ALL COURSES ==========");
        for (Course course : courses.values()) {
            System.out.println(course);
        }
    }
    
    // ==================== ENROLLMENT MANAGEMENT ====================
    
    public void enrollStudentInCourse(int studentId, String courseId) {
        Student student = students.get(studentId);
        Course course = courses.get(courseId);
        
        if (student == null) {
            System.out.println("Error: Student with ID " + studentId + " not found.");
            return;
        }
        if (course == null) {
            System.out.println("Error: Course with ID " + courseId + " not found.");
            return;
        }
        if (course.isFull()) {
            System.out.println("Error: Course " + course.getCourseName() + " is full.");
            return;
        }
        
        student.enrollCourse(course);
        course.enrollStudent();
        System.out.println("Student " + student.getName() + " enrolled in " + course.getCourseName());
        saveData();
    }
    
    public void removeStudentFromCourse(int studentId, String courseId) {
        Student student = students.get(studentId);
        Course course = courses.get(courseId);
        
        if (student == null || course == null) {
            System.out.println("Error: Student or Course not found.");
            return;
        }
        
        if (student.getCourses().remove(course)) {
            course.removeStudent();
            System.out.println("Student " + student.getName() + " removed from " + course.getCourseName());
            saveData();
        } else {
            System.out.println("Error: Student is not enrolled in this course.");
        }
    }
    
    public void displayStudentCourses(int studentId) {
        Student student = students.get(studentId);
        if (student == null) {
            System.out.println("Error: Student with ID " + studentId + " not found.");
            return;
        }
        
        List<Course> enrolledCourses = student.getCourses();
        if (enrolledCourses.isEmpty()) {
            System.out.println(student.getName() + " is not enrolled in any course.");
            return;
        }
        
        System.out.println("\n========== COURSES FOR " + student.getName() + " ==========");
        for (Course course : enrolledCourses) {
            System.out.println(course);
        }
    }
    
    // ==================== GRADE MANAGEMENT ====================
    
    public void addGradeToStudent(int studentId, double grade) {
        Student student = students.get(studentId);
        if (student != null) {
            student.addGrade(grade);
            System.out.println("Grade added to " + student.getName());
            saveData();
        } else {
            System.out.println("Error: Student with ID " + studentId + " not found.");
        }
    }
    
    public void displayStudentGrades(int studentId) {
        Student student = students.get(studentId);
        if (student == null) {
            System.out.println("Error: Student with ID " + studentId + " not found.");
            return;
        }
        
        List<Double> grades = student.getGrades();
        if (grades.isEmpty()) {
            System.out.println(student.getName() + " has no grades yet.");
            return;
        }
        
        System.out.println("\n========== GRADES FOR " + student.getName() + " ==========");
        for (int i = 0; i < grades.size(); i++) {
            System.out.println("Grade " + (i + 1) + ": " + grades.get(i));
        }
        System.out.println("GPA: " + String.format("%.2f", student.calculateGPA()));
        System.out.println("Grade Point: " + student.getGradePoint());
    }
    
    // ==================== DATA PERSISTENCE ====================
    
    @SuppressWarnings("unchecked")
    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(STUDENTS_FILE))) {
            students = (Map<Integer, Student>) ois.readObject();
            System.out.println("Students data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous student data found. Starting with empty database.");
            students = new HashMap<>();
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(COURSES_FILE))) {
            courses = (Map<String, Course>) ois.readObject();
            System.out.println("Courses data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous course data found. Starting with empty database.");
            courses = new HashMap<>();
        }
    }
    
    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STUDENTS_FILE))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving student data: " + e.getMessage());
        }
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(COURSES_FILE))) {
            oos.writeObject(courses);
        } catch (IOException e) {
            System.out.println("Error saving course data: " + e.getMessage());
        }
    }
    
    // ==================== STATISTICS ====================
    
    public void displayStatistics() {
        System.out.println("\n========== SYSTEM STATISTICS ==========");
        System.out.println("Total Students: " + students.size());
        System.out.println("Total Courses: " + courses.size());
        
        int totalEnrollments = 0;
        for (Course course : courses.values()) {
            totalEnrollments += course.getCurrentEnrollment();
        }
        System.out.println("Total Enrollments: " + totalEnrollments);
    }
    
    // Getters for testing
    public Map<Integer, Student> getStudents() {
        return students;
    }
    
    public Map<String, Course> getCourses() {
        return courses;
    }
}
