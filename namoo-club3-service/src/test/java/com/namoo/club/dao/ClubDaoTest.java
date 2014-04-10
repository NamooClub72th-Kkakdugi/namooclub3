package com.namoo.club.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.dao.jdbc.ClubDaoJdbc;

import dom.entity.Club;
import dom.entity.ClubMember;
import dom.entity.Community;
import dom.entity.SocialPerson;

public class ClubDaoTest {
	
	private ClubDao clubDao;
	private MemberDao memberDao;

	int clubNo;
	int categoryNo;
	String clubName = "test club";
	String clubDes = "test club's description";
	
	@Before
	public void setUp() throws Exception{
		//
		clubDao = new ClubDaoJdbc();
		PrepareBuilder.createCommunity();
		categoryNo = PrepareBuilder.createCategory();
		PrepareBuilder.addMember();
	}
	@After
	public void tearDown() throws Exception {
		//
		memberDao.deleteAllClubMember(clubNo);
		clubDao.deleteClub(clubNo);


		PrepareBuilder.deleteMember();
		PrepareBuilder.deleteCommunity();
		PrepareBuilder.deleteUser();
	}
	//-----------------------------------------
	@Test
	public void testReadAllClubs() {
		Community community = PrepareBuilder.readCommunity();
		int before = clubDao.readAllClubs(community.getComNo()).size();
		createClub();
		int after = clubDao.readAllClubs(community.getComNo()).size();
		assertEquals(before+1, after);
	}

	@Test
	public void testReadClub() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateClub() {
		//
		createClub();
		// 검증
		Club club = clubDao.readClub(clubNo);
		assertEquals(categoryNo, club.getCategoryNo());
		assertEquals(PrepareBuilder.COM_NO, club.getComNo());
		assertEquals(clubDes, club.getDescription());
		assertEquals(clubName, club.getName());
		assertEquals(clubNo, club.getClubNo());
		
	}
	private void createClub() {

		SocialPerson socialPerson = PrepareBuilder.readUser();
		Club club = new Club(categoryNo, PrepareBuilder.COM_NO, clubName, clubDes, socialPerson);
		clubNo = clubDao.createClub(PrepareBuilder.COM_NO, club);
		ClubMember clubMember = new ClubMember(clubNo, socialPerson);
		
		memberDao.addClubMember(clubMember);
	}

	@Test
	public void testUpdateClub() {
		//
		createClub();
		
		Club club = clubDao.readClub(clubNo);
		club.setName("Test Club2");
		club.setDescription("Test Club2's description");
		
		clubDao.updateClub(club);
		
		club = clubDao.readClub(clubNo);
		assertEquals("Test Club2", club.getName());
		assertEquals("Test Club2's description", club.getDescription());
	}

	@Test
	public void testDeleteClub() {
		fail("Not yet implemented");
	}

}
