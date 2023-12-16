package model;
//课程
public class Course {
    private int courseId;//用来查询
    private String courseNumber;//课程号
    private String courseName;
    private int departmentId;
    private int credits;
    private String category;

    public Course() {
    }

    public Course(String courseNumber,String courseName,int departmentId,int credits,String category) {
        this.courseName = courseNumber;
        this.courseNumber = courseName;
        this.departmentId=departmentId;
        this.credits = credits;
        this.category=category;
    }

    public Course(int courseId, String courseNumber, String courseName,int departmentId,  int credits,String category) {
        this.courseId = courseId;
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.departmentId=departmentId;
        this.credits = credits;
        this.category=category;
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

    public int getDepartmentId()
    {
        return departmentId;
    }
    public void setDepartmentId(int departmentId)
    {
        this.departmentId=departmentId;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getCategory()
    {
        return category;
    }
    public void setCategory(String category)
    {
        this.category=category;
    }


    @Override
    public String toString() {
        return "{" +
                "\"courseId\": " + courseId +
                ", \"courseNumber\": \"" + courseNumber + "\"" +
                ", \"courseName\": \"" + courseName + "\"" +
                ", \"departmentId\": \"" + departmentId + "\"" +
                ", \"credits\": " + credits +
                ", \"category\": \"" + category + "\"" +
                '}';
    }

}
