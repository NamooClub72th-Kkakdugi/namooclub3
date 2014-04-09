package com.namoo.club.dao.factory;

import com.namoo.club.dao.ClubDao;
import com.namoo.club.dao.CommunityDao;
import com.namoo.club.dao.MemberDao;
import com.namoo.club.dao.UserDao;
import com.namoo.club.dao.jdbc.MariaDBDaoFactory;



public abstract class DaoFactory {
	//
	public static enum DbType {
		MariaDB
	}
	
	public static DaoFactory createFactory(DbType dbType) {
		//
		if (dbType == DbType.MariaDB) {
			return new MariaDBDaoFactory();
		}
		return null;
	}
	
	public abstract UserDao getUserDao();
	public abstract CommunityDao getCommunityDao();
	public abstract ClubDao getClubDao();
	public abstract MemberDao getMemberDao();

}
