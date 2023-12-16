package controller;

import model.Course;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static util.Util.fixCors;
import static util.json.JsonParser.parseRequestToMap;
import static service.CourseService.*;

@WebServlet(name = "course", urlPatterns = "/course")
public class CourseController extends HttpServlet {
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        fixCors(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        fixCors(req, resp);

        try {
            resp.getWriter().write("{\"courseList\":" + getAllCourses() + "}");
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
            case "add": {
                try {
                    String courseName = map.get("courseName");
                    String courseNumber = map.get("courseNumber");
                    String instructor = map.get("instructor");
                    int credits = Integer.parseInt(map.get("credits"));
                    String description = map.get("description");
                    addCourse(new Course(courseName, courseNumber, instructor, credits, description));
                    resp.getWriter().write("success");
                } catch (Exception e) {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
                }
            }
            break;
            case "drop": {
                try {
                    int courseId = Integer.parseInt(map.get("courseId"));
                    dropCourse(courseId);
                    resp.getWriter().write("success");
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
                }
            }
            break;
            case "modify": {
                try {
                    int courseId = Integer.parseInt(map.get("courseId"));
                    String courseName = map.get("courseName");
                    String courseNumber = map.get("courseNumber");
                    String instructor = map.get("instructor");
                    int credits = Integer.parseInt(map.get("credits"));
                    String description = map.get("description");
                    modifyCourse(new Course(courseId, courseName, courseNumber, instructor, credits, description));
                    resp.getWriter().write("success");
                } catch (Exception e) {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
                    System.err.println(e.getMessage());
                }
            }
            break;
            default: {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        }
    }
}
