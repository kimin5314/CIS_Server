package service;

import model.Course;
import model.Enrollment;
import model.Student;

import java.util.Vector;

import static dao.EnrollmentDao.*;
import static dao.StudentDao.*;
import static dao.CourseDao.*;

public class EnrollmentService {
    public static void enrollCourse(String studentNumber, int courseId) throws Exception {
        try {
            Student s = selectStudent(studentNumber);
            if (s == null) {
                throw new Exception("Student not found.");
            }
            Course c = selectCourse(courseId);
            if (c == null) {
                throw new Exception("Course not found.");
            }
            Enrollment e = selectEnrollment(s.getStudentId(), c.getCourseId());
            if (e == null) {
                insertEnrollment(new Enrollment(s.getStudentId(), courseId, new java.sql.Date(System.currentTimeMillis())));
            } else {
                throw new Exception("Already enrolled.");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void dropCourse(String studentNumber, int courseId) throws Exception {
        try {
            Student s = selectStudent(studentNumber);
            if (s == null) {
                throw new Exception("Student not found.");
            }
            Course c = selectCourse(courseId);
            if (c == null) {
                throw new Exception("Course not found.");
            }
            Enrollment e = selectEnrollment(s.getStudentId(), c.getCourseId());
            if (e == null) {
                throw new Exception("Not enrolled.");
            }
            deleteEnrollment(s.getStudentId(), c.getCourseId());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static Vector<Course> getEnrollmentList(String studentNumber) throws Exception {
        try {
            Vector<Course> courses = new Vector<>();
            Vector<Enrollment> allEnrollments = selectAllEnrollments();
            Student s = selectStudent(studentNumber);
            assert s != null;
            for (Enrollment e : allEnrollments) {
                if (e.getStudentId() == s.getStudentId()) {
                    courses.add(selectCourse(e.getCourseId()));
                }
            }
            return courses;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static Vector<Enrollment> getAllEnrollmentRecords() throws Exception {
        try {
            return selectAllEnrollments();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }
}
