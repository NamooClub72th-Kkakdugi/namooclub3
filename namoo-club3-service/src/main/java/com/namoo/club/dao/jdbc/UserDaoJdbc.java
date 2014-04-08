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
		} catch (SQLException e){
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public SocialPerson readUser(String userId) {
		//
		return null;
	}

	@Override
	public void createUser(SocialPerson user) {
		//

	}

	@Override
	public void updateUser(SocialPerson user) {
		//

	}

	@Override
	public void deleteUser(String userId) {
		//

	}

}
