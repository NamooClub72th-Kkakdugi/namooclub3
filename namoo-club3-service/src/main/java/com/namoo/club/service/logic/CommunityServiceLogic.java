package com.namoo.club.service.logic;

import java.util.List;

import com.namoo.club.service.facade.CommunityService;

import dom.entity.Community;
import dom.entity.CommunityMember;
import dom.entity.SocialPerson;

public class CommunityServiceLogic implements CommunityService {

	@Override
	public void registCommunity(String communityName, String description, String userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Community findCommunity(int communityNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void joinAsMember(int communityNo, String userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Community> findAllCommunities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommunityMember findCommunityMember(int communityNo, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommunityMember> findAllCommunityMember(int communityNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countMembers(int communityNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeCommunity(int communityNo) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Community> findBelongCommunities(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Community> findManagedCommunities(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void withdrawalCommunity(int communityNo, String userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void commissionManagerCommunity(int communityNo, SocialPerson user) {
		// TODO Auto-generated method stub

	}

}
