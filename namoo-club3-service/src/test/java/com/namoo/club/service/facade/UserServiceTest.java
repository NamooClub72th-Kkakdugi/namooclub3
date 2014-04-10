package com.namoo.club.service.facade;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.service.facade.UserService;
import com.namoo.club.service.logic.UserServiceLogic;

import dom.entity.SocialPerson;

public class UserServiceTest {
	//
	private UserService service;
	
	String email = "a_a_a_a";
	String name = "a_a_a_a";
	String password = "a_a_a_a";
	
	@Before
	public void setUp() throws Exception {
		service = new UserServiceLogic();
		service.removeTowner(email);
	}
	
	@After
	public void tearDown() throws Exception {
		service.removeTowner(email);
	}
	
	@Test
	public void testLoginAsTowner() {
		boolean login = service.loginAsTowner(email, password);
		
		assertEquals(true, login);
	}

	@Test
	public void testRegistTowner() {
		//
		service.registTowner(name, email, password);
		
		SocialPerson user = service.findTowner(email);
		assertEquals(password, user.getPassword());
		assertEquals(name, user.getName());
		assertEquals(email, user.getEmail());
	}

	@Test
	public void testFindAllTowner() {
		//
		int before = service.findAllTowner().size();
		service.registTowner(name, email, password);
		int after = service.findAllTowner().size();
		
		assertEquals(before+1, after);
	}
}
