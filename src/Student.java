import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private int studentId;
    private String name;
    private String email;
    private String phone;
    private String dateOfBirth;
    private String address;
    private String enrollmentDate;
    private List<Course> courses;
    private List<Double> grades;
    
    // Constructor
    public Student(int studentId, String name, String email, String phone,
                   String dateOfBirth, String address, String enrollmentDate) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.enrollmentDate = enrollmentDate;
        this.courses = new ArrayList<>();
        this.grades = new ArrayList<>();
    }
    
    // Getters and Setters
    public int getStudentId() {
        return studentId;
    }
    
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getEnrollmentDate() {
        return enrollmentDate;
    }
    
    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
    
    public List<Course> getCourses() {
        return courses;
    }
    
    public List<Double> getGrades() {
        return grades;
    }
    
    // Methods
    public void enrollCourse(Course course) {
        if (course != null && !courses.contains(course)) {
            courses.add(course);
        }
    }
    
    public boolean addGrade(double grade) {
        if (grade >= 0 && grade <= 100) {
            grades.add(grade);
            return true;
        } else {
            System.out.println("Invalid grade. Grade must be between 0 and 100.");
            return false;
        }
    }
    
    public double calculateGPA() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }
    
    public String getGradePoint() {
        double gpa = calculateGPA();
        if (gpa >= 90) return "A";
        else if (gpa >= 80) return "B";
        else if (gpa >= 70) return "C";
        else if (gpa >= 60) return "D";
        else return "F";
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", address='" + address + '\'' +
                ", enrollmentDate='" + enrollmentDate + '\'' +
                ", courses=" + courses.size() +
                ", GPA=" + String.format("%.2f", calculateGPA()) +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentId == student.studentId;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
    
    // Main method for demonstration and testing
    public static void main(String[] args) {
        System.out.println("=== STUDENT MANAGEMENT SYSTEM - STUDENT DEMO ===");
        
        // 1. Create a Student
        Student student = new Student(
                101,
                "Alice Johnson",
                "alice.johnson@example.com",
                "+1-555-0199",
                "2002-05-15",
                "123 University Ave, Cityville",
                "2024-09-01"
        );
        
        System.out.println("\nCreated Student:");
        System.out.println(student);
        
        // 2. Create Courses and Enroll
        Course cs101 = new Course("CS101", "Introduction to Computer Science", "Dr. Smith", 3, "Mon/Wed 10:00 AM", 30);
        Course math201 = new Course("MATH201", "Calculus I", "Dr. Davis", 4, "Tue/Thu 2:00 PM", 25);
        
        student.enrollCourse(cs101);
        student.enrollCourse(math201);
        
        System.out.println("\nEnrolled Courses (" + student.getCourses().size() + "):");
        for (Course course : student.getCourses()) {
            System.out.println(" - " + course.getCourseName() + " (" + course.getCourseId() + ") by " + course.getInstructor());
        }
        
        // 3. Add Grades
        student.addGrade(95.0);
        student.addGrade(88.5);
        student.addGrade(91.0);
        
        System.out.println("\nGrades:");
        List<Double> grades = student.getGrades();
        for (int i = 0; i < grades.size(); i++) {
            System.out.println(" - Grade " + (i + 1) + ": " + grades.get(i));
        }
        
        // 4. Calculate GPA and Grade Point
        System.out.println("\nAcademic Standing:");
        System.out.println(" - GPA: " + String.format("%.2f", student.calculateGPA()));
        System.out.println(" - Grade Letter: " + student.getGradePoint());
        
        System.out.println("\nUpdated Student Summary:");
        System.out.println(student);
    }
}
