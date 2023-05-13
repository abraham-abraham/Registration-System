package com.soen387.registrationsystem;

import com.soen387.registrationsystem.gateway.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet(name = "CourseByStudent", value = "/courses-by-student")
public class CourseByStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private final String indexUrl = "http://localhost:8080/RegistrationSystem_war_exploded/create-course.jsp";

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

    private String getStudentListElement(Person p) {
        return "<li>" + p.firstName + " " + p.lastName + " " + p.id + "</li>";
    }
    private String getCourseListElement(Course c) {
        return "<li>" + c.courseCode + "</li>";
    }
    private String getHTMLStudentList(String title, ArrayList<Person> persons, String redirectUrl, String redirectName) {
        String head = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<p>" + title + "</p>\n" +
                "<ul>";
        String body = "";
        for (Person p : persons) {
            body += getStudentListElement(p);
        }
        body += "</ul>";
        String end = "<a href=\"" + redirectUrl + "\">" + redirectName + "</a>\n" +
                "</body>\n" +
                "</html>";
        return head + body + end;
    }

    private String getHTMLCourseList(String title, ArrayList<Course> courses, String redirectUrl, String redirectName) {
        String head = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<p>" + title + "</p>\n" +
                "<ul>";
        String body = "";
        for (Course c : courses) {
            body += getCourseListElement(c);
        }
        body += "</ul>";
        String end = "<a href=\"" + redirectUrl + "\">" + redirectName + "</a>\n" +
                "</body>\n" +
                "</html>";
        return head + body + end;
    }
    private boolean studentExists(int studentId, Connection conn) throws SQLException {
        ArrayList<Student> res = Gateway.findStudent(studentId, conn);
        if (res == null) throw new SQLException();
        return res.size() > 0;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int employmentID = Integer.parseInt(request.getParameter("employmentID"));
        int studentID = Integer.parseInt(request.getParameter("studentID"));

        Connection conn = null;

        try {
            conn = DBConnection.getSQLConnection();

            if (!isEmployee(employmentID, conn)) {
                out.print(getHTML("Not allowed", indexUrl, "Back"));
                return;
            }

            if (!studentExists(studentID, conn)) {
                out.print(getHTML("Student " + studentID + " does not exist in db!", indexUrl, "Back"));
                return;
            }

            ArrayList<Course> courses = Gateway.findCoursesByStudent(studentID, conn);

            if (courses == null) {
                // error occurred
                conn.rollback();
                out.print(getHTML("Error getting the courses", indexUrl, "Back"));
                return;
            } else {
                out.print(getHTMLCourseList("Courses being taken by the student are:", courses,
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
