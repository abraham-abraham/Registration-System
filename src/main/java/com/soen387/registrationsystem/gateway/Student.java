package com.soen387.registrationsystem.gateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Student extends Person {
	public int studentId;

	// use this constructor for insertion
	public Student(Person p) {
		super(p);
		this.studentId = ID.getID();

	}

	public Student(int studentId, Person p) {
		super(p);
		this.studentId = studentId;
	}
	public Student insert(Connection conn) {
		try {
			super.insert(conn);
			PreparedStatement insertStatement = conn.prepareStatement(
					"INSERT INTO Student " +
					"(studentID, personID)" +
					"VALUES (?, ?)");
			insertStatement.setInt(1, studentId);
			insertStatement.setInt(2, super.id);
			insertStatement.execute();
			insertStatement.close();
			return this;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean delete(Connection conn) {
		try {
			PreparedStatement deleteStudent = conn.prepareStatement("DELETE FROM Student WHERE studentID = ?");
			deleteStudent.setInt(1, studentId);
			deleteStudent.executeUpdate();
			deleteStudent.close();
			delete(super.id, conn);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Person update(Connection conn) {
		try {

			super.update(conn);
			PreparedStatement updateStatement = conn.prepareStatement("UPDATE Student SET" +
					 "studentID = ?" +
					"WHERE studentID = ?");

			updateStatement.setInt(1, studentId);
			updateStatement.execute();
			updateStatement.close();
			return this;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Student find(Connection conn) {
		try {
			PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM Student WHERE studentID = ?");
			selectStatement.setInt(1, studentId);
			ResultSet result = selectStatement.executeQuery();
			ArrayList<Student> temp = ResultSetConverter.getStudentList(result, conn);
			selectStatement.close();
			if (!temp.isEmpty())
			return temp.get(0);
			else return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// do Static: insert, delete, find, (whichever we need, check gateway) and non static: save
	// for insert, we will need to call Person.insert, then we insert student
	// for delete too, we delete student, then we delete person
	// for update, maybe update base or something
}
