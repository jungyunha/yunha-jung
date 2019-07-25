package com.revature.dao;
import com.revature.bean.Reimbursement;
import com.revature.ulti.ConnFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDAOImpl implements ReimbursementDAO {

	@Override
	public List<Reimbursement> getReimbursementsByEmployeeUsername(String username) throws SQLException {
		List<Reimbursement> reim = new ArrayList<Reimbursement>();
		ResultSet rs = null;

		String sql = "SELECT * FROM REIMBURSEMENTS WHERE EMP_USERNAME = ?";

		try(Connection con = ConnFactory.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);){
			ps.setString(1, username);
			rs = ps.executeQuery();

			while(rs.next()){
				Reimbursement reimbursement = new Reimbursement();
				reimbursement.setReimbursementId(rs.getInt("ERB_ID"));
				reimbursement.setReimbursementAmount(rs.getFloat("ERB_AMOUNT"));
				reimbursement.setReimbursementStatus(rs.getString("ERB_STATUS"));
				reimbursement.setManagerId(rs.getInt("MANAGER_ID"));
				reimbursement.setEmployeeId(rs.getInt("EMP_ID"));
				reim.add(reimbursement);
			}

		}
		return reim;

	}

	@Override
	public int createReimbursement(Reimbursement reimbursement) throws SQLException {
		 int reimbursementCreated = 0;
		String sql = "INSERT INTO REIMBURSEMENTS (ERB_ID, ERB_AMOUNT, ERB_STATUS, MANAGER_ID, EMP_ID) VALUES (?,?,?,?,?)";
		try(Connection con = ConnFactory.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, reimbursement.getReimbursementId());
			ps.setFloat(2, reimbursement.getReimbursementAmount());
			ps.setString(3, reimbursement.getReimbursementStatus());
			ps.setInt(4, reimbursement.getManagerId());
			ps.setInt(5, reimbursement.getEmployeeId());

			reimbursementCreated = ps.executeUpdate();
		}
		return reimbursementCreated;
	}

	@Override
	public int denyReimbursementByErbId(int id) throws SQLException {
		int deniedReimbursements = 0;
		String sql = "UPDATE REIMBURSEMENTS SET ERB_STATUS = 'DENIED' WHERE ERB_ID = ?";

		try(Connection con = ConnFactory.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);){
			ps.setInt(1, id);
			deniedReimbursements = ps.executeUpdate();
		}
		return deniedReimbursements;
	}

	@Override
	public Reimbursement getReimbursementById(int id) throws SQLException {
		Reimbursement reimbursement = new Reimbursement();
		ResultSet rs = null;
		String sql = "SELECT * FROM `` WHERE ERB_ID = ?";

		try(Connection con = ConnFactory.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);){
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while(rs.next()){
				reimbursement.setReimbursementId(rs.getInt("ERB_ID"));
				reimbursement.setReimbursementAmount(rs.getFloat("ERB_AMOUNT"));
				reimbursement.setReimbursementStatus(rs.getString("ERB_STATUS"));
				reimbursement.setManagerId(rs.getInt("MANAGER_ID"));
				reimbursement.setEmployeeId(rs.getInt("EMP_ID"));

			}
			return reimbursement;
		}
	}

	@Override
	public int approveReimbursementByErbId(int id) throws SQLException {
		int approvedReimbursements = 0;
		String sql = "UPDATE REIMBURSEMENTS SET ERB_STATUS = 'APPROVED' WHERE ERB_ID = ?";
		try (Connection con = ConnFactory.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
		}
		return approvedReimbursements;
	}

	@Override
	public List<Reimbursement> getAllDeniedReimbursements() throws SQLException {
		return null;
	}

	@Override
	public List<Reimbursement> getAllPendingReimbursementsByEmployeeId(int id) throws SQLException {
		List<Reimbursement> reim = new ArrayList<>();
		ResultSet rs = null;
		String sql = "SELECT * FROM REIMBURSEMENTS WHERE ERB_STATUS = 'PENDING' AND EMP_ID = ?";
		try(Connection con = ConnFactory.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);){

			ps.setInt(1,id);
			rs = ps.executeQuery();

			while(rs.next()){
				Reimbursement reimbursement = new Reimbursement();
				reimbursement.setReimbursementId(rs.getInt("ERB_ID"));
				reimbursement.setReimbursementAmount(rs.getFloat("ERB_AMOUNT"));
				reimbursement.setReimbursementStatus(rs.getString("ERB_STATUS"));
				reimbursement.setManagerId(rs.getInt("MANAGER_ID"));
				reimbursement.setEmployeeId(rs.getInt("EMP_ID"));
				reim.add(reimbursement);
			}
		}
		return reim;
	}


	@Override
	public List<Reimbursement> getReimbursementsByEmployeeId(int id) throws SQLException {
		List<Reimbursement> reim = new ArrayList<>();
		ResultSet rs = null;
		String sql = "SELECT * FROM REIMBURSEMENTS WHERE EMP_ID = ?";

		try(Connection con = ConnFactory.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);){

			ps.setInt(1, id);
			rs = ps.executeQuery();

			while(rs.next()){
				Reimbursement reimbursement = new Reimbursement();
				reimbursement.setReimbursementId(rs.getInt("ERB_ID"));
				reimbursement.setReimbursementAmount(rs.getFloat("ERB_AMOUNT"));
				reimbursement.setReimbursementStatus(rs.getString("ERB_STATUS"));
				reimbursement.setManagerId(rs.getInt("MANAGER_ID"));
				reimbursement.setEmployeeId(rs.getInt("EMP_ID"));
				reim.add(reimbursement);
			}
			return reim;
		}
	}

	@Override
	public List<Reimbursement> getAllPendingReimbursements() throws SQLException {
		List<Reimbursement> reim = new ArrayList<>();

		ResultSet rs = null;

		String sql = "SELECT * FROM REIMBURSEMENTS WHERE ERB_STATUS = 'PENDING'";

		try(Connection con = ConnFactory.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);){

			while(rs.next()){
				Reimbursement reimbursement = new Reimbursement();
				reimbursement.setReimbursementId(rs.getInt("ERB_ID"));
				reimbursement.setReimbursementAmount(rs.getFloat("ERB_AMOUNT"));
				reimbursement.setReimbursementStatus(rs.getString("ERB_STATUS"));
				reimbursement.setManagerId(rs.getInt("MANAGER_ID"));
				reimbursement.setEmployeeId(rs.getInt("EMP_ID"));

				reim.add(reimbursement);
			}
			return reim;
		}
	}

	@Override
	public List<Reimbursement> getAllResolvedReimbursementsEmployeeId(int id) throws SQLException {
		List<Reimbursement> reim = new ArrayList<>();
		ResultSet rs =null;
		String sql = "SELECT * FROM REIMBURSEMENT WHERE ERB_STATUS = 'APPROVED' OR ERB_STATUS = 'DENIED' AND EMP_ID = ?";

		try(Connection con = ConnFactory.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);){

			ps.setInt(1, id);
			rs = ps.executeQuery(sql);

			while(rs.next()) {
				Reimbursement reimbursement = new Reimbursement();
				reimbursement.setReimbursementId(rs.getInt("ERB_ID"));
				reimbursement.setReimbursementAmount(rs.getLong("ERB_AMOUNT"));
				reimbursement.setReimbursementStatus(rs.getString("ERB_STATUS"));
				reimbursement.setManagerId(rs.getInt("MANAGER_ID"));
				reimbursement.setEmployeeId(rs.getInt("EMP_ID"));
				reim.add(reimbursement);
			}
		}
		return reim;
	}

	@Override
	public List<Reimbursement> getAllResolvedReimbursements() throws SQLException {
		List<Reimbursement> reim = new ArrayList<Reimbursement>();

		ResultSet rs = null;
		String sql = "SELECT * FROM REIMBURSEMENTS WHERE ERB_STATUS = 'APPROVED' OR ERB_STATUS = 'DENIED'";
		try (Connection con = ConnFactory.getConnection();
			 Statement s = con.createStatement();) {
			rs = s.executeQuery(sql);

			while (rs.next()) {
				Reimbursement reimbursement = new Reimbursement();
				reimbursement.setReimbursementId(rs.getInt("ERB_ID"));
				reimbursement.setReimbursementAmount(rs.getFloat("ERB_AMOUNT"));
				reimbursement.setReimbursementStatus(rs.getString("ERB_STATUS"));
				reimbursement.setManagerId(rs.getInt("MANAGER_ID"));
				reimbursement.setEmployeeId(rs.getInt("EMP_ID"));
				reim.add(reimbursement);

			}
		}
		return reim;
	}

	@Override
	public List<Reimbursement> getAllReimbursements() throws SQLException {
		List<Reimbursement> reimbursementList = new ArrayList<Reimbursement>();

		String sql = "SELECT * FROM reimbursements";

		ResultSet rs = null;

		try(Connection con = ConnFactory.getConnection();
			Statement state = con.createStatement();) {

			rs = state.executeQuery(sql);

			while(rs.next()){
				Reimbursement reim = new Reimbursement();

				int reimbursementId = rs.getInt("ERB_ID");
				reim.setReimbursementId(reimbursementId);

				float reimbursementAmount = rs.getFloat("ERB_AMOUNT");
				reim.setReimbursementAmount(reimbursementAmount);

				String reimbursementStatus = rs.getString("ERB_STATUS");
				reim.setReimbursementStatus(reimbursementStatus);

				int reimbursementManagerId = rs.getInt("MANAGER_ID");
				reim.setManagerId(reimbursementManagerId);

				int reimbursementEmployeeId = rs.getInt("EMP_ID");
				reim.setEmployeeId(reimbursementEmployeeId);

				reimbursementList.add(reim);

			}
		}
		return reimbursementList;
	}

}
