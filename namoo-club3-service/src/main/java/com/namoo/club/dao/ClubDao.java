package com.namoo.club.dao;

import java.util.List;

import dom.entity.Club;
import dom.entity.ClubManager;
import dom.entity.ClubMember;

public interface ClubDao {
	//
	List<Club> readAllClubs(int comNo);
	ClubMember addClubMember(ClubMember clubMember);
	ClubManager addClubManager(ClubManager clubManager);
	ClubMember deleteClubMember(ClubMember clubMember);
	ClubManager deleteClubManager(ClubManager clubManager);
	Club readClub(int clubNo);
	int createClub(int comNo, Club club);
	void updateClub(Club club);
	void deleteClub(int clubNo);

}
