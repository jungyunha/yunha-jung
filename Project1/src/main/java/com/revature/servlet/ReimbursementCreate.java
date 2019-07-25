package com.revature.servlet;
import com.revature.bean.Reimbursement;
import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/create")
public class ReimbursementCreate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ReimbursementCreate() {
        super();
    }

    protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if(session != null){
            req.getRequestDispatcher("createReimbursement.html").forward(req, resp);
        }else{
            resp.sendRedirect("login");
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int reimbursementId = Integer.parseInt(req.getParameter("reimbursementId"));
        float reimbursementAmount = Float.parseFloat(req.getParameter("reimbursementAmount"));
        int managerId = Integer.parseInt(req.getParameter("managerId"));
        int employeeId = Integer.parseInt(req.getParameter("employeeId"));

        // need to test this.
        Reimbursement r = new Reimbursement();
        r.setReimbursementId(reimbursementId);
        r.setReimbursementAmount(reimbursementAmount);
        r.setManagerId(managerId);
        r.setEmployeeId(employeeId);

        System.out.println(r);

        ReimbursementDAO rd = new ReimbursementDAOImpl();
        int numCreated = 0;
        try {
            numCreated = rd.createReimbursement(r);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(numCreated);

        if (numCreated==1) {
            resp.sendRedirect("employee");
        } else {
            resp.sendRedirect("create");
        }


    }
}
