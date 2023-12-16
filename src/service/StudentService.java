package service;

import model.Student;

import static dao.StudentDao.*;

public class StudentService {
    public static void register(Student s) throws Exception {
        try {
            insertStudent(s);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static boolean login(String studentNumber, String password) throws Exception {
        try {
            Student s = selectStudent(studentNumber);
            assert s != null;
            if (s.isOnline()) {
                throw new Exception("You have already logged in.");
            }
            if (s.getPassword().equals(password)) {
                s.setOnline(true);
                updateStudent(s);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void logout(String studentNumber) throws Exception {
        try {
            Student s = selectStudent(studentNumber);
            if (s != null) {
                s.setOnline(false);
                updateStudent(s);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void addStudent(Student s) throws Exception {
        try {
            insertStudent(s);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void modifyStudent(Student s) throws Exception {
        try {
            updateStudent(s);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void dropStudent(int studentId) throws Exception {
        try {
            deleteStudent(studentId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
