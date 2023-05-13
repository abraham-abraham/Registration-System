package com.soen387.registrationsystem.gateway;

import java.sql.*;
import java.util.ArrayList;

public class ResultSetConverter {
    public static ArrayList<Person> getPersonList(ResultSet resultSet) throws SQLException {
            ArrayList<Person> list = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("personID");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                String email = resultSet.getString("email");
                String dob = resultSet.getString("dateOfBirth");
                Person person = new Person(id, firstName, lastName, address, phoneNumber, email, dob);
                list.add(person);
            }
            return list;
    }
    public static ArrayList<Course> getCourseList(ResultSet resultSet) throws SQLException {
            ArrayList<Course> list = new ArrayList<>();
            while (resultSet.next()) {
                String courseCode = resultSet.getString("courseCode");
                String title = resultSet.getString("title");
                String semester = resultSet.getString("semester");
                String days = resultSet.getString("days");
                String time = resultSet.getString("time");
                String room = resultSet.getString("room");
                String instructor = resultSet.getString("instructor");
                String startDate = resultSet.getString("startDate");
                String endDate = resultSet.getString("endDate");
                Course course = new Course(courseCode, title, semester, days, time, room, instructor, startDate, endDate);
                list.add(course);
            }
            return list;
    }
    public static ArrayList<Admin> getAdminList(ResultSet resultSet, Connection conn) throws SQLException {
            ArrayList<Admin> list = new ArrayList<>();
            while (resultSet.next()) {
                int personId = resultSet.getInt("personId");
                int employmentId = resultSet.getInt("employmentId");
                ArrayList<Person> persons = Person.find(personId, conn);
                if (persons.size() == 0) throw new SQLException("Person with id " + personId + " did not exist!");
                Person p = persons.get(0);
                Admin admin = new Admin(employmentId, p);
                list.add(admin);
            }
            return list;
    }
    public static ArrayList<Student> getStudentList(ResultSet studentSet, Connection conn) throws SQLException {
            ArrayList<Student> list = new ArrayList<>();
            while (studentSet.next()) {
                int personId = studentSet.getInt("personId");
                int studentId = studentSet.getInt("studentId");
                ArrayList<Person> persons = Person.find(personId, conn);
                if (persons.size() == 0) throw new SQLException("Person with id " + personId + " did not exist!");
                Person p = persons.get(0);
                // TODO: update constructor, use p
                Student student = new Student(studentId, p);
                list.add(student);
            }
            return list;
    }
    public static ArrayList<Registered> getRegisteredList(ResultSet resultSet) throws SQLException {
            ArrayList<Registered> list = new ArrayList<>();
            while (resultSet.next()) {
                int studentId = resultSet.getInt("studentId");
                String courseCode = resultSet.getString("courseCode");
                Registered registered = new Registered(courseCode, studentId);
                list.add(registered);
            }
            return list;
    }
}
