package com.namoo.club.service.facade;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.service.facade.UserService;
import com.namoo.club.service.logic.UserServiceLogic;

import dom.entity.SocialPerson;

public class UserServiceTest extends DbCommonTest{
	//
	private UserService service;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		service = new UserServiceLogic();
	}
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}
	
	@Test
	public void testLoginAsTowner() {
		boolean login = service.loginAsTowner("ekdgml", "asdf");
		
		assertEquals(true, login);
	}

	@Test
	public void testRegistTowner() {
		//
		service.registTowner("박상희2", "ekdgml2", "asdf2");
		
		SocialPerson user = service.findTowner("ekdgml2");
		assertEquals("asdf2", user.getPassword());
		assertEquals("박상희2", user.getName());
		assertEquals("ekdgml2", user.getEmail());
	}

	@Test
	public void testFindAllTowner() {
		//
		List<SocialPerson> users = service.findAllTowner();
		
		assertEquals(3, users.size());
	}
}
