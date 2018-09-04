package com.ibm.java.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.java.beans.Employ;
import com.ibm.java.service.EmployService;

/**
 * Servlet implementation class Hello
 */
@WebServlet("/hello")
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EmployService service;

	@Override
	public void init() {
		service = new EmployService();
	}

	/**
	 * Default constructor.
	 */
	public Hello() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * response.getWriter().append("<!DOCTYPE html>\r\n" + "<html>\r\n" +
		 * "<head>\r\n" + "<meta charset=\"ISO-8859-1\">\r\n" +
		 * "<title>User Login</title>\r\n" + "</head>\r\n" + "<body>\r\n" +
		 * "	<form method=\"POST\" action=\"\">\r\n" + "		<div>\r\n" +
		 * "			<input type=\"text\" name=\"username\" placeholder=\"Username\" />\r\n"
		 * + "		</div>\r\n" +
		 * "		<input type=\"password\" name=\"password\" placeholder=\"Password\" /> <input\r\n"
		 * + "			type=\"submit\" name=\"login\" value=\"Login\" />\r\n" +
		 * "	</form>\r\n" + "</body>\r\n" + "</html>");
		 */

		request.getRequestDispatcher("WEB-INF/views/hello.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		System.out.println("<div>User : " + user + "</div><div>Pass: " + pass + "</div>");
		Employ emp = service.build(user, pass);
		try {
			response.getWriter().append("<div>" + service.fetchEmployByName(emp) + "</div>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
