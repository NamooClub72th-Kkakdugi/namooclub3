package com.namoo.club.dao;

import com.namoo.club.dao.jdbc.CommunityDaoJdbc;
import com.namoo.club.dao.jdbc.MemberDaoJdbc;
import com.namoo.club.dao.jdbc.UserDaoJdbc;

import dom.entity.ClubCategory;
import dom.entity.Community;
import dom.entity.CommunityMember;
import dom.entity.SocialPerson;


public class PrepareBuilder {
	//
	static final String EMAIL = "abcd";
	static int COM_NO;
	
	public static SocialPerson createUser() {
		UserDao dao = new UserDaoJdbc();
		SocialPerson user = new SocialPerson("abcd", "password", "abcd@abcd.abcd");
		dao.createUser(user);
		return user;
	}
	
	public static void deleteUser() {
		UserDao dao = new UserDaoJdbc();
		dao.deleteUser(EMAIL);
	}
	
	public static SocialPerson readUser() {
		UserDao dao = new UserDaoJdbc();
		SocialPerson user = dao.readUser(EMAIL);
		return user;
	}
	
	public static Community createCommunity() {
		CommunityDao dao = new CommunityDaoJdbc();
		SocialPerson author = createUser();
		Community community = new Community("com_test", "com_test_description", author);
		COM_NO = dao.createCommunity(community);
		community.setComNo(COM_NO);
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
	
	public static int createCategory() {
		CommunityDao dao = new CommunityDaoJdbc();
		ClubCategory category = new ClubCategory(1, COM_NO, "TestCategory");
		dao.createClubCategory(COM_NO, category);
		return category.getCategoryNo();
	}
	
	public static CommunityMember addMember() {
		//
		MemberDao memberDao = new MemberDaoJdbc();
		CommunityMember communityMember = new CommunityMember(COM_NO, new SocialPerson(EMAIL));
		memberDao.addCommunityMember(COM_NO, communityMember);
		
		return communityMember;
	}
	
	public static void deleteMember() {
		//
		MemberDao memberDao = new MemberDaoJdbc();
		memberDao.deleteAllComMember(COM_NO);
		
	}
	
}
