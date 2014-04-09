package com.namoo.club.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.namoo.club.dao.MemberDao;

import dom.entity.CommunityManager;
import dom.entity.CommunityMember;
import dom.entity.SocialPerson;

public class MemberDaoJdbc implements MemberDao {
	//
	@Override
	public CommunityManager addCommunityManager(int comNo, CommunityManager comManager) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		CommunityManager manager = null;

		try {
			conn = DbConnection.getConnection();
			String sql = "INSERT INTO communitymember(com_no, email, is_manager) VALUES (?, ?, '1')";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, comNo);
			pstmt.setString(2, comManager.getEmail());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rset != null)try {rset.close();} catch (SQLException e) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException e) {}
			if (conn != null)try {conn.close();} catch (SQLException e) {}
		}
		return manager;
	}

	@Override
	public CommunityMember addCommunityMember(int comNo, CommunityMember comMember) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		CommunityMember member = null;

		try {
			conn = DbConnection.getConnection();
			String sql = "INSERT INTO communitymember(com_no, email, is_manager) VALUES (?, ?, '2')";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, comNo);
			pstmt.setString(2, comMember.getEmail());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rset != null)try {rset.close();} catch (SQLException e) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException e) {}
			if (conn != null)try {conn.close();} catch (SQLException e) {}
		}
		return member;
	}
	
	@Override
	public CommunityMember readCommunityMember(int comNo, String email) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
	    CommunityMember comMember = null;
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT com_no, email, is_manager FROM communitymember WHERE com_no = ? AND is_manager ='2' AND email=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comNo);
			pstmt.setString(2, email);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				int comNo2 = rset.getInt("com_no");
				String email2 = rset.getString("email");
				
				comMember = new CommunityMember(comNo2, new SocialPerson(email2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)try {pstmt.close();} catch (SQLException e) {	}
			if (conn != null)try {conn.close();} catch (SQLException e) {}
		}
		return comMember;
	}


	@Override
	public CommunityManager readCommunityManager(int comNo) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		CommunityManager comManager = null;
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT com_no, email, is_manager FROM communitymember WHERE com_no = ? AND is_manager ='1'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comNo);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				int comNo2 = rset.getInt("com_no");
				comManager = new CommunityManager(comNo2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)try {pstmt.close();} catch (SQLException e) {	}
			if (conn != null)try {conn.close();} catch (SQLException e) {}
		}
		return comManager;
	}

	@Override
	public List<CommunityMember> readAllCommunityMember(int comNo) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
	    List<CommunityMember> members = new ArrayList<CommunityMember>();
	    
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT com_no, email, is_manager FROM communitymember WHERE com_no = ? AND is_manager ='2'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comNo);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				int comNo2 = rset.getInt("com_no");
				String email2 = rset.getString("email");
				
				CommunityMember comMember = new CommunityMember(comNo2, new SocialPerson(email2));
				members.add(comMember);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)try {pstmt.close();} catch (SQLException e) {	}
			if (conn != null)try {conn.close();} catch (SQLException e) {}
		}
		return members;
	}
	
	@Override
	public void deleteAllComMember(int comNo) {
		//
		deleteAllCommunityMembership(comNo, "2");
	}

	@Override
	public void deleteAllComManager(int comNo) {
		//
		deleteAllCommunityMembership(comNo, "1");
	}
	
	private void deleteAllCommunityMembership(int comNo, String is_manager) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbConnection.getConnection();
			String sql = "DELETE FROM communitymember WHERE com_no = ? AND is_manager = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comNo);
			pstmt.setString(2, is_manager);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)try {pstmt.close();} catch (SQLException e) {	}
			if (conn != null)try {conn.close();} catch (SQLException e) {}
		}
	}

	@Override
	public void deleteCommuninyMember(int comNo, String email) {
		//
		deleteCommunityMembership(comNo, email, "2");
	}

	@Override
	public void deleteCommunityManager(int comNo, String email) {
		//
		deleteCommunityMembership(comNo, email, "1");
	}
	
	private void deleteCommunityMembership(int comNo, String email, String is_manager) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbConnection.getConnection();
			String sql = "DELETE FROM communitymember WHERE com_no = ? AND is_manager = ? AND email=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comNo);
			pstmt.setString(2, is_manager);
			pstmt.setString(3, email);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)try {pstmt.close();} catch (SQLException e) {	}
			if (conn != null)try {conn.close();} catch (SQLException e) {}
		}
	}
	
}
