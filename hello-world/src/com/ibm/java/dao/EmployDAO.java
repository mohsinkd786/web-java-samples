package com.ibm.java.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibm.java.beans.Employ;

public class EmployDAO implements DAOI {

	@Override
	public List<Employ> fetchAll() throws SQLException {

		String query = "SELECT * FROM employs";
		ResultSet results = Connection.conn.createStatement().executeQuery(query);
		List<Employ> employs = new ArrayList<Employ>();
		while (results.next()) {
			employs.add(new Employ(results.getInt("emp_id"), results.getString("name"), results.getString("email"),
					results.getInt("p_id"), results.getInt("salary")));
		}
		return employs;
	}

	@Override
	public Employ fetchOne(int id) throws SQLException {
		String query = "SELECT * FROM employs where emp_id=?";
		PreparedStatement statement = Connection.conn.prepareStatement(query);
		statement.setInt(1, id);
		ResultSet results = statement.executeQuery();
		Employ emp = null;
		while (results.next()) {
			emp = new Employ(results.getInt("emp_id"), results.getString("name"), results.getString("email"),
					results.getInt("p_id"), results.getInt("salary"));
		}
		return emp;
	}

	@Override
	public void add(Employ t) throws SQLException {
		String query = "INSERT INTO employs(emp_id,name,email,p_id,salary) VALUES(?,?,?,?,?)";
		PreparedStatement statement = Connection.conn.prepareStatement(query);
		statement.setInt(1, t.getId());
		statement.setString(2, t.getName());
		statement.setString(3, t.getEmail());
		statement.setInt(4, t.getP_id());
		statement.setInt(5, t.getSalary());
		statement.executeUpdate();
	}

	@Override
	public void updateOne(Employ t) throws SQLException {
		String query = "UPDATE employs SET name=?, email=?, p_id=?, salary=? where emp_id=?";
		PreparedStatement statement = Connection.conn.prepareStatement(query);
		statement.setString(1, t.getName());
		statement.setString(2, t.getEmail());
		statement.setInt(3, t.getP_id());
		statement.setInt(4, t.getSalary());
		statement.setInt(5, t.getId());
		statement.executeUpdate();
	}

	@Override
	public void employTransact(Employ t) throws SQLException {
		String query = "INSERT INTO employs(emp_id,name,email,p_id,salary) VALUES(?,?,?,?,?)";
		java.sql.Connection conn = Connection.conn;
		conn.setAutoCommit(false);

		// Savepoint sav = conn.setSavepoint("begin_trans");

		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, t.getId());
		statement.setString(2, t.getName());
		statement.setString(3, t.getEmail());
		statement.setInt(4, t.getP_id());
		statement.setInt(5, t.getSalary());

		statement.executeUpdate();
		conn.commit();
		// conn.rollback(sav);
	}

	public Employ fetchEmployByName(Employ t) throws SQLException {
		String query = "SELECT * FROM employs where name=? and email=?";
		PreparedStatement psT = Connection.conn.prepareStatement(query);
		psT.setString(1, t.getName());
		psT.setString(2, t.getEmail());
		ResultSet results = psT.executeQuery();
		Employ emp = null;
		while (results.next()) {
			emp = new Employ(results.getInt("emp_id"), results.getString("name"), results.getString("email"),
					results.getInt("p_id"), results.getInt("salary"));
		}
		return emp;
	}
}
