package com.revature.bean;

public class Reimbursement {

	private int reimbursementId;
	private float reimbursementAmount;
	private String reimbursementStatus; // 'APPROVED', 'DENIED', 'PENDING'
	private int managerId;
	private int employeeId;
	private Employee employee;
	private Employee manager;

	public Reimbursement() {
		super();
	}

	public Reimbursement(int reimbursementId, float reimbursementAmount, String reimbursementStatus, int managerId, int employeeId){
		super();
		this.reimbursementId = reimbursementId;
		this.reimbursementAmount = reimbursementAmount;
		this.reimbursementStatus = reimbursementStatus;
		this.managerId = managerId;
		this.employeeId = employeeId;
	}

	public int getReimbursementId() {
		return reimbursementId;
	}

	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}

	public float getReimbursementAmount() {
		return reimbursementAmount;
	}

	public void setReimbursementAmount(float reimbursementAmount) {
		this.reimbursementAmount = reimbursementAmount;
	}

	public String getReimbursementStatus() {
		return reimbursementStatus;
	}

	public void setReimbursementStatus(String reimbursementStatus) {
		this.reimbursementStatus = reimbursementStatus;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManagers(Employee manager) {
		this.manager = manager;
	}
}
