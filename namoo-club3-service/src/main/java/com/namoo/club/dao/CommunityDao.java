package com.namoo.club.dao;

import java.util.List;

import dom.entity.Community;
import dom.entity.CommunityManager;
import dom.entity.CommunityMember;

public interface CommunityDao {
	//
	List<Community> readAllCommunities();
	CommunityMember addCommunityMember(CommunityMember comMember);
	CommunityManager addCommunityManager(CommunityManager comManager);
	Community readCommunity(int comNo);
	int createCommunity(Community community);
	void updateCommunity(Community community);
	void deleteCommunity(int comNo);
	void deleteAllComMember(int comNo);
	void deleteAllComManager(int comNo);

}
