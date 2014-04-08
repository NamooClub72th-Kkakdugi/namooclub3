package com.namoo.club.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.namoo.club.dao.CommunityDao;

import dom.entity.Community;
import dom.entity.CommunityManager;
import dom.entity.CommunityMember;
import dom.entity.SocialPerson;

public class CommunityDaoJdbc implements CommunityDao {

	@Override
	public List<Community> readAllCommunities() {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		List<Community> communities = new ArrayList<Community>();

		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT a.com_no, a.com_nm, a.com_des, a.com_date, b.userid FROM community a INNER JOIN communitymember b on a.com_no = b.com_no";
			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				int comNo = rset.getInt("com_no");
				String comName = rset.getString("com_nm");
				String comDescription = rset.getString("com_des");
				Date date = rset.getDate("com_date");
				String userId = rset.getString("userid");

				Community community = new Community(comName, comDescription, new SocialPerson(userId));
				community.setComNo(comNo);
				community.setOpenDate(date);
				communities.add(community);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rset != null)try {rset.close();} catch (SQLException e) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException e) {}
			if (conn != null)try {conn.close();} catch (SQLException e) {}
		}
		return communities;
	}

	@Override
	public Community readCommunity(int comNo) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Community community = null;

		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT a.com_no, a.com_nm, a.com_des, a.com_date, b.userid FROM community a "
					+ "INNER JOIN communitymember b ON a.com_no = b.com_no WHERE a.com_no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, comNo);
			pstmt.executeQuery();

			rset = pstmt.executeQuery();

			if (rset.next()) {
				String userId = rset.getString("userid");
				String comName = rset.getString("com_nm");
				String description = rset.getString("com_des");
				Date date = rset.getDate("com_date");
				int comNo2 = rset.getInt("com_no");

				community = new Community(comName, description, new SocialPerson(userId));
				community.setComNo(comNo2);
				community.setOpenDate(date);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rset != null)try {rset.close();} catch (SQLException e) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException e) {}
			if (conn != null)try {conn.close();} catch (SQLException e) {}
		}
		return community;
	}

	@Override
	public int createCommunity(Community community) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		int comNo = 1;

		try {
			conn = DbConnection.getConnection();
			String sql = "INSERT INTO community(com_nm, com_des, com_date) VALUES (?, ?, sysdate())";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, community.getComName());
			pstmt.setString(2, community.getDescription());

			pstmt.executeUpdate();

			rset = pstmt.getGeneratedKeys();
			if (rset.next()) {
				comNo = rset.getInt("com_no");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rset != null)try {rset.close();} catch (SQLException e) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException e) {}
			if (conn != null)try {conn.close();} catch (SQLException e) {}
		}
		return comNo;

	}

	@Override
	public CommunityManager addCommunityManager(CommunityManager comManager) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		CommunityManager manager = null;

		try {
			conn = DbConnection.getConnection();
			String sql = "INSERT INTO communitymember(com_no, userid, type) VALUES (?, ?, 1)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, comManager.getCommunityNo());
			pstmt.setString(2, comManager.getUserId());

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
	public CommunityMember addCommunityMember(CommunityMember comMember) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		CommunityMember member = null;

		try {
			conn = DbConnection.getConnection();
			String sql = "INSERT INTO communitymember(com_no, userid, type) VALUES (?, ?, 2)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, comMember.getComNo());
			pstmt.setString(2, comMember.getUser().getUserId());

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
	public void updateCommunity(Community community) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DbConnection.getConnection();
			String sql = "UPDATE community SET com_nm=? com_des=? com_date=sysdate() WHERE com_no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, community.getComName());
			pstmt.setString(2, community.getDescription());
			pstmt.setInt(3, community.getComNo());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)try {pstmt.close();} catch (SQLException e) {}
			if (conn != null)try {conn.close();} catch (SQLException e) {}
		}
	}

	@Override
	public void deleteCommunity(int comNo) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DbConnection.getConnection();
			String sql = "DELETE FROM community WHERE com_no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, comNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)try {pstmt.close();} catch (SQLException e) {}
			if (conn != null)try {conn.close();} catch (SQLException e) {}
		}
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
	
	private void deleteAllCommunityMembership(int comNo, String type) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbConnection.getConnection();
			String sql = "DELETE FROM communitymember WHERE com_no = ? AND type = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comNo);
			pstmt.setString(2, type);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)try {pstmt.close();} catch (SQLException e) {	}
			if (conn != null)try {conn.close();} catch (SQLException e) {}
		}
	}
}
