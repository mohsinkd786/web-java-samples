package com.ibm.java.service;

import java.sql.SQLException;
import java.util.List;

import com.ibm.java.api.EmployServiceI;
import com.ibm.java.beans.Employ;
import com.ibm.java.dao.EmployDAO;

public class EmployService implements EmployServiceI {
	private EmployDAO dao;
	{
		dao = new EmployDAO();
	}

	public List<Employ> fetchAll() throws SQLException {
		return dao.fetchAll();
	}

	public Employ fetchOne(int id) throws SQLException {
		return dao.fetchOne(id);
	}

	public void add(Employ t) throws SQLException {
		dao.add(t);
	}

	public void updateOne(Employ t) throws SQLException {
		dao.updateOne(t);
	}

	public void employTransact(Employ t) throws SQLException {
		dao.employTransact(t);
	}

	@Override
	public boolean fetchEmployByName(Employ t) throws SQLException {
		if (dao.fetchEmployByName(t) != null) {
			return true;
		} else {
			return false;
		}
	}

	public Employ build(String user, String pass) {
		return new Employ(0, user, pass, 0, 0);
	}
}
