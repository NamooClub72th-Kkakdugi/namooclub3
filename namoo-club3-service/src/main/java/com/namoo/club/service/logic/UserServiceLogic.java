package com.namoo.club.service.logic;

import java.util.List;

import com.namoo.club.service.facade.UserService;

import dom.entity.SocialPerson;

public class UserServiceLogic implements UserService {

	@Override
	public boolean loginAsUser(String userId, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void registUser(String userId, String naem, String email, String password) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeUser(String userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public SocialPerson findUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SocialPerson> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}
