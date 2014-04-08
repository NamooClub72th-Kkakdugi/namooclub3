package com.namoo.club.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.dao.jdbc.CommunityDaoJdbc;
import com.namoo.club.dao.jdbc.UserDaoJdbc;

import dom.entity.Community;
import dom.entity.SocialPerson;

public class CommunityDaoTest {
	//
	private CommunityDao dao;
	
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
		PrepareBuilder.deleteUser();
		dao.deleteCommunity(comNo);
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
	}

	@Test
	public void testUpdateCommunity() {
		fail("Not yet implemented");
	}

}
