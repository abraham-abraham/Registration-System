package com.soen387.registrationsystem;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.soen387.registrationsystem.gateway.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "CreateCourseServlet", value = "/create-course")
public class CreateCourseServlet extends HttpServlet {
    private final String indexUrl = "http://localhost:8080/RegistrationSystem_war_exploded/create-course.jsp";
    private String getHTML(String content, String redirectUrl, String redirectName) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "  <p>" + content + "</p>\n" +
                "<a href=\"" + redirectUrl + "\">" + redirectName + "</a>\n" +
                "</body>\n" +
                "</html>";
    }

    private boolean isEmployee(int id, Connection conn) throws SQLException {
        ArrayList<Admin> res = Gateway.findAdmin(id, conn);
        if (res == null) throw new SQLException();
        return res.size() > 0;
    }

    private boolean courseCodeExists(String courseCode, Connection conn) throws SQLException {
        ArrayList<Course> res = Gateway.findCourse(courseCode, conn);
        if (res == null) throw new SQLException();
        return res.size() > 0;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int employmentID = Integer.parseInt(request.getParameter("employmentID"));
        String courseCode = request.getParameter("courseCode");
        String title = request.getParameter("title");
        String semester = request.getParameter("semester");
        String days = request.getParameter("days");
        String courseTime = request.getParameter("courseTime");
        String instructor = request.getParameter("instructor");
        String room = request.getParameter("room");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        Connection conn = null;

        try {
            conn = DBConnection.getSQLConnection();
            Course course = new Course(courseCode, title, semester, days, courseTime, instructor, room,startDate, endDate);

            if (!isEmployee(employmentID, conn)) {
                out.print(getHTML("Not allowed", indexUrl, "Back"));
                return;
            }

            if (courseCodeExists(courseCode, conn)) {
                out.print(getHTML("Course code " + courseCode + " already exists!", indexUrl, "Back"));
                return;
            }

            Course createCourseRes = Gateway.insertCourse(course, conn);

            if (createCourseRes == null) {
                // error occurred
                conn.rollback();
                out.print(getHTML("Error creating a course", indexUrl, "Back"));
                return;
            } else {
                out.print(getHTML("You have created the course " + courseCode,
                        indexUrl, "Back"));
            }
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            if (conn != null) try { conn.close(); } catch (SQLException sqlException) { }
            out.print(getHTML("There was an error.", indexUrl, "Back"));
        }
    }
}
