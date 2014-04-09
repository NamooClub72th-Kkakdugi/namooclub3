package com.namoo.club.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.dao.jdbc.UserDaoJdbc;

import dom.entity.SocialPerson;


public class UserDaoTest {
	//
	private UserDao dao;
	
	String email = "ekdgml";
	String password = "abcd";
	String name = "박상희";
	
	@Before
	public void setUp() throws Exception {
		dao = new UserDaoJdbc();
		dao.deleteUser("ekdgml");
	}
	
	@After
	public void tearDown() throws Exception {
		dao.deleteUser("ekdgml");
	}
	@Test
	public void testReadAllUsers() {
		int before = dao.readAllUsers().size();
		createUser();
		int after = dao.readAllUsers().size();
		assertEquals(before+1, after);
	}

	@Test
	public void testCreateUser() {
		createUser();
		
		//검증
		SocialPerson user = dao.readUser(email);
		assertEquals(password, user.getPassword());
		assertEquals(name, user.getName());
		assertEquals(email, user.getEmail());
	}

	private void createUser() {
		SocialPerson user = new SocialPerson(name, email, password);
		dao.createUser(user);
	}

	@Test
	public void testUpdateUser() {
		createUser();
		SocialPerson user = dao.readUser(email);
		user.setPassword("aaaa");
		
		dao.updateUser(user);
		
		user = dao.readUser(email);
		assertEquals("aaaa", user.getPassword());
	}

}
