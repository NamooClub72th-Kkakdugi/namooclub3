package com.namoo.club.service.facade;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.service.logic.ClubServiceLogic;

import dom.entity.Club;

public class ClubServiceTest extends DbCommonTest {
	//
	private ClubService clubService;

	@Before
	public  void seUp() throws Exception {
		//
		super.setUp();
		clubService = new ClubServiceLogic();
	}
	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}
	
	//-------------------------------------------------------------
	@Test
	public void testRegistClub() {
		//
		Club club = clubService.registClub(1, 1, "TestClub", "TestClub'description", "wntjd1211");
		
		// 검증
		club = clubService.findClub(club.getClubNo());
		assertEquals("TestClub", club.getName());
		
	}

	@Test
	public void testFindClub() {
		fail("Not yet implemented");
	}

	@Test
	public void testJoinAsMemberIntStringStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testJoinAsMemberIntString() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllClubs() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindClubMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllClubMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountMembers() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveClub() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindBelongClubs() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindManagedClubs() {
		fail("Not yet implemented");
	}

	@Test
	public void testWithdrawalClub() {
		fail("Not yet implemented");
	}

	@Test
	public void testCommissionManagerCommunity() {
		fail("Not yet implemented");
	}

}
