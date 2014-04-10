package com.namoo.club.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.namoo.club.dao.CommunityDao;
import com.namoo.club.shared.exception.NamooClubExceptionFactory;

import dom.entity.ClubCategory;
import dom.entity.Community;

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
			String sql = "SELECT com_no, com_nm, com_des, com_date FROM community";
			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				int comNo = rset.getInt("com_no");
				String comName = rset.getString("com_nm");
				String comDescription = rset.getString("com_des");
				Date date = rset.getDate("com_date");

				Community community = new Community(comName, comDescription);
				community.setComNo(comNo);
				community.setOpenDate(date);
				communities.add(community);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw NamooClubExceptionFactory.createRuntime("커뮤니티목록을 조회하는 중 오류가 발생하였습니다.");
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
			String sql = "SELECT com_no, com_nm, com_des, com_date FROM community WHERE com_no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, comNo);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				String comName = rset.getString("com_nm");
				String description = rset.getString("com_des");
				Date date = rset.getDate("com_date");
				int comNo2 = rset.getInt("com_no");

				community = new Community(comName, description);
				community.setComNo(comNo2);
				community.setOpenDate(date);
			}
		} catch (SQLException e) {
			e.printStackTrace();throw NamooClubExceptionFactory.createRuntime("커뮤니티 번호를 받아 커뮤니티를 조회하는 중 오류가 발생하였습니다.");
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

		try {
			conn = DbConnection.getConnection();
			String sql = "INSERT INTO community(com_nm, com_des, com_date) VALUES (?, ?, sysdate())";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, community.getName());
			pstmt.setString(2, community.getDescription());

			pstmt.executeUpdate();

			rset = pstmt.getGeneratedKeys();
			if (rset.next()) {
				community.setComNo(rset.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw NamooClubExceptionFactory.createRuntime("커뮤니티를 생성하는 중 오류가 발생하였습니다.");
		} finally {
			if (rset != null)try {rset.close();} catch (SQLException e) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException e) {}
			if (conn != null)try {conn.close();} catch (SQLException e) {}
		}
		return community.getComNo();

	}

	

	@Override
	public void updateCommunity(Community community) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DbConnection.getConnection();
			String sql = "UPDATE community SET com_nm=?, com_des=?, com_date=sysdate() WHERE com_no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, community.getName());
			pstmt.setString(2, community.getDescription());
			pstmt.setInt(3, community.getComNo());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw NamooClubExceptionFactory.createRuntime("커뮤니티정보를 업데이트하는 중 오류가 발생하였습니다.");
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
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();throw NamooClubExceptionFactory.createRuntime("커뮤니티를 삭제하는 중 오류가 발생하였습니다.");
		} finally {
			if (pstmt != null)try {pstmt.close();} catch (SQLException e) {}
			if (conn != null)try {conn.close();} catch (SQLException e) {}
		}
	}

	
	@Override
	public List<ClubCategory> readAllCategories(int comNo) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<ClubCategory> categories = new ArrayList<ClubCategory>();
		
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT category_no, com_no, category_nm FROM clubcategory";
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				int categoryNo = rset.getInt("category_no");
				int comNo2 = rset.getInt("com_no");
				String categoryName = rset.getString("category_nm");
				
				ClubCategory category = new ClubCategory(categoryNo, comNo2, categoryName);
				categories.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw NamooClubExceptionFactory.createRuntime("카테고리목록을 조회하는 중 오류가 발생하였습니다.");
		} finally {
			if (rset != null)try {rset.close();} catch (SQLException e) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException e) {}
			if (conn != null)try {conn.close();} catch (SQLException e) {}
		}
		return categories;
	}
	
	@Override
	public void createClubCategory(int comNo, ClubCategory category) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			conn = DbConnection.getConnection();
			String sql = "INSERT INTO clubcategory(category_no, com_no, category_nm) VALUES (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, category.getCategoryNo());
			pstmt.setInt(2, comNo);
			pstmt.setString(3, category.getCategoryName());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw NamooClubExceptionFactory.createRuntime("카테고리를 생성하는 중 오류가 발생하였습니다.");
		} finally {
			if (rset != null)try {rset.close();} catch (SQLException e) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException e) {}
			if (conn != null)try {conn.close();} catch (SQLException e) {}
		}
	}



	@Override
	public List<Community> readAllManagedCommunities(String email) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
	    List<Community> communities = new ArrayList<Community>();
	    
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT a.com_no, a.com_nm, a.com_des, a.com_date, b.email FROM community a " +
					"INNER JOIN communitymember b ON a.com_no = b.com_no AND b.is_manager='1'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				int comNo = rset.getInt("com_no");
				String name = rset.getString("com_nm");
				String description = rset.getString("com_des");
				Date date = rset.getDate("com_date");
				Community community = new Community(name, description);
				community.setComNo(comNo);
				community.setOpenDate(date);
				communities.add(community);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw NamooClubExceptionFactory.createRuntime("관리하는 커뮤니티목록을 조회하는 중 오류가 발생하였습니다.");
		} finally {
			if (pstmt != null)try {pstmt.close();} catch (SQLException e) {	}
			if (conn != null)try {conn.close();} catch (SQLException e) {}
		}
		return communities;
	}

	@Override
	public void deleteAllClubCategory(int comNo) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn= DbConnection.getConnection();
			String sql = "DELETE FROM clubcategory WHERE com_no=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, comNo);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw NamooClubExceptionFactory.createRuntime("카테고리를 모두 삭제하는 중 오류가 발생하였습니다.");
		} finally {
			if (pstmt != null)try {pstmt.close();} catch (SQLException e) {	}
			if (conn != null)try {conn.close();} catch (SQLException e) {}
		}
		
	}

}
