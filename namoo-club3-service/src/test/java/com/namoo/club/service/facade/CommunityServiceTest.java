package com.namoo.club.service.facade;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.service.facade.CommunityService;
import com.namoo.club.service.logic.CommunityServiceLogic;

import dom.entity.ClubCategory;
import dom.entity.Community;
import dom.entity.CommunityMember;
import dom.entity.SocialPerson;

public class CommunityServiceTest extends DbCommonTest{
	//
	private CommunityService service; 
	
	
	@Before
	public void setUp() throws Exception {
		//
		super.setUp();
		service = new CommunityServiceLogic();
	}
	
	@After
	public void tearDown() throws Exception {
		//
		super.tearDown();
	}
	
//	@Test
//	public void testRegistCommunity() {
//		//
//		List<ClubCategory> categories = new ArrayList<>();
//		ClubCategory category1 = new ClubCategory("1", communityNo, "category1"); 
//		service.registCommunity("com3", "com3_des", "ekdgml", categories);
//	}

	@Test
	public void testFindCommunity() {
		//
		Community community = service.findCommunity(1);
		
		//검증
		assertEquals("com1", community.getName());
	}

	@Test
	public void testJoinAsMemberAndUser() {
		//
		service.joinAsMember(2, "abcd", "abcdId", "jkl");
		
		//검증
		CommunityMember member = service.findCommunityMember(2, "abcdId");
		assertEquals("abcdId", member.getEmail());
	}

	@Test
	public void testJoinAsMember() {
		//
		service.joinAsMember(2, "hong");
		
		//검증
		CommunityMember member = service.findCommunityMember(2, "hong");
		assertEquals("hong", member.getEmail());
	}

	@Test
	public void testFindAllCommunities() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindCommunityMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllCommunityMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountMembers() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveCommunity() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindBelongCommunities() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindManagedCommnities() {
		fail("Not yet implemented");
	}

	@Test
	public void testWithdrawalCommunity() {
		fail("Not yet implemented");
	}

	@Test
	public void testCommissionManagerCommunity() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllCategories() {
		fail("Not yet implemented");
	}

	@Test
	public void testRegistCategory() {
		fail("Not yet implemented");
	}

}
