package com.namoo.club.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sun.font.CreatedFontTracker;

import com.namoo.club.dao.jdbc.ClubDaoJdbc;

import dom.entity.Club;
import dom.entity.Community;
import dom.entity.SocialPerson;

public class ClubDaoTest {
	
	private ClubDao clubDao;

	int clubNo;
	String clubName = "test club";
	String clubDes = "test club's description";
	
	@Before
	public void setUp() throws Exception{
		//
		clubDao = new ClubDaoJdbc();
		PrepareBuilder.createCommunity();
	}
	@After
	public void tearDown() throws Exception {
		//
		clubDao.deleteClub(clubNo);
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
		assertEquals(PrepareBuilder.COM_NO, club.getComNo());
		assertEquals(clubDes, club.getClubDes());
		assertEquals(clubName, club.getClubName());
		assertEquals(clubNo, club.getClubNo());
		
	}
	private void createClub() {
		SocialPerson socialPerson = PrepareBuilder.readUser();
		Club club = new Club(PrepareBuilder.COM_NO, clubName, clubDes, socialPerson);
		clubNo = clubDao.createClub(PrepareBuilder.COM_NO, club);
	}

	@Test
	public void testUpdateClub() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteClub() {
		fail("Not yet implemented");
	}

}
