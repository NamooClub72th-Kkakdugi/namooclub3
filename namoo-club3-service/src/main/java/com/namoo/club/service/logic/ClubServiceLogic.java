package com.namoo.club.service.logic;

import java.util.ArrayList;
import java.util.List;

import com.namoo.club.dao.ClubDao;
import com.namoo.club.dao.CommunityDao;
import com.namoo.club.dao.UserDao;
import com.namoo.club.dao.factory.DaoFactory;
import com.namoo.club.dao.factory.DaoFactory.DbType;
import com.namoo.club.service.facade.ClubService;
import com.namoo.club.service.logic.exception.NamooClubExceptionFactory;

import dom.entity.Club;
import dom.entity.ClubMember;
import dom.entity.Community;
import dom.entity.SocialPerson;

public class ClubServiceLogic implements ClubService {
	//
	private ClubDao clubDao;
	private CommunityDao comDao;
	
	public ClubServiceLogic() {
		DaoFactory daoFactory = DaoFactory.createFactory(DbType.MariaDB);
		this.clubDao = daoFactory.getClubDao();
		this.comDao = daoFactory.getCommunityDao();
	}
	//------------------------------------------------------------------------
	@Override
	public void registClub(String category, String communityName, String clubName, String description, String email) {
		// 
		if (isExistClubByName(communityNo, club.getClubName())) {
			throw NamooClubExceptionFactory.createRuntime("이미 존재하는 클럽입니다.");
		}
		clubDao.createClub(communityNo, club);
	}
	private boolean isExistClubByName(int communityNo, String clubName) {
		//
		Community community = comDao.readCommunity(communityNo);
		List<Club> clubs = community.getClubs();

		if (community != null && !clubs.isEmpty()) {
			for (Club club : clubs) {
				if (club.getClubName().equals(clubName)) {
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
	public void joinAsMember(String clubName, String name, String email, String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void joinAsMember(String clubName, String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Club> findAllClubs(String cmId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClubMember findClubMember(String clubName, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClubMember> findAllClubMember(String clubName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countMembers(String clubName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeClub(String clubId, String cmId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Club> findBelongclubs(String email, String cmId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Club> findManagedClubs(String email, String cmId) {
		// 
		List<Club> clubs = clubDao.readAllClubs(communityNo);
		if (clubs == null) return null;
		
		List<Club> manages = new ArrayList<>();
		for (Club club : clubs) {
			if(club.getManager().getId().equals(userId)) {
				manages.add(club);
			}
		}
		return manages;
	}

	@Override
	public void withdrawalClub(String clubName, String email) {
		//
		Club club = clubDao.readClub(clubNo);
		
		club.removeMember(userId);
		
	}

	@Override
	public void commissionManagerCommunity(String clId, SocialPerson rolePerson) {
		//
		Club club = clubDao.readClub(clubNo);
		club.setManager(user);
		
	}
//
//	
//	//-----------------------------------------------------------

//
//	@Override
//	public Club findClub(int clubNo) {
//		//
//		
//	}
//
//	@Override
//	public void joinAsMember(int clubNo, SocialPerson socialPerson) {
//		// 
//		Club club = clubDao.readClub(clubNo);
//		
//		if(club == null) {
//			throw NamooClubExceptionFactory.createRuntime("클럽이 존재하지 않습니다.");
//		}
//		
//		SocialPerson user = new SocialPerson(socialPerson.getUserId());
//		club.addMember(user);
//	}
//
//	@Override
//	public List<Club> findAllClubs(int comNo) {
//		//
//		return clubDao.readAllClubs(comNo);
//	}
//
//	@Override
//	public ClubMember findClubMember(int clubNo, String userId) {
//		//
//		Club club = clubDao.readClub(clubNo);
//		List<ClubMember> clubMembers = club.getMembers();
//		for(ClubMember clubMember : clubMembers) {
//			if(clubMember.getUser().getUserId().equals(userId)) {
//				return clubMember;
//			}
//		}
//		return null;
//	}
//
//	@Override
//	public List<ClubMember> findAllClubMember(int clubNo) {
//		// 
//		Club club = clubDao.readClub(clubNo);
//		return club.getMembers();
//	}
//
//	@Override
//	public int countMembers(int clubNo) {
//		// 
//		Club club = clubDao.readClub(clubNo);
//		return club.getMembers().size();
//	}
//
//	@Override
//	public void removeClub(int clubNo) {
//		// 
//		clubDao.deleteClub(clubNo);
//		
//		
//	}
//
//	@Override
//	public List<Club> findBelongclubs(String userId, int communityNo) {
//		// 
//		List<Club> clubs = clubDao.readAllClubs(communityNo);
//		if (clubs == null) return null;
//		
//		List<Club> belongs = new ArrayList<>();
//		for(Club club : clubs) {
//			if(club.findMember(userId) != null) {
//				belongs.add(club);
//			}
//		}
//		return belongs;
//	}
//
//	@Override
//	public List<Club> findManagedClubs(String userId, int communityNo) {
//		// 

//	}
//
//	@Override
//	public void withdrawalClub(int clubNo, String userId) {
//		// 

//	}
//
//	@Override
//	public void commissionManagerCommunity(int clubNo, SocialPerson user) {
//		//

//	}

}
