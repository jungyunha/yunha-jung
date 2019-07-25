package com.revature.dao;

import com.revature.bean.Reimbursement;

import java.sql.SQLException;
import java.util.List;

public interface ReimbursementDAO {

	int createReimbursement(Reimbursement reimbursement) throws SQLException;
	int approveReimbursementByErbId(int id) throws SQLException;
	int denyReimbursementByErbId(int id) throws SQLException;

	Reimbursement getReimbursementById(int id)throws SQLException;


	List<Reimbursement> getAllDeniedReimbursements()throws SQLException;
	List<Reimbursement> getAllPendingReimbursementsByEmployeeId(int id)throws SQLException;
	List<Reimbursement> getAllResolvedReimbursementsEmployeeId(int id)throws SQLException;
	List<Reimbursement> getReimbursementsByEmployeeId(int id)throws SQLException;
	List<Reimbursement> getAllPendingReimbursements()throws SQLException;
	List<Reimbursement> getAllResolvedReimbursements()throws SQLException;

	List<Reimbursement> getAllReimbursements()throws SQLException;
	List<Reimbursement> getReimbursementsByEmployeeUsername(String username)throws SQLException;
}
