package com.namoo.club.service.logic;

import java.util.ArrayList;
import java.util.List;

import com.namoo.club.dao.CommunityDao;
import com.namoo.club.dao.MemberDao;
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
	private MemberDao memberDao;
	
	public CommunityServiceLogic() {
		DaoFactory daoFactory = DaoFactory.createFactory(DbType.MariaDB);
		this.dao = daoFactory.getCommunityDao();
		this.userDao = daoFactory.getUserDao();
		this.memberDao = daoFactory.getMemberDao();
	}
	
	@Override
	public Community registCommunity(String communityName, String description, String email, List<ClubCategory> categories) {
		//
		if (isExistCommunityByName(communityName)) {
			throw NamooClubExceptionFactory.createRuntime("이미 존재하는 게시판입니다.");
		}
		Community community = new Community(communityName, description, new SocialPerson(email));
		int communityNo = dao.createCommunity(community);
		
		CommunityManager comManager = new CommunityManager(communityNo, new SocialPerson(email));
		memberDao.addCommunityManager(communityNo, comManager);
		
		// 카테고리 추가
		registCategory(communityNo, categories);
		
		return community;
	}
	
	public void registCategory(int communityNo, List<ClubCategory> categories) {
		//
		for (ClubCategory category : categories) {
			dao.createClubCategory(communityNo, category);
		}
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
		
		if (memberDao.readCommunityMember(communityNo, email) != null) {
			throw NamooClubExceptionFactory.createRuntime("이미 커뮤니티 회원입니다.");
		}
		
		SocialPerson user = new SocialPerson(email);
		userDao.createUser(user);
		memberDao.addCommunityMember(communityNo, new CommunityMember(communityNo, user));
	}

	@Override
	public void joinAsMember(int communityNo, String email) {
		//
		Community community = dao.readCommunity(communityNo);
		
		if (community == null) {
			throw NamooClubExceptionFactory.createRuntime("커뮤니티가 존재하지 않습니다.");
		}
		memberDao.addCommunityMember(communityNo, new CommunityMember(communityNo, new SocialPerson(email)));
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
		
		for (CommunityMember member : memberDao.readAllCommunityMember(communityNo)) {
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
		return memberDao.readAllCommunityMember(communityNo);
	}

	@Override
	public int countMembers(int communityNo) {
		//
		Community community = dao.readCommunity(communityNo);
		if (community != null) {
			return memberDao.readAllCommunityMember(communityNo).size();
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
			if (memberDao.readCommunityMember(community.getComNo(), email) != null) {
				belongs.add(community);
			}
		}
		return belongs;
	}

	@Override
	public List<Community> findManagedCommunities(String email) {
		//
		List<Community> commnities = dao.readAllCommunities();
		if (commnities == null) return null;
		
		List<Community> managers = new ArrayList<>();
		for (Community community : commnities) {
			if (!dao.readAllManagedCommunities(email).isEmpty()) {
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
		memberDao.deleteCommuninyMember(communityNo, email);
	}

	@Override
	public void commissionManagerCommunity(int communityNo, SocialPerson rolePerson) {
		//
		memberDao.deleteCommunityManager(communityNo, rolePerson.getEmail());
		memberDao.addCommunityMember(communityNo, new CommunityMember(communityNo, rolePerson));
	}
	
	@Override
	public void commissionMemberCommunity(int communityNo, SocialPerson rolePerson) {
		//
		memberDao.addCommunityManager(communityNo, new CommunityManager(communityNo, rolePerson));
	}

	@Override
	public List<ClubCategory> findAllCategories(int communityNo) {
		//
		return dao.readAllCategories(communityNo);
	}
}
