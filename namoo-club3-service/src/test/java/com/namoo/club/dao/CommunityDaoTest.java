package com.namoo.club.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.dao.jdbc.CommunityDaoJdbc;
import com.namoo.club.dao.jdbc.MemberDaoJdbc;

import dom.entity.Community;

public class CommunityDaoTest {
	//
	private CommunityDao dao;
	private MemberDao memberDao;
	
	int comNo;
	String comName = "com_test";
	String comDescription = "com_test_description";
	
	@Before
	public void setUp() throws Exception {
		//
		memberDao = new MemberDaoJdbc();
		dao = new CommunityDaoJdbc();
	}
	
	@After
	public void tearDown() throws Exception {
		//
		memberDao.deleteCommunityManager(comNo);
		memberDao.deleteAllComMember(comNo);
		dao.deleteCommunity(comNo);
	}
	//-------------------------------------------
	@Test
	public void testReadAllCommunities() {
		//
		int before = dao.readAllCommunities().size();
		createCommunity();
		int after = dao.readAllCommunities().size();
		
		assertEquals(before+1, after);
	}
	@Test
	public void testCreateCommunity() {
		//
		createCommunity();
		
		//검증
		Community community = dao.readCommunity(comNo);
		assertEquals(comNo, community.getComNo());
		assertEquals(comName, community.getName());
		assertEquals(comDescription, community.getDescription());
	}

	private void createCommunity() {
		//
	}

	@Test
	public void testUpdateCommunity() {
		//
		createCommunity();
		Community community = dao.readCommunity(comNo);
		community.setName("after Community");
		community.setDescription("after Community Description");
		
		dao.updateCommunity(community);
		
		community = dao.readCommunity(comNo);
		assertEquals("after Community", community.getName());
		assertEquals("after Community Description", community.getDescription());
	}
}
