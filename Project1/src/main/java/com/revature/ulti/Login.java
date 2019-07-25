package com.revature.ulti;

import com.revature.bean.Employee;
import com.revature.dao.EmoployeeDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Login {

    public static boolean loginConfirm(String username, String password) {
        List<Employee> loginCheck = new ArrayList<>();
        EmoployeeDAOImpl emp = new EmoployeeDAOImpl();
        try {
            loginCheck.addAll(emp.returnAllEmployee());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        for (Employee empo : loginCheck) {
            if (empo.getUsername() == null) {
                return false;
            }
            if (empo.getUsername().equals(username) && empo.getPassword().equals(password)) {
                    return true;
            }
        }
        return false;
    }
}
