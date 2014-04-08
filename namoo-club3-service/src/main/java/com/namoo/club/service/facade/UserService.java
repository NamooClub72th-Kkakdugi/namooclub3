package com.namoo.club.service.facade;

import java.util.List;


import dom.entity.SocialPerson;

public interface UserService {
	//
	List<SocialPerson> findAllUsers();
	SocialPerson findUser(String userId);
	void registUser(SocialPerson user);
	void modifyUser(SocialPerson user);
	void removeUser(String userId);
	boolean login(String userId, String password);
}
