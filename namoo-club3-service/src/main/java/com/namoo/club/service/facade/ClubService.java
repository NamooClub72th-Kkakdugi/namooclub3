package com.namoo.club.service.facade;

import java.util.List;

import dom.entity.Club;
import dom.entity.ClubMember;
import dom.entity.SocialPerson;

public interface ClubService {
	//
	public void registClub(String category, String communityName, String clubName, String description, String email);
	public Club findClub(int clubNo);
	public void joinAsMember(int clubNo, String userId);
	public List<Club> findAllClubs(int clubNo);
	public ClubMember findClubMember(int clubNo, String userId);
	public List<ClubMember> findAllClubMember(int clubNo);
	public int countMembers(int clubNo);
	public void removeClub(int clubNo, int communityNo);
	public List<Club> findBelongclubs(String userId, int communityNo);
	public List<Club> findManagedClubs(String userId, int communityNo);
	public void withdrawalClub(int clubNo, String userId);
	public void commissionManagerCommunity(int clubNo, SocialPerson user);

}
