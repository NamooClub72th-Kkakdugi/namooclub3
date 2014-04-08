package com.namoo.club.service.logic;

import java.util.List;

import com.namoo.club.service.facade.ClubService;

import dom.entity.Club;
import dom.entity.ClubMember;
import dom.entity.SocialPerson;

public class ClubServiceLogic implements ClubService {

	@Override
	public void registClub(String category, String communityName, String clubName, String description, String email) {
		// TODO Auto-generated method stub

	}

	@Override
	public Club findClub(int clubNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void joinAsMember(int clubNo, String userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Club> findAllClubs(int clubNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClubMember findClubMember(int clubNo, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClubMember> findAllClubMember(int clubNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countMembers(int clubNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeClub(int clubNo, int communityNo) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Club> findBelongclubs(String userId, int communityNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Club> findManagedClubs(String userId, int communityNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void withdrawalClub(int clubNo, String userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void commissionManagerCommunity(int clubNo, SocialPerson user) {
		// TODO Auto-generated method stub

	}

}
