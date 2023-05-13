package com.soen387.registrationsystem;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

import com.soen387.registrationsystem.gateway.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "registerServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    private final String indexUrl = "http://localhost:8080/RegistrationSystem_war_exploded";
    private String getHTML(String content, String redirectUrl, String redirectName) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <p>" + content + "</p>\n" +
                "<a href=\"" + redirectUrl + "\">" + redirectName + "</a>\n" +
                "</body>\n" +
                "</html>";
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String role = request.getParameter("role");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String dob = request.getParameter("dateOfBirth");

        Connection conn = null;

        try {
            conn = DBConnection.getSQLConnection();
            Person person = new Person(firstName, lastName, address, email, phoneNumber, dob);

            Person insertPersonRes = person.insert(conn);

            if (insertPersonRes == null) {
                // error occurred
                conn.rollback();
                out.print(getHTML("Error inserting person", indexUrl, "Back"));
                return;
            }

            if (role.equals("administrator")) {
                Admin admin = new Admin(person);
                Admin insertRes = Gateway.insertAdmin(admin, conn);
                if (insertRes == null) {
                    conn.rollback();
                    out.print(getHTML("Error inserting admin", indexUrl, "Back"));
                    return;
                }
                out.print(getHTML("You have registered! Your id is " + admin.employmentId + ". Please save it.",
                        indexUrl + "/create-course.jsp", "Courses dashboard"));
            } else if (role.equals("student")) {
                Student student = new Student(person);
                Student insertRes = Gateway.insertStudent(student, conn);
                if (insertRes == null) {
                    conn.rollback();
                    out.print(getHTML("Error inserting student", indexUrl, "Back"));
                    return;
                }
                out.print(getHTML("You have registered! Your id is " + student.studentId + ". Please save it.",
                        indexUrl + "/enroll-course.jsp", "Enroll courses"));
            } else {
                out.print(getHTML("There was an error.", indexUrl, "Back"));
            }

            // @everyone, this line commits all the sql executions
            // so if there was any error, it will not commit (basically it is a transaction, either it succeeds or not
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            if (conn != null) try { conn.close(); } catch (SQLException sqlException) { }
            out.print(getHTML("There was an error.", indexUrl, "Back"));
        }
    }
}
