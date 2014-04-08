package com.namoo.club.service.facade;

import java.util.List;

import dom.entity.SocialPerson;

public interface UserService {
	//
	boolean loginAsUser(String userId, String password);
	void registUser(String userId, String naem, String email, String password);
	void removeUser(String userId);
	SocialPerson findUser(String userId);
	List<SocialPerson> findAllUsers();
}
