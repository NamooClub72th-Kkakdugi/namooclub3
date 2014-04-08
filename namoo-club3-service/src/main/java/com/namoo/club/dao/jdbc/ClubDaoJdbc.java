package com.namoo.club.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.namoo.club.dao.ClubDao;

import dom.entity.Club;
import dom.entity.ClubManager;
import dom.entity.ClubMember;
import dom.entity.SocialPerson;

public class ClubDaoJdbc implements ClubDao {

	@Override
	public List<Club> readAllClubs(int comNo) {
		// 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		List<Club> clubs = new ArrayList<>();
		
		try {
			conn = DbConnection.getConnection();
			
			String sql = "SELECT a.club_no, a.com_no, a.category_no, a.club_nm, a.club_des, a.club_date FROM club a"
					+ " INNER JOIN clubmember b ON a.club_no = b.club_no";
			pstmt = conn.prepareStatement(sql);
			
			resultSet = pstmt.executeQuery();
			while(resultSet.next()){
				//
				int categoryNo = resultSet.getInt("category_no");
				comNo = resultSet.getInt("com_no");
				String clubNm = resultSet.getString("club_nm");
				String clubDes = resultSet.getString("club_des");
				Date date = resultSet.getDate("club_date");
				String userId = resultSet.getString("userid");
				
				Club club = new Club(categoryNo, comNo, clubNm, clubDes, new SocialPerson(userId));
				clubs.add(club);
				club.setOpenDate(date);
			}
		}
		 catch(SQLException e) {
			 e.printStackTrace();
		 } finally {
			 if ( resultSet != null) try { resultSet.close(); } catch (SQLException e) { }
			 if ( pstmt != null) try { pstmt.close(); } catch (SQLException e) { }
			 if ( conn != null) try { conn.close(); } catch (SQLException e) { }
		 }
		return clubs;
	}

	@Override
	public Club readClub(int clubNo) {
		// 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Club club = null;
		try {
			conn = DbConnection.getConnection();
			
			String sql = "SELECT a.club_no, a.com_no, a.category_no, a.club_nm, a.club_des, a.club_date FROM club a"
					+ " INNER JOIN clubmember b ON a.club_no = b.club_no WHERE a.club_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, clubNo);
			
			resultSet= pstmt.executeQuery();
			
			if(resultSet.next()) {
				clubNo = resultSet.getInt("club_no");
				int comNo = resultSet.getInt("com_no");
				int categoryNo = resultSet.getInt("category_no");
				String clubName = resultSet.getString("club_nm");
				String clubDes = resultSet.getString("club_des");
				String userId = resultSet.getString("userId");
				
				club = new Club(categoryNo, comNo, clubName, clubDes, new SocialPerson(userId));
				club.setClubNo(clubNo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			 if ( resultSet != null) try { resultSet.close(); } catch (SQLException e) { }
			 if ( pstmt != null) try { pstmt.close(); } catch (SQLException e) { }
			 if ( conn != null) try { conn.close(); } catch (SQLException e) { }
		}

		return club;
	}

	@Override
	public int createClub(int comNo, Club club) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int clubNo = 0;
		
		try {
			conn = DbConnection.getConnection();
			
			String sql = "INSERT INTO club(club_no, com_no, category_no, club_nm, club_des, club_date)"
					+ " VALUES(?, ?, ?, ?, ?, sysdate())";
			pstmt = conn.prepareStatement(sql);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, club.getClubNo());
			pstmt.setInt(2, club.getComNo());
			pstmt.setInt(3, club.getCategoryNo());
			pstmt.setString(4, club.getClubName());
			pstmt.setString(5, club.getClubDes());
			
			pstmt.executeUpdate();
			
			resultSet = pstmt.getGeneratedKeys();
			if(resultSet.next()) {
				clubNo = resultSet.getInt("club_no");
			} 
		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
			 if ( resultSet != null) try { resultSet.close(); } catch (SQLException e) { }
			 if ( pstmt != null) try { pstmt.close(); } catch (SQLException e) { }
			 if ( conn != null) try { conn.close(); } catch (SQLException e) { }
		}
		return clubNo;
	}

	@Override
	public void updateClub(Club club) {
		// 
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbConnection.getConnection();
			
			String sql = "UPDATE club SET club_nm =? club_des = ? club_date = ? WHERE club_no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, club.getClubName());
			pstmt.setString(2, club.getClubDes());
			pstmt.setString(3, club.getClubDes());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if ( pstmt != null) try { pstmt.close(); } catch (SQLException e) { }
			if ( conn != null) try { conn.close(); } catch (SQLException e) { }
		}
	}

	@Override
	public void deleteClub(int clubNo) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbConnection.getConnection();
			
			String sql = "DELETE FROM club WHERE club_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, clubNo);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) try { conn.close(); } catch (SQLException e) { }
			if (pstmt != null) try { pstmt.close(); } catch (SQLException e) { }
		}
	}
	
	//----------------------------------------------
	@Override
	public ClubMember addClubMember(ClubMember clubMember) {
		// 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			conn = DbConnection.getConnection();
			
			String sql = "INSERT INTO clubmember(club_no, user_id, type)"
					+ " VALUES(?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, clubMember.getClubNo());
			pstmt.setString(2, clubMember.getUser().getUserId());
			
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
			
			String sql = "INSERT INTO clubmember(club_no, user_id, type"
					+ " VALUES(?, ?, 1)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, clubManager.getClubNo());
			pstmt.setString(2, clubManager.getUserId());
			
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
	public void deleteAllClubMember(int clubNo) {
		// 
		deleteAllClubMembership(clubNo, "2");
	}

	@Override
	public void deleteAllClubManager(int clubNo) {
		// 
		deleteAllClubMembership(clubNo, "1");
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
}
