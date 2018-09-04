package com.ibm.java.dao;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {

	public static java.sql.Connection conn;
	static {
		conn = connect();
	}

	private static java.sql.Connection connect() {
		String url = "jdbc:mysql://localhost/ibm_training";
		String username = "root";
		String password = "pass@word1";
		try {
			// connection + load driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
