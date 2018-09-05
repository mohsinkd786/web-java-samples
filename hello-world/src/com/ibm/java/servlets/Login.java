package com.ibm.java.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.java.beans.Employ;
import com.ibm.java.service.EmployService;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	private EmployService service;

	@Override
	public void init() {
		service = new EmployService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		Employ emp = service.build(user, pass);
		try {
			List<Employ> emps = service.fetchEmployByName(emp);
			if (emps != null) {
				request.setAttribute("emps", emps);
				request.getRequestDispatcher("WEB-INF/views/dashboard.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "Invalid credentials!");
				request.getRequestDispatcher("WEB-INF/views/home.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
