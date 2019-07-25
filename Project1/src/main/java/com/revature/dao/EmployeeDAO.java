package com.revature.dao;
import java.sql.SQLException;
import java.util.List;

import com.revature.bean.Employee;

public interface EmployeeDAO {

	public Employee returnEmployee(String username) throws SQLException;

	public void addEmployee(Employee emp) throws SQLException;

	public  List<Employee>returnAllEmployee() throws SQLException;

	Employee getEmployeesById(int id) throws SQLException;

	public int updateEmployee(int id) throws SQLException;

	public void removeEmployee(Employee emp) throws SQLException;

	public  List<Employee> getEmployee() throws SQLException;

}
