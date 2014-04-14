package com.namoo.club.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.dao.jdbc.MemberDaoJdbc;

import dom.entity.ClubKingManager;
import dom.entity.ClubManager;
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
		//
		ClubManager clubManager = new ClubManager(2, new SocialPerson("wntjd", "이주성"));
		dao.addClubManager(clubManager);
		//검증
		assertEquals("이주성", dao.readClubManager(2, "wntjd").getName());
	}

	@Test
	public void testAddKingManager() {
		//
		ClubKingManager clubKingManager = new ClubKingManager(2, new SocialPerson("wntjd", "이주성"));
		dao.addKingManager(clubKingManager);
		//검증
		assertEquals("이주성", dao.readClubKingManager(2).getName());
	}

	@Test
	public void testDeleteAllClubMember() {
		//
		dao.deleteAllClubMember(1);
		//검증
		assertEquals(2, dao.readAllClubMembers(1).size());
	}

	@Test
	public void testDeleteAllClubManager() {
		//
		dao.deleteAllClubManager(1);
		//검증
		assertEquals(2, dao.readAllClubMembers(1).size());
	}

	@Test
	public void testDeleteClubMember() {
		//
		dao.deleteClubMember(1, "wntjd");
		//검증
		assertEquals(2, dao.readAllClubMembers(1).size());
	}

	@Test
	public void testDeleteClubManager() {
		//
		dao.deleteClubManager(1, "hong");
		//검증
		assertEquals(2, dao.readAllClubMembers(1).size());
	}

	@Test
	public void testDeleteClubKingManger() {
		//
		dao.deleteClubKingManger(1);
		//검증
		assertEquals(2, dao.readAllClubMembers(1).size());
	}

	@Test
	public void testReadAllClubMembers() {
		//
		assertEquals(3, dao.readAllClubMembers(1).size());
	}

	@Test
	public void testReadAllClubManagers() {
		//
		assertEquals(2, dao.readAllClubManagers(1).size());
	}

	@Test
	public void testReadClubMember() {
		//
		ClubMember clubMember = dao.readClubMember(1, "ekdgml");
		assertEquals("박상희", clubMember.getName());
	}

	@Test
	public void testReadClubManager() {
		//
		ClubManager clubManager = dao.readClubManager(1, "hong");
		assertEquals("홍길동", clubManager.getName());
	}

	@Test
	public void testReadClubKingManager() {
		//
		ClubKingManager clubKingManager = dao.readClubKingManager(1);
		assertEquals("박상희", clubKingManager.getName());
	}

}
