package model;
//课堂
public class Classroom {
    private int classroomId;
    private String classroomTime;
    private String classroomPlace;
    private int classroomStudentNumber;
    private String classroomTeacher;
    private int courseId;
    public Classroom(){

    }
    public Classroom(int classroomId,
                     String classroomTime,
                     String classroomPlace,
                     int classroomStudentNumber,
                     String classroomTeacher,
                     int courseId)
    {
        this.classroomId=classroomId;
        this.classroomTime=classroomTime;
        this.classroomPlace=classroomPlace;
        this.classroomStudentNumber=classroomStudentNumber;
        this.classroomTeacher=classroomTeacher;
        this.courseId=courseId;
    }

    public Classroom(String classroomTime,
                     String classroomPlace,
                     int classroomStudentNumber,
                     String classroomTeacher,
                     int courseId)
    {
        this.classroomTime=classroomTime;
        this.classroomPlace=classroomPlace;
        this.classroomStudentNumber=classroomStudentNumber;
        this.classroomTeacher=classroomTeacher;
        this.courseId=courseId;
    }
}
