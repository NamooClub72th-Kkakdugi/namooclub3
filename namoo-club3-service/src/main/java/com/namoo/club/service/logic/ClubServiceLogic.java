package com.namoo.club.service.logic;

import java.util.ArrayList;
import java.util.List;

import com.namoo.club.dao.ClubDao;
import com.namoo.club.dao.CommunityDao;
import com.namoo.club.dao.MemberDao;
import com.namoo.club.dao.factory.DaoFactory;
import com.namoo.club.dao.factory.DaoFactory.DbType;
import com.namoo.club.service.facade.ClubService;
import com.namoo.club.shared.exception.NamooClubExceptionFactory;

import dom.entity.Club;
import dom.entity.ClubManager;
import dom.entity.ClubMember;
import dom.entity.Community;
import dom.entity.SocialPerson;

public class ClubServiceLogic implements ClubService {
	//
	private ClubDao clubDao;
	private CommunityDao comDao;
	private MemberDao memberDao;
	
	public ClubServiceLogic() {
		DaoFactory daoFactory = DaoFactory.createFactory(DbType.MariaDB);
		this.clubDao = daoFactory.getClubDao();
		this.comDao = daoFactory.getCommunityDao();
		this.memberDao = daoFactory.getMemberDao();
	}
	//------------------------------------------------------------------------

	@Override
	public Club registClub(int categoryNo, int communityNo, String clubName, String description, String email) {
		// 
		if (isExistClubByName(communityNo, clubName)) {
			throw NamooClubExceptionFactory.createRuntime("이미 존재하는 클럽입니다.");
		}
		
		Club club = new Club(categoryNo, communityNo, clubName, description, new SocialPerson(email));
		int clubNo = clubDao.createClub(communityNo, club);
		
		memberDao.addClubManager(new ClubManager(clubNo, new SocialPerson(email)));
		return club;
	}
	private boolean isExistClubByName(int communityNo, String clubName) {
		//
		Community community = comDao.readCommunity(communityNo);
		List<Club> clubs = community.getClubs();

		if (community != null && !clubs.isEmpty()) {
			for (Club club : clubs) {
				if (club.getName().equals(clubName)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Club findClub(int clubNo) {
		//
		return clubDao.readClub(clubNo);
	}

	@Override
	public void joinAsMember(int clubNo, String email) {
		// 
		Club club = clubDao.readClub(clubNo);
		if(club == null) {
			throw NamooClubExceptionFactory.createRuntime("클럽이 존재하지 않습니다.");
		}
	
		memberDao.addClubMember(new ClubMember(clubNo, new SocialPerson(email)));
	}

	@Override
	public List<Club> findAllClubs(int comNo) {
		//
		return clubDao.readAllClubs(comNo);
	}

	@Override
	public ClubMember findClubMember(int clubNo, String email) {
		//
		Club club = clubDao.readClub(clubNo);
		if (club == null) {
			throw NamooClubExceptionFactory.createRuntime("클럽이 존재하지 않습니다.");
		}
		
		return memberDao.readClubMember(clubNo, email);
	}

	@Override
	public List<ClubMember> findAllClubMember(int clubNo) {
		// 
		Club club = clubDao.readClub(clubNo);
		if (club == null) {
			throw NamooClubExceptionFactory.createRuntime("클럽이 존재하지 않습니다.");
		}
		return memberDao.readAllClubMembers(clubNo);
	}

	@Override
	public int countMembers(int clubNo) {
		// 
		Club club = clubDao.readClub(clubNo);
		if (club == null) {
			throw NamooClubExceptionFactory.createRuntime("클럽이 존재하지 않습니다.");
		}
		return memberDao.readAllClubMembers(clubNo).size();
	}

	@Override
	public void removeClub(int clubNo, int comNo) {
		// 
		if (clubDao.readClub(clubNo) == null) {
			throw NamooClubExceptionFactory.createRuntime("존재하지 않는 클럽입니다.");
		}
		clubDao.deleteClub(clubNo);
	}

	@Override
	public List<Club> findBelongClubs(String email, int comNo) {
		// 
		List<Club> clubs = clubDao.readAllClubs(comNo);
		if (clubs == null) return null;
		
		List<Club> belongs = new ArrayList<>();
		for (Club club : clubs) {
			if (memberDao.readClubMember(club.getComNo(), email) != null) {
				belongs.add(club);
			}
		}
		return belongs;
	}

	@Override
	public List<Club> findManagedClubs(String email, int comNo) {
		// 
		List<Club> clubs = clubDao.readAllClubs(comNo);
		if (clubs == null) return null;

		List<Club> managers = new ArrayList<>();
		for (Club club : clubs) {
			if (memberDao.readClubManager(club.getClubNo(), email) != null) {
				managers.add(club);
			}
		}
		return managers;
		
		
	}

	@Override
	public void withdrawalClub(int clubNo, String email) {
		//
		Club club = clubDao.readClub(clubNo);
		if (club == null) {
			throw NamooClubExceptionFactory.createRuntime("클럽이 존재하지 않습니다.");
		}
		memberDao.deleteClubMember(clubNo, email);
	}

	@Override
	public void commissionFromManagerClub(int clubNo, SocialPerson rolePerson) {
		//
		memberDao.deleteClubManager(clubNo, rolePerson.getEmail());
		memberDao.addClubMember(new ClubMember(clubNo, rolePerson));
	}

	@Override
	public void commissionGoManagerClub(int clubNo, SocialPerson rolePerson) {
		//
		memberDao.deleteClubMember(clubNo, rolePerson.getEmail());
		memberDao.addClubManager(new ClubManager(clubNo, rolePerson));
	}
}
