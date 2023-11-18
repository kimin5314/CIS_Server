package controller;

import module.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import static java.lang.Integer.parseInt;
import static util.json.JsonParser.*;
import static util.json.JsonBuilder.*;
import static service.EnrollmentService.*;

@WebServlet(name = "enrollment", urlPatterns = "/enrollment")
public class EnrollmentController extends HttpServlet {
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/plain;charset=utf-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
        resp.setHeader("Access-Control-Allow-Credentials", "false");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doOptions(req, resp);

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
        doOptions(req, resp);

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
