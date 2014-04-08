package com.namoo.club.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.dao.jdbc.CommunityDaoJdbc;
import com.namoo.club.dao.jdbc.UserDaoJdbc;

public class CommunityDaoTest {
	//
	private CommunityDao dao;
	
	int comNo;
	@Before
	public void setUp() throws Exception {
		dao = new CommunityDaoJdbc();
		PrepareBuilder.createUser();
		dao.deleteCommunity(comNo);
	}
	
	@After
	public void tearDown() throws Exception {
		PrepareBuilder.deleteUser();
	}
	
	@Test
	public void testReadAllCommunities() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateCommunity() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateCommunity() {
		fail("Not yet implemented");
	}

}
