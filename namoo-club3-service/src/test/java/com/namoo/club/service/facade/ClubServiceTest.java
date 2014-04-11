package com.namoo.club.service.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.service.logic.ClubServiceLogic;

import dom.entity.Club;
import dom.entity.ClubMember;
import dom.entity.SocialPerson;

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
		Club club = clubService.registClub(1, 1, "TestClub", "TestClub'description", "wntjd");
		
		// 검증
		club = clubService.findClub(club.getClubNo());
		assertEquals("TestClub", club.getName());
		
	}

	@Test
	public void testFindClub() {
		//
		Club club = clubService.findClub(1);
		// 검증
		assertEquals("club1", club.getName());
	}

	@Test
	public void testJoinAsMember() {
		//
		clubService.joinAsMember(4, "wntjd");
		
		//
		clubService.findClubMember(4, "wntjd");
		
	}

	@Test
	public void testFindAllClubs() {
		//
		List<Club> clubs = clubService.findAllClubs(1);
		//검증
		assertEquals(4, clubs.size());
	}

	@Test
	public void testFindClubMember() {
		//
		ClubMember clubMember = clubService.findClubMember(1, "wntjd");
		
		// 검증
		assertEquals("wntjd", clubMember.getEmail());
	}

	@Test
	public void testFindAllClubMember() {
		//
		int clubMembers = clubService.findAllClubMember(1).size();
		// 검증
		assertEquals(3, clubMembers);
	}

	@Test
	public void testRemoveClub() {
		//
		clubService.removeClub(1, 1);
		// 검증
		assertEquals(3, clubService.findAllClubs(1).size());
		
	}

	@Test
	public void testFindBelongClubs() {
		//
		List<Club> clubs = clubService.findBelongClubs("wntjd", 1);
		// 검증
		assertEquals(2, clubs.size());
		
	}

	@Test
	public void testFindManagedClubs() {
		//
		List<Club> managedClubs = clubService.findManagedClubs("wntjd", 1);
		//
		assertEquals(2, managedClubs.size());
	}

	@Test
	public void testWithdrawalClub() {
		//
		clubService.withdrawalClub(1, "wntjd");
		//검증
		assertEquals(0, clubService.findAllClubMember(1).size());
	}

	@Test
	public void testCommissionFromManagerCommunity() {
		//
		clubService.commissionFromManagerClub(1, new SocialPerson("hong"));
		
		//
		assertEquals(1, clubService.findAllClubManager(1).size());
	}
	
	@Test
	public void testCommissionGoManagerClub() {
		//
		clubService.commissionGoManagerClub(1, new SocialPerson("wntjd"));
		//
		assertEquals(3, clubService.findAllClubManager(1).size());
		
	}
	
	@Test
	public void testCommissionGoKingManagerClub() {
		//
		clubService.commissionGoKingManagerClub(1, new SocialPerson("ekdgml"), new SocialPerson("hong"));
		//
		assertEquals("hong", clubService.findClubKingManager(1).getEmail());
		
	}
}
