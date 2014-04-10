package com.namoo.club.dao;

import java.util.List;

import dom.entity.Club;
import dom.entity.ClubKingManager;
import dom.entity.ClubManager;
import dom.entity.ClubMember;
import dom.entity.CommunityManager;
import dom.entity.CommunityMember;

public interface MemberDao {
	//
	//community
	CommunityMember addCommunityMember(int comNo, CommunityMember comMember);
	CommunityManager addCommunityManager(int comNo, CommunityManager comManager);
	CommunityMember readCommunityMember(int comNo, String email);
	List<CommunityMember> readAllCommunityMember(int comNo);
	CommunityManager readCommunityManager(int comNo);
	void deleteAllComMember(int comNo);
	void deleteAllComManager(int comNo);
	void deleteCommuninyMember(int comNo, String email);
	void deleteCommunityManager(int comNo, String email);
	
	//club
	List<ClubMember> readAllClubMembers(int clubNo);
	List<Club> readBelongClubs(String email, int comNo);
	List<Club> readManagedClubs(String email, int comNo);
	ClubMember readClubMember(int clubNo, String email);
	ClubMember addClubMember(ClubMember clubMember);
	ClubManager addClubManager(ClubManager clubManager);
	ClubKingManager addKingManager(ClubKingManager clubKingManager);
	void deleteAllClubMember(int clubNo);
	void deleteAllClubManager(int clubNo);
	void deleteAllClubKingManger(int clubNo);
}
