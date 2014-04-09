package com.namoo.club.service.logic;

import java.util.ArrayList;
import java.util.List;

import com.namoo.club.dao.CommunityDao;
import com.namoo.club.dao.UserDao;
import com.namoo.club.dao.factory.DaoFactory;
import com.namoo.club.dao.factory.DaoFactory.DbType;
import com.namoo.club.service.facade.CommunityService;
import com.namoo.club.shared.exception.NamooClubExceptionFactory;

import dom.entity.ClubCategory;
import dom.entity.Community;
import dom.entity.CommunityManager;
import dom.entity.CommunityMember;
import dom.entity.SocialPerson;

public class CommunityServiceLogic implements CommunityService {
	//
	private CommunityDao dao;
	private UserDao userDao;
	
	public CommunityServiceLogic() {
		DaoFactory daoFactory = DaoFactory.createFactory(DbType.MariaDB);
		this.dao = daoFactory.getCommunityDao();
		this.userDao = daoFactory.getUserDao();
	}
	
	@Override
	public void registCommunity(String communityName, String description, String email) {
		//
		if (isExistCommunityByName(communityName)) {
			throw NamooClubExceptionFactory.createRuntime("이미 존재하는 게시판입니다.");
		}
		Community community = new Community(communityName, description, new SocialPerson(email));
		int communityNo = dao.createCommunity(community);
		
		CommunityManager comManager = new CommunityManager(communityNo, new SocialPerson(email));
		dao.addCommunityManager(comManager);
	}

	private boolean isExistCommunityByName(String communityName) {
		//
		List<Community> communities = dao.readAllCommunities();

		if (communities != null && !communities.isEmpty()) {
			for (Community community : communities) {
				if (community.getName().equals(communityName)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Community findCommunity(int communityNo) {
		//
		return dao.readCommunity(communityNo);
	}

	@Override
	public void joinAsMember(int communityNo, String name, String email, String password) {
		//
		Community community = dao.readCommunity(communityNo);
		
		if (community == null) {
			throw NamooClubExceptionFactory.createRuntime("커뮤니티가 존재하지 않습니다.");
		}
		
		if (dao.readCommunityMember(communityNo, email) != null) {
			throw NamooClubExceptionFactory.createRuntime("이미 커뮤니티 회원입니다.");
		}
		
		SocialPerson user = new SocialPerson(email);
		userDao.createUser(user);
		community.addMember(user);
	}

	@Override
	public void joinAsMember(int communityNo, String email) {
		//
		Community community = dao.readCommunity(communityNo);
		
		if (community == null) {
			throw NamooClubExceptionFactory.createRuntime("커뮤니티가 존재하지 않습니다.");
		}
		community.addMember(new SocialPerson(email));
	}

	@Override
	public List<Community> findAllCommunities() {
		//
		return dao.readAllCommunities();
	}

	@Override
	public CommunityMember findCommunityMember(int communityNo, String email) {
		// 
		Community community = dao.readCommunity(communityNo);
		
		if (community == null) {
			throw NamooClubExceptionFactory.createRuntime("커뮤니티가 존재하지 않습니다.");
		}
		
		for (CommunityMember member : community.getMembers()) {
			if (member.getEmail().equals(email)) {
				return member;
			}
		}
		
		return null;
	}

	@Override
	public List<CommunityMember> findAllCommunityMember(int communityNo) {
		//
		Community community = dao.readCommunity(communityNo);
		
		if (community == null) {
			throw NamooClubExceptionFactory.createRuntime("커뮤니티가 존재하지 않습니다.");
		}
		return community.getMembers();
	}

	@Override
	public int countMembers(int communityNo) {
		//
		Community community = dao.readCommunity(communityNo);
		if (community != null) {
			return community.getMembers().size();
		}
		return 0;
	}

	@Override
	public void removeCommunity(int communityNo) {
		//
		dao.deleteCommunity(communityNo);
	}

	@Override
	public List<Community> findBelongCommunities(String email) {
		//
		List<Community> commnities = dao.readAllCommunities();
		if (commnities == null) return null;
		
		List<Community> belongs = new ArrayList<>();
		for (Community community : commnities) {
			if (community.findMember(email) != null) {
				belongs.add(community);
			}
		}
		return belongs;
	}

	@Override
	public List<Community> findManagedCommnities(String email) {
		//
		List<Community> commnities = dao.readAllCommunities();
		if (commnities == null) return null;
		
		List<Community> managers = new ArrayList<>();
		for (Community community : commnities) {
			if (community.getManager().getEmail().equals(email)) {
				managers.add(community);
			}
		}
		return managers;
	}

	@Override
	public void withdrawalCommunity(int communityNo, String email) {
		//
		Community community = dao.readCommunity(communityNo);
		if (community == null) {
			throw NamooClubExceptionFactory.createRuntime("커뮤니티가 존재하지 않습니다.");
		}
		
		community.removeMember(email);
	}

	@Override
	public void commissionManagerCommunity(int communityNo, SocialPerson rolePerson) {
		//
		Community community = dao.readCommunity(communityNo);
		community.setManager(rolePerson);
		dao.deleteCommunityManager(communityNo, rolePerson.getEmail());
		dao.addCommunityMember(new CommunityMember(communityNo, rolePerson));
	}

	@Override
	public List<ClubCategory> findAllCategories(int communityNo) {
		//
		return dao.readAllCategories(communityNo);
	}

	@Override
	public void registCategory(int communityNo, List<ClubCategory> categories) {
		//
		for (ClubCategory category : categories) {
			dao.createClubCategory(communityNo, category);
		}
	}

}
