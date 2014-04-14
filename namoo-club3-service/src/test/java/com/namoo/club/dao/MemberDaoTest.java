package com.namoo.club.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.dao.jdbc.MemberDaoJdbc;

import dom.entity.ClubMember;
import dom.entity.CommunityManager;
import dom.entity.CommunityMember;
import dom.entity.SocialPerson;

public class MemberDaoTest extends DbCommonTest{
	//
	private MemberDao dao;
	
	@Before
	public void setUp() throws Exception {
		//
		super.setUp();
		dao = new MemberDaoJdbc();
	}
	
	@After
	public void tearDown() throws Exception {
		//
		super.tearDown();
	}

	@Test
	public void testAddCommunityManager() {
		//
		CommunityManager comManager = new CommunityManager(1, new SocialPerson("wntjd", "이주성"));
		dao.addCommunityManager(1, comManager);
		//검증
		assertEquals("wntjd", dao.readCommunityManager(1).getEmail());
		assertEquals("이주성", dao.readCommunityManager(1).getName());
	}

	@Test
	public void testAddCommunityMember() {
		//
		CommunityMember comMember = new CommunityMember(1, new SocialPerson("wntjd", "이주성"));
		dao.addCommunityMember(1, comMember);
		//검증
		assertEquals("wntjd", dao.readCommunityMember(1, "wntjd").getEmail());
		assertEquals("이주성", dao.readCommunityMember(1, "wntjd").getName());
	}

	@Test
	public void testReadCommunityMember() {
		//
		CommunityMember comMember = dao.readCommunityMember(1, "ekdgml");
		//검증
		assertEquals("박상희", comMember.getName());
	}

	@Test
	public void testReadCommunityManager() {
		//
		CommunityManager comManager = dao.readCommunityManager(1);
		//검증
		assertEquals("박상희", comManager.getName());
	}

	@Test
	public void testReadAllCommunityMember() {
		//
		assertEquals(2, dao.readAllCommunityMember(1).size());
	}

	@Test
	public void testDeleteAllComMember() {
		//
		dao.deleteAllComMember(1);
		assertEquals(1, dao.readAllCommunityMember(1).size());
	}

	@Test
	public void testDeleteCommuninyMember() {
		//
		dao.deleteCommuninyMember(1, "hong");
		assertEquals(1, dao.readAllCommunityMember(1).size());
	}

	@Test
	public void testDeleteCommunityManager() {
		//
		dao.deleteCommunityManager(1);
		assertNull(dao.readCommunityManager(1));
	}

	@Test
	public void testAddClubMember() {
		//
		ClubMember clubMember = new ClubMember(2, new SocialPerson("wntjd", "이주성"));
		dao.addClubMember(clubMember);
		//검증
		clubMember = dao.readClubMember(2, "wntjd");
		assertEquals("이주성", clubMember.getName());
	}

	@Test
	public void testAddClubManager() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddKingManager() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAllClubMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAllClubManager() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteClubMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteClubManager() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteClubKingManger() {
		fail("Not yet implemented");
	}

	@Test
	public void testReadAllClubMembers() {
		fail("Not yet implemented");
	}

	@Test
	public void testReadAllClubManagers() {
		fail("Not yet implemented");
	}

	@Test
	public void testReadClubMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testReadClubManager() {
		fail("Not yet implemented");
	}

	@Test
	public void testReadClubKingManager() {
		fail("Not yet implemented");
	}

}
