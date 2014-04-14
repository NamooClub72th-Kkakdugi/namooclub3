package com.namoo.club.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.dao.jdbc.CommunityDaoJdbc;

import dom.entity.ClubCategory;
import dom.entity.Community;
import dom.entity.SocialPerson;

public class CommunityDaoTest extends DbCommonTest {
	//
	private CommunityDao dao;
	
	@Before
	public void setUp() throws Exception {
		//
		super.setUp();
		dao = new CommunityDaoJdbc();
	}
	
	@After
	public void tearDown() throws Exception {
		//
		super.tearDown();
	}
	//-------------------------------------------
	@Test
	public void testReadAllCommunities() {
		//
		assertEquals(2, dao.readAllCommunities().size());
	}
	@Test
	public void testCreateCommunity() {
		//
		Community community = new Community("com3", "com3_des", new SocialPerson("wntjd", "이주성"));
		int comNo = dao.createCommunity(community);
		//검증
		community = dao.readCommunity(comNo);
		assertEquals("com3", community.getName());
		assertEquals("com3_des", community.getDescription());
	}

	@Test
	public void testUpdateCommunity() {
		//
		Community community = dao.readCommunity(1);
		community.setName("com1_test");
		community.setDescription("com1_des_test");
		dao.updateCommunity(community);
		
		//검증
		community = dao.readCommunity(1);
		assertEquals("com1_test", community.getName());
		assertEquals("com1_des_test", community.getDescription());
	}
	
	@Test
	public void testDeleteCommunity() {
		//
		dao.deleteCommunity(2);
		//검증
		assertEquals(1, dao.readAllCommunities().size());
	}
	
	
	@Test
	public void testReadAllCategories() {
		//
		assertEquals(2, dao.readAllCategories(1).size());
	}
	
	@Test
	public void testCreateClubCategory() {
		//
		ClubCategory category = new ClubCategory(2, 2, "category2");
		dao.createClubCategory(2, category);
		//검증
		assertEquals(2, dao.readAllCategories(2).size());
	}
	
	@Test
	public void testDeleteAllClubCategory() {
		//
		dao.deleteAllClubCategory(2);
		assertEquals(0, dao.readAllCategories(2).size());
	}
}
