package com.soen387.registrationsystem.gateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Admin extends Person {

	public int employmentId;

	public Admin(Person p) {
		super(p);
		this.employmentId = ID.getID();

	}

	public Admin(int employmentId, Person p) {
		super(p);
		this.employmentId = employmentId;
	}
	public Admin insert(Connection conn) {
		try {
			super.insert(conn);
			PreparedStatement insertStatement = conn.prepareStatement(
					"INSERT INTO Administrator " +
							"(employmentId, personID)" +
							"VALUES (?, ?)");
			insertStatement.setInt(1, employmentId);
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
			PreparedStatement deleteAdmin = conn.prepareStatement("DELETE FROM Administrator WHERE employmentID = ?");
			deleteAdmin.setInt(1, employmentId);
			deleteAdmin.executeUpdate();
			deleteAdmin.close();
			delete(super.id, conn);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Admin update(Connection conn) {
		try {

			super.update(conn);
			PreparedStatement updateStatement = conn.prepareStatement("UPDATE Administrator SET" +
					"employmentId = ?" +
					"WHERE studentId = ?");

			updateStatement.setInt(1, employmentId);
			updateStatement.execute();
			updateStatement.close();
			return this;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Admin find(Connection conn) {
		try {
			PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM Administrator WHERE employmentId = ?");
			selectStatement.setInt(1, employmentId);
			ResultSet result = selectStatement.executeQuery();
			ArrayList<Admin> temp = ResultSetConverter.getAdminList(result, conn);
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
