package dao;

import module.Student;

import util.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class StudentDao {
    public static void insertStudent(Student s) throws Exception {
        Connection conn = null;
        try {
            conn = ConnectionPool.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO student (studentName, studentNumber, admissionDate, password, gender) VALUE (?, ?, ?, ?, ?)");
            stmt.setString(1, s.getStudentName());
            stmt.setString(2, s.getStudentNumber());
            stmt.setDate(3, s.getAdmissionDate());
            stmt.setString(4, s.getPassword());
            stmt.setString(5, s.getGender());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            ConnectionPool.returnConnection(conn);
        }
    }

    public static void deleteStudent(String studentNumber) throws Exception {
        Connection conn = null;
        try {
            conn = ConnectionPool.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM student WHERE studentNumber = ?");
            stmt.setString(1, studentNumber);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            ConnectionPool.returnConnection(conn);
        }
    }

    public static void deleteStudent(int studentId) throws Exception {
        Connection conn = null;
        try {
            conn = ConnectionPool.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM student WHERE studentId = ?");
            stmt.setInt(1, studentId);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            ConnectionPool.returnConnection(conn);
        }
    }

    public static void updateStudent(Student s) throws Exception {
        Connection conn = null;
        try {
            conn = ConnectionPool.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE student SET studentName = ?, admissionDate = ?, gender = ?, password = ?, isOnline = ? where studentId = ?");
            stmt.setString(1, s.getStudentName());
            stmt.setDate(2, s.getAdmissionDate());
            stmt.setString(3, s.getGender());
            stmt.setString(4, s.getPassword());
            stmt.setBoolean(5, s.isOnline());
            stmt.setInt(6, s.getStudentId());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            ConnectionPool.returnConnection(conn);
        }
    }

    public static Student selectStudent(int studentId) throws Exception {
        Connection conn = null;
        try {
            conn = ConnectionPool.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM student WHERE studentId = ?");
            stmt.setInt(1, studentId);
            Student rs = queryStudent(conn, stmt);
            if (rs != null) {
                return rs;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            ConnectionPool.returnConnection(conn);
        }
        return null;
    }

    public static Student selectStudent(String studentNumber) throws Exception {
        Connection conn = null;
        try {
            conn = ConnectionPool.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM student WHERE studentNumber = ?");
            stmt.setString(1, studentNumber);
            Student rs = queryStudent(conn, stmt);
            if (rs != null) {
                return rs;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            ConnectionPool.returnConnection(conn);
        }
        return null;
    }

    private static Student queryStudent(Connection conn, PreparedStatement stmt) throws Exception {
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Student(
                    rs.getInt("studentId"),
                    rs.getString("studentName"),
                    rs.getString("studentNumber"),
                    rs.getDate("admissionDate"),
                    rs.getString("gender"),
                    rs.getString("password"),
                    rs.getBoolean("isOnline"));
        }
        return null;
    }

    public static Vector<Student> selectAllStudents() throws Exception {
        try {
            Vector<Student> students = new Vector<>();
            Connection conn = ConnectionPool.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM student");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("studentId"),
                        rs.getString("studentName"),
                        rs.getString("studentNumber"),
                        rs.getDate("admissionDate"),
                        rs.getString("gender"),
                        rs.getString("password"),
                        rs.getBoolean("isOnline")
                ));
            }
            ConnectionPool.returnConnection(conn);
            return students;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
