package com.namoo.club.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.namoo.club.dao.UserDao;

import dom.entity.SocialPerson;

public class UserDaoJdbc implements UserDao {

	@Override
	public List<SocialPerson> readAllUsers() {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<SocialPerson> users = new ArrayList<SocialPerson>();

		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT email, name, password FROM user";
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				String email = rset.getString("email");
				String name = rset.getString("name");
				String password = rset.getString("password");

				SocialPerson user = new SocialPerson(name, email, password);
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rset != null) try { rset.close(); } catch (SQLException e) { }
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) { }
			if (conn != null) try { conn.close(); } catch (SQLException e) { }
		}
		return users;
	}

	@Override
	public SocialPerson readUser(String email) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		SocialPerson user = null;

		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT email, name, password FROM user WHERE email=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			
			rset = pstmt.executeQuery();
			

			if (rset.next()) {
				String name = rset.getString("name");
				String email2 = rset.getString("email");
				String password = rset.getString("password");
				user = new SocialPerson(name, email2, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rset != null) try { rset.close(); } catch (SQLException e) { }
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) { }
			if (conn != null) try { conn.close(); } catch (SQLException e) { }
		}
		return user;
	}

	@Override
	public void createUser(SocialPerson user) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DbConnection.getConnection();
			String sql = "INSERT INTO user(email, name, password) VALUES(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getPassword());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) { }
			if (conn != null) try { conn.close(); } catch (SQLException e) { }
		}
	}

	@Override
	public void updateUser(SocialPerson user) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DbConnection.getConnection();
			String sql = "UPDATE user SET password=? WHERE email=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getEmail());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) { }
			if (conn != null) try { conn.close(); } catch (SQLException e) { }
		}
	}

	@Override
	public void deleteUser(String email) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DbConnection.getConnection();
			String sql = "DELETE FROM user WHERE email =?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) { }
			if (conn != null) try { conn.close(); } catch (SQLException e) { }
		}
	}

}
