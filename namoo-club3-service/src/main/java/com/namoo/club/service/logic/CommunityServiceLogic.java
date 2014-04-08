package com.namoo.club.service.logic;

import java.util.ArrayList;
import java.util.List;

import com.namoo.club.dao.CommunityDao;
import com.namoo.club.dao.factory.DaoFactory;
import com.namoo.club.dao.factory.DaoFactory.DbType;
import com.namoo.club.service.facade.CommunityService;
import com.namoo.club.service.logic.exception.NamooClubExceptionFactory;

import dom.entity.ClubCategory;
import dom.entity.Community;
import dom.entity.CommunityMember;
import dom.entity.SocialPerson;

public class CommunityServiceLogic implements CommunityService {
	//
	private CommunityDao dao;
	
	public CommunityServiceLogic() {
		DaoFactory daoFactory = DaoFactory.createFactory(DbType.MariaDB);
		this.dao = daoFactory.getCommunityDao();
	}
	
	@Override
	public void registCommunity(Community community) {
		//
		if (isExistCommunityByName(community.getComName())) {
			throw NamooClubExceptionFactory.createRuntime("이미 존재하는 게시판입니다.");
		}
		dao.createCommunity(community);
	}
	
	private boolean isExistCommunityByName(String communityName) {
		//
		List<Community> communities = dao.readAllCommunities();

		if (communities != null && !communities.isEmpty()) {
			for (Community community : communities) {
				if (community.getComName().equals(communityName)) {
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
	public void joinAsMember(int communityNo, String userId) {
		//
		Community community = dao.readCommunity(communityNo);
		
		if (community == null) {
			throw NamooClubExceptionFactory.createRuntime("커뮤니티가 존재하지 않습니다.");
		}
		
		SocialPerson user = new SocialPerson(userId);
		community.addMember(user);
	}

	@Override
	public List<Community> findAllCommunities() {
		//
		return dao.readAllCommunities();
	}

	@Override
	public CommunityMember findCommunityMember(int communityNo, String userId) {
		//
		Community community = dao.readCommunity(communityNo);
		
		if (community == null) {
			throw NamooClubExceptionFactory.createRuntime("커뮤니티가 존재하지 않습니다.");
		}
		
		for (CommunityMember member : community.getMembers()) {
			if (member.getUser().getUserId().equals(userId)) {
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
	public void modifyCommunity(Community community) {
		//
		dao.updateCommunity(community);
	}

	@Override
	public void removeCommunity(int communityNo) {
		//
		dao.deleteCommunity(communityNo);
	}

	@Override
	public List<Community> findBelongCommunities(String userId) {
		//
		List<Community> commnities = dao.readAllCommunities();
		if (commnities == null) return null;
		
		List<Community> belongs = new ArrayList<>();
		for (Community community : commnities) {
			if (community.findMember(userId) != null) {
				belongs.add(community);
			}
		}
		return belongs;
	}

	@Override
	public List<Community> findManagedCommunities(String userId) {
		//
		List<Community> commnities = dao.readAllCommunities();
		if (commnities == null) return null;
		
		List<Community> manages = new ArrayList<>();
		for (Community community : commnities) {
			if (community.getManager().getUserId().equals(userId)) {
				manages.add(community);
			}
		}
		return manages;
	}

	@Override
	public void withdrawalCommunity(int communityNo, String userId) {
		//
		Community community = dao.readCommunity(communityNo);
		if (community == null) {
			throw NamooClubExceptionFactory.createRuntime("커뮤니티가 존재하지 않습니다.");
		}
		
		community.removeMember(userId);
	}

	@Override
	public void commissionManagerCommunity(int communityNo, SocialPerson user) {
		//
		Community community = dao.readCommunity(communityNo);
		community.setManager(user);
	}

	@Override
	public List<ClubCategory> findAllCategories(int communityNo) {
		//
		return dao.readAllCategories(communityNo);
	}

	@Override
	public void registCategory(int communityNo, ClubCategory category) {
		//
		dao.createClubCategory(communityNo, category);
	}


}
