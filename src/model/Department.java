package model;

public class Department {
    private int departmentId;
    private String departmentNumber;
    private String departmentName;
    public Department(){

    }
    public Department(String departmentNumber,String departmentName)
    {
        this.departmentNumber=departmentNumber;
        this.departmentName=departmentName;
    }
    public Department(int departmentId,String departmentNumber,String departmentName)
    {
        this.departmentId=departmentId;
        this.departmentNumber=departmentNumber;
        this.departmentName=departmentName;
    }

    public int getDepartmentId()
    {
        return departmentId;
    }
    public void setDepartmentId(int departmentId)
    {
        this.departmentId=departmentId;
    }
    public String getDepartmentNumber()
    {
        return departmentNumber;
    }
    public void setDepartmentNumber(String departmentNumber)
    {
        this.departmentNumber=departmentNumber;
    }
    public String getDepartmentName()
    {
        return departmentName;
    }
    public void setDepartmentName(String departmentName)
    {
        this.departmentName=departmentName;
    }
    public String toString() {
        return "{" +
                "\"departmentId\": " + departmentId +
                ", \"departmentNumber\": \"" + departmentNumber + "\"" +
                ", \"departmentName\": \"" + departmentName + "\"" +
                '}';
    }



}
