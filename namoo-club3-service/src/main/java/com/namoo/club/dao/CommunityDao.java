package com.namoo.club.dao;

import java.util.List;

import dom.entity.ClubCategory;
import dom.entity.Community;
import dom.entity.CommunityManager;
import dom.entity.CommunityMember;

public interface CommunityDao {
	//
	//Community
	List<Community> readAllCommunities();
	Community readCommunity(int comNo);
	int createCommunity(Community community);
	void updateCommunity(Community community);
	void deleteCommunity(int comNo);
	
	//Member, Manager
	CommunityMember addCommunityMember(CommunityMember comMember);
	CommunityManager addCommunityManager(CommunityManager comManager);
	CommunityMember readCommunityMember(int comNo, String email);
	List<CommunityMember> readAllCommunityMember(int comNo);
	CommunityManager readCommunityManager(int comNo, String email);
	void deleteAllComMember(int comNo);
	void deleteAllComManager(int comNo);
	void deleteCommuninyMember(int comNo, String email);
	void deleteCommunityManager(int comNo, String email);
	
	// ClubCategory
	List<ClubCategory> readAllCategories(int comNo);
	int createClubCategory(int comNo, ClubCategory category);

}
