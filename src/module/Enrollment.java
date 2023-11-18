package module;

import java.sql.Date;

public class Enrollment {
    private int enrollmentId;
    private final int studentId;
    private final int courseId;
    private String grade;
    private final Date enrollmentDate;

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public Enrollment(int studentId, int courseId, Date enrollmentDate) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
    }

    public Enrollment(int studentId, int courseId, String grade, Date enrollmentDate) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
        this.enrollmentDate = enrollmentDate;
    }

    public Enrollment(int enrollmentId, int studentId, int courseId, String grade, Date enrollmentDate) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
        this.enrollmentDate = enrollmentDate;
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

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    @Override
    public String toString() {
        return "{" +
                "\"enrollmentId\": " + enrollmentId +
                ", \"studentId\": " + studentId +
                ", \"courseId\": " + courseId +
                ", \"grade\": \"" + grade + "\"" +
                ", \"enrollmentDate\": \"" + enrollmentDate + "\"" +
                '}';
    }
}
