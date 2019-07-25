package com.revature.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bean.Reimbursement;
import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDAOImpl;
import com.revature.ulti.ReimbursementManage;
import com.revature.ulti.SessionManage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ReimbursementServlet() {
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionManage sessionmanage = new SessionManage();
        String reimbursementStatus = req.getParameter("reimbursementStatus");
        String employeeId = req.getParameter("employeeId");
        String reimbursementId = req.getParameter("reimbursementId");
        String action = req.getParameter("action");

        ObjectMapper om = new ObjectMapper();
        PrintWriter pw = resp.getWriter();

        ReimbursementDAO rd = new ReimbursementDAOImpl();
        List<Reimbursement> reimbursement = new ArrayList<Reimbursement>();

        if(action != null && reimbursementId != null){
            try{
                int reimbursementIdNum = Integer.parseInt(reimbursementId);
                Reimbursement reim = rd.getReimbursementById(reimbursementIdNum);
                if (action.equalsIgnoreCase("approve") && reim != null) {
                    System.out.println("trying to remove " + reim + "request");
                } else if (action.equalsIgnoreCase("deny") && reim != null) {
                    System.out.println("trying to remove " + reim + "request");
                    rd.denyReimbursementByErbId(reimbursementIdNum);
                } else {
                    pw.write("N/A");
                }

            } catch (Exception e) {
                System.out.println("Please check reimbursementId!");
                pw.write("N/A");
            }
        }
        if (reimbursementStatus != null) {
            if (reimbursementStatus.equalsIgnoreCase("pending")) {
                try {
                    reimbursement = rd.getAllPendingReimbursements();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (reimbursementStatus.equalsIgnoreCase("resolved")) {
                try {
                    reimbursement = rd.getAllResolvedReimbursements();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // if param for employeeId is in session, get ERB for that employeeId
        } else if (employeeId != null) {
            try {
                int id = Integer.parseInt(employeeId);
                reimbursement = rd.getAllResolvedReimbursementsEmployeeId(id);
            } catch (Exception e) {
                pw.write("N/A");
            }
        } else {
            // get all erbs if no emp id exists in params
            try {
                reimbursement = rd.getAllReimbursements();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        boolean userExistsInSession = sessionmanage.validateUserExistence(req);
        ReimbursementManage rm = new ReimbursementManage();
        try {
            reimbursement = rm.employeeERB(reimbursement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (userExistsInSession) {
            String reimbursementsString = om.writeValueAsString(reimbursement);
            reimbursementsString = "{\"reimbursements\": " + reimbursementsString + "}";
            pw.write(reimbursementsString);
        } else {
            resp.sendRedirect("login");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // start post process

        doGet(request, response);

    }

}