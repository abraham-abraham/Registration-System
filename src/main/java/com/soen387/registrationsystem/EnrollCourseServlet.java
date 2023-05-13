package com.soen387.registrationsystem;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.mysql.cj.exceptions.ConnectionIsClosedException;
import com.soen387.registrationsystem.gateway.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "EnrollCourseServlet", value = "/enroll-course")
public class EnrollCourseServlet extends HttpServlet {
    private final String indexUrl = "http://localhost:8080/RegistrationSystem_war_exploded/enroll-course.jsp";
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

    private boolean studentExists(int studentId, Connection conn) throws SQLException {
        ArrayList<Student> res = Gateway.findStudent(studentId, conn);
        if (res == null) throw new SQLException();
        return res.size() > 0;
    }

    private boolean canRegister(String courseCode, Connection conn) throws SQLException, ParseException {

        float now = System.currentTimeMillis();
        now = now / 1000;
        ArrayList<Course> res = Gateway.findCourse(courseCode, conn);
        if (res == null) throw  new SQLException();
        Date courseStartDate = new Date();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            courseStartDate = formatter.parse(res.get(0).startDate);
        }
        catch (ParseException e){
            System.out.println(e);
        }
        float couresStartSeconds = (courseStartDate.getTime())/1000;
        System.out.println("course code " + courseCode);
        System.out.println("course start date " + courseStartDate);
        System.out.println("Get(0) " + res.get(0).startDate);
        System.out.println("courseStartSeconds " + couresStartSeconds);
        System.out.println("now " + now);
        float oneWeek = 60 * 60 * 24 * 7; // 604800

        float deadline = couresStartSeconds + oneWeek;
        return now <= deadline;

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
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int studentID = Integer.parseInt(request.getParameter("studentID"));
        String courseCode = request.getParameter("courseCode");
        Connection conn = null;

        try {
            conn = DBConnection.getSQLConnection();
            Registered registered = new Registered(courseCode, studentID);

            if (!studentExists(studentID, conn)) {
                out.print(getHTML("Student id does not exist", indexUrl, "Back"));
                return;
            }

            if (!courseExist(courseCode, conn)) {
                out.print(getHTML("Course does not exist", indexUrl, "Back"));
                return;
            }

            if (isRegistered(courseCode, studentID, conn)) {
                out.print(getHTML("You are already registered to this course", indexUrl, "Back"));
                return;
            }

            if (registeredCount(studentID, conn) >= 5) {
                out.print(getHTML("Limit of 5 reached, you cannot register more classes", indexUrl, "Back"));
                return;
            }

            if(!canRegister(courseCode, conn)){
                out.print(getHTML("The one week deadline for enrolling has passed", indexUrl, "Back"));
                return;
            }

            Registered enrollCourseRes = Gateway.insertRegistered(registered, conn);

            if (enrollCourseRes == null) {
                // error occurred
                conn.rollback();
                out.print(getHTML("Error enrolling to class", indexUrl, "Back"));
                return;
            }

            else {
                out.print(getHTML("You have enrolled in course " + courseCode,
                        indexUrl, "Back"));
            }
            conn.commit();
            conn.close();
        } catch (SQLException | ParseException e) {
            if (conn != null) try { conn.close(); } catch (SQLException sqlException) { }
            out.print(getHTML("There was an error.", indexUrl, "Back"));
        }
    }
}
