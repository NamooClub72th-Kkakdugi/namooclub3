package com.namoo.club.dao;

import java.util.List;

import dom.entity.Club;
import dom.entity.ClubKingManager;
import dom.entity.ClubManager;
import dom.entity.ClubMember;

public interface ClubDao {
	//
	List<Club> readAllClubs(int comNo);
	Club readClub(int clubNo);
	int createClub(int comNo, Club club);
	void updateClub(Club club);
	void deleteClub(int clubNo);

	//
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
