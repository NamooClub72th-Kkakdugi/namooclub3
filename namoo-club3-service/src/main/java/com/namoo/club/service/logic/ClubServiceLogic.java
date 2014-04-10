package com.namoo.club.service.logic;

import java.util.List;

import com.namoo.club.dao.ClubDao;
import com.namoo.club.dao.CommunityDao;
import com.namoo.club.dao.MemberDao;
import com.namoo.club.dao.UserDao;
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
	private SocialPerson socialPerson;
	private UserDao userDao;
	private MemberDao memberDao;
	
	public ClubServiceLogic() {
		DaoFactory daoFactory = DaoFactory.createFactory(DbType.MariaDB);
		this.clubDao = daoFactory.getClubDao();
		this.comDao = daoFactory.getCommunityDao();
		this.memberDao = daoFactory.getMemberDao();
	}
	//------------------------------------------------------------------------

	@Override
	public void registClub(int categoryNo, int communityNo, String clubName, String description, String email) {
		// 
		if (isExistClubByName(communityNo, clubName)) {
			throw NamooClubExceptionFactory.createRuntime("이미 존재하는 클럽입니다.");
		}
		
		Club club = new Club(categoryNo, communityNo, clubName, description, new SocialPerson(email));
		int clubNo = clubDao.createClub(communityNo, club);
		
		memberDao.addClubManager(new ClubManager(clubNo, new SocialPerson(email)));
		
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
	public void joinAsMember(int clubNo, String email, String name, String password) {
		// 
		Club club = clubDao.readClub(clubNo);
		
		if(club == null) {
			throw NamooClubExceptionFactory.createRuntime("클럽이 존재하지 않습니다.");
		}
		// 	 email로 유저를 찾는게 맞는건가?
		if(userDao.readUser(email) != null){
			throw NamooClubExceptionFactory.createRuntime("해당 주민이 이미 존재합니다.");
		}
		
		SocialPerson user = new SocialPerson(email, name, password);
		userDao.createUser(user);
		memberDao.addClubMember(new ClubMember(clubNo, user));
	}

	@Override
	public void joinAsMember(int clubNo, String email) {
		// 
		Club club = clubDao.readClub(clubNo);
		if(club == null) {
			throw NamooClubExceptionFactory.createRuntime("클럽이 존재하지 않습니다.");
		}
		if (userDao.readUser(email) != null){
			throw NamooClubExceptionFactory.createRuntime("해당 주민이 이미 존재합니다.");
		}
		memberDao.addClubMember(new ClubMember(clubNo, socialPerson));
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
		
		return memberDao.readBelongClubs(email, comNo);
	}

	@Override
	public List<Club> findManagedClubs(String email, int comNo) {
		// 
		List<Club> clubs = clubDao.readAllClubs(comNo);
		if (clubs == null) return null;
		
		return memberDao.readManagedClubs(email, comNo);
	}

	@Override
	public void withdrawalClub(int clubNo, String email) {
		//
		Club club = clubDao.readClub(clubNo);
		if (club == null) {
			throw NamooClubExceptionFactory.createRuntime("클럽이 존재하지 않습니다.");
		}
		//TODO deleteManager
	}

	@Override
	public void commissionManagerCommunity(int clubNo, SocialPerson rolePerson) {
		//
		Club club = clubDao.readClub(clubNo);
		if (club == null) {
			throw NamooClubExceptionFactory.createRuntime("클럽이 존재하지 않습니다.");
		}
		memberDao.addClubManager(new ClubManager(clubNo, rolePerson));
	}
}
