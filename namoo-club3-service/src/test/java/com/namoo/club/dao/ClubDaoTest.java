package com.namoo.club.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.dao.jdbc.ClubDaoJdbc;

import dom.entity.Club;
import dom.entity.SocialPerson;


public class ClubDaoTest extends DbCommonTest {
	
	private ClubDao dao;
	
	@Before
	public void setUp() throws Exception{
		//
		super.setUp();
		dao = new ClubDaoJdbc();
	}
	@After
	public void tearDown() throws Exception {
		//
		super.tearDown();
	}
	//-----------------------------------------
	@Test
	public void testReadAllClubs() {
		//
		assertEquals(4, dao.readAllClubs(1).size());
	}

	@Test
	public void testReadClub() {
		//
		Club club = dao.readClub(1);
		assertEquals(1, club.getCategoryNo());
		assertEquals("club1_des", club.getDescription());
		assertEquals("club1", club.getName());
	}

	@Test
	public void testCreateClub() {
		//
		Club club = new Club(1, 1, "club_5", "club_5_des", new SocialPerson("ekdgml", "박상희"));
		int clubNo = dao.createClub(1, club);
		//검증
		club = dao.readClub(clubNo);
		assertEquals(1, club.getCategoryNo());
		assertEquals(1, club.getComNo());
		assertEquals("club_5", club.getName());
		assertEquals("club_5_des", club.getDescription());
	}

	@Test
	public void testUpdateClub() {
		//
		Club club = dao.readClub(2);
		club.setName("club_2_test");
		club.setDescription("club_2_des_test");
		dao.updateClub(club);
		//검증
		club = dao.readClub(2);
		assertEquals("club_2_test", club.getName());
		assertEquals("club_2_des_test", club.getDescription());
	}

	@Test
	public void testDeleteClub() {
		//
		dao.deleteClub(1);
		assertEquals(3, dao.readAllClubs(1).size());
	}

}
