package com.revature.ulti;

import com.revature.bean.Employee;
import com.revature.dao.EmoployeeDAOImpl;
import com.revature.dao.EmployeeDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class SessionManage {

    public void createSessionForManagerAccounts(String username, HttpSession session) throws SQLException {
        EmployeeDAO emp = new EmoployeeDAOImpl();
        Employee employee = emp.returnEmployee(username);

        session.setAttribute("id", employee.getId());
        session.setAttribute("name", employee.getName());
        session.setAttribute("is_manager", employee.getEmployeetype());
        session.setAttribute("email", employee.getEmail());
    }

    // validates that user does indeed exist and has a valid account with associated
    // data, then sets the user session to true
    public boolean validateUserExistence(HttpServletRequest request) {
        boolean userExists = false;
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("username") != null) {
            userExists = true;
        }
        return userExists;
    }
}
