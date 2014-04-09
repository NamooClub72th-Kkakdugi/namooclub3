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
			String sql = "SELECT a.com_no, a.com_nm, a.com_des, a.com_date, b.email FROM community a " +
					"INNER JOIN communitymember b ON a.com_no = b.com_no AND b.is_manager='1' ";
			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				int comNo = rset.getInt("com_no");
				String comName = rset.getString("com_nm");
				String comDescription = rset.getString("com_des");
				Date date = rset.getDate("com_date");
				String email = rset.getString("email");

				Community community = new Community(comName, comDescription, new SocialPerson(email));
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
			String sql = "SELECT a.com_no, a.com_nm, a.com_des, a.com_date, b.email FROM community a";
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

		try {
			conn = DbConnection.getConnection();
			String sql = "INSERT INTO community(com_nm, com_des, com_date) VALUES (?, ?, sysdate())";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, community.getName());
			pstmt.setString(2, community.getDescription());

			pstmt.executeUpdate();

			rset = pstmt.getGeneratedKeys();
			if (rset.next()) {
				community.setComNo(rset.getInt("com_no"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rset != null)try {rset.close();} catch (SQLException e) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException e) {}
			if (conn != null)try {conn.close();} catch (SQLException e) {}
		}
		return community.getComNo();

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
			String sql = "INSERT INTO communitymember(com_no, email, is_manager) VALUES (?, ?, 1)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, comManager.getCommunityNo());
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
	public CommunityMember addCommunityMember(CommunityMember comMember) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		CommunityMember member = null;

		try {
			conn = DbConnection.getConnection();
			String sql = "INSERT INTO communitymember(com_no, email, is_manager) VALUES (?, ?, 2)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, comMember.getCommunityNo());
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
				
				ClubCategory category = new ClubCategory(comNo2, categoryName);
				category.setCategoryNo(categoryNo);
				categories.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rset != null)try {rset.close();} catch (SQLException e) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException e) {}
			if (conn != null)try {conn.close();} catch (SQLException e) {}
		}
		return categories;
	}
	
	@Override
	public int createClubCategory(int comNo, ClubCategory category) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			conn = DbConnection.getConnection();
			String sql = "INSERT INTO clubcategory(com_no, category_nm) VALUES (?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, comNo);
			pstmt.setString(2, category.getCategoryName());
			
			pstmt.executeUpdate();
			rset = pstmt.getGeneratedKeys();
			if (rset.next()) {
				category.setCategoryNo(rset.getInt("category_no"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rset != null)try {rset.close();} catch (SQLException e) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException e) {}
			if (conn != null)try {conn.close();} catch (SQLException e) {}
		}
		return category.getCategoryNo();
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
		} finally {
			if (pstmt != null)try {pstmt.close();} catch (SQLException e) {	}
			if (conn != null)try {conn.close();} catch (SQLException e) {}
		}
		return communities;
	}

}
