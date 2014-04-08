package com.namoo.club.service.facade;

import java.util.List;

import dom.entity.Club;
import dom.entity.ClubMember;
import dom.entity.SocialPerson;

public interface ClubService {
	//
	public void registClub(int communityNo, Club club);
	public Club findClub(int clubNo);
	public void joinAsMember(int clubNo, SocialPerson socialPerson);
	public List<Club> findAllClubs(int comNo);
	public ClubMember findClubMember(int clubNo, String userId);
	public List<ClubMember> findAllClubMember(int clubNo);
	public int countMembers(int clubNo);
	public void removeClub(int clubNo);
	public List<Club> findBelongclubs(String userId, int communityNo);
	public List<Club> findManagedClubs(String userId, int communityNo);
	public void withdrawalClub(int clubNo, String userId);
	public void commissionManagerCommunity(int clubNo, SocialPerson user);

}
