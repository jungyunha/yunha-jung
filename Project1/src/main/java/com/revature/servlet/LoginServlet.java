package com.revature.servlet;

import com.revature.dao.EmoployeeDAOImpl;
import com.revature.dao.EmployeeDAO;
import com.revature.ulti.Login;
import com.revature.ulti.SessionManage;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * doGet: this method will handle all GET requests made to this servlet
	 */

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// write a message to the response body with PrintWriter
		//resp.getWriter().write("hello from Login Servlet!");

		//serve the Login.html page as the response
		req.getRequestDispatcher("Login.html").forward(req, resp);
		/*
		 * RequestDispatcher is used to perform a 'foward' -passing request to another resource without clients awareness.
		 */
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EmployeeDAO e = new EmoployeeDAOImpl();
		Login login = new Login();
		HttpSession session = req.getSession();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		boolean loginConfirm = login.loginConfirm(username, password);
		if (loginConfirm == true) {
			try {
				if (e.returnEmployee(username).getEmployeetype() == 0) {
					SessionManage sessionManager = new SessionManage();
					sessionManager.createSessionForManagerAccounts(username, session);
					System.out.println("Login Success");
					resp.sendRedirect("employee");
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				if (e.returnEmployee(username).getEmployeetype() == 1) {
					SessionManage sessionManager = new SessionManage();
					sessionManager.createSessionForManagerAccounts(username, session);
					resp.sendRedirect(	"manager");
					System.out.println("Login Success");
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} else {
			System.out.println(username + password);
			System.out.println("Login not successful");
			resp.sendRedirect("login");
		}
	}
}