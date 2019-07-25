package com.revature.dao;

import java.util.ArrayList;
import java.util.List;
import com.revature.bean.Employee;
import com.revature.ulti.ConnFactory;

import java.io.IOException;
import java.sql.*;

public class EmoployeeDAOImpl implements EmployeeDAO {

	public static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public Employee returnEmployee(String username) throws SQLException {
		Connection con = cf.getConnection();
		String sql = "SELECT * FROM EMPLOYEES WHERE EMP_USERNAME = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		Employee emp = null;
		while (rs.next()) {
			emp = new Employee(rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7));
			emp.setId(rs.getInt(1));
		}
		return emp;
	}

	@Override
	public void addEmployee(Employee emp) throws SQLException {
		Connection con = cf.getConnection();
	}

	@Override
	public List<Employee> returnAllEmployee() throws SQLException {
		List<Employee> employeeList = new ArrayList<>();
		Connection con = cf.getConnection();
		String sql = "SELECT * FROM EMPLOYEES";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		Employee emp = null;
		while (rs.next()) {
			emp = new Employee(rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7));
			emp.setId(rs.getInt(1));
			employeeList.add(emp);
		}
		return employeeList;
	}

	@Override
	public Employee getEmployeesById(int id) throws SQLException {
		Employee emp = new Employee();
		String sql = "SELECT * FROM EMPLOYEES WHERE EMP_ID = ?";
		ResultSet rs = null;

		try (Connection con = ConnFactory.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				emp.setId(rs.getInt("EMP_ID"));
				emp.setName(rs.getString("name"));
				emp.setManager_id(rs.getInt("MANAGER_ID"));
				emp.setUsername(rs.getString("emp_username"));
				emp.setPassword(rs.getString("EMP_PASSWORD"));
				emp.setManager_id(rs.getInt("IS_MANAGER"));
				emp.setEmail(rs.getString("email"));

			}
		}
		return emp;
	}

	@Override
	public int updateEmployee(int id) throws SQLException {
		int updatedEmployee = 0;
		String sql = "UPDATE EMPLOYEES SET FIRSTNAME = ? WHERE EMP_ID = ?";

		try (Connection con = ConnFactory.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);
			updatedEmployee = ps.executeUpdate();

			return updatedEmployee;
		}
	}

	public int updateEmployeeByName(String firstname) {
		int updatedEmployee = 0;
		String sql = "UPDATE EMPLOYEES SET FIRSTNAME = ? WHERE EMP_NAME = ?";

		try (Connection con = ConnFactory.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, firstname);
			updatedEmployee = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updatedEmployee;
	}

	public void removeEmployee(Employee emp) throws SQLException {

	}

	@Override
	public List<Employee> getEmployee() throws SQLException {
		List<Employee> employeeList = new ArrayList<>();
		String sql = "SELECT * FROM EMPLOYEES";

		try(Connection con = cf.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);){
			while(rs.next()){
				Employee emp = new Employee();
				String username = rs.getString("emp_username");
				emp.setUsername(username);
				String password = rs.getString("emp_password");
				emp.setPassword(password);
				int employeetype = rs.getInt("is_manager");
				emp.setEmployeetype(employeetype);
			}
		}
		return employeeList;
	}
}