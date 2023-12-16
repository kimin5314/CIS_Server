package dao;

import model.Course;
import util.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class CourseDao {
    public static void insertCourse(Course c) throws Exception {
        Connection conn = null;
        try {
            conn = ConnectionPool.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO course (courseName, courseNumber, credits, instructor, description) VALUE (?, ?, ?, ?, ?)");
            stmt.setString(1, c.getCourseName());
            stmt.setString(2, c.getCourseNumber());
            stmt.setInt(3, c.getCredits());
            stmt.setString(4, c.getInstructor());
            stmt.setString(5, c.getDescription());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            ConnectionPool.returnConnection(conn);
        }
    }

    public static void deleteCourse(String courseNumber) throws Exception {
        Connection conn = null;
        try {
            conn = ConnectionPool.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM course WHERE courseNumber = ?");
            stmt.setString(1, courseNumber);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            ConnectionPool.returnConnection(conn);
        }
    }

    public static void deleteCourse(int courseId) throws Exception {
        Connection conn = null;
        try {
            conn = ConnectionPool.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM course WHERE courseId = ?");
            stmt.setInt(1, courseId);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            ConnectionPool.returnConnection(conn);
        }
    }

    public static void updateCourse(Course c) throws Exception {
        Connection conn = null;
        try {
            conn = ConnectionPool.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE course SET courseName = ?, courseNumber = ?, credits = ?, instructor = ?, description = ? WHERE courseId = ?");
            stmt.setString(1, c.getCourseName());
            stmt.setString(2, c.getCourseNumber());
            stmt.setInt(3, c.getCredits());
            stmt.setString(4, c.getInstructor());
            stmt.setString(5, c.getDescription());
            stmt.setInt(6, c.getCourseId());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            ConnectionPool.returnConnection(conn);
        }
    }

    public static Course selectCourse(int courseId) throws Exception {
        Connection conn = null;
        try {
            conn = ConnectionPool.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM course WHERE courseId = ?");
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Course(
                        rs.getInt("courseId"),
                        rs.getString("courseName"),
                        rs.getString("courseNumber"),
                        rs.getString("instructor"),
                        rs.getInt("credits"),
                        rs.getString("description"));
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            ConnectionPool.returnConnection(conn);
        }
        return null;
    }

    public static Course selectCourse(String courseNumber) throws Exception {
        Connection conn = null;
        try {
            conn = ConnectionPool.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM course WHERE courseNumber = ?");
            stmt.setString(1, courseNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Course(
                        rs.getInt("courseId"),
                        rs.getString("courseName"),
                        rs.getString("courseNumber"),
                        rs.getString("instructor"),
                        rs.getInt("credits"),
                        rs.getString("description"));
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            ConnectionPool.returnConnection(conn);
        }
        return null;
    }

    public static Vector<Course> selectAllCourses() throws Exception {

        Connection conn = null;
        try {
            conn = ConnectionPool.getConnection();
            Vector<Course> courses = new Vector<>();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM course");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                courses.add(new Course(
                        rs.getInt("courseId"),
                        rs.getString("courseName"),
                        rs.getString("courseNumber"),
                        rs.getString("instructor"),
                        rs.getInt("credits"),
                        rs.getString("description")));
            }
            return courses;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            ConnectionPool.returnConnection(conn);
        }
    }
}
