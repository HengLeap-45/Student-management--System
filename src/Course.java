import java.io.Serializable;
import java.util.Objects;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    private String courseId;
    private String courseName;
    private String instructor;
    private int credits;
    private String schedule;
    private int maxCapacity;
    private int currentEnrollment;
    
    // Constructor
    public Course(String courseId, String courseName, String instructor,
                  int credits, String schedule, int maxCapacity) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.instructor = instructor;
        this.credits = credits;
        this.schedule = schedule;
        this.maxCapacity = maxCapacity;
        this.currentEnrollment = 0;
    }
    
    // Getters and Setters
    public String getCourseId() {
        return courseId;
    }
    
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public String getInstructor() {
        return instructor;
    }
    
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
    
    public int getCredits() {
        return credits;
    }
    
    public void setCredits(int credits) {
        this.credits = credits;
    }
    
    public String getSchedule() {
        return schedule;
    }
    
    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
    
    public int getMaxCapacity() {
        return maxCapacity;
    }
    
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
    
    public int getCurrentEnrollment() {
        return currentEnrollment;
    }
    
    // Methods
    public boolean enrollStudent() {
        if (currentEnrollment < maxCapacity) {
            currentEnrollment++;
            return true;
        }
        return false;
    }
    
    public boolean removeStudent() {
        if (currentEnrollment > 0) {
            currentEnrollment--;
            return true;
        }
        return false;
    }
    
    public int getAvailableSeats() {
        return maxCapacity - currentEnrollment;
    }
    
    public boolean isFull() {
        return currentEnrollment >= maxCapacity;
    }
    
    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", instructor='" + instructor + '\'' +
                ", credits=" + credits +
                ", schedule='" + schedule + '\'' +
                ", enrollment=" + currentEnrollment + "/" + maxCapacity +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseId, course.courseId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(courseId);
    }
}
