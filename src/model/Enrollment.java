package model;

//选课表
public class Enrollment {
    private int enrollmentId;
    private final int studentId;
    private final int courseId;
    private String grade;


    public Enrollment(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Enrollment(int studentId, int courseId, String grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
    }

    public Enrollment(int enrollmentId, int studentId, int courseId, String grade) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
    }


    public int getEnrollmentId() {
        return enrollmentId;
    }
    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


    @Override
    public String toString() {
        return "{" +
                "\"enrollmentId\": " + enrollmentId +
                ", \"studentId\": " + studentId +
                ", \"courseId\": " + courseId +
                ", \"grade\": \"" + grade + "\"" +
                '}';
    }
}
