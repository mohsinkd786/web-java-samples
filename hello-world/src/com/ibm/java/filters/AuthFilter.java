package com.ibm.java.filters;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.ibm.java.api.EmployServiceI;
import com.ibm.java.beans.Employ;
import com.ibm.java.service.EmployService;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter(urlPatterns = { "/login" })
public class AuthFilter implements Filter {

	private EmployServiceI service = new EmployService();

	public AuthFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Employ emp = service.build(request.getParameter("username"), request.getParameter("password"));
		try {
			if (service.fetchEmployByName(emp)) {
				// redirect to the servlet
				chain.doFilter(request, response);
			} else {
				request.setAttribute("isLoggedIn", "Invalid Credentials,Please retry");
				request.getRequestDispatcher("home").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
}
