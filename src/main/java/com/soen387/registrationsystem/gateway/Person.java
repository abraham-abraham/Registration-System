package com.soen387.registrationsystem.gateway;

import java.sql.*;
import java.util.ArrayList;

public class Person {
	public int id;
	public String firstName;
	public String lastName;
	public String address;
	public String phoneNumber;
	public String email;
	public String dateOfBirth;

	public Person(Person p) {
		this.id = p.id;
		this.firstName = p.firstName;
		this.lastName = p.lastName;
		this.address = p.address;
		this.phoneNumber = p.phoneNumber;
		this.email = p.email;
		this.dateOfBirth = p.dateOfBirth;
	}

	// use this constructor for insertion
	public Person(String firstName, String lastName, String address, String phoneNumber, String email,
				  String dateOfBirth) {

		this.id = ID.getID();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
	}

	public Person(int id, String firstName, String lastName, String address, String phoneNumber, String email,
			String dateOfBirth) {

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
	}

	public static ArrayList<Person> find(int id, Connection conn) {
		try {
			PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM Person WHERE personID = ?");
			selectStatement.setInt(1, id);
			ResultSet result = selectStatement.executeQuery();
			ArrayList<Person> temp = ResultSetConverter.getPersonList(result);
			selectStatement.close();
			return temp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Person find(Connection conn) {
		try {
			PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM Person WHERE studentID = ?");
			selectStatement.setInt(1, id);
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

	public static ArrayList<Person> find(String email, Connection conn) {
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

	public Person insert(Connection conn) {
		try {
			PreparedStatement insertStatement = conn.prepareStatement("INSERT INTO Person " +
					"(personID, firstName, lastName, address, email, phoneNumber, dateOfBirth) " +
					"VALUES (?, ?, ?, ?, ?, ?, ?)");
			insertStatement.setInt(1, id);
			insertStatement.setString(2, firstName);
			insertStatement.setString(3, lastName);
			insertStatement.setString(4, address);
			insertStatement.setString(5, email);
			insertStatement.setString(6, phoneNumber);
			insertStatement.setString(7, dateOfBirth);
			insertStatement.execute();
			insertStatement.close();
			return this;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Person update(Connection conn) {
		try {
			PreparedStatement updateStatement = conn.prepareStatement("UPDATE Person SET" +
					"firstName = ?, lastName = ?, address = ?, email = ?, phoneNumber = ?, dateOfBirth = ?" +
					"WHERE personID = ?");

			updateStatement.setString(1, firstName);
			updateStatement.setString(2, lastName);
			updateStatement.setString(3, address);
			updateStatement.setString(4, email);
			updateStatement.setString(5, phoneNumber);
			updateStatement.setString(6, dateOfBirth);
			updateStatement.setInt(7, id);

			updateStatement.execute();
			updateStatement.close();
			return this;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean delete(int id, Connection conn) {
		try {
			PreparedStatement deleteStatement = conn.prepareStatement("DELETE FROM Person " +
					"WHERE personID = ?");
			deleteStatement.setInt(1, id);
			deleteStatement.execute();
			deleteStatement.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
