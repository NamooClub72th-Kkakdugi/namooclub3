package com.namoo.club.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.namoo.club.dao.ClubDao;

import dom.entity.Club;
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
			
			String sql = "SELECT a.club_no, a.com_no, a.category_no, a.club_nm, a.club_des, a.club_date FROM club a INNER JOIN clubmember b on a.club_no = b.club_no";
			pstmt = conn.prepareStatement(sql);
			
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()){
				//
				
				comNo = Integer.parseInt(resultSet.getString("com_no"));
				String clubNm = resultSet.getString("club_nm");
				String clubDes = resultSet.getString("club_des");
				String userId = resultSet.getString("userid");
				
				Club club = new Club(comNo, clubNm, clubDes, new SocialPerson(userId));
				clubs.add(club);
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
			
			String sql = "SELECT a.club_no, a.com_no, a.category_no, a.club_nm, a.club_des, a.club_date FROM club a INNER JOIN clubmember b on a.club_no = b.club_no WHERE club_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, clubNo);
			
			resultSet= pstmt.executeQuery();
			
			if(resultSet.next()) {
				clubNo = resultSet.getInt("club_no");
				int comNo = resultSet.getInt("com_no");
//				int categoryNo = resultSet.getInt("category_no");
				String clubName = resultSet.getString("club_nm");
				String clubDes = resultSet.getString("club_des");
				String userId = resultSet.getString("userId");
				
				club = new Club(comNo, clubName, clubDes, new SocialPerson(userId));
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
	public void createClub(int comNo, Club club) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			conn = DbConnection.getConnection();
			
			String sql = "INSERT INTO club_tb(club_no, com_no, category_no, club_nm, club_des, club_date) VALUES(?, ?, ?, ?, ?, sysdate())";
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
				comNo = resultSet.getInt("com_no");
			} 
		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
			 if ( resultSet != null) try { resultSet.close(); } catch (SQLException e) { }
			 if ( pstmt != null) try { pstmt.close(); } catch (SQLException e) { }
			 if ( conn != null) try { conn.close(); } catch (SQLException e) { }
		}
	}

	@Override
	public void updateClub(Club club) {
		// 
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbConnection.getConnection();
			
			String sql = "UPDATE club_tb  SET club_nm =? club_des = ? club_date = ? WHERE club_no = ?";
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
			
			String sql = "DELETE FROM club_tb WHERE club_no = ?";
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

}
