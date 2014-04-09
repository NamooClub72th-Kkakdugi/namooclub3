package com.namoo.club.service.facade;

import com.namoo.club.dao.jdbc.CommunityDaoJdbc;
import com.namoo.club.dao.jdbc.UserDaoJdbc;
import com.namoo.club.service.logic.CommunityServiceLogic;
import com.namoo.club.service.logic.UserServiceLogic;

import dom.entity.ClubCategory;
import dom.entity.Community;
import dom.entity.CommunityMember;
import dom.entity.SocialPerson;


public class PrepareBuilder {
	//
	static final String EMAIL = "a_a_a_a";
	static int COM_NO;
	
	public static SocialPerson createUser() {
		UserService service = new UserServiceLogic();
		SocialPerson user = new SocialPerson("abcd", "password", "abcd@abcd.abcd");
		service.registTowner("a_a_a_a", EMAIL, "a_a_a_a");
		return user;
	}
	
	public static void deleteUser() {
		UserService service = new UserServiceLogic();
		service.removeTowner(EMAIL);
	}
	
	public static SocialPerson readUser() {
		UserService service = new UserServiceLogic();
		SocialPerson user = service.findTowner(EMAIL);
		return user;
	}
	
//	public static Community createCommunity() {
//		CommunityService service = new CommunityServiceLogic();
//		SocialPerson author = createUser();
//		Community community = new Community("com_test", "com_test_description", author);
//		COM_NO = service.registCommunity("communityServiceTest", "communityServiceTestDescription", EMAIL);
//		community.setComNo(COM_NO);
//		return community;
//	}
//	
//	public static Community readCommunity() {
//		CommunityDao communityDao = new CommunityDaoJdbc();
//		return communityDao.readCommunity(COM_NO);
//	}
//	
//	public static void deleteCommunity() {
//		CommunityDao dao = new CommunityDaoJdbc();
//		dao.deleteCommunity(COM_NO);
//	}
//	
//	public static int createCategory() {
//		CommunityDao dao = new CommunityDaoJdbc();
//		ClubCategory category = new ClubCategory(COM_NO, "TestCategory");
//		dao.createClubCategory(COM_NO, category);
//		return category.getCategoryNo();
//	}
//	
//	public static CommunityMember addMember() {
//		//
//		CommunityDao dao = new CommunityDaoJdbc();
//		CommunityMember communityMember = new CommunityMember(COM_NO, new SocialPerson(EMAIL));
//		dao.addCommunityMember(communityMember);
//		
//		return communityMember;
//	}
//	
//	public static void deleteMember() {
//		//
//		CommunityDao dao = new CommunityDaoJdbc();
//		dao.deleteAllComMember(COM_NO);
//		
//	}
	
}
