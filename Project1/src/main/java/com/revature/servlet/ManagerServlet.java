package com.revature.servlet;

import com.revature.bean.Reimbursement;
import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDAOImpl;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

	public class ManagerServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null) {
			req.getRequestDispatcher("Manager.html").forward(req, resp);
		}
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doGet(req, resp);
		int reimbursementId = Integer.parseInt(req.getParameter("reimbursementId"));
		String reimbursementStatus = req.getParameter("reimbursementStatus");

		// APPROVES
		Reimbursement r = new Reimbursement();
		r.setReimbursementId(reimbursementId);
		r.setReimbursementStatus(reimbursementStatus);

		ReimbursementDAO rd = new ReimbursementDAOImpl();
		int approvedReimbursements = 0;
		try {
			approvedReimbursements = rd.approveReimbursementByErbId(reimbursementId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(approvedReimbursements);
//
		if (approvedReimbursements==1) {
			resp.sendRedirect("manager");
		} else {
			resp.sendRedirect("create");
		}



	}

}
