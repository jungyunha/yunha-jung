package com.revature.ulti;

import com.revature.bean.Employee;
import com.revature.bean.Reimbursement;
import com.revature.dao.EmoployeeDAOImpl;
import com.revature.dao.EmployeeDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementManage {

    public List<Reimbursement> employeeERB(List<Reimbursement> reimbursements) throws SQLException {
        List<Reimbursement> empArray = new ArrayList<>();
        empArray.addAll(reimbursements);
        EmployeeDAO e = new EmoployeeDAOImpl();

        for (Reimbursement reim : empArray) {
            Employee emp = e.getEmployeesById(reim.getEmployeeId());
            emp.setPassword("");
            reim.setEmployee(emp);

            if(reim.getManagerId() != 0 ){
                Employee manager = e.getEmployeesById(reim.getManagerId());
            }
        }
        return empArray;
    }
}
