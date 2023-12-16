package model;

public class Major {
    private int majorId;//用来查询
    private String majorNumber;//专业号
    private String majorName;//专业名
    private String departmentNumber;//系号


    public Major() {
    }
    public Major(String majorNumber,String majorName,String departmentNumber) {
        this.majorNumber=majorNumber;
        this.majorName=majorName;
        this.departmentNumber=departmentNumber;
    }
    public Major(int majorId,String majorNumber,String majorName,String departmentNumber) {
        this.majorId=majorId;
        this.majorNumber=majorNumber;
        this.majorName=majorName;
        this.departmentNumber=departmentNumber;
    }
    public int getMajorId()
    {
        return majorId;
    }
    public void setMajorId(int majorID)
    {
        this.majorId=majorId;
    }
    public String getMajorNumber()
    {
        return majorNumber;
    }
    public void setMajorNumber(String majorNumber)
    {
        this.majorNumber=majorNumber;
    }
    public String getMajorName()
    {
        return majorName;
    }
    public void setMajorName(String majorName)
    {
        this.majorName=majorName;
    }
    public String getDepartmentNumber()
    {
        return departmentNumber;
    }
    public void setDepartmentNumber(String departmentNumber)
    {
        this.departmentNumber=departmentNumber;
    }

    @Override
    public String toString() {
        return "{" +
                "\"majorId\": " + majorId +
                ", \"studentName\": \"" + majorName + "\"" +
                ", \"majorNumber\": \"" + majorNumber + "\"" +
                ", \"departmentNumber\": \"" + departmentNumber + "\"" +
                '}';
    }
}
