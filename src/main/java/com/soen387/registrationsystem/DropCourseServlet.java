package com.soen387.registrationsystem;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.soen387.registrationsystem.gateway.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "DropCourseServlet", value = "/drop-course")
public class DropCourseServlet extends HttpServlet {
    private final String indexUrl = "http://localhost:8080/RegistrationSystem_war_exploded/drop-course.jsp";

    private boolean studentExists(int studentId, Connection conn) throws SQLException {
        ArrayList<Student> res = Gateway.findStudent(studentId, conn);
        if (res == null) throw new SQLException();
        return res.size() > 0;
    }

    private boolean courseExist(String courseCode, Connection conn) throws SQLException {
        ArrayList<Course> res = Gateway.findCourse(courseCode, conn);
        if (res == null) throw new SQLException();
        return res.size() > 0;
    }

    private boolean isRegistered(String courseCode, int studentId, Connection conn) throws SQLException {
        ArrayList<Registered> res = Gateway.findRegistered(courseCode, studentId, conn);
        if (res == null) {
            throw new SQLException("There was an error");
        }
        return res.size() > 0;
    }

    private int registeredCount(int studentID, Connection conn) throws SQLException {
        ArrayList<Registered> res = Gateway.findRegisteredByStudent(studentID, conn);
        if (res == null) throw new SQLException();
        return res.size();
    }
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
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int studentID = Integer.parseInt(request.getParameter("studentID"));
        String courseCode = request.getParameter("courseCode");

        Connection conn = null;

        try {
            conn = DBConnection.getSQLConnection();

            if (!studentExists(studentID, conn)) {
                out.print(getHTML("Student id does not exist", indexUrl, "Back"));
                return;
            }

            if (!courseExist(courseCode, conn)) {
                out.print(getHTML("Course does not exist", indexUrl, "Back"));
                return;
            }

            if (!isRegistered(courseCode, studentID, conn)) {
                out.print(getHTML("You are not registered to this course", indexUrl, "Back"));
                return;
            }

            boolean res = Gateway.deleteRegistered(courseCode, studentID, conn);

            if (!res) {
                // error occurred
                conn.rollback();
                out.print(getHTML("Error dropping a course", indexUrl, "Back"));
                return;
            } else {
                out.print(getHTML("You have dropped the course " + courseCode,
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
