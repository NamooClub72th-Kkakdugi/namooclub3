package com.namoo.club.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.namoo.club.dao.MemberDao;
import com.namoo.club.shared.exception.NamooClubExceptionFactory;

import dom.entity.Club;
import dom.entity.ClubKingManager;
import dom.entity.ClubManager;
import dom.entity.ClubMember;
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
			throw NamooClubExceptionFactory.createRuntime("커뮤니티 매니저로 등록하는 중 오류가 발생하였습니다.");
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
			throw NamooClubExceptionFactory.createRuntime("커뮤니티 멤버로 등록하는 중 오류가 발생하였습니다.");
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
			String sql = "SELECT com_no, email FROM communitymember WHERE com_no = ? AND is_manager ='2' AND email=?";
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
			throw NamooClubExceptionFactory.createRuntime("커뮤니티 멤버를 조회하는 중 오류가 발생하였습니다.");
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
			throw NamooClubExceptionFactory.createRuntime("커뮤니티 매니저를 조회하는 중 오류가 발생하였습니다.");
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
			throw NamooClubExceptionFactory.createRuntime("모든 커뮤니티 멤버를 조회하는 중 오류가 발생하였습니다.");
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
			throw NamooClubExceptionFactory.createRuntime("모든 커뮤니티 멤버쉽을 삭제하는 중 오류가 발생하였습니다.");
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
			throw NamooClubExceptionFactory.createRuntime("커뮤니티 멤버쉽을 삭제하는 중 오류가 발생하였습니다.");
		} finally {
			if (pstmt != null)try {pstmt.close();} catch (SQLException e) {	}
			if (conn != null)try {conn.close();} catch (SQLException e) {}
		}
	}
	
	//------------------------------------------------------------------------------------------------------------------
	
	@Override
	public ClubMember addClubMember(ClubMember clubMember) {
		// 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			conn = DbConnection.getConnection();
			
			String sql = "INSERT INTO clubmember(club_no, email, type)"
					+ " VALUES(?, ?, 'b')";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, clubMember.getClubNo());
			pstmt.setString(2, clubMember.getEmail());
			
			pstmt.executeUpdate();
			
			resultSet = pstmt.getGeneratedKeys();
		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
			 if ( resultSet != null) try { resultSet.close(); } catch (SQLException e) { }
			 if ( pstmt != null) try { pstmt.close(); } catch (SQLException e) { }
			 if ( conn != null) try { conn.close(); } catch (SQLException e) { }
		}
		return clubMember;
	}

	@Override
	public ClubManager addClubManager(ClubManager clubManager) {
		// 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			conn = DbConnection.getConnection();
			
			String sql = "INSERT INTO clubmember(club_no, email, type)"
					+ " VALUES(?, ?, 'a')";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, clubManager.getClubNo());
			pstmt.setString(2, clubManager.getEamil());
			
			pstmt.executeUpdate();
			
			resultSet = pstmt.getGeneratedKeys();
		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
			 if ( resultSet != null) try { resultSet.close(); } catch (SQLException e) { }
			 if ( pstmt != null) try { pstmt.close(); } catch (SQLException e) { }
			 if ( conn != null) try { conn.close(); } catch (SQLException e) { }
		}
		return clubManager;
	}
	
	@Override
	public ClubKingManager addKingManager(ClubKingManager clubKingManager) {
		// 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			conn = DbConnection.getConnection();
			
			String sql = "INSERT INTO clubmember(club_no, email, type"
					+ " VALUES(?, ?, 'c')";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, clubKingManager.getClubNo());
			pstmt.setString(2, clubKingManager.getEmail());
			
			pstmt.executeUpdate();
			
			resultSet = pstmt.getGeneratedKeys();
		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
			 if ( resultSet != null) try { resultSet.close(); } catch (SQLException e) { }
			 if ( pstmt != null) try { pstmt.close(); } catch (SQLException e) { }
			 if ( conn != null) try { conn.close(); } catch (SQLException e) { }
		}
		return clubKingManager;
	}
	
	@Override
	public void deleteAllClubMember(int clubNo) {
		// 
		deleteAllClubMembership(clubNo, "b");
	}

	@Override
	public void deleteAllClubManager(int clubNo) {
		// 
		deleteAllClubMembership(clubNo, "a");
	}
	
	@Override
	public void deleteAllClubKingManger(int clubNo) {
		// 
		deleteAllClubMembership(clubNo, "c");
		
	}
	private void deleteAllClubMembership(int clubNo, String type) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbConnection.getConnection();
			
			String sql = "DELETE FROM clubmember WHERE club_no = ? AND type = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, clubNo);
			pstmt.setString(2, type);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if ( pstmt != null) try { pstmt.close(); } catch (SQLException e) { }
			if ( conn != null) try { conn.close(); } catch (SQLException e) { }
		}
	}

	@Override
	public List<ClubMember> readAllClubMembers(int clubNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Club> readBelongClubs(String email, int comNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Club> readManagedClubs(String email, int comNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClubMember readClubMember(int clubNo, String email) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
