package module;

import java.sql.Date;

public class Student {
    private int studentId;
    private String studentName;
    private String studentNumber;
    private Date admissionDate;
    private String gender;
    private String password;
    private boolean isOnline;

    public Student() {
    }

    public Student(String studentName, String studentNumber, Date admissionDate, String gender, String password) {
        this.studentName = studentName;
        this.studentNumber = studentNumber;
        this.admissionDate = admissionDate;
        this.gender = gender;
        this.password = password;
    }

    public Student(int studentId, String studentName, String studentNumber, Date admissionDate, String gender, String password, boolean isOnline) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentNumber = studentNumber;
        this.admissionDate = admissionDate;
        this.gender = gender;
        this.password = password;
        this.isOnline = isOnline;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    @Override
    public String toString() {
        return "{" +
                "\"studentId\": " + studentId +
                ", \"studentName\": \"" + studentName + "\"" +
                ", \"studentNumber\": \"" + studentNumber + "\"" +
                ", \"admissionDate\": \"" + admissionDate + "\"" +
                ", \"gender\": \"" + gender + "\"" +
                ", \"password\": \"" + password + "\"" +
                ", \"isOnline\": " + isOnline +
                '}';
    }

}
