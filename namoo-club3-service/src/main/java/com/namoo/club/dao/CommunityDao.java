package com.namoo.club.dao;

import java.util.List;

import dom.entity.Community;

public interface CommunityDao {
	//
	List<Community> readAllCommunities();
	Community readCommunity(int comNo);
	void createCommunity(Community community);
	void updateCommunity(Community community);
	void deleteCommunity(int comNo);

}
