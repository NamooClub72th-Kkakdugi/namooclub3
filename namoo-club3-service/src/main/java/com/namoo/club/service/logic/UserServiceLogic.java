package com.namoo.club.service.logic;

import java.util.List;

import com.namoo.club.dao.CommunityDao;
import com.namoo.club.dao.UserDao;
import com.namoo.club.dao.factory.DaoFactory;
import com.namoo.club.dao.factory.DaoFactory.DbType;
import com.namoo.club.service.facade.UserService;
import com.namoo.club.service.logic.exception.NamooClubException;

import dom.entity.Community;
import dom.entity.SocialPerson;

public class UserServiceLogic implements UserService {
	//
	private UserDao userDao;
	private CommunityDao comDao;
	
	public UserServiceLogic() {
		//
		DaoFactory daoFactory = DaoFactory.createFactory(DbType.MariaDB);
		this.userDao = daoFactory.getUserDao();
		this.comDao = daoFactory.getCommunityDao();
	}

	@Override
	public List<SocialPerson> findAllUsers() {
		//
		return userDao.readAllUsers();
	}

	@Override
	public SocialPerson findUser(String userId) {
		//
		return userDao.readUser(userId);
	}

	@Override
	public void registUser(SocialPerson user) {
		//
		if (userDao.readUser(user.getUserId()) != null) {
			throw new NamooClubException("이미 가입되어있는 사용자입니다.");
		}
		userDao.createUser(user);
	}

	@Override
	public void modifyUser(SocialPerson user) {
		//
		if (userDao.readUser(user.getUserId()) == null) {
			throw new NamooClubException("해당하는 사용자가 없습니다.");
		} 
		userDao.updateUser(user);
	}

	@Override
	public void removeUser(String userId) {
		//
		List<Community> communities = comDao.readAllCommunities();
		if (communities != null) {
			for (Community community : communities) {
				if (userId.equals(community.getManager().getUserId())) {
					throw new NamooClubException("게시판 관리자는 탈퇴할 수 없습니다.");
				}
				userDao.deleteUser(userId);
			}
		}
	}

	@Override
	public boolean login(String userId, String password) {
		//
		SocialPerson user = userDao.readUser(userId);
		if (user != null && password.equals(user.getPassword())) {
			return true;
		}
		return false;
	}
	
	

}
