package com.namoo.club.dao;

import com.namoo.club.dao.jdbc.CommunityDaoJdbc;
import com.namoo.club.dao.jdbc.UserDaoJdbc;

import dom.entity.Community;
import dom.entity.SocialPerson;


public class PrepareBuilder {
	//
	static final String USER_ID = "_a_a_test";
	static int COM_NO;
	
	public static SocialPerson createUser() {
		UserDao dao = new UserDaoJdbc();
		SocialPerson user = new SocialPerson(USER_ID, "abcd", "password", "abcd@abcd.abcd");
		dao.createUser(user);
		return user;
	}
	
	public static void deleteUser() {
		UserDao dao = new UserDaoJdbc();
		dao.deleteUser(USER_ID);
	}
	
	public static SocialPerson readUser() {
		UserDao dao = new UserDaoJdbc();
		SocialPerson user = dao.readUser(USER_ID);
		return user;
	}
	
	public static Community createCommunity() {
		CommunityDao dao = new CommunityDaoJdbc();
		SocialPerson author = createUser();
		Community community = new Community("com_test", "com_test_description", author);
		COM_NO = dao.createCommunity(community);
		community.setComNo(COM_NO);;
		return community;
	}
	
	public static Community readCommunity() {
		CommunityDao communityDao = new CommunityDaoJdbc();
		return communityDao.readCommunity(COM_NO);
	}
	
	public static void deleteCommunity() {
		CommunityDao dao = new CommunityDaoJdbc();
		dao.deleteCommunity(COM_NO);
	}
	
}
