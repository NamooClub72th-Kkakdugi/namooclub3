package com.namoo.club.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.dao.jdbc.CommunityDaoJdbc;
import com.namoo.club.dao.jdbc.MemberDaoJdbc;

import dom.entity.Community;
import dom.entity.CommunityManager;
import dom.entity.CommunityMember;
import dom.entity.SocialPerson;

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
		PrepareBuilder.createUser();
	}
	
	@After
	public void tearDown() throws Exception {
		//
		memberDao.deleteAllComManager(comNo);
		memberDao.deleteAllComMember(comNo);
		dao.deleteCommunity(comNo);
		PrepareBuilder.deleteUser();
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
		SocialPerson user = PrepareBuilder.readUser();
		Community community = new Community(comName, comDescription, user);
		comNo = dao.createCommunity(community);
		
		CommunityManager comManager = new CommunityManager(comNo, user);
		CommunityMember comMember = new CommunityMember(comNo, user);
		if (memberDao.readAllCommunityMember(comNo).contains(user)) {
			memberDao.addCommunityManager(comNo, comManager);
		} else {
			memberDao.addCommunityMember(comNo, comMember);
		}
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
