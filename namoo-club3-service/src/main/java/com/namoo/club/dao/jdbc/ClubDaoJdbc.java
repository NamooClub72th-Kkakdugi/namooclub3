package com.namoo.club.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.namoo.club.dao.ClubDao;
import com.sun.org.apache.bcel.internal.generic.NEWARRAY;

import dom.entity.Club;

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
			
			String sql = "SELECT "
		}
	}

	@Override
	public Club readClub(int clubNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createClub(int comNo, Club club) {
		// TODO Auto-generated method stub

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
