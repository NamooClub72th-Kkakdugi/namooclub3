package com.namoo.club.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.dao.jdbc.ClubDaoJdbc;

import dom.entity.Club;

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
	}
	@After
	public void tearDown() throws Exception {
		//
		memberDao.deleteAllClubMember(clubNo);
		clubDao.deleteClub(clubNo);


	}
	//-----------------------------------------
	@Test
	public void testReadAllClubs() {
	}

	@Test
	public void testReadClub() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateClub() {
		//
		// 검증
		
	}

	@Test
	public void testUpdateClub() {
		//
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
