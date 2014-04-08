package com.namoo.club.service.facade;

import java.util.List;

import dom.entity.Community;
import dom.entity.CommunityMember;
import dom.entity.SocialPerson;

public interface CommunityService {
	//
	public void registCommunity(Community community);
	public Community findCommunity(int communityNo);
	public void joinAsMember(int communityNo, String userId);
	public List<Community> findAllCommunities();
	public CommunityMember findCommunityMember(int communityNo, String userId);
	public List<CommunityMember> findAllCommunityMember(int communityNo);
	public int countMembers(int communityNo);
	void modifyCommunity(Community community);
	public void removeCommunity(int communityNo);
	public List<Community> findBelongCommunities(String userId);
	public List<Community> findManagedCommunities(String userId);
	public void withdrawalCommunity(int communityNo, String userId);
	public void commissionManagerCommunity(int communityNo, SocialPerson user);
	
}
