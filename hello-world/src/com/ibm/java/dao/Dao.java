package com.ibm.java.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.ibm.java.beans.Employ;

public class Dao {

	private static Connection connection;
	static {
		try {
			connection = getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost/ibm_training";
		String username = "root";
		String password = "pass@word1";
		try {
			// connection + load driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Employ> fetchEmployees() throws SQLException {
		Statement statement = null;
		ResultSet results = null;

		// statement
		statement = connection.createStatement();
		// query
		String query = "SELECT * FROM employs";
		// fetch results
		results = statement.executeQuery(query);
		List<Employ> emps = new ArrayList<Employ>();
		while (results.next()) {
			emps.add(new Employ(results.getInt("emp_id"), results.getString("name"), results.getString("email"),
					results.getInt("p_id"), results.getInt("salary")));
		}
		results.close();
		statement.close();
		return emps;
	}

	public static Employ fetchEmployee(int empId, int pId) throws SQLException {
		String sql = "SELECT * FROM employs where emp_id=? and p_id=?";
		PreparedStatement prepareStatement = connection.prepareStatement(sql);

		prepareStatement.setInt(1, empId);
		prepareStatement.setInt(2, pId);

		ResultSet results = prepareStatement.executeQuery();
		Employ emp = null;
		while (results.next()) {
			emp = new Employ(results.getInt("emp_id"), results.getString("name"), results.getString("email"),
					results.getInt("p_id"), results.getInt("salary"));
		}
		return emp;
	}

	public static void calculate(int first, int next) throws SQLException {
		// procedure
		CallableStatement call = connection.prepareCall("{ call calculate(?,?,?)  }");
		call.setInt(1, first);
		call.setInt(2, next);
		call.registerOutParameter(3, Types.INTEGER);
		call.executeUpdate();

		System.out.println("SUM is " + call.getInt(3));
	}

	public static void sum(int first, int next) throws SQLException {
		// function
		CallableStatement call = connection.prepareCall("{ ?= call summ(?,?)  }");
		call.setInt(2, first);
		call.setInt(3, next);
		call.registerOutParameter(1, Types.INTEGER);
		call.executeUpdate();

		System.out.println("SUM is " + call.getInt(1));
	}
}
