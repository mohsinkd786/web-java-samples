package com.ibm.java.api;

import java.sql.SQLException;
import java.util.List;

import com.ibm.java.beans.Employ;

public interface EmployServiceI {
	List<Employ> fetchAll() throws SQLException;

	Employ build(String user, String pass);

	boolean fetchEmployByName(Employ t) throws SQLException;
}