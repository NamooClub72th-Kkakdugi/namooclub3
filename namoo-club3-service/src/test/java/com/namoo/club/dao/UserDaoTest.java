package com.namoo.club.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.dao.jdbc.UserDaoJdbc;

import dom.entity.SocialPerson;


public class UserDaoTest extends DbCommonTest{
	//
	private UserDao dao;
	
	@Before
	public void setUp() throws Exception {
		//
		super.setUp();
		dao = new UserDaoJdbc();
	}
	
	@After
	public void tearDown() throws Exception {
		//
		super.tearDown();
	}
	@Test
	public void testReadAllUsers() {
		//
		assertEquals(3, dao.readAllUsers().size());
	}

	@Test
	public void testCreateUser() {
		//
		SocialPerson user = new SocialPerson("abcd", "abcd@a.a", "abcd");
		dao.createUser(user);
		
		//검증
		user = dao.readUser("abcd@a.a");
		assertEquals("abcd", user.getPassword());
		assertEquals("abcd", user.getName());
		assertEquals("abcd@a.a", user.getEmail());
	}

	@Test
	public void testUpdateUser() {
		//
		SocialPerson user = dao.readUser("ekdgml");
		user.setPassword("asdfasdf");
		dao.updateUser(user);
		//검증
		user = dao.readUser("ekdgml");
		assertEquals("asdfasdf", user.getPassword());
	}

}
