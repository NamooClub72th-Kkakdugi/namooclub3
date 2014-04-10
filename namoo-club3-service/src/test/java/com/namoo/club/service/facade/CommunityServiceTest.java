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
	
	@Test
	public void testRegistCommunity() {
		//
		List<ClubCategory> categories = new ArrayList<>();
		ClubCategory category1 = new ClubCategory(1, 3, "category1"); 
		ClubCategory category2 = new ClubCategory(2, 3, "category2"); 
		categories.add(category1);
		categories.add(category2);
		service.registCommunity("com3", "com3_des", "ekdgml", categories);
		
		//검증
		
	}

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
		service.joinAsMember(2, "wntjd");
		
		//검증
		CommunityMember member = service.findCommunityMember(2, "wntjd");
		assertEquals("wntjd", member.getEmail());
	}

	@Test
	public void testFindAllCommunities() {
		//
		List<Community> communities = service.findAllCommunities();
		
		//검증
		assertEquals(2, communities.size());
	}

	@Test
	public void testRemoveCommunity() {
		//
		service.removeCommunity(1, true);
		
		List<Community> communities = service.findAllCommunities();
		assertEquals(1, communities.size());
	}

	@Test
	public void testFindBelongCommunities() {
		//
		
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

}
