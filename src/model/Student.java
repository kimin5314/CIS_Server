package model;

public class Student {
    private int studentId;
    private String studentNumber;
    private String studentName;
    private String gender;
    private String password;
    private String majorNumber;

    public Student() {
    }

    public Student(String studentName, String studentNumber, String gender, String password) {
        this.studentName = studentName;
        this.studentNumber = studentNumber;
        this.gender = gender;
        this.password = password;
    }

    public Student(int studentId, String studentName, String studentNumber, String gender, String password) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentNumber = studentNumber;
        this.gender = gender;
        this.password = password;
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


    @Override
    public String toString() {
        return "{" +
                "\"studentId\": " + studentId +
                ", \"studentName\": \"" + studentName + "\"" +
                ", \"studentNumber\": \"" + studentNumber + "\"" +
                ", \"gender\": \"" + gender + "\"" +
                ", \"password\": \"" + password + "\"" +
                '}';
    }

}
