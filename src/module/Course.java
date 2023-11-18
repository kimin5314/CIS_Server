package module;

public class Course {
    private int courseId;
    private String courseName;
    private String instructor;
    private int credits;
    private String description;
    private String courseNumber;

    public Course() {
    }

    public Course(String courseName, String courseNumber, String instructor, int credits, String description) {
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.instructor = instructor;
        this.credits = credits;
        this.description = description;
    }

    public Course(int courseId, String courseName, String courseNumber,  String instructor,int credits, String description) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.instructor = instructor;
        this.credits = credits;
        this.description = description;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "{" +
                "\"courseId\": " + courseId +
                ", \"courseName\": \"" + courseName + "\"" +
                ", \"courseNumber\": \"" + courseNumber + "\"" +
                ", \"instructor\": \"" + instructor + "\"" +
                ", \"credits\": " + credits +
                ", \"description\": \"" + description + "\"" +
                '}';
    }

}
