package com.revature.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bean.Reimbursement;
import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDAOImpl;
import com.revature.ulti.ReimbursementManage;
import com.revature.ulti.SessionManage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/requests")
public class ReimbursementShow extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ReimbursementShow(){
        super();
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        SessionManage sm = new SessionManage();
        int id = (Integer) session.getAttribute("id");

        ReimbursementDAO rd = new ReimbursementDAOImpl();

        ObjectMapper om = new ObjectMapper();
        PrintWriter pw = resp.getWriter();

        List<Reimbursement> reimbursementList = null;
        try {
            reimbursementList = rd.getReimbursementsByEmployeeId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(reimbursementList);
        boolean userExist = sm.validateUserExistence(req);
        if(userExist) {
            ReimbursementManage rm = new ReimbursementManage();
            try {
                reimbursementList = rm.employeeERB(reimbursementList);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String er = om.writeValueAsString(reimbursementList);
            er = "{\"erbemployee\": " + er + "}";
            pw.write(er);
        }else{
            System.out.println("None");
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
    }
}
