package com.soen387.registrationsystem.gateway;

import java.sql.*;
import java.util.ArrayList;

public class Gateway {
    public static ArrayList<Person> findStudentsByCourse(String courseCode, Connection conn){
        try {
            PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM Registered INNER JOIN Student INNER JOIN Person ON (Registered.studentID = Student.studentID AND Student.personID = Person.personID) WHERE courseCode = ?");
            selectStatement.setString(1, courseCode);
            ResultSet result = selectStatement.executeQuery();
            ArrayList<Person> temp = ResultSetConverter.getPersonList(result);
            System.out.println("temp " + temp);
            selectStatement.close();
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Course> findCoursesByStudent(int studentID, Connection conn) {
        try {
            PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM Registered INNER JOIN Course ON Registered.courseCode = Course.courseCode WHERE studentID = ?");
            selectStatement.setInt(1, studentID);
            ResultSet result = selectStatement.executeQuery();
            ArrayList<Course> temp = ResultSetConverter.getCourseList(result);
            selectStatement.close();
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Person> findPersonByEmail(String email, Connection conn){
        try {
            PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM Person WHERE email = ?");
            selectStatement.setString(1, email);
            ResultSet result = selectStatement.executeQuery();
            ArrayList<Person> temp = ResultSetConverter.getPersonList(result);
            selectStatement.close();
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean deleteRegistered(String courseCode, int studentId, Connection conn) {
        try {
            PreparedStatement selectStatement = conn.prepareStatement("DELETE FROM Registered WHERE courseCode = ? AND studentID = ?");
            selectStatement.setString(1, courseCode);
            selectStatement.setInt(2, studentId);
            selectStatement.executeUpdate();
            selectStatement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<Person> findPerson(String personId, Connection conn) {
        try {
            PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM Person WHERE personID = ?");
            selectStatement.setString(1, personId);
            ResultSet result = selectStatement.executeQuery();
            ArrayList<Person> temp = ResultSetConverter.getPersonList(result);
            selectStatement.close();
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static ArrayList<Course> findCourse(String courseCode, Connection conn) {
        try {
            PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM Course WHERE courseCode = ?");
            selectStatement.setString(1, courseCode);
            ResultSet result = selectStatement.executeQuery();
            ArrayList<Course> temp = ResultSetConverter.getCourseList(result);
            selectStatement.close();
            return temp;
            } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static ArrayList<Admin> findAdmin(int employmentID, Connection conn) {
        try {
            PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM Administrator WHERE employmentID = ?");
            selectStatement.setInt(1, employmentID);
            ResultSet result = selectStatement.executeQuery();
            ArrayList<Admin> temp = ResultSetConverter.getAdminList(result, conn);
            selectStatement.close();
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Student> findStudent(int studentID, Connection conn) {
        try {
            PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM Student WHERE studentID = ?");
            selectStatement.setInt(1, studentID);
            ResultSet result = selectStatement.executeQuery();
            ArrayList<Student> temp = ResultSetConverter.getStudentList(result, conn);
            selectStatement.close();
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static ArrayList<Registered> findRegistered(int registerID, Connection conn) {
        try {
            PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM Registered WHERE registerID = ?");
            selectStatement.setInt(1, registerID);
            ResultSet result = selectStatement.executeQuery();
            ArrayList<Registered> temp = ResultSetConverter.getRegisteredList(result);
            selectStatement.close();
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Registered> findRegisteredByStudent(int studentID, Connection conn) {
        try {
            PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM Registered WHERE studentID = ?");
            selectStatement.setInt(1, studentID);
            ResultSet result = selectStatement.executeQuery();
            ArrayList<Registered> temp = ResultSetConverter.getRegisteredList(result);
            selectStatement.close();
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static ArrayList<Registered> findRegistered(String courseCode, int studentID, Connection conn) {
        try {
            PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM Registered WHERE courseCode = ? AND studentID = ?");
            selectStatement.setString(1, courseCode);
            selectStatement.setInt(2, studentID);
            ResultSet result = selectStatement.executeQuery();
            ArrayList<Registered> temp = ResultSetConverter.getRegisteredList(result);
            selectStatement.close();
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Person insertPerson(Person person, Connection conn) {
        try {
            PreparedStatement insertStatement = conn.prepareStatement("INSERT INTO Person " +
                    "(personID, firstName, lastName, address, email, phoneNumber, dateOfBirth) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)");
            System.out.println("id " + person.id + " first name: " + person.firstName + "Last name: " + person.lastName);
            insertStatement.setInt(1, person.id);
            insertStatement.setString(2, person.firstName);
            insertStatement.setString(3, person.lastName);
            insertStatement.setString(4, person.address);
            insertStatement.setString(5, person.email);
            insertStatement.setString(6, person.phoneNumber);
            insertStatement.setString(7, person.dateOfBirth);
            insertStatement.execute();
            insertStatement.close();
            return person;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Student insertStudent(Student student, Connection conn) {
        try {
            PreparedStatement insertStatement = conn.prepareStatement("INSERT INTO Student " +
                    "(studentID, personID)" +
                    "VALUES (?, ?)");
            insertStatement.setInt(1, student.studentId);
            insertStatement.setInt(2, student.id);
            insertStatement.execute();
            insertStatement.close();
            return student;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Admin insertAdmin(Admin admin, Connection conn) {
        try {
            PreparedStatement insertStatement = conn.prepareStatement("INSERT INTO Administrator " +
                    "(employmentID, personID)" +
                    "VALUES (?, ?)");
            insertStatement.setInt(1, admin.employmentId);
            insertStatement.setInt(2, admin.id);
            insertStatement.execute();
            insertStatement.close();
            return admin;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Course insertCourse (Course course, Connection conn) {
        try {
            PreparedStatement insertStatement = conn.prepareStatement("INSERT INTO Course " +
                    "(courseCode, title, semester, days, time, instructor, room, startDate, endDate)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            insertStatement.setString(1, course.courseCode);
            insertStatement.setString(2, course.title);
            insertStatement.setString(3, course.semester);
            insertStatement.setString(4, course.days);
            insertStatement.setString(5, course.time);
            insertStatement.setString(6, course.instructor);
            insertStatement.setString(7, course.room);
            insertStatement.setString(8, course.startDate);
            insertStatement.setString(9, course.endDate);
            insertStatement.execute();
            insertStatement.close();
            return course;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
     }

    public static Registered insertRegistered (Registered registered, Connection conn) {
        try {
            PreparedStatement insertStatement = conn.prepareStatement("INSERT INTO Registered " +
                    "(studentID, courseCode)" +
                    "VALUES (?, ?)");
            insertStatement.setInt(1, registered.studentId);
            insertStatement.setString(2, registered.courseCode);
            insertStatement.execute();
            insertStatement.close();
            return registered;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
