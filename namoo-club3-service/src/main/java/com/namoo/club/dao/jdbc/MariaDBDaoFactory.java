package com.namoo.club.dao.jdbc;

import com.namoo.club.dao.ClubDao;
import com.namoo.club.dao.CommunityDao;
import com.namoo.club.dao.UserDao;
import com.namoo.club.dao.factory.DaoFactory;


public class MariaDBDaoFactory extends DaoFactory {

	@Override
	public UserDao getUserDao() {
		//
		return new UserDaoJdbc();
	}

	@Override
	public CommunityDao getCommunityDao() {
		//
		return new CommunityDaoJdbc();
	}

	@Override
	public ClubDao getClubDao() {
		//
		return new ClubDaoJdbc();
	}


}
