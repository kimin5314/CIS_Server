package dao;

import module.Enrollment;
import util.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class EnrollmentDao {
    public static void insertEnrollment(Enrollment em) throws Exception {
        Connection conn = null;
        try {
            conn = ConnectionPool.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO enrollment (studentId, courseId, enrollmentDate) VALUES (?, ?, ?)");
            stmt.setInt(1, em.getStudentId());
            stmt.setInt(2, em.getCourseId());
            stmt.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            ConnectionPool.returnConnection(conn);
        }
    }

    public static void deleteEnrollment(int studentId, int courseId) throws Exception {
        Connection conn = null;
        try {
            conn = ConnectionPool.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM enrollment WHERE studentId = ? AND courseId = ?");
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            ConnectionPool.returnConnection(conn);
        }
    }

    public static void deleteEnrollment(int enrollmentId) throws Exception {
        Connection conn = null;
        try {
            conn = ConnectionPool.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM enrollment WHERE enrollmentId = ?");
            stmt.setInt(1, enrollmentId);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            ConnectionPool.returnConnection(conn);
        }
    }

    public static void updateEnrollment(Enrollment em) throws Exception {
        Connection conn = null;
        try {
            conn = ConnectionPool.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE enrollment SET grade = ? WHERE studentId = ? AND courseId = ?");
            stmt.setString(1, em.getGrade());
            stmt.setInt(2, em.getStudentId());
            stmt.setInt(3, em.getCourseId());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            ConnectionPool.returnConnection(conn);
        }
    }

    public static Vector<Enrollment> selectEnrollmentList(int id, String column) throws Exception {
        Connection conn = null;
        try {
            conn = ConnectionPool.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM enrollment WHERE ? = ?");
            stmt.setInt(2, id);
            stmt.setString(1, column);
            ResultSet rs = stmt.executeQuery();
            Vector<Enrollment> enrollments = new Vector<>();
            while (rs.next()) {
                enrollments.add(new Enrollment(
                        rs.getInt("studentId"),
                        rs.getInt("courseId"),
                        rs.getDate("enrollmentDate")));
            }
            return enrollments;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            ConnectionPool.returnConnection(conn);
        }
    }

    public static Enrollment selectEnrollment(int studentId, int courseId) throws Exception {
        Connection conn = null;
        try {
            conn = ConnectionPool.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM enrollment WHERE studentId = ? AND courseId = ?");
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Enrollment(
                        rs.getInt("studentId"),
                        rs.getInt("courseId"),
                        rs.getDate("enrollmentDate"));
            }
            return null;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            ConnectionPool.returnConnection(conn);
        }
    }

    public static Vector<Enrollment> selectAllEnrollments() throws Exception {
        Connection conn = null;
        try {
            conn = ConnectionPool.getConnection();
            Vector<Enrollment> enrollments = new Vector<>();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM enrollment");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                enrollments.add(new Enrollment(
                        rs.getInt("enrollmentId"),
                        rs.getInt("studentId"),
                        rs.getInt("courseId"),
                        rs.getString("grade"),
                        rs.getDate("enrollmentDate")));
            }
            return enrollments;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            ConnectionPool.returnConnection(conn);
        }
    }
}


