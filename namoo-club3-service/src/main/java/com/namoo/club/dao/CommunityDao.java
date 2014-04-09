package com.namoo.club.dao;

import java.util.List;

import dom.entity.ClubCategory;
import dom.entity.Community;
import dom.entity.CommunityManager;
import dom.entity.CommunityMember;

public interface CommunityDao {
	//
	List<Community> readAllCommunities();
	CommunityMember addCommunityMember(CommunityMember comMember);
	CommunityManager addCommunityManager(CommunityManager comManager);
	CommunityMember readCommunityMember(int comNo, String userId);
	Community readCommunity(int comNo);
	int createCommunity(Community community);
	void updateCommunity(Community community);
	void deleteCommunity(int comNo);
	void deleteAllComMember(int comNo);
	void deleteAllComManager(int comNo);
	void deleteCommuninyMember(int comNo, String userId);
	void deleteCommunityManager(int comNo, String userId);
	
	// 클럽 카테고리 관련
	List<ClubCategory> readAllCategories(int comNo);
	void createClubCategory(int comNo, ClubCategory category);

}
