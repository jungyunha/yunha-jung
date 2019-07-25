package com.revature.servlet;

import com.revature.bean.Employee;
import com.revature.dao.EmoployeeDAOImpl;
import com.revature.dao.EmployeeDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/profile")
public class EditProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditProfileServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            request.getRequestDispatcher("editProfile.html").forward(request, response);
        } else {
            response.sendRedirect("login");
        }

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);

        int empId = Integer.parseInt(req.getParameter("id"));
        String empName = req.getParameter("name");

        Employee e = new Employee();
        e.setId(empId);
        e.setName(empName);

        EmployeeDAO ed = new EmoployeeDAOImpl();
        int updatedEmployee = 0;
        try {
            updatedEmployee = ed.updateEmployee(empId);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(updatedEmployee);
//
        if (updatedEmployee == 1) {
            resp.sendRedirect("employeehome");
        } else {
            resp.sendRedirect("profile");
        }
    }

}
