package service;

import model.Course;
import model.Enrollment;

import java.util.Vector;

import static dao.CourseDao.*;
import static dao.EnrollmentDao.*;


public class CourseService {
    public static Vector<Course> getAllCourses() throws Exception {
        try {
            return selectAllCourses();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static Vector<Course> getCourseList(int studentNumber) throws Exception {
        try {
            Vector<Course> courses = new Vector<>();
            for (Enrollment e : selectEnrollmentList(studentNumber, "studentNumber")) {
                courses.add(selectCourse(e.getCourseId()));
            }
            return courses;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static Course getCourse(String courseNumber) throws Exception {
        try {
            return selectCourse(courseNumber);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void addCourse(Course course) throws Exception {
        try {
            insertCourse(course);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void dropCourse(int courseId) throws Exception {
        try {
            deleteCourse(courseId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void modifyCourse(Course course) throws Exception {
        try {
            updateCourse(course);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
