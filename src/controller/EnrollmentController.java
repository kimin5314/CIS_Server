package controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static util.Util.fixCors;
import static util.json.JsonParser.*;
import static service.EnrollmentService.*;

@WebServlet(name = "enrollment", urlPatterns = "/enrollment")
public class EnrollmentController extends HttpServlet {
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        fixCors(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        fixCors(req, resp);

        String studentNumber = req.getParameter("studentNumber");
        try {
            if (studentNumber.equals("all")) {
                resp.getWriter().write("{\"enrollmentList\":" + getAllEnrollmentRecords() + "}");
            } else {
                resp.getWriter().write("{\"courseList\":" + getEnrollmentList(studentNumber) + "}");
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
            case "enroll": {
                String studentNumber = map.get("studentNumber");
                int courseId = parseInt(map.get("courseId"));

                try {
                    enrollCourse(studentNumber, courseId);
                    resp.getWriter().write("success");
                } catch (Exception e) {
                    resp.getWriter().write(e.getMessage());
                    System.err.println(e.getMessage());
                }
                break;
            }
            case "drop": {
                String studentNumber = map.get("studentNumber");
                int courseId = parseInt(map.get("courseId"));
                try {
                    dropCourse(studentNumber, courseId);
                    resp.getWriter().write("success");
                } catch (Exception e) {
                    resp.getWriter().write(e.getMessage());
                    System.err.println(e.getMessage());
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
