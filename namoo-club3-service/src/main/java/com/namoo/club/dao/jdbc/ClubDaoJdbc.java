package com.namoo.club.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.namoo.club.dao.ClubDao;
import com.sun.org.apache.bcel.internal.generic.NEWARRAY;

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
			
			String sql = "SELECT club_no, com_no, category_no, club_nm, club_des, club_date";
			pstmt = conn.prepareStatement(sql);
			
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()){
				//
				
				comNo = Integer.parseInt(resultSet.getString("com_no"));
				String clubNm = resultSet.getString("club_nm");
				String clubDes = resultSet.getString("club_des");
				
				Club club = new Club(comNo, clubNm, clubDes, new SocialPerson());
				clubs.add(club);
			}
		}
		 catch(SQLException e) {
			 e.printStackTrace();
		 } finally {
			 if ( resultSet != null) try { resultSet.close(); } catch (SQLException e) { }
			 if ( pstmt != null) try { resultSet.close(); } catch (SQLException e) { }
			 if ( conn != null) try { resultSet.close(); } catch (SQLException e) { }
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
			
			String sql = "SELECT club_no, com_no, category_no, club_nm, club_des, club_date WHERE club_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, clubNo);
			
			resultSet= pstmt.executeQuery();
			
			if(resultSet.next()) {
				clubNo = resultSet.getInt("club_no");
				int comNo = resultSet.getInt("com_no");
				int categoryNO = resultSet.getInt("category_no");
				String clubNm = resultSet.getString("club_nm");
				String clubDes = resultSet.getString("club_des");
				
				club = new Club(comNo, clubNm, clubDes, new SocialPerson());
				club.setClubNo(clubNo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			 if ( resultSet != null) try { resultSet.close(); } catch (SQLException e) { }
			 if ( pstmt != null) try { resultSet.close(); } catch (SQLException e) { }
			 if ( conn != null) try { resultSet.close(); } catch (SQLException e) { }
		}
		
		
	}

	@Override
	public void createClub(int comNo, Club club) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			conn = DbConnection.getConnection();
			
			String sql = "SELECT club_no, com_no, category_no, club_nm, club_des, club_date FROM club_tb club_no = ?";
			pstmt = conn.prepareStatement(sql);
		}

	}

	@Override
	public void updateClub(Club club) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteClub(int clubNo) {
		// TODO Auto-generated method stub

	}

}
