package com.namoo.club.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.dao.jdbc.CommunityDaoJdbc;

import dom.entity.Community;
import dom.entity.CommunityManager;
import dom.entity.CommunityMember;
import dom.entity.SocialPerson;

public class CommunityDaoTest {
	//
	private CommunityDao dao;
	private UserDao userDao;
	
	int comNo;
	String comName = "com_test";
	String comDescription = "com_test_description";
	
	@Before
	public void setUp() throws Exception {
		//
		dao = new CommunityDaoJdbc();
		PrepareBuilder.createUser();
	}
	
	@After
	public void tearDown() throws Exception {
		//
		dao.deleteAllComManager(comNo);
		dao.deleteAllComMember(comNo);
		dao.deleteCommunity(comNo);
		PrepareBuilder.deleteUser();
	}
	
	@Test
	public void testReadAllCommunities() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateCommunity() {
		//
		createCommunity();
		
		//검증
		Community community = dao.readCommunity(comNo);
		assertEquals(comNo, community.getComNo());
		assertEquals(comName, community.getComName());
		assertEquals(comDescription, community.getDescription());
	}

	private void createCommunity() {
		//
		SocialPerson user = PrepareBuilder.readUser();
		Community community = new Community(comName, comDescription, user);
		comNo = dao.createCommunity(community);
		
		CommunityManager comManager = new CommunityManager(comNo, user);
		CommunityMember comMember = new CommunityMember(comNo, user);
		if (community.getMembers().contains(user)) {
		dao.addCommunityManager(comManager);
		} else {
			dao.addCommunityMember(comMember);
		}
	}

	@Test
	public void testUpdateCommunity() {
		fail("Not yet implemented");
	}
	
	

}
