import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


class Student implements Serializable {
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
        if (!courses.contains(course)) {
            courses.add(course);
        }
    }
    
    public void addGrade(double grade) {
        if (grade >= 0 && grade <= 100) {
            grades.add(grade);
        } else {
            System.out.println("Invalid grade. Grade must be between 0 and 100.");
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
}
