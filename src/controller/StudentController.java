package controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.Map;
import java.util.Objects;

import module.Student;

import static dao.StudentDao.*;
import static service.StudentService.*;
import static util.Util.fixCors;
import static util.json.JsonParser.*;

@WebServlet(name = "student", urlPatterns = "/student")
public class StudentController extends HttpServlet {
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        fixCors(req, resp);
    }

    @Override // improve: use other method to  check if admin is online
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        fixCors(req, resp);

        String studentNumber = req.getParameter("studentNumber");

        try {
            if (studentNumber.equals("all")) {
                if (Objects.requireNonNull(selectStudent("admin")).isOnline()) {
                    resp.getWriter().write("{\"studentList\":" + selectAllStudents() + "}");
                } else {
                    resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                }
            } else {
                Student s = selectStudent(studentNumber);
                assert s != null;
                resp.getWriter().write(s.getStudentName());
            }
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        fixCors(req, resp);

        Map<String, String> map = parseRequestToMap(req);

        String action = map.get("action");
        switch (action) {
            case "register": {
                try {
                    String studentNumber = map.get("studentNumber");
                    String studentName = map.get("studentName");
                    String password = map.get("password");
                    Date admissionDate = Date.valueOf(map.get("admissionDate"));
                    String gender = map.get("gender");
                    register(new Student(studentName, studentNumber, admissionDate, gender, password));
                    resp.getWriter().write("success");
                } catch (Exception e) {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
                }
                break;
            }
            case "login": {
                try {
                    String studentNumber = map.get("studentNumber");
                    String password = map.get("password");
                    if (login(studentNumber, password)) {
                        if (studentNumber.equals("admin")) {
                            resp.getWriter().write("admin");
                        } else {
                            resp.getWriter().write("success");
                        }
                    } else {
                        resp.getWriter().write("wrong password or account");
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    resp.getWriter().write(e.getMessage());
                }
                break;
            }
            case "logout": {
                String studentNumber = map.get("studentNumber");
                try {
                    logout(studentNumber);
                    resp.getWriter().write("success");
                } catch (Exception e) {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
                }
                break;
            }
            case "add": {
                try {
                    String studentNumber = map.get("studentNumber");
                    String studentName = map.get("studentName");
                    String password = map.get("password");
                    Date admissionDate = Date.valueOf(map.get("admissionDate"));
                    String gender = map.get("gender");
                    addStudent(new Student(studentName, studentNumber, admissionDate, gender, password));
                    resp.getWriter().write("success");
                } catch (Exception e) {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
                }
                break;
            }
            case "modify": {
                try {
                    int studentId = Integer.parseInt(map.get("studentId"));
                    String studentNumber = map.get("studentNumber");
                    if (studentNumber.equals("admin")) {
                        resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                        break;
                    }
                    String studentName = map.get("studentName");
                    String password = map.get("password");
                    Date admissionDate = Date.valueOf(map.get("admissionDate"));
                    String gender = map.get("gender");
                    boolean isOnline = Boolean.parseBoolean(map.get("isOnline"));
                    modifyStudent(new Student(studentId, studentName, studentNumber, admissionDate, gender, password, isOnline));
                    resp.getWriter().write("success");
                } catch (Exception e) {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
                }
                break;
            }
            case "drop": {
                try {
                    int studentId = Integer.parseInt(map.get("studentId"));
                    if (studentId == 1) {
                        resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                        break;
                    }
                    dropStudent(studentId);
                    resp.getWriter().write("success");
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
                }
                break;
            }
            default: {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
                break;
            }
        }
    }
}
