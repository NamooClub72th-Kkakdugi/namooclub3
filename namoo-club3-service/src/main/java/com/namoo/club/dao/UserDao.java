package com.namoo.club.dao;

import java.util.List;

import dom.entity.SocialPerson;

public interface UserDao {
	//
	List<SocialPerson> readAllUsers();
	SocialPerson readUser(String userId);
	void createUser(SocialPerson user);
	void updateUser(SocialPerson user);
	void deleteUser(String userId);

}
