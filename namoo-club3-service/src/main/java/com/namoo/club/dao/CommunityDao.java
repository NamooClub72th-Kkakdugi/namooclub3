package com.namoo.club.dao;

import java.util.List;

import dom.entity.Community;
import dom.entity.CommunityMember;
import dom.entity.SocialPerson;

public interface CommunityDao {
	//
	List<Community> readAllCommunities();
	//CommunityMember addCommunityMember(CommunityMember comMember);
	Community readCommunity(int comNo);
	int createCommunity(Community community);
	void updateCommunity(Community community);
	void deleteCommunity(int comNo);

}
