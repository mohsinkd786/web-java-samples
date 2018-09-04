package com.ibm.java.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibm.java.beans.Employ;

public interface DAOI {

	List<Employ> fetchAll() throws SQLException;

	Employ fetchOne(int id) throws SQLException;

	void add(Employ t) throws SQLException;

	void updateOne(Employ t) throws SQLException;

	void employTransact(Employ t) throws SQLException;
}
