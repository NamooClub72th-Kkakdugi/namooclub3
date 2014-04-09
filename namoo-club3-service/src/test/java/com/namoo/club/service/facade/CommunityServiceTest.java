package com.namoo.club.service.facade;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.service.logic.CommunityServiceLogic;

import dom.entity.ClubCategory;
import dom.entity.SocialPerson;

public class CommunityServiceTest {
	//
	private CommunityService service; 
	
	int communityNo;
	String communityName = "CommunityServiceTest";
	String description = "CommunityServiceTestDescription";
	List<ClubCategory> categories = new ArrayList<ClubCategory>();
	
	@Before
	public void setUp() throws Exception {
		service = new CommunityServiceLogic();
		PrepareBuilder.createUser();
	}
	
	@After
	public void tearDown() throws Exception {
		SocialPerson user = PrepareBuilder.readUser();
		service.commissionManagerCommunity(communityNo, user);
		service.removeCommunity(communityNo);
		PrepareBuilder.deleteUser();
	}
	
	@Test
	public void testRegistCommunity() {
		ClubCategory clubCategory1 = new ClubCategory(1, communityNo, "category1");
		ClubCategory clubCategory2 = new ClubCategory(2, communityNo, "category2");
		categories.add(clubCategory1);
		categories.add(clubCategory2);
		service.registCommunity(communityName, description, PrepareBuilder.EMAIL, categories);
	}

	@Test
	public void testFindCommunity() {
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
